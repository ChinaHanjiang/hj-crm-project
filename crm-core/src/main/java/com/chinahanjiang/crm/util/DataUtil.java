package com.chinahanjiang.crm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.chinahanjiang.crm.dto.CustomerDto;
import com.chinahanjiang.crm.dto.EyTreeDto;
import com.chinahanjiang.crm.pojo.Customer;
import com.chinahanjiang.crm.pojo.Location;
import com.google.gson.Gson;

public class DataUtil {

	private static DateFormat sdf_d = new SimpleDateFormat("yyyy/MM/dd");

	private static SimpleDateFormat sdf_dt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static List<CustomerDto> convertCustomerToDto(List<Customer> cls) {
		// TODO Auto-generated method stub
		
		List<CustomerDto> cds = new ArrayList<CustomerDto>();
		Iterator<Customer> it = cls.iterator();
		while(it.hasNext()){
			
			Customer c = it.next();
			CustomerDto cd = new CustomerDto();
			
			cd.setId(c.getId());
			cd.setName(c.getName());
			cd.setCode(c.getCode());
			cd.setAddress(c.getAddress());
			cd.setLocation(c.getLocation()==null?"":c.getLocation().getName());
			cd.setTelephone(c.getTelephone());
			cd.setFax(c.getFax());
			cd.setRemarks(c.getRemarks());
			
			cd.setCreateTime(c.getCreateTime()==null?"":sdf_dt.format(c.getCreateTime()));
			cd.setUpdateTime(c.getUpdateTime()==null?"":sdf_dt.format(c.getUpdateTime()));
			
			cds.add(cd);
		}
		return cds;
	}
	
	public static String locationToJson(Location loc) {

		Gson gson = new Gson();
    	EyTreeDto btd = convertLocationToDto(loc);
    	String str = null;
    	if(btd != null){
    		str = gson.toJson(btd);
    	}
		return str;
	}

	private static EyTreeDto convertLocationToDto(Location loc) {
		// TODO Auto-generated method stub
		
		EyTreeDto btd = null;
		int isDelete = loc.getIsDelete();
		if (loc != null && isDelete != 1) {
			btd = new EyTreeDto();
			btd.setId(loc.getId());
			btd.setText(loc.getName());
			btd.setState(loc.getState());

			List<Location> children = loc.getChildLocs();
			if (children != null && children.size() != 0) {
				btd.setIsF(1);
				List<EyTreeDto> btdchild = new ArrayList<EyTreeDto>();
				EyTreeDto btdc = null;
				for (Location t : children) {

					btdc = convertLocationToDto(t);
					if (btdc != null) {
						btdchild.add(btdc);
					}
				}

				btd.setChildren(btdchild);
			} else {
				
				btd.setIsF(0);
			}

		}

		return btd;
		
	}

}