package com.napkinstudio.manager;

import com.napkinstudio.dao.ICommentsDao;
import com.napkinstudio.entity.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentsManager {

@Autowired
private ICommentsDao commentsDao;

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
