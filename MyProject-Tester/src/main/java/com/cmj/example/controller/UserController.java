package com.cmj.example.controller;

import com.cmj.example.base.UserBase;
import com.cmj.example.base.UserBaseExample;
import com.cmj.example.base.vo.UserCreateTimeVo;
import com.cmj.example.mapper.UserBaseMapper;
import com.cmj.example.mapper.UserMapper;
import com.cmj.example.utils.DateTimeUtils;
import com.cmj.example.utils.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/2/25
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserBaseMapper userBaseMapper;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/editUserCreateTime")
    public String editUserCreateTime() {
        int i = 1;
        int start;
        while (true) {
            start = (i - 1) * 400;
            if (i * 400 >= 1211002) {
                break;
            }
            List<UserBase> userBaseList = userBaseMapper.selectByExample(new UserBaseExample().createCriteria().andIsDeleteEqualTo(0).example().limit(start, 400));
            ArrayList<UserCreateTimeVo> updateList = Lists.newArrayList();
            String date = "";
            for (UserBase userBase : userBaseList) {
                if (StringUtils.isEmpty(date)) {
                    date = DateTimeUtils.addDays(DateTimeUtils.format(userBase.getCreateTime(), DateTimeUtils.DATETIME_FORMAT), DateTimeUtils.DATETIME_FORMAT, i);
                }
//                UserCreateTimeVo userCreateTimeVo = new UserCreateTimeVo();
//                userCreateTimeVo.setUserId(userBase.getId());
//                userCreateTimeVo.setCreateTime();
//                updateList.add(userCreateTimeVo);
//                userBase.setCreateTime(DateTimeUtils.parse(DateTimeUtils.addDays(DateTimeUtils.format(userBase.getCreateTime(), DateTimeUtils.DATETIME_FORMAT), DateTimeUtils.DATETIME_FORMAT, i),DateTimeUtils.DATETIME_FORMAT));
//                userBaseMapper.updateByPrimaryKeySelective(userBase, UserBase.Column.createTime);
            }
            userMapper.updateCreateTimeById(date, userBaseList.stream().map(UserBase::getId).collect(Collectors.toList()));
            i++;
        }

        userBaseMapper.selectByExample(new UserBaseExample().createCriteria().andIsDeleteEqualTo(0).example());
        return "success";
    }

}
