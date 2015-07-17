package com.mhuang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App extends Thread 
{
	public static volatile List<String> msgList = new ArrayList<String>();
    
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
		applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }
}
