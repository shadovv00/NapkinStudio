package com.napkinstudio.manager;

import com.napkinstudio.dao.ICommentsDao;
import com.napkinstudio.entity.Comments;
import com.napkinstudio.entity.Order;
import com.napkinstudio.entity.UserOrder;
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


    public Map <Integer,List<Comments>> findCommentsbyOrderId(Integer id, UserOrder userOrder){
        List<Comments> commentsList =commentsDao.findCommentsbyOrderId(id);

        checkUnread(commentsList,userOrder);

        return   groupList(commentsList) ;
    }

    @Transactional
    public void save(Comments comment) {
        commentsDao.save(comment);
        Order theOrder = comment.getOrder();
        System.out.println(theOrder.getOrderId());
        theOrder.setLastModifiedDate(new Date());

        orderManager.save(theOrder);

    }

    public Integer countAllUnreadComments(Integer userId, Integer orderId) {
        return commentsDao.countAllUnreadComments(userId, orderId);
    }
    public Integer countUnreadCommentsByRoleId(Integer userId, Integer orderId, Integer role1Id) {
        return commentsDao.countUnreadCommentsByRoleId(userId, orderId,role1Id);
    }


    public List<Comments> findCommentsByOrderAndRoleId(Integer orderId, Integer roleId, UserOrder userOrder) {

        List<Comments> commentsList = commentsDao.findCommentsByOrderAndRoleId(orderId,roleId);
        checkUnread(commentsList, userOrder);

        return commentsList;
    }

    public Comments findCommentById(Integer commentId) {
        return commentsDao.findOne(commentId);
    }

    public Map<Integer,List<Comments>> findCommentsbyOrderAndRoleIDs(Integer orderId, List<Integer> roleIdList, UserOrder userOrder) {

        List<Comments> commentsList =commentsDao.findCommentsbyOrderAndRoleIDs(orderId, roleIdList);

        checkUnread(commentsList,userOrder);

        return   groupList(commentsList) ;
    }

    public Integer countUnreadCommentsByRoleIds(Integer userId, Integer orderId, List<Integer> roleIdList) {
        return commentsDao.countUnreadCommentsByRoleIds(userId,orderId, roleIdList );
    }

    public void deleteById(Integer commentId) {
        commentsDao.delete(commentId);
    }

    private Map<Integer,List<Comments>> groupList(List<Comments> commentsList){
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

        return groupedComments;
    }

    private void checkUnread(List<Comments> commentsList, UserOrder userOrder) {
        if (commentsList != null && commentsList.size() > 0) {
            int i = 0;
            while (i < commentsList.size() && (commentsList.get(i).getLastModifiedDate().after(userOrder.getLastLook())) && (commentsList.get(i).getFromUser().getUserId() != userOrder.getUser().getUserId()) ) {
                commentsList.get(i).setUnread(true);
                i++;
            }
        }
    }
}
