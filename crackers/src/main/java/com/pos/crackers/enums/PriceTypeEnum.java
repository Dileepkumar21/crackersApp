package com.pos.crackers.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PriceTypeEnum {

    SINGLE("SINGLE"),
    PACK("PACK");

    private final String code;

    public String getCode(){
        return this.code;
    }
    private static final Map<String, PriceTypeEnum> lookup = new HashMap<>();

    static {
        for ( PriceTypeEnum i : EnumSet.allOf(PriceTypeEnum.class)) lookup.put(i.getCode(), i);
    }
    PriceTypeEnum(String code){
        this.code = code;
    }


    public static PriceTypeEnum get(String code){
        return lookup.get(code);
    }

}
