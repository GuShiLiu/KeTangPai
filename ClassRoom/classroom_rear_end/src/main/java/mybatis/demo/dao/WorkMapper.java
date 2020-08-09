package mybatis.demo.dao;

import mybatis.demo.entity.Work;
import mybatis.demo.entity.WorkSheetOfStudent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkMapper {

    void addWork(Work work);

    void deleteWork(long wid);

    List<WorkSheetOfStudent> getAllWorksheeetofByWid(long wid);

    Work getWorkByWid(long wid);

    void addWorkOfStudent(WorkSheetOfStudent workSheetOfStudent);

    int getCountWidAndUid(WorkSheetOfStudent workSheetOfStudent);

    void updateWorkOfStudent(WorkSheetOfStudent workSheetOfStudent);

    WorkSheetOfStudent getWorkSheetOfStudentByWidAndUid(WorkSheetOfStudent workSheetOfStudent);

    void updateOfWork(WorkSheetOfStudent workSheetOfStudent);

}
