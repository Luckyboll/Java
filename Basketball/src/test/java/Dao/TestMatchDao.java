package Dao;

import Entity.Match;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class TestMatchDao {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    MatchDao matchDao = context.getBean("matchDaoImpl", MatchDao.class);

    @Test
    public void save() {
        Match match = new Match();
        match.setId(4);
        match.setName("NBA Finals");
        match.setNum(1);
        matchDao.save(match);
    }

    @Test
    public void findById() {
        Match retrievedMatch = matchDao.findById(1);
        System.out.println("Retrieved Match: " + retrievedMatch);
    }

    @Test
    public void update() {
        Match retrievedMatch = matchDao.findById(1);
        retrievedMatch.setName("NBA Finals Updated");
        matchDao.update(retrievedMatch);
    }

    @Test
    public void findAll() {
        List<Match> matches = matchDao.findAll();
        for (Match m : matches) {
            System.out.println("Match: " + m);
        }
    }

    @Test
    public void delete() {
        matchDao.delete(4);
    }
}
