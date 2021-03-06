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

package com.snsoft.teamreading.controller.studentController;

import com.snsoft.teamreading.enums.Code;
import com.snsoft.teamreading.pojo.User;
import com.snsoft.teamreading.service.TaskService;
import com.snsoft.teamreading.utils.ModelAndViewUtil;
import com.snsoft.teamreading.utils.RowBoundsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller("StudentTaskController")
@RequestMapping("student")
public class TaskController {
    @Resource
    TaskService taskService;
    @Resource
    ModelAndView mv;

    /**
     * 接受任务
     *
     * @param user session中用户信息
     * @param id   任务id
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/receiveTask", method = RequestMethod.POST)
    public ModelAndView receiveTask(@SessionAttribute("user") User user,
                                    @RequestParam("id") String id) {
        try {
            return taskService.receiveTask(user.getId(), id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }

    /**
     * 获取所有已接受可提交解读的任务
     *
     * @param user session中用户信息
     * @param page 分页参数
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/getAcceptedTask", method = RequestMethod.GET)
    public ModelAndView getAcceptedTask(@SessionAttribute("user") User user,
                                        @RequestParam(value = "page", required = false) Integer page) {
        try {
            return taskService.getAcceptedTasksNotFinal(user.getId(), RowBoundsUtil.getRowBounds(page));
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }

    /**
     * 删除已接受任务
     *
     * @param user session中用户信息
     * @param id   已接受任务id
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/deleteAcceptedTask", method = RequestMethod.POST)
    public ModelAndView deleteAcceptedTask(@SessionAttribute("user") User user,
                                           @RequestParam("id") String id) {
        try {
            return taskService.deleteAcceptedTask(user.getId(), id);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }
}
