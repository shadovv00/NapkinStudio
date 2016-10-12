package com.napkinstudio.dao;


import com.napkinstudio.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProcessDao extends JpaRepository<Process,Integer> {

}
