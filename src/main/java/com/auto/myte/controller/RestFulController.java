package com.auto.myte.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auto.myte.beans.DataTablesViewBean;
import com.auto.myte.config.PropertiesConfig;
import com.auto.myte.entity.ReceiptInfo;
import com.auto.myte.service.ReceiptInfoService;
import com.auto.myte.utils.DateUtils;
import com.auto.myte.utils.FileUtils;
import com.auto.myte.utils.ZipUtils;

import ch.qos.logback.core.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Configuration
@RestController
@Api(value= "Receipt使用类", description ="App相关操作接口定义类")
public class RestFulController {
	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private ReceiptInfoService service;

	/**
	 * 一览显示
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "一览显示",httpMethod ="GET", notes = "list")
	@RequestMapping("/tables")
	public DataTablesViewBean<ReceiptInfo> list(HttpServletRequest request) {
//		DataTableUtils du = new DataTableUtils(request, null, null);
		// 翻页设置，注释掉
//		String start = request.getParameter("start");  
//		String length = request.getParameter("length");  
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		// 翻页设置，注释掉
//		List<ReceiptInfo> receiptInfoList = service.getAllReceiptByEid(userDetails.getUsername(), Integer.parseInt(start), Integer.parseInt(length));
		List<ReceiptInfo> receiptInfoList = service.getAllReceiptByKey(userDetails.getUsername(), DateUtils.getMyteEndDateTime());
		Map<String, List> map = new HashMap<String, List>();
		map.put("data", receiptInfoList);
		DataTablesViewBean<ReceiptInfo> dvb = new DataTablesViewBean<ReceiptInfo>();
		dvb.setData(receiptInfoList);
		dvb.setRecordsFiltered(21);
		dvb.setRecordsTotal(21);
		return dvb;
	}

	/**
	 * 查询显示
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "查询显示",httpMethod ="GET", notes = "search")
	@RequestMapping("/search")
	public DataTablesViewBean<ReceiptInfo> search(@RequestParam(value = "submitDate", required = false) String submitDate) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		List<ReceiptInfo> receiptInfoList = service.getAllReceiptByKey(userDetails.getUsername(), submitDate);
		Map<String, List> map = new HashMap<String, List>();
		map.put("data", receiptInfoList);
		DataTablesViewBean<ReceiptInfo> dvb = new DataTablesViewBean<ReceiptInfo>();
		dvb.setData(receiptInfoList);
		dvb.setRecordsFiltered(21);
		dvb.setRecordsTotal(21);
		return dvb;
	}

	/**
	 * 详细表示
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "详细表示",httpMethod ="GET", notes = "details")
	@RequestMapping("/details")
	public ReceiptInfo details(@RequestParam(value = "id", required = false) String id) {
		ReceiptInfo receiptInfo = service.getReceiptBaseInfo(id);
		return receiptInfo;
	}

	/**
	 * 更新（详细画面的update button）
	 * @param form
	 * @return
	 */
	@ApiOperation(value = "详细表示",httpMethod ="POST", notes = "details")
	@RequestMapping("/update")
	public Map<String, String> update(@RequestBody ReceiptInfo form) {
		Map<String, String> map = new HashMap<>();
		try {
			form.setStatus("正常処理された。");
			int flg = service.updateReceiptInfoByKey(form);
			if (flg > 0) {
				map.put("status", "0");
				map.put("message", "update is ok");
			} else {
				map.put("status", "1");
				map.put("message", "update is ng");
			}
		} catch (Exception e) {
			map.put("status", "1");
			map.put("message", "update is exception");
			logger.error(e.getMessage());
		}
		return map;
	}

	/**
	 * 一览画面的删除button
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "一览画面的删除",httpMethod ="POST", notes = "delete")
	@RequestMapping("/delete")
	public Map<String, Integer> delete(@RequestParam(value = "id", required = false) String id) {
		Map<String, Integer> map = new HashMap<>();
		int flg = service.deleteByPrimaryKey(id);
		map.put("flg", Integer.valueOf(flg));
		return map;
	}

	/**
	 * 一览画面的runScript button，调用cmd命令执行脚本（配置在application.properties中）
	 */
	@ApiOperation(value = "调用cmd命令执行脚本",httpMethod ="GET", notes = "run")
	@RequestMapping("/run")
	public void run() {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        String fileLac = "";
        try {
        	String osName = System.getProperty("os.name" ); 
            logger.info(osName);
            fileLac = propertiesConfig.getRunScript();//要调用的程序路径
            String [] cmd={"cmd","/C", fileLac}; 
            p = rt.exec(cmd);
            logger.info(p.toString());
//            Thread.sleep(10000);
            
            InputStream stdin = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("<output></output>");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("");
            int exitVal = p.exitValue();
            System.out.println("Process exitValue: " + exitVal);
            System.out.println("<output></output>");
            
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
	}

	/**
	 * 下载
	 * Handle request to download an Excel document
	 */
	@ApiOperation(value = "下载Excel和Sikuli的Zip文件",httpMethod ="GET", notes = "downloadr")
	@RequestMapping(value = "/downloadr", method = RequestMethod.GET)
	public Map<String, String> download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";

		String fileName = "";
		try {
			fileName = downloadExcel(result);
			fileName = fileName + "@" + this.downloadZip(result);
			result = "ダウンロード成功！";
		} catch(Exception e) {
			result = "ダウンロード失敗！";
		}
		Map<String, String> map = new HashMap<>();
		map.put("messageInfo", fileName + " " + result);
		return map;
	}

	/**
	 * 下载Excel
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private String downloadExcel(String result) throws Exception {
		String fileName = propertiesConfig.getExcel();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			File file = ResourceUtils.getFile("classpath:template.xls");
			Workbook wb = null;
			try {

				wb = new XSSFWorkbook(new FileInputStream(file));
			} catch(Exception ex) {
				wb = new HSSFWorkbook(new FileInputStream(file));
			}
			Sheet sheet = wb.getSheetAt(0);
			List<ReceiptInfo> receiptInfoList = service.getAllReceiptByEid(userDetails.getUsername());
			int i = 1;
			int row = 4;
			for (ReceiptInfo receiptInfo : receiptInfoList) {
				if (i == 1) {
					// DATA_START
					sheet.getRow(row).getCell(0).setCellValue("DATA_START");
				}
				// Amount
				sheet.getRow(row).getCell(1).setCellValue(receiptInfo.getAmount());
				// On(Date)
				sheet.getRow(row).getCell(2).setCellValue(receiptInfo.getDate());

	        	if (i == receiptInfoList.size()) {
					// DATA_START
					sheet.getRow(row).getCell(0).setCellValue("DATA_END");
	        	}
				i++;
				row++;
			}

			wb.write(os);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw e;
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		File file = new File(fileName);
		FileUtil.createMissingParentDirectories(file);
        FileOutputStream fos = new FileOutputStream(file);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(fos);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return fileName;
	}
	


	/**
	 * 下载ZIP并解压缩
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private String downloadZip(String result) throws Exception {

		String fileName = propertiesConfig.getLocZip();
		if (FileUtils.judeFileExists(new File(fileName))) {
			return fileName;
		}
		try {
			FileUtils.download(propertiesConfig.getDownloadZip(), fileName);
			boolean zip = ZipUtils.unZipFiles(fileName, propertiesConfig.getRunfolder());
			if (zip) {

				logger.error("unzip failure!!");
			}
		} catch (Exception e) {
			throw e;
		}
		
		return fileName;
	}
}
