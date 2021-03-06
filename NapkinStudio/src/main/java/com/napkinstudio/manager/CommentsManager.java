package com.napkinstudio.manager;

import com.napkinstudio.dao.ICommentsDao;
import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class CommentsManager {

@Autowired
private ICommentsDao commentsDao;
@Autowired
private OrderManager orderManager;

//    @Transactional
//    public List<Comments> findByRoleId(Integer id) {
//        return statusDao.findByRoleId(id);
//    }


    public Map <Integer,List<Comments>> findCommentsbyOrderId(Integer id){
        List<Comments> commentsList =commentsDao.findCommentsbyOrderId(id);
        Map <Integer,List<Comments>> groupedComments = new HashMap<>();

        for(Comments comment: commentsList){
            Integer key = comment.getForRole().getId();
            if(groupedComments.containsKey(key)){
                List<Comments> list =  groupedComments.get(key);

                list.add(comment);
            }else{
                List<Comments> list = new ArrayList<Comments>();
                list.add(comment);
                groupedComments.put(key, list);
            }
        }

      return   groupedComments ;
    }
    @Transactional
    public void save(Comments comment) {
        commentsDao.save(comment);
        Order theOrder = comment.getOrder();
        System.out.println(theOrder.getOrderId());
        theOrder.setLastModifiedDate(new Date());
        orderManager.save(theOrder);

    }
}
