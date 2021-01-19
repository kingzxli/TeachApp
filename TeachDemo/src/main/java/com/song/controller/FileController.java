package com.song.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.song.entity.CustomException;

@RestController
public class FileController {

	private static final String FILE_PATH = "D:\\file\\";
	/**
	 * 下载文件
	 * @param fileName
	 * @param response
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:46:53
	 */
	@GetMapping("/download/{fileName}")
	public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) {
		fileName = fileName +".xlsx";
		String fullPath = FILE_PATH+fileName;
		File file = new File(fullPath);
		try {
			@SuppressWarnings("resource")
			FileInputStream inStream = new FileInputStream(file);
			// 设置响应头
			setResponseHeader(response, fileName);
			//
			byte[] buf = new byte[2048];
			int readLength;
			OutputStream output = response.getOutputStream();

			while (((readLength = inStream.read(buf)) != -1)) {
				output.write(buf, 0, readLength);
			}
		} catch (Exception e) {
			throw new CustomException("下载文件出错, " + e.getMessage());
		}
	}
	
	/**
	 * 设置响应头
	 * @param response
	 * @param fileName
	 * @author Rex.Tan
	 * @date 2019年10月16日 上午9:34:36
	 */
	public void setResponseHeader(HttpServletResponse response, String fileName) {
		// 清空输出流
		response.reset();
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GB2312"), "8859_1"));
		} catch (UnsupportedEncodingException e) {
			throw new CustomException("设置响应头失败, " + e.getMessage());
		}
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
    }
}
