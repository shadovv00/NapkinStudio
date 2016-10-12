package com.napkinstudio.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.napkinstudio.dao.IAttachmentDao;
import com.napkinstudio.entity.Attachment;

@Service
public class AttachmentManager {
	@Autowired
	private IAttachmentDao attachmentDao;
	
	
	
	@Transactional
    public boolean save(List<Attachment> list) {
		boolean status = false;
		attachmentDao.save(list);
		status = true;
		return status;
    }
	
	
	
	public List<Attachment> findAttachmentsByRoleId(Integer id) {
        List<Attachment> attachmentList = attachmentDao.findAttachmentsbyRoleId(id);
        return attachmentList;
    }
	
	
	
	public Map<String, Attachment> findAttachmentMapByRoleId(Integer id) {
		Map<String, Attachment> map = new HashMap<>();
        List<Attachment> attachmentList = attachmentDao.findAttachmentsbyRoleId(id);
        for(Attachment attachment : attachmentList) {
        	map.put(attachment.getName(), attachment);
        }
        return map;
    }
	
	
	
	public Map<String, Attachment> findAttachmentMapByOrderIdRoleId(Integer orderId, Integer roleId) {
		Map<String, Attachment> map = new HashMap<>();
        List<Attachment> attachmentList = attachmentDao.findAttachmentMapByOrderIdRoleId(orderId, roleId);
        for(Attachment attachment : attachmentList) {
        	map.put(attachment.getName(), attachment);
        }
        return map;
    }
	
	
	
	public Map<String, Attachment> findAttachmentMapByOrderId(Integer orderId) {
		Map<String, Attachment> map = new HashMap<>();
        List<Attachment> attachmentList = attachmentDao.findAttachmentMapByOrderId(orderId);
        for(Attachment attachment : attachmentList) {
        	map.put(attachment.getName(), attachment);
        }
        return map;
    }
	
	
	
	@Transactional
    public void deleteAttachment(Attachment attachment) {
    	attachmentDao.delete(attachment);
    }
	
}
