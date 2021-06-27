package com.harsh.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class DynamicTasks {

	@Autowired
	private TaskScheduler taskScheduler;

	@PostConstruct
	public void scheduleTasks() throws NoSuchMethodException, SecurityException {
		List<String> cronTriggers = callData();
		
		for(int i=0;i<cronTriggers.size();i++) {
			String x= cronTriggers.get(i);
			
			taskScheduler.schedule(() -> {
				dynamicTasksScheduleThreadPool();
			}, new CronTrigger(x));
		}
		
	}

	private List<String> callData() {
		List<String> link = new ArrayList<>();
		String x= null;
		Scanner Text_Scan = null;
		File myFile = new File("cronExp.txt");
//		System.out.println(myFile);
		try {
			Text_Scan = new Scanner(myFile);
//			System.out.println(Text_Scan);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		while(Text_Scan.hasNext()) {
			x=(Text_Scan.nextLine());
			link.add(x);
		}
		return link;
//		return link;
	}
	
	public void dynamicTasksScheduleThreadPool() {
		System.out.println("Hello World");
	}
}