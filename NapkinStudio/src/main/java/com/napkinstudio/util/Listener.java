package com.napkinstudio.util;

import com.napkinstudio.entity.AbstractEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by User1 on 20.07.2016.
 */
public class Listener {
    @PrePersist
    public void setFirstModifiedDate(AbstractEntity entity) {
        entity.setLastModifiedDate(new Date());
    }

    @PreUpdate
    public void setLastModifiedDate(AbstractEntity entity) {
        entity.setLastModifiedDate(new Date());
    }
}
