package com.aaskit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by mkalyan on 8/22/16.
 */
public class EmployerDAO {
    private static final Logger logger = LoggerFactory.getLogger(EmployerDAO.class);

    private JdbcTemplate template;

    private static final String INSERT_EMPLOYER = "INSERT INTO EMPLOYER (NAME, LOCATION, WEBSITE, IS_ACTIVE, JOINED_DATE) VALUES ('%s' , '%s' , '%s', 'Y', now())";
    private static final String FIND_ALL_EMPLOYERS = "SELECT NAME, LOCATION, WEBSITE, IS_ACTIVE, JOINED_DATE FROM EMPLOYER";

    public EmployerDAO(JdbcTemplate template) {
        this.template = template;
    }

    public void createNewEmployer(String name, String location, String website) {
        String sql = String.format(INSERT_EMPLOYER, name, location, website);
        logger.info("Executing sql: {} ", sql);
        template.execute(sql);
    }

    public List<Map<String,Object>> findAllEmployers() {
        return template.queryForList(FIND_ALL_EMPLOYERS);
    }

}
