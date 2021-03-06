package com.ibest.recon.service;

import java.util.ArrayList;
import java.util.List;

import com.ibest.framework.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ibest.framework.common.utils.PageList;

import com.ibest.recon.dao.PaySystemLocalReconDao;
import com.ibest.recon.entity.PaySystemLocalRecon;
import com.ibest.recon.dto.input.PaySystemLocalReconInputDTO;

@Service
@Transactional(readOnly=true)
public class PaySystemLocalReconService {

	@Autowired
	protected PaySystemLocalReconDao paySystemLocalReconDao;
	
	public PaySystemLocalRecon findById(String id) throws Exception{
		return paySystemLocalReconDao.findById(id);
	}
	
	@Transactional(readOnly=false)
	public int insert(PaySystemLocalRecon paySystemLocalRecon) throws Exception{
		paySystemLocalRecon.preInsert();
		int result = paySystemLocalReconDao.insert(paySystemLocalRecon);
		return result;
	}
	
	@Transactional(readOnly=false)
	public int deleteById(String id) throws Exception{
		int result = paySystemLocalReconDao.deleteById(id);
		return result;
	}
	
	@Transactional(readOnly=false)
	public int deleteByIds(String ids) throws Exception{
		int result = paySystemLocalReconDao.deleteByIds(StringUtils.tokenizeToList(ids));
		return result;
	}
	
	@Transactional(readOnly=false)
	public int update(PaySystemLocalRecon paySystemLocalRecon) throws Exception{
		paySystemLocalRecon.preUpdate();
		int result = paySystemLocalReconDao.update(paySystemLocalRecon);
		return result;
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param inputDto
	 * @return
	 * @throws Exception
	 */
	public PageList<PaySystemLocalRecon> findByPage(PageList<PaySystemLocalRecon> page, PaySystemLocalReconInputDTO inputDto) throws Exception{
		
		if(page == null){
			page = new PageList<PaySystemLocalRecon>();
		}
		
		long totalCount = paySystemLocalReconDao.countByObject(inputDto);
		if(totalCount > 0){
			// 设置记录总条数
			page.setTotal(totalCount);
			
			// 设置分页参数，查询数据
			inputDto.setLimitStart((page.getPage() - 1) * page.getPageSize());
			inputDto.setLimitSize(page.getPageSize());
			page.setRows(paySystemLocalReconDao.findByObject(inputDto));
		}
		
		return page;
	}
	
	/**
	* 查询列表
	*/
	public PaySystemLocalRecon findByObject(PaySystemLocalReconInputDTO inputDto) throws Exception{
		return paySystemLocalReconDao.findOneByObject(inputDto);
	}

	public PaySystemLocalRecon findByOrderId(String orderId) throws Exception{
		return paySystemLocalReconDao.findByOrderId(orderId);
	}
}
