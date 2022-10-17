package com.nowcoder.community.entity;

import lombok.Data;

/**
 * 封装分页相关的信息
 */
public class Page {

    // 当前页码
    private int current = 1;
    // 显示上限
    private int limit = 10;
    // total number of rows, which is used to calculate the # of pages
    private int rows;
    // searching locations
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >= 1) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     */
    public int getOffset(){
        return (current - 1) * limit;
    }

    /**
     * 获取总页数
     */
    public int getPages(){
        if(rows % limit == 0){
            return rows / limit;
        }else{
            return rows / limit + 1;
        }
    }

    /**
     * 获取起始页码
     * 显示当前页的前后两页
     */
    public int getFrom(){
        int from = current - 2;
        return Math.max(1, from);
    }

    /**
     * 获取结束页码
     */
    public int getEnd(){
        int end = current + 2;
        return Math.min(end, this.getPages());
    }

}
