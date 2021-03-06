/*
 * @copyright ：神农大学生软件创新中心 版权所有 © 2019
 *
 * @author 16级信息与计算科学潘鹏程
 *
 * @version
 *
 * @date 2019.08.22
 *
 * @Description
 */

package com.snsoft.teamreading.dao;

import com.snsoft.teamreading.pojo.Team;
import com.snsoft.teamreading.pojo.TeamStu;
import com.snsoft.teamreading.returnPojo.StudentInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamDao {

    //根据id查询团队
    @Select("select id, name, teacher_id, assistant_id from team where id = #{id} and is_remove = '0'")
    Team getTeamById(String id);

    //更新团队的导师助手
    @Update("update team set assistant_id = #{assistantId} where id = #{id}")
    int updateAssistant(Team team);

    //创建团队
    @Insert("insert into team (id, name, teacher_id) values(#{id}, #{name}, #{teacherId})")
    int addTeam(Team team);

    //删除团队前先删除团队-学生-导师表中属于该团队的记录
    @Delete("delete from team_stu where team_id = #{teamId}")
    void deleteTeamStuByTeamId(String teamId);

    //删除团队
    @Update("update team set is_remove = '1' where id = #{id}")
    int deleteTeam(String id);

    //查询学生是否在团队中
    @Select("select team_id, student_id from team_stu " +
            "where team_id = #{teamId} " +
            "and student_id = #{studentId} " +
            "and is_remove = '0'")
    TeamStu getTeamStuByTeamIdAndStudentId(@Param("teamId") String teamId,
                                           @Param("studentId") String studentId);

    //查询学生所在的所有团队
    @Select("select team_id, student_id from team_stu" +
            "where student_id = #{studentId} " +
            "and is_remove = '0'")
    List<TeamStu> getTeamStuByStudentId(String studentId);

    //将学生添加到团队中
    @Insert("insert into team_stu (team_id, student_id) values(#{teamId}, #{studentId})")
    int addSToTeam(@Param("teamId") String teamId,
                   @Param("studentId") String studentId);

    //查询学生是否被团队中移除
    @Select("select team_id, student_id from team_stu " +
            "where student_id = #{studentId} and " +
            "team_id = #{teamId} and is_remove = '1'")
    TeamStu getRemovedTeamStu(@Param("teamId") String teamId,
                              @Param("studentId") String studentId);

    // 更新team_stu表的移除标记
    @Update("update team_stu set is_remove = #{isRemove} " +
            "where team_id = #{teamId} " +
            "and student_id = #{studentId}")
    int updateTeamStu(@Param("teamId") String teamId,
                      @Param("studentId") String studentId,
                      @Param("isRemove") char isRemove);

    // 添加团队时在score_standard表中添加一条该团队的积分标准记录
    @Insert("insert into score_standard (team_id) values (#{teamId})")
    int addScoreStandard(String teamId);

    // 查询学生所在的所有团队信息
    @Select("select id, name, teacher_id, assistant_id from team where id in " +
            "(" +
            "select team_id from team_stu " +
            "where student_id = #{studentId}" +
            ") " +
            "and is_remove = '0'")
    List<Team> getTeamsByStudentId(String studentId);

    // 查询导师创建的所有团队信息
    @Select("select id, name, teacher_id, assistant_id from team " +
            "where teacher_id = #{teacherId} " +
            "and is_remove = '0'")
    List<Team> getTeamsByTeacherId(String teacherId);

    // 根据团队id查询所有的学生
    @Select("select id, name, score from student " +
            "where id in " +
            "(" +
            "select student_id from team_stu " +
            "where team_id = #{teamId}" +
            ") " +
            "and is_remove = '0'")
    List<StudentInfo> getStudentsByTeamId(String id);

    // 根据已接受任务id获取所在团队
    Team getTeamByReceivedTaskId(String answerId);
}
