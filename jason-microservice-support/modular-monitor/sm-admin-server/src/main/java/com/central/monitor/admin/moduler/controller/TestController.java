package com.central.monitor.admin.moduler.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;


@RequestMapping("/test")
@RestController
public class TestController {

	@Autowired
	private JavaMailSender javaMailSender;

	@GetMapping("/sendMail")
	public String send() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("3697871@qq.com");
		message.setTo("jiangjie@smartcloudgis.com");
		message.setSubject("测试邮件");
		message.setText("好好学习");
		javaMailSender.send(message);
		return "hello";
	}

	@GetMapping("/sendMai2")
	public String send1() throws MessagingException {

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "utf-8");

		msgHelper.setFrom("3697871@qq.com");
		msgHelper.setTo("jiangjie@smartcloudgis.com");
		msgHelper.setSubject("测试发送带附件的邮件");
		msgHelper.setText("测试邮件");

		FileSystemResource file = new FileSystemResource(new File("D:"+File.separator+"test.jpg"));
		msgHelper.addAttachment("test.jpg", file); // 添加附件

		// Properties prop = new Properties();
		// prop.put("mail.smtp.auth", "true");
		// prop.put("mail.smtp.timeout", "25000");
		// javaMailSender.setJavaMailProperties(prop);

		javaMailSender.send(msg);

		return "hello";
	}
}
