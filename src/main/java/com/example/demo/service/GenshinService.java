package com.example.demo.service;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.domain.entity.*;
import com.example.demo.mapper.GenshinMapper;
import com.example.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;

@Service
public class GenshinService extends ServiceImpl<GenshinMapper, GenshinItem> implements IGenshinService {

    @Autowired
    private GenshinMapper genshinMapper;

    public List<GenshinItem> getItemLists(String url, String gachaType)  {
        String baseurl = ""+url;
        List<GenshinItem> items = new ArrayList<>();

        int page = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(baseurl);
        sb.append("gacha_type="+gachaType+"&");
        sb.append("page="+page +"&");
        sb.append("size=10&");
        String baseurlplus = sb.toString();
        String end_id = "0";
        while (true)
        {
            StringBuilder str = new StringBuilder();
            str.append(baseurlplus);
            str.append("end_id=" + end_id);
            List<GenshinItem> itemEntityList = getGenshinItem(str.toString());

            this.saveOrUpdateBatch(itemEntityList);


            itemEntityList.forEach(item->{
                System.out.println("类型："+item.getItemType()+",星级："+item.getRankType()+",名称：【"+item.getName()+"】，时间："+item.getTime()+";");
              ///genshinMapper.insert(item);
            });
            System.out.println("[" + getGachaTypeName(gachaType) + "]-第【"+page+"】页");
            // 如果查询到的数据为空表示查完了
            if (itemEntityList.size() == 0)
            {
                System.out.println("[" + getGachaTypeName(gachaType) + "]-查询完毕");
                break;
            }

            // 将数据装到map里
            for (int j = 0; j < itemEntityList.size(); j++)
            {
                items.addAll(itemEntityList);
            }
            // 得到最后一个元素的end_id
            end_id = itemEntityList.get(itemEntityList.size() - 1).getId();
            page++;
            try {
                // 每个查询需要间隔一段时间
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return items;
    }

    private List<GenshinItem> getGenshinItem(String url)  {
        try {
            Map<String,String> headers = new HashMap<>();
            headers.put("Content-Type","text/html;charset=UTF-8");
            headers.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
            headers.put("Content-Type","application/x-javascript;charset=UTF-8");
            headers.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");

            String jsonstr = HttpUtil.get(url,headers);
            GenshinResult genshin = JSONUtil.toBean(jsonstr, GenshinResult.class);
            GenshinData genshinData = genshin.getData();
           return genshinData.getList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public String getGachaTypeName(String gachaType){
        if (gachaType.equals("100"))
        {
            return "新手祈愿";
        }
        else if (gachaType.equals("200"))
        {
            return "常驻祈愿";
        }
        else if (gachaType.equals("301"))
        {
            return "活动祈愿1&2";
        }
        else if (gachaType.equals("302"))
        {
            return "武器祈愿";
        }
        else
        {
            return "新手祈愿";
        }
    }
}
