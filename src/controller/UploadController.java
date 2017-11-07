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
	 * ����������ת���ϴ��ļ�up.jspҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "toPage")
	public String toPage() {
		return "up";
	}

	/**
	 * ���ļ��ϴ�
	 */
	@RequestMapping("uploadpic1")
	public String uploadPic(MultipartFile file, HttpServletRequest request,
			HttpSession session) {
		// ���ж��ϴ��ļ�file�Ƿ�Ϊ�գ�������˱�500
		if (!file.isEmpty()) {
			// ��ȡupload001���ļ���uploadpic1
			String path = session.getServletContext().getRealPath("upload001");
			// ��ȡ�ļ�����
			String fileName = file.getOriginalFilename();
			// ������
			// ��ȡ�ļ�����׺���ַ���
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			// ��ȡ��ǰʱ����ַ���
			Date date = new Date();
			SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
			String str1 = smp.format(date);
			// ��ȡ��������ַ���
			Random random = new Random();
			int i = random.nextInt(1000000);
			String str2 = Integer.toString(i);
			// ��fileName���¸�ֵ(������)
			fileName = str1 + str2 + suffix;
			// ���ļ���д��upload�ļ���
			File targetFile = new File(path, fileName);
			// ��������ļ�Ŀ¼�����ڣ������´�����Ŀ¼
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// �����ļ�
			try {
				// ��MultipartFile�ļ�������������Ŀ���ļ�
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
	 * ���ļ��ϴ�
	 */
	@RequestMapping("uploadpic2")
	public String uploadPic2(@RequestParam("file") MultipartFile[] files,
			HttpServletRequest request, HttpSession session) {

		// ��ȡupload���ļ���uploadpic1
		String path = session.getServletContext().getRealPath("upload001");
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			// ���ж��ϴ��ļ�file�Ƿ�Ϊ�գ�������˱�500
			if (!file.isEmpty()) {

				// ��ȡ�ļ���
				String fileName = file.getOriginalFilename();

				// ������
				// ��ȡ�ļ�����׺���ַ���
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				// ��ȡ��ǰʱ����ַ���
				Date date = new Date();
				SimpleDateFormat smp = new SimpleDateFormat("yyyyMMddHHmmss");
				String str1 = smp.format(date);
				// ��ȡ��������ַ���
				Random random = new Random();
				int i1 = random.nextInt(1000000);
				String str2 = Integer.toString(i1);
				// ��fileName���¸�ֵ(������)
				fileName = str1 + str2 + suffix;
				// ���ļ���д��upload�ļ���
				File targetFile = new File(path, fileName);
				// ��������ļ�Ŀ¼�����ڣ������´�����Ŀ¼
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// �����ļ�
				try {
					// ��MultipartFile�ļ�������������Ŀ���ļ�
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
