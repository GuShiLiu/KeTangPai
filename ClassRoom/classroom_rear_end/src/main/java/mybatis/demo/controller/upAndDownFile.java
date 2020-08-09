package mybatis.demo.controller;


import mybatis.demo.entity.WorkSheetOfStudent;
import mybatis.demo.service.WorkService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mybatis.demo.config.AppConfig;

@RestController
@RequestMapping("/upAndDownFile")
public class upAndDownFile {
    @Autowired
    private AppConfig config;
    @Autowired
    private WorkService workService;

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(HttpServletRequest request1, MultipartHttpServletRequest request,int uid,int wid) throws IOException {
        //服务器中的文件地址
        String ctxPath = request1.getSession().getServletContext()
                .getRealPath("/");
        int uid1 = uid;
        int wid1 = wid;
        System.out.println("uid="+uid1);
        System.out.println("wid="+wid1);
        System.out.println(ctxPath);
        List<String> result = new ArrayList<>();
        List<MultipartFile> files = request.getFiles("file");
        if (files.size() > 0) {
            for (MultipartFile multipartFile : files) {
                //文件名字
                System.out.println("multipartFile is"+multipartFile.getOriginalFilename());
                result.add(handleFileUpload(multipartFile,request,uid1,wid1));
            }
        }
        System.out.println("result is "+result);
        String tempFileURL = result.get(0);
        System.out.println("temFileURL is "+ tempFileURL);
        return result.get(0);
    }

    /**
     * @ 单一文件上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,int uid,int wid) {
        if (!file.isEmpty()) {
            //文件的名字
            System.out.println("file name is "+file.getOriginalFilename());
            String saveFileName = getFileName(file);
            System.out.println(saveFileName);
            File saveFile = new File(config.getUploadFolder() + saveFileName);
            //文件的URL
            System.out.println("文件在本地的地址为:"+saveFile.getAbsolutePath());
            WorkSheetOfStudent workSheetOfStudent = new WorkSheetOfStudent();
            workSheetOfStudent.setFileName(file.getOriginalFilename());
            workSheetOfStudent.setUid(uid);
            workSheetOfStudent.setWid(wid);
            workSheetOfStudent.setFileURL(saveFileName);
            workService.addWorkOfStudent(workSheetOfStudent);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return config.getPreviewPath() + saveFile.getName();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败,";
            }
        } else {
            return "上传失败，因为文件为空.";
        }
    }
    private static final Logger logger = LoggerFactory.getLogger(upAndDownFile.class);

    private String getFileName(MultipartFile file) {
        String name = file.getOriginalFilename();
        StringBuilder sb = new StringBuilder();
        Date date = new Date();
        sb.append(date.getTime());
        sb.append(name.substring(name.indexOf(".")));
        return sb.toString();
    }

    @PostMapping("/download")
    public Object downloadFile(@RequestParam String fileName, final HttpServletResponse response, final HttpServletRequest request){
        String rootPath = config.getUploadFolder();
        OutputStream os = null;
        InputStream is= null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(rootPath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
                logger.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println(e);
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }
}
