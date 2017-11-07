package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	/**
	 * 根据请求跳转到上传文件up.jsp页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toPage")
	public String toPage() {
		return "up";
	}

	/**
	 * 单文件上传
	 */
	@RequestMapping("uploadpic1")
	public String uploadPic(MultipartFile file, HttpServletRequest request,
			HttpSession session) {
		// 先判断上传文件file是否为空，否则空了报500
		if (!file.isEmpty()) {
			// 获取upload001的文件夹uploadpic1
			String path = session.getServletContext().getRealPath("upload001");
			// 获取文件名字
			String fileName = file.getOriginalFilename();
			// 重命名
			// 获取文件名后缀的字符串
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			// 获取当前时间的字符串
			Date date = new Date();
			SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
			String str1 = smp.format(date);
			// 获取随机数的字符串
			Random random = new Random();
			int i = random.nextInt(1000000);
			String str2 = Integer.toString(i);
			// 给fileName重新赋值(重命名)
			fileName = str1 + str2 + suffix;
			// 把文件名写入upload文件夹
			File targetFile = new File(path, fileName);
			// 如果保存文件目录不存在，则重新创建该目录
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 保存文件
			try {
				// 把MultipartFile文件刘的数据输入目标文件
				file.transferTo(targetFile);
				
				session.setAttribute("picpath", "upload001/"+fileName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "up";
	}

	/**
	 * 多文件上传
	 */
	@RequestMapping("uploadpic2")
	public String uploadPic2(@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpSession session) {

		// 获取upload的文件夹uploadpic1
		String path = session.getServletContext().getRealPath("upload001");
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// 先判断上传文件file是否为空，否则空了报500
			if (!file.isEmpty()) {

				// 获取文件名
				String fileName = file.getOriginalFilename();

				// 重命名
				// 获取文件名后缀的字符串
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				// 获取当前时间的字符串
				Date date = new Date();
				SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
				String str1 = smp.format(date);
				// 获取随机数的字符串
				Random random = new Random();
				int i1 = random.nextInt(1000000);
				String str2 = Integer.toString(i1);
				// 给fileName重新赋值(重命名)
				fileName = str1 + str2 + suffix;
				// 把文件名写入upload文件夹
				File targetFile = new File(path, fileName);
				// 如果保存文件目录不存在，则重新创建该目录
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 保存文件
				try {
					// 把MultipartFile文件刘的数据输入目标文件
					file.transferTo(targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "up";
	}
}
