package com.cmj.example;

import com.alibaba.fastjson.JSONArray;
import com.cmj.example.base.UserBase;
import com.cmj.example.mapper.UserBaseMapper;
import com.cmj.example.utils.common.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mengjie_chen
 * @description date 2020/11/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationRunner.class)
public class ApplicationTester {

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Test
    public void readText() {
        String txtPath = "E:\\GitWorkSpace\\Chinese-Names-Corpus-master\\Chinese_Names_Corpus\\Chinese_Names_Corpus_Gender（120W）.txt";
        File file = new File(txtPath);
        List<UserBase> userBaseList = new ArrayList<>(10);
        if (file.isFile() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String text = null;
                int i = 0;
                while ((text = bufferedReader.readLine()) != null) {
                    String[] strings = text.split(",");
                    int sex = -1;
                    if ("男".equals(strings[1])) {
                        sex = 1;
                    } else if ("女".equals(strings[1])) {
                        sex = 1;
                    } else {
                        sex = 2;
                    }
                    UserBase userBase = UserBase.builder()
                            .name(strings[0])
                            .password(CommonUtils.randomUUID())
                            .sex(sex)
                            .age((int) (Math.random() * 90) + 10)
                            .outerId(CommonUtils.randomUUID())
                            .build();
                    userBaseList.add(userBase);
                    if (i % 1000 == 0) {
                        userBaseMapper.batchInsertSelective(userBaseList, UserBase.Column.name, UserBase.Column.password, UserBase.Column.sex, UserBase.Column.age, UserBase.Column.outerId);
                        userBaseList.clear();
                    }
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
