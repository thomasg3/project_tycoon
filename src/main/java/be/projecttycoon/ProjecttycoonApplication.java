package be.projecttycoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ProjecttycoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjecttycoonApplication.class, args);
	}
}
