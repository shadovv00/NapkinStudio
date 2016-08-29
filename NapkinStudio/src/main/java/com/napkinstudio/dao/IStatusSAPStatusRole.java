package com.napkinstudio.dao;

import com.napkinstudio.entity.StatusSAPStatusRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User1 on 26.08.2016.
 */
@Repository
public interface IStatusSAPStatusRole extends JpaRepository<StatusSAPStatusRole,Integer> {

}
