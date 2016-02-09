package be.projecttycoon;

import be.projecttycoon.db.UserRepository;
import be.projecttycoon.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class ProjecttycoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjecttycoonApplication.class, args);
	}
}
