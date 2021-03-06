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

import com.snsoft.teamreading.dao.PendingAnswerDao;
import com.snsoft.teamreading.enums.Code;
import com.snsoft.teamreading.enums.Identity;
import com.snsoft.teamreading.pojo.User;
import com.snsoft.teamreading.utils.ModelAndViewUtil;
import com.snsoft.teamreading.utils.RowBoundsUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller("CommonPendingAnswerController")
@RequestMapping("/common")
public class PendingAnswerController {

    @Resource
    PendingAnswerDao pendingAnswerDao;
    @Resource
    ModelAndView mv;

    /**
     * 如果当前用户是学生则返回其提交的所有尚未审核的解读，如果是导师则返回其创建的所有团队尚未审核的解读
     *
     * @param user session中用户信息
     * @param page 分页参数
     * @return ModelAndView视图
     */
    @RequestMapping(value = "/getPendingAnswer", method = RequestMethod.GET)
    public ModelAndView getPendingAnswer(@SessionAttribute("user") User user,
                                         @RequestParam(value = "page", required = false) Integer page) {
        RowBounds rowBounds = RowBoundsUtil.getRowBounds(page);
        try {
            if (user.getIdentityMark() == Identity.STUDENT.getIdentity())
                return ModelAndViewUtil.addObject(mv, "data",
                        pendingAnswerDao.getStudentPendingAnswerInfo(user.getId(), rowBounds));
            else
                return ModelAndViewUtil.addObject(mv, "data",
                        pendingAnswerDao.getTeacherPendingAnswerInfo(user.getId(), rowBounds));
        } catch (RuntimeException e) {
            return ModelAndViewUtil.addObject(mv, Code.ERROR);
        }
    }
}
