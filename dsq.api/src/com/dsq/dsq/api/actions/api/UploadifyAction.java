package com.dsq.dsq.api.actions.api;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import com.dsq.dsq.core.entity.Attachment;
import com.dsq.dsq.core.service.AttachmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import coo.base.util.DateUtils;

/**
 * 上传图片管理。
 */
@Controller
@RequestMapping("/api/file")
public class UploadifyAction {
	@Resource
	private AttachmentService attachmentService;
	@Value(value = "${content.path}")
	private String folder;
	@Value(value = "${content.server.url}")
	private String url;

	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public String upload(){

		return "{'path':'" + url + "/" + "','id':'"
				+ 1000 + "'}";
	}
	/**
	 * 上传图片。
	 * 
	 * @param file
	 *            图片文件
	 * @return 返回JSON格式字符串。
	 * @throws Exception
	 *             上传图片异常
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(
			@RequestParam(value = "file", required = false) MultipartFile file)
			throws Exception {
		byte[] bytes = file.getBytes();
		String newFileName = DateUtils.format(new Date(),
				DateUtils.MILLISECOND_N);
		String prefix = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		String origFileName = file.getOriginalFilename().substring(0,
				file.getOriginalFilename().lastIndexOf("."));
		File dirPath = new File(folder);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		FileCopyUtils
				.copy(bytes, new File(folder + "/" + newFileName + prefix));
		Attachment attachment = new Attachment();
		attachment.setCreateTime(new Date());
		attachment.setName(origFileName);
		attachment.setPath(newFileName + prefix);
		attachmentService.createAttachment(attachment);
		return "{'path':'" + url + "/" + newFileName + prefix + "','id':'"
				+ attachment.getId() + "'}";
	}

}