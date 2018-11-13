package com.newIns.dao.imp;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class NiSurveyQuestionnaireMapperImp {
	
	public void test() throws IOException{
		
		String resource = "config/sqlMapConfig.xml";
		
		Reader reader = Resources.getResourceAsReader(resource);  
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Object selectOne = sqlSession.selectOne("1");
		
	}
	
}
