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

package com.snsoft.teamreading.controller.commonController;

import com.snsoft.teamreading.dao.UserDao;
import com.snsoft.teamreading.enums.Code;
import com.snsoft.teamreading.enums.Identity;
import com.snsoft.teamreading.pojo.Student;
import com.snsoft.teamreading.pojo.Teacher;
import com.snsoft.teamreading.pojo.User;
import com.snsoft.teamreading.utils.ModelAndViewUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller("CommonLoginAndOutController")
//该注解将类型为User的对象自动存入Session中
@SessionAttributes(types = {User.class})
@RequestMapping("/common")
public class LoginAndOutController {

    @Resource
    UserDao userDao;
    @Resource
    ModelAndView mv;

    /**
     * 登录处理
     *
     * @param id  用户账号
     * @param pwd 用户密码
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView Login(@RequestParam("id") String id, @RequestParam("pwd") String pwd) {
        Teacher teacher;
        Student student;
        try {
            teacher = userDao.getTeacherById(id);
            student = userDao.getStudentByIdNotRemoved(id);
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(this.mv, Code.ERROR);
        }

        User user = new User();
        if (teacher != null) {
            user.setId(teacher.getId());
            user.setPwd(teacher.getPwd());
            user.setIdentityMark(Identity.TEACHER.getIdentity());
        } else if (student != null) {
            user.setId(student.getId());
            user.setPwd(student.getPwd());
            user.setIdentityMark(Identity.STUDENT.getIdentity());
        } else {
            return ModelAndViewUtil.addObject(this.mv, Code.FAIL, "账号不存在");
        }

        if (!user.getPwd().equals(pwd)) {
            return ModelAndViewUtil.addObject(this.mv, Code.FAIL, "密码错误");
        }

        ModelAndView mv = ModelAndViewUtil.addObject(this.mv, Code.SUCCESS);
        mv.addObject("user", user);
        mv.addObject("identity", user.getIdentityMark());
        return mv;
    }

    /**
     * 登出账号，清空session并重定向到登录页面
     *
     * @param sessionStatus Spring会自动注入该对象
     */
    @RequestMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login.html";
    }
}
