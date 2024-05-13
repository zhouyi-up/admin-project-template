package com.liuujun.mshop.common;

import lombok.Data;

@Data
public class PageDto {

    private int page = 1;
    private int pageSize = 10;
}
