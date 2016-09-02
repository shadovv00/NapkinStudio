package com.napkinstudio.dao;


import com.napkinstudio.entity.ProgresBarFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProgresBarFieldsDao extends JpaRepository<ProgresBarFields,Integer> {


    public List<ProgresBarFields> findAll();
    public List<Object[]> findBarByRolePVICheckReject(@Param("roleId") Integer roleId,@Param("pVIcheckScen") Boolean pVIcheckScen,
                                                           @Param("rejected") Boolean rejected);


}
