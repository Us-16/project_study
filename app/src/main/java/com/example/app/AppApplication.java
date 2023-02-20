package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);

		Test();
	}

	public static void Test(){
		//File dir = new File("/Users/janghyolim/Code/project_study/app/src/main/resources/static/files/");
		File dir = new File("./app/src/main/resources/static/files");
		File files[] = dir.listFiles();
		for(File name : files){
			System.out.println("File: " + name);
		}
	}
}
