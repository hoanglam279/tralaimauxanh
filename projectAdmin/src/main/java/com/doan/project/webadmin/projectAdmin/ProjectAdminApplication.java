package com.doan.project.webadmin.projectAdmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
/*
 * @EnableJpaRepositories("com.doan")
 * 
 * @ComponentScan("com.doan")
 */
//@EnableJdbcHttpSession
public class ProjectAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAdminApplication.class, args);
	}

}
