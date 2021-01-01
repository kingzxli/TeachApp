package com.song.service;

import com.song.pojo.BdOrder;

public interface BdOrderService {
	
	void insert(BdOrder bdorder);
	void update(BdOrder bdorder);
	
	BdOrder selectBytp(String tpOrderId);
	void delete(String tpOrderId);

}
