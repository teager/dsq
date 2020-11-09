package com.dsq.dsq.core.service;

import javax.annotation.Resource;

import com.dsq.dsq.core.entity.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import coo.core.hibernate.dao.Dao;

/**
 * 附件管理。
 */
@Service
public class AttachmentService {
	@Resource
	private Dao<Attachment> attachmentDao;

	/**
	 * 新增附件。
	 * 
	 * @param attachment
	 *            附件
	 */
	@Transactional
	public void createAttachment(Attachment attachment) {
		attachmentDao.save(attachment);
	}
}