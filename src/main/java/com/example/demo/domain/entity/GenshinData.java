package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenshinData {
    private Integer page;
    private Integer size;
    private Integer total ;
    private List<GenshinItem> list ;
    private String region;
}
