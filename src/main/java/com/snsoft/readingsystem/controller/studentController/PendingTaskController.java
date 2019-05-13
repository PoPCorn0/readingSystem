/*
 * @copyright ：神农大学生软件创新中心 版权所有 © 2019
 *
 * @author 16级信息与计算科学潘鹏程
 *
 * @version
 *
 * @date 2019.05.12
 *
 * @Description
 */

package com.snsoft.readingsystem.controller.studentController;

import com.snsoft.readingsystem.dao.PendingTaskDao;
import com.snsoft.readingsystem.pojo.PendingTask;
import com.snsoft.readingsystem.service.PendingTaskService;
import com.snsoft.readingsystem.utils.AllConstant;
import com.snsoft.readingsystem.utils.ModelAndViewUtil;
import com.snsoft.readingsystem.utils.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
public class PendingTaskController {

    @Resource
    PendingTaskService pendingTaskService;
    @Resource
    PendingTaskDao pendingTaskDao;

    @RequestMapping(value = "/student/commitTask", method = RequestMethod.POST)
    public ModelAndView commitTask(@SessionAttribute("user") User user,
                                   @RequestParam("title") String title,
                                   @RequestParam("content") String content,
                                   @RequestParam("teamId") String teamId) {
        PendingTask pendingTask = new PendingTask();
        pendingTask.setId(UUID.randomUUID().toString());
        pendingTask.setAuthorId(user.getId());
        pendingTask.setTeamId(teamId);
        pendingTask.setTitle(title);
        pendingTask.setContent(content);

        try {
            return pendingTaskService.commitTask(pendingTask);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.getModelAndView(AllConstant.CODE_ERROR);
        }
    }

    @RequestMapping(value = "/student/deletePendingTask", method = RequestMethod.POST)
    public ModelAndView deletePendingTask(@SessionAttribute("user") User user,
                                          @RequestParam("id") String id) {
        try {
            return pendingTaskService.deletePendingTask(user.getId(), id);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.getModelAndView(AllConstant.CODE_ERROR);
        }
    }

}