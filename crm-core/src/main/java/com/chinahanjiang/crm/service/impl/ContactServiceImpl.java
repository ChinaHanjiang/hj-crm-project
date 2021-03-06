package com.chinahanjiang.crm.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chinahanjiang.crm.dao.ContactDao;
import com.chinahanjiang.crm.dto.ContactDto;
import com.chinahanjiang.crm.dto.MessageDto;
import com.chinahanjiang.crm.dto.SearchResultDto;
import com.chinahanjiang.crm.pojo.Contact;
import com.chinahanjiang.crm.pojo.Customer;
import com.chinahanjiang.crm.service.ContactService;
import com.chinahanjiang.crm.service.CustomerService;
import com.chinahanjiang.crm.util.DataUtil;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Service("contactService")
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class ContactServiceImpl implements ContactService {

	@Resource
	private ContactDao contactDao;
	
	@Resource
	private CustomerService customerService;
	
	@Override
	public SearchResultDto searchAndCount(int customerId, String order,
			String sort, int page, int row) {
		
		Customer c = customerService.findById(customerId);
		List<ContactDto> cds = null;
		int records = 0;
		Search search = new Search();
		if(c!=null){
			
			search.addFilterEqual("customer", c);
			search.addFilterEqual("isDelete",1);
			search.setMaxResults(row);
			search.setPage(page - 1 < 0 ? 0 : page - 1);
			SearchResult<Contact> result = searchAndCount(search);
			List<Contact> cls = result.getResult();
			
			cds = DataUtil.convertContactToDto(cls);
			records = result.getTotalCount();
		} else {
			
			cds = new ArrayList<ContactDto>();
			records = 0;
		}
		
		SearchResultDto srd = new SearchResultDto();
		srd.getRows().clear();
		srd.getRows().addAll(cds);
		srd.setTotal(records);
		
		return srd;
	}
	
	private SearchResult<Contact> searchAndCount(Search search) {

		return contactDao.searchAndCount(search);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MessageDto update(ContactDto cd) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		MessageDto md = new MessageDto();
		int c_id = cd.getId();
		Contact c = null;
		
		int customerId = cd.getCustomerId();
		Customer customer = customerService.findById(customerId);
		
		if(c_id == 0){
			
			c = new Contact();
			c.setCreateTime(now);
			if(customer!=null){
				
				c.setCustomer(customer);
			} else {
				
				md.setT(false);
				md.setMessage("联系人对应的公司不存在！");
				
				return md;
			}
		} else {
			
			c = contactDao.find(c_id);
			
			if(c!=null){
				c.setUpdateTime(now);
			}
		}
		
		if(c != null){
			
			c.setName(cd.getName());
			c.setMobilePhone(cd.getPhone());
			c.setEmail(cd.getEmail());
			c.setDuty(cd.getDuty());
			c.setRemarks(cd.getRemarks());
			c.setSex(cd.getSexId());
			
			boolean isR = contactDao.save(c);
			if(isR){
				
				md.setT(true);
				md.setMessage("联系人添加成功！");
			} else {
				
				md.setT(true);
				md.setMessage("联系人修改成功！");
			}
		}else {
			
			md.setT(false);
			md.setMessage("联系人不存在！");
		}
		
		return md;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MessageDto delete(ContactDto cd) {
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		MessageDto md = new MessageDto();
		int id = cd.getId();
		
		Contact c = contactDao.find(id);
		if(c!=null){
			
			c.setIsDelete(0);
			c.setUpdateTime(now);
			
			contactDao.save(c);
			md.setT(true);
			md.setMessage("联系人删除成功！");
			
		} else {
			
			md.setT(false);
			md.setMessage("联系人找不到！");
		}
		
		return md;
	}

	@Override
	public List<ContactDto> search(ContactDto cd) {
		
		List<ContactDto> cds = new ArrayList<ContactDto>();
		
		int id = cd.getId();
		if(id!=0){
			
			Customer c = customerService.findById(id);
			if(c!=null){
				
				Search search = new Search();
				search.addFilterEqual("customer", c);
				List<Contact> cts = contactDao.search(search);
				
				cds = DataUtil.convertContactToDto(cts);
			}
		}
		
		return cds;
	}

	@Override
	public Contact findById(int cid) {
		
		return contactDao.find(cid);
	}
}
