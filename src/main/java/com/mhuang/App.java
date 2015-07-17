package com.mhuang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App extends Thread 
{
	public static volatile List<String> msgList = new ArrayList<String>();
    
	public static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static volatile int i = 0;
	
	@Override
	public synchronized void start() {
		while(true){
			if(i==100){
				break;
			}
			msgList.add("你好你你你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好:"+(++i));
		}
	}

	public volatile static ApplicationContext applicationContext = null;
	
	public static void main( String[] args )
    {
		logger.info("==== begin loader spring config ====");
		applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		logger.info("==== end loader spring config ====");
    }
}
