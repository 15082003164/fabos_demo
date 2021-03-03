package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.CommentMapper;
import com.cxxy.bysj.entity.Comment;
import com.cxxy.bysj.entity.CommentExample;
import com.cxxy.bysj.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Override
    public void insertSelective(Comment comment){
        commentMapper.insertSelective(comment);
    }

    @Override
    public List<Comment> selectByExample(CommentExample commentExample) {
        return commentMapper.selectByExample(commentExample);
    }

}
