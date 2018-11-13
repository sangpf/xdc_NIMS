package com.newIns.dao.page;

import java.util.List;

import com.newIns.model.page.Channel;

public interface ChannelDao {
	
	List<Channel> listChannel();

	// 根据主键查询
	Channel findById(Integer channelId);
	
	
}
