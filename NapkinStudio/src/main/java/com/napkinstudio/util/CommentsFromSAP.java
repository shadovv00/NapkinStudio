package com.napkinstudio.util;

import java.util.LinkedList;
import com.napkinstudio.util.CommentFromSAP;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("commentaries")
public class CommentsFromSAP {
    @XStreamImplicit
    public LinkedList<CommentFromSAP> commentFromSAP;



    public LinkedList<CommentFromSAP> getComments() {
        return commentFromSAP;
    }

    public void setComments(LinkedList<CommentFromSAP> commentFromSAP) {
        this.commentFromSAP = commentFromSAP;
    }


}
