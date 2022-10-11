package com.example.demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@TableName("GenshinItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenshinItem {
    @TableId
    private String id ;
    private String uid ;
    @JsonProperty("item_id")
    private String itemId ;
    @JsonProperty("item_type")
    private String itemType ;
    private Integer count ;
    private String name ;
    @JsonProperty("gacha_type")
    private String gachaType ;
    private Date time ;

    private String lang ;
    @JsonProperty("rank_type")
    private Integer rankType ;

}
