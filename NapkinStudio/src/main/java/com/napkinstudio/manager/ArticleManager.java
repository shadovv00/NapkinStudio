package com.napkinstudio.manager;

import com.napkinstudio.dao.IArticleDao;
import com.napkinstudio.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ArticleManager {

@Autowired
private IArticleDao articleDao;

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }

}
