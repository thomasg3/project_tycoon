package be.projecttycoon.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by kiwi on 9/02/2016.
 */


@Configuration
public class SpringRootConfig {

    @Autowired
    DataSource dataSource;

    
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @PostConstruct
    public void startDBManager() {

        //hsqldb
        //DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:tycoondb", "--user", "sa", "--password", "" });


    }
}
