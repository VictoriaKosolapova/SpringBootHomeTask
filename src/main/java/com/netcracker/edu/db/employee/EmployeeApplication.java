package com.netcracker.edu.db.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@SpringBootApplication
public class EmployeeApplication {

	private EmbeddedDatabase database = new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.setName("testdb")
			.addScript("fillData.sql")
			.build();

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}
}
