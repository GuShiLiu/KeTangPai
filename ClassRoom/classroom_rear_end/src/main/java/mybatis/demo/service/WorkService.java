package mybatis.demo.service;

import mybatis.demo.dao.WorkMapper;
import mybatis.demo.entity.Work;
import mybatis.demo.entity.WorkSheetOfStudent;
import mybatis.demo.security02.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class WorkService {
    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private UserService userService;

    public void addWork(Work work){
//        String endDate = work.getEndDate().toString();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        Date date1 = df.parse(endDate);
//        work.setEndDate(date1);
        work.setReleaseDate(date);
        workMapper.addWork(work);
    }

    public void deleteWork(long wid){
        workMapper.deleteWork(wid);
    }

    public List<WorkSheetOfStudent> getAllWorksheeetofByWid(long wid){
        List<WorkSheetOfStudent> workSheetOfStudents = workMapper.getAllWorksheeetofByWid(wid);
        for (WorkSheetOfStudent workSheetOfStudent: workSheetOfStudents){
            workSheetOfStudent.setStudent(userService.getUserByUid(workSheetOfStudent.getUid()));
            workSheetOfStudent.setWork(workMapper.getWorkByWid(workSheetOfStudent.getWid()));
        }
        return workSheetOfStudents;
    }

    public Work getWorkByWid(long wid){
        return workMapper.getWorkByWid(wid);
    }

    public void addWorkOfStudent(WorkSheetOfStudent workSheetOfStudent){
        workSheetOfStudent.setUpTime(new Date());
        if (workMapper.getCountWidAndUid(workSheetOfStudent)>0){
            workMapper.updateWorkOfStudent(workSheetOfStudent);
        }else {
            workMapper.addWorkOfStudent(workSheetOfStudent);
        }
    }
    public WorkSheetOfStudent getWorkSheetOfStudentByWidAndUid(WorkSheetOfStudent workSheetOfStudent){
        return workMapper.getWorkSheetOfStudentByWidAndUid(workSheetOfStudent);
    }

    public void updateOfWork(WorkSheetOfStudent workSheetOfStudent){
        workMapper.updateOfWork(workSheetOfStudent);
    }
}
