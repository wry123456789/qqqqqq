package com.example.demo;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wyr.mysql.Demo1;

@Component
public class Demo {
	Demo1 demo1 =new Demo1();
	
	@Scheduled(cron="0/2 * * * * ?")//
	public void scheduledMethod(){
		demo1.getAll();
		System.out.println("定时器被触发"+new Date());
	}

}
