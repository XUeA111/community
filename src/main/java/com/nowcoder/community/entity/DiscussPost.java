package com.nowcoder.community.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DiscussPost {
    int id;
    int userId;
    String title;
    String text;
    int type;
    int status;
    Date createTime;
    int commentCount;
    double score;
}
