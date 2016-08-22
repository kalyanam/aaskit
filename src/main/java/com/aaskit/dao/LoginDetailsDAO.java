package com.aaskit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by mkalyan on 8/22/16.
 */
public class LoginDetailsDAO {
    private static final Logger logger = LoggerFactory.getLogger(LoginDetailsDAO.class);

    private JdbcTemplate template;

    private static final String INSERT_USER = "INSERT INTO USERS (EMAIL, PASSWORD, IS_VERIFIED, IS_ACTIVE, JOINED_DATE) VALUES ('%s' , '%s' , 'N', 'Y', now())";

    public LoginDetailsDAO(JdbcTemplate template) {
        this.template = template;
    }

    public void createNewUser(String email, String password) {
        String sql = String.format(INSERT_USER, email, password);
        logger.info("Executing sql: {} ", sql);
        template.execute(sql);
    }
}
