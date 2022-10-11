package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.domain.entity.GenshinItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenshinMapper extends BaseMapper<GenshinItem> {

}
