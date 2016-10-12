package com.napkinstudio.util;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.LinkedList;

@XStreamAlias("statusChangeHistory")
public class StatusChangesFromSAP {
    @XStreamImplicit
    public LinkedList<StatusChangeFromSAP> statusChangeFromSAP;


    public LinkedList<StatusChangeFromSAP> getStatusChanges() {
        return statusChangeFromSAP;
    }

    public void setStatusChanges(LinkedList<StatusChangeFromSAP> statusChangeFromSAP) {
        this.statusChangeFromSAP = statusChangeFromSAP;
    }


}
