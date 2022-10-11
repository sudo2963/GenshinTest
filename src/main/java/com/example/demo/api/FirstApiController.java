package com.example.demo.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.domain.entity.GenshinItem;
import com.example.demo.response.ResponseError;
import com.example.demo.response.ResponseResult;
import com.example.demo.response.ResponseSuccess;
import com.example.demo.service.GenshinService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/first")
public class FirstApiController {
    @Autowired
    private GenshinService genshinService;

    @RequestMapping("/getCkData")
    public ResponseResult getCkData(String url,String gachaType)
    {

        //"https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?win_mode=fullscreen&authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=e26ce9efdc5b0b3193edf2aa4736c04983a37a4d&timestamp=1664323645&lang=zh-cn&device_type=mobile&game_version=CNRELiOS3.1.0_R10676272_S10805493_D10772333&plat_type=ios&region=cn_gf01&authkey=aZUFFIuz6aKgtB54sAjwkAU0SWPW60pD6gfhZY3IZ4GHthT3iJ5iun1Fmdhh2lAEG9PEwu8K8Y67dDFyktwzbdKcAxTSU6pmfEDf7An7maLJHDn/l/MK/b8mZW8xkVixvATcK5Tf6zUd3BwRuN/g/J+ilQjKTsh8FbOrePrv82DG/EGUk78qtbpKpott0j6olLj0p58kzh7cdjJom2mqFUQXc2pTErA6+xqpzJoiY6ZErjh4YJG6VjMcTswoxePhufMyJqjTBKTr9YyDTs+DMImSO5U+c3bRW5BO6bTB5hbWWBAOasvTEon7bp+srZtLUtelpScqM5afTHiUdrdWHniQK/BmPrO0DwO3BMAKVPSHGeQNg1a78ZoBFD+mGF/O50mHwqeN3B+TF/h6STnSRGXP1FN6vqUix3iEss5ksBoUk+PyQcJkwjTCFU9UbeFCoQkAwSKGu8Cnw9meIt8VG+S6D04QOcLIqCp5269ATKUtsvZUEbNM6Gp5BvSMoA/ll0AqrZ9FTMcK01Xnejj+uXeKDvhhWm3O2syK6A+k9yZCSbWCfJcZTqekDC/FwQIfzOCj7CdZXLp9uUIXhX2cZHDzYFqWqhFRAplWbsH0dueK4yKy5G18hZqrCNQ7jfgpGVlZcJhr33U5vYHznUh1MAQ6hiKxX1c+W9d5yNl8qKG5Ek6efAMF8USOX1cwnE300RmqXgdw5Wpv52LZ/QotnFxm+ymjesIPPbEFPw5UmiYyismKHd0T4bzL+9vw5GkaxrpKCfbB+vs48PVO0TUOHgvEG7JhrKnNKmfWDGJe9SKvTbdNM4hfJ7SV7NbB1KI1ZAc6Rr20meh9XT/uqJUCsnQa3Zy162a/BkdnY1gywKP4b32ogsatU1hVTA61ryjtITr3ACJ5El+FyarFlaNdb1VItqB9JewNGHHnY10SxEPVOEbIKGM7/KzDTU6QKgj3MtiTqWVZiU0Law8WEkUlQ2rDd+xJPNS857LmxOT07Scd4P/D54cFJOZ6/Zc0nuC1&game_biz=hk4e_cn&gacha_type=200&page=1&size=5&end_id=0"
        //"https://hk4e-api.mihoyo.com/event/gacha_info/api/getGachaLog?win_mode=fullscreen&authkey_ver=1&sign_type=2&auth_appid=webview_gacha&init_type=301&gacha_id=e26ce9efdc5b0b3193edf2aa4736c04983a37a4d&timestamp=1664323645&lang=zh-cn&device_type=mobile&game_version=CNRELiOS3.1.0_R10676272_S10805493_D10772333&plat_type=ios&region=cn_gf01&authkey=aZUFFIuz6aKgtB54sAjwkAU0SWPW60pD6gfhZY3IZ4GHthT3iJ5iun1Fmdhh2lAEG9PEwu8K8Y67dDFyktwzbdKcAxTSU6pmfEDf7An7maLJHDn%2fl%2fMK%2fb8mZW8xkVixvATcK5Tf6zUd3BwRuN%2fg%2fJ%2bilQjKTsh8FbOrePrv82DG%2fEGUk78qtbpKpott0j6olLj0p58kzh7cdjJom2mqFUQXc2pTErA6%2bxqpzJoiY6ZErjh4YJG6VjMcTswoxePhufMyJqjTBKTr9YyDTs%2bDMImSO5U%2bc3bRW5BO6bTB5hbWWBAOasvTEon7bp%2bsrZtLUtelpScqM5afTHiUdrdWHniQK%2fBmPrO0DwO3BMAKVPSHGeQNg1a78ZoBFD%2bmGF%2fO50mHwqeN3B%2bTF%2fh6STnSRGXP1FN6vqUix3iEss5ksBoUk%2bPyQcJkwjTCFU9UbeFCoQkAwSKGu8Cnw9meIt8VG%2bS6D04QOcLIqCp5269ATKUtsvZUEbNM6Gp5BvSMoA%2fll0AqrZ9FTMcK01Xnejj%2buXeKDvhhWm3O2syK6A%2bk9yZCSbWCfJcZTqekDC%2fFwQIfzOCj7CdZXLp9uUIXhX2cZHDzYFqWqhFRAplWbsH0dueK4yKy5G18hZqrCNQ7jfgpGVlZcJhr33U5vYHznUh1MAQ6hiKxX1c%2bW9d5yNl8qKG5Ek6efAMF8USOX1cwnE300RmqXgdw5Wpv52LZ%2fQotnFxm%2bymjesIPPbEFPw5UmiYyismKHd0T4bzL%2b9vw5GkaxrpKCfbB%2bvs48PVO0TUOHgvEG7JhrKnNKmfWDGJe9SKvTbdNM4hfJ7SV7NbB1KI1ZAc6Rr20meh9XT%2fuqJUCsnQa3Zy162a%2fBkdnY1gywKP4b32ogsatU1hVTA61ryjtITr3ACJ5El%2bFyarFlaNdb1VItqB9JewNGHHnY10SxEPVOEbIKGM7%2fKzDTU6QKgj3MtiTqWVZiU0Law8WEkUlQ2rDd%2bxJPNS857LmxOT07Scd4P%2fD54cFJOZ6%2fZc0nuC1&game_biz=hk4e_cn&gacha_type=200&page=1&size=5&end_id=0"

        List<String> gachaTypeList = new ArrayList<>();
        if(StringUtils.isBlank(gachaType)){
            gachaTypeList.add("100");
            gachaTypeList.add("200");
            gachaTypeList.add("301");
            gachaTypeList.add("302");
        }else{
            gachaTypeList.add(gachaType);
        }
        List<String> urlList = StrUtil.split(url,"?");
        String baseUrl =urlList.get(0);
        String baseParam =urlList.get(1).replace("/","%2f").replace("+","%2b");
        HashMap<String, String> mapParam = new HashMap<String, String>();
        List<String> paramList =StrUtil.split(baseParam,"&");
        String [] excludeName = new String[]{"gacha_type","page","size","end_id"};
        paramList.forEach(f->{
            List<String> param = StrUtil.split(f,"=");
            String key =param.get(0);
            String value =param.get(1);
            if(!Arrays.stream(excludeName).filter(e -> e.equals(key)).findAny().isPresent() ){
                mapParam.put(key,value);
            }
        });
        StringBuilder sb = new StringBuilder();
        mapParam.forEach((key, value) -> sb.append(key+"="+value+"&"));

        Map<String, List<GenshinItem>> genshinItemLists = new HashMap<>();
        gachaTypeList.forEach(gacha -> {
            List<GenshinItem> list = genshinService.getItemLists(baseUrl+"?"+sb.toString(),gacha);
        });

        return new ResponseSuccess(url);
    }
}
