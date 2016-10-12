package com.napkinstudio.util;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.LinkedList;

@XStreamAlias("attachments")
public class AttachmentsFromSAP {
    @XStreamImplicit
    public LinkedList<AttachmentFormSAP> attachmentFromSAP;


    public LinkedList<AttachmentFormSAP> getAttachments() {
        return attachmentFromSAP;
    }

    public void setAttachments(LinkedList<AttachmentFormSAP> attachmentFromSAP) {
        this.attachmentFromSAP = attachmentFromSAP;
    }


}
