package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.Comment;
import com.cxxy.bysj.entity.CommentExample;

import java.util.List;

public interface CommentService {
    public void insertSelective(Comment comment);

    public List<Comment> selectByExample(CommentExample commentExample);
}
