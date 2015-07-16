package com.mhuang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
			msgList.add("你好:"+(++i));
		}
	}

	public static void main( String[] args )
    {
		new App().start();
    }
}
