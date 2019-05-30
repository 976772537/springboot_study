package com.example.datasource_schame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@SpringBootApplication
public class DatasourceSchameApplication implements CommandLineRunner {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		SpringApplication.run(DatasourceSchameApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		showConnection();
		showData();
	}

	private void showData() {
		jdbcTemplate.queryForList ("SELECT * FROM FOO")
				.forEach (row-> log.info (row.toString ()));
	}

	private void showConnection() throws SQLException {
		log.info (dataSource.toString ());
		Connection con=dataSource.getConnection ();
		log.info (con.toString ());
		con.close ();
	}
}
