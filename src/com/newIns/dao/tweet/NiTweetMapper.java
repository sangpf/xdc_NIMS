package com.newIns.dao.tweet;

import java.util.List;
import java.util.Map;

import com.newIns.model.tweet.NiTweet;

/**
 * 推文接口
 * @author 11409
 *
 */
public interface NiTweetMapper {
	
	//查询所有
	List<NiTweet> findAll();
	
	//保存推文
	void saveTweet(NiTweet niTweet);
	
	//批量删除
	void deleteTweetByIds(Map<String,Object> retMap);

	//根据id查询一个
	NiTweet findOneById(Integer tweetId);

	//编辑
	void updateById(NiTweet niTweet);

	//根据名称模糊查询
	List<NiTweet> findByName(String tweetIdOrName_Str);

	//根据id查询, 封装成集合
	List<NiTweet> findListById(Integer tweetId);
	
	
}
