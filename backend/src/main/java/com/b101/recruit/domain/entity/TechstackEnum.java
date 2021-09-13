package com.b101.recruit.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TechstackEnum {
	java, spring, vue, react; // 추가필요
	
	@JsonCreator
    public static TechstackEnum from(String s) {
        return TechstackEnum.valueOf(s);
    }
}