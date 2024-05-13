package com.liuujun.mshop.common;

import com.github.pagehelper.Page;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.function.Function;

/**
 * @author zhouyi
 */
@Data
public class BasePage<T> {

    private int page;
    private int pageSize;
    private long total;
    private int pages;
    private List<T> records;

    public static <R> BasePage<R> of(int page, int pageSize, long total, List<R> records) {

        BasePage<R> basePage = new BasePage<>();
        basePage.setPage(page);
        basePage.setPageSize(pageSize);
        basePage.setTotal(total);
        basePage.setRecords(records);
        return basePage;
    }

    public static <R> BasePage<R> of(Page<R> resultPage) {

        BasePage<R> basePage = new BasePage<>();
        basePage.setPage(resultPage.getPageNum());
        basePage.setPageSize(resultPage.getPageSize());
        basePage.setTotal(resultPage.getTotal());
        basePage.setPages(resultPage.getPages());
        basePage.setRecords(resultPage.getResult());
        return basePage;
    }
    public static <R,T> BasePage<T> of(Page<R> resultPage, Function<R,T> converter) {

        BasePage<T> basePage = new BasePage<>();
        basePage.setPage(resultPage.getPageNum());
        basePage.setPageSize(resultPage.getPageSize());
        basePage.setTotal(resultPage.getTotal());
        basePage.setPages(resultPage.getPages());

        List<R> result = resultPage.getResult();
        if (CollectionUtils.isEmpty(result)) {
            basePage.setRecords(Lists.newArrayList());
        }else {
            basePage.setRecords(result.stream().map(converter).toList());
        }
        return basePage;
    }
}
