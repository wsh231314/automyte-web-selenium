package com.auto.myte.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.auto.myte.beans.Message;
import com.auto.myte.common.message.SpringMessageResourceMessages;
import com.auto.myte.config.PropertiesConfig;
import com.auto.myte.entity.ReceiptInfo;
import com.auto.myte.service.ReceiptInfoService;
import com.auto.myte.utils.AZureOcrUtils;
import com.auto.myte.utils.CommonUtils;
import com.auto.myte.utils.DateUtils;
import com.auto.myte.utils.ParseJsonUtils;

@Controller
@RequestMapping("/expense")
public class ExpenseController {
	private static Logger logger = LoggerFactory.getLogger(ExpenseController.class);

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private SpringMessageResourceMessages messageSource;

	@Autowired
	private ReceiptInfoService service;

	/**
	 * Handle request to download an Excel document
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			File file = ResourceUtils.getFile("classpath:template.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			Sheet sheet = wb.getSheetAt(0);
			List<ReceiptInfo> receiptInfoList = service.getAllReceiptByEid(userDetails.getUsername());
			int i = 1;
			int row = 2;
			for (ReceiptInfo receiptInfo : receiptInfoList) {
				// No.
				sheet.getRow(row).getCell(1).setCellValue(i);
				// Receipt type
				sheet.getRow(row).getCell(2).setCellValue(receiptInfo.getCategory_name());
				// Country of Expense
				sheet.getRow(row).getCell(3).setCellValue("ja");
				// CNY
				sheet.getRow(row).getCell(4).setCellValue("Ja");
				// Amount
				sheet.getRow(row).getCell(5).setCellValue(receiptInfo.getAmount());
				// On
				sheet.getRow(row).getCell(6).setCellValue(receiptInfo.getDate());
				// Reason
				String reason = "";
				if ("1".equals(receiptInfo.getCategory_id())) {
					reason = "Client Site/Other<-> Other/Client Site";
				} else if ("2".equals(receiptInfo.getCategory_id())) {
					reason = "Oviertime Meal Allowance";
				}
				sheet.getRow(row).getCell(7).setCellValue(reason);
				i++;
				row++;
			}

			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);

		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((userDetails.getUsername() + ".xlsx").getBytes(), "iso-8859-1"));

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

}
