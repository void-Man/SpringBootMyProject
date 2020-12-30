package com.cmj.example.fund;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmj.example.base.IndustrBase;
import com.cmj.example.mapper.IndustrBaseMapper;
import com.cmj.example.utils.CollectionUtils;
import com.cmj.example.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/12/30
 */
@Service
public class IndustrServiceImpl implements IndustrService {

    @Autowired
    private IndustrBaseMapper industrBaseMapper;

    @Override
    public void addIndustr(String path) throws IOException {
        String content = FileUtils.readFile(path);
        List<IndustrBase> industrBaseList = this.convertVo(content);
        CollectionUtils.subList(industrBaseList, 200).forEach(industrBases -> industrBaseMapper.batchInsertSelective(industrBases, IndustrBase.Column.name));
    }

    /**
     * 信息转换
     *
     * @param content
     * @return java.util.List<com.cmj.example.base.IndustrBase>
     * @author mengjie_chen
     * @date 2020/12/31
     */
    private List<IndustrBase> convertVo(String content) {
        JSONArray diffList = JSONObject.parseObject(content).getJSONObject("data").getJSONArray("diff");
        List<IndustrBase> industrBaseList = new ArrayList<>(10);
        for (int i = 0; i < diffList.size(); i++) {
            JSONObject diffVo = diffList.getJSONObject(i);
            String name = diffVo.getString("f100");
            if (industrBaseList.stream().anyMatch(industrBase -> industrBase.getName().equals(name))) {
                continue;
            }
            IndustrBase industrBase = IndustrBase.builder()
                    .name(name)
                    .build();
            industrBaseList.add(industrBase);
        }
        return industrBaseList;
    }
}
