package com.aaskit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by mkalyan on 8/22/16.
 */
public class LoginDetailsDAO {
    private static final Logger logger = LoggerFactory.getLogger(LoginDetailsDAO.class);

    private JdbcTemplate template;

    private static final String INSERT_USER = "INSERT INTO USERS (EMAIL, PASSWORD, IS_VERIFIED, IS_ACTIVE, JOINED_DATE) VALUES ('%s' , '%s' , 'N', 'Y', now())";
    private static final String CHECK_USER = "SELECT EMAIL, PASSWORD FROM USERS WHERE EMAIL= '%s' AND IS_ACTIVE = 'Y'";

    public LoginDetailsDAO(JdbcTemplate template) {
        this.template = template;
    }

    public void createNewUser(String email, String password) {
        String sql = String.format(INSERT_USER, email, password);
        logger.info("Executing sql: {} ", sql);
        template.execute(sql);
    }

    public boolean isValidUser(String email, String password) {
        String sql = String.format(CHECK_USER, email);
        logger.info("Executing sql: {}", sql);
        List<Map<String, Object>> results = template.queryForList(sql);
        if(results.size() > 1) {
            logger.error("ALERT: More than one user registered with the same user id");
            return false;
        }
        if(results.get(0).get("password").equals(password)) {
            logger.error("Successfully logged in: {}", email);
            return true;
        }

        return false;
    }
}
