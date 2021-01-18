package com.song.util.poi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;
import com.song.entity.CustomException;



/**
 * 文件操作工具类
 */
@Component
public class FileUtil {
	private static final String FILE_PATH = "D:\\file";
	private static final String FILE_DOWNLOAD = "http://www.xurijiajiao.cn:8080";
	
	public String writeExcel(Workbook wb, String name) {
		this.checkFolderExist(new File(FILE_PATH));
		// 将文件写入服务器目录
		String fileName = name + DateUtils.timestamp() + ".xlsx"; //excel 2007
        String fullPath = FILE_PATH + "\\" + fileName;
        try {
			FileOutputStream output = new FileOutputStream(fullPath);
			wb.write(output);
			output.flush();
			// 释放对象
			wb.close();
			output.close();
		} catch (FileNotFoundException e1) {
			throw new CustomException("创建文件失败, " + e1.getMessage());
		} catch (IOException e) {
			throw new CustomException("写入文件失败, " + e.getMessage());
		}
        
        return FILE_DOWNLOAD +"/download/"+ fileName;
	}
	
	/**
	 * 单元格样式
	 * @param workbook
	 * @return
	 */
	public CellStyle createStyle(Workbook workbook) {
		CellStyle style = (CellStyle) workbook.createCellStyle();
		
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setFontName("宋体");
		
		style.setFont(font);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setLocked(false);
		return style;
	}
	
	/**
	 * 锁定单元格样式
	 * @param workbook
	 * @return
	 */
	public CellStyle createLockStyle(Workbook workbook) {
		CellStyle style = (CellStyle) workbook.createCellStyle();
		
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setFontName("宋体");
		
		style.setFont(font);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		//style.setFillBackgroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		//style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());// 设置背景色		
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setLocked(true);
		return style;
	}
	
	/**
	 * 创建单元格
	 * @param row
	 * @param col
	 * @param value
	 * @param style
	 */
	public void cellSetUp(Row row,Integer col,String value,CellStyle style) {		
		Cell cell = row.createCell(col);
        cell.setCellValue(value);
        cell.setCellStyle(style);
	}
	
	/**
	 * 合并单元格
	 * @param sheet
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	public void cellRangeAddressSetUp(Sheet sheet,Integer firstRow,Integer lastRow,Integer firstCol,Integer lastCol) {			
		 CellRangeAddress region = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
	     sheet.addMergedRegion(region);
	}

	/**
	 * 目录不存在, 创建目录
	 * @param file
	 * @author Rex.Tan
	 * @date 2019年10月15日 下午6:12:23
	 */
	private void checkFolderExist(File file) {
		if (!file.exists()) {
			file.mkdirs();
        } 
	}
}
