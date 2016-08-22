package com.aaskit.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by mkalyan on 8/22/16.
 */
public class PostingDAO {
    private static final Logger logger = LoggerFactory.getLogger(PostingDAO.class);

    private JdbcTemplate template;

    private static final String INSERT_EMPLOYER = "INSERT INTO JOB_POSTINGS (EMPLOYER_ID, LOCATION, JOB_DESCRIPTION, PRIMARY_SKILLS, POSTING_DATE) " +
            "VALUES ('%d' , '%s' , '%s', '%s', now())";

    private static final String FIND_ALL_POSTINGS = "SELECT EMPLOYER_ID, LOCATION, JOB_DESCRIPTION, PRIMARY_SKILLS, POSTING_DATE FROM JOB_POSTINGS";

    public PostingDAO(JdbcTemplate template) {
        this.template = template;
    }

    public void createNewPosting(Integer employerId, String location, String jobDescription, String primarySkills) {
        String sql = String.format(INSERT_EMPLOYER, employerId, location, jobDescription, primarySkills);
        logger.info("Executing sql: {} ", sql);
        template.execute(sql);
    }

    public List<Map<String,Object>> findAllPostings() {
        return template.queryForList(FIND_ALL_POSTINGS);
    }
}
