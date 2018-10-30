package com;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wjx.aspect.HelloWorld;



/**
 * junit测试是否成功
 * @author wjx
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/applicationContext.xml")
public class JUnitTest {
	
	@Autowired
	private HelloWorld helloWorld;
	
	@Autowired DataSource dataSource;

	@Test
	public void test(){
		helloWorld.printHelloWorld();
	}
	
	@Test
	public void getConnection(){
		try {
			dataSource.getConnection();
			System.out.println("数据源connection："+dataSource.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
