package com.napkinstudio.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.napkinstudio.entity.Attachment;

public interface IAttachmentDao extends JpaRepository<Attachment,Integer> {
	
	List<Attachment> findAttachmentsbyRoleId(@Param("id") Integer id);

	List<Attachment> findAttachmentMapByOrderIdRoleId(@Param("orderId")Integer orderId, @Param("roleId")Integer roleId);

	void deleteByName(@Param("name") String name);

	List<Attachment> findAttachmentMapByOrderId(@Param("orderId")Integer orderId);

}
