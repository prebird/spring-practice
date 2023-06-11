package com.example.springpractice.jpaShop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UpdateIdListRequest {
    List<Long> idList;
    String className;
}
