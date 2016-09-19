package com.napkinstudio.dao;

import com.napkinstudio.entity.SynchronizationDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISynchronizationDateDao extends JpaRepository<SynchronizationDate,Integer> {

}
