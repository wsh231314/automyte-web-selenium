package com.auto.myte.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auto.myte.entity.ReceiptInfo;
import com.auto.myte.mapper.ReceiptInfoMapper;

@Component
public class ReceiptInfoService {
	private static Logger logger = LoggerFactory.getLogger(ReceiptInfoService.class);
	@Autowired
	private ReceiptInfoMapper receiptInfoMapper;

	public ReceiptInfo getReceiptBaseInfo(String id) {
		ReceiptInfo info = receiptInfoMapper.selectByPrimaryKey(id);
		return info;
	}

	public List<ReceiptInfo> getAllReceipt() {
		return receiptInfoMapper.selectAll();
	}

	public List<ReceiptInfo> getAllReceiptByEid(String eid, int start, int length) {

		return receiptInfoMapper.selectAllByEid(eid, new RowBounds(start, length));
	}

	public List<ReceiptInfo> getAllReceiptByEid(String eid) {

		return receiptInfoMapper.selectAllByEid(eid);
	}

	public List<ReceiptInfo> getAllReceiptByKey(String eid, String submitDate) {

		return receiptInfoMapper.selectAllByKey(eid, submitDate);
	}

	public int insertReceiptInfo(ReceiptInfo receipt) {
		return receiptInfoMapper.insertReceiptInfo(receipt);
	}

	public int updateReceiptInfoByKey(ReceiptInfo receipt) {
		return receiptInfoMapper.updateReceiptInfoByKey(receipt);
	}

	public int deleteByPrimaryKey(String id) {
		return receiptInfoMapper.deleteByPrimaryKey(id);
	}

}
