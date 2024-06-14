package Dao;

import Entity.Team;
import Entity.Match;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class TestTeamDao {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    TeamDao teamDao = (TeamDao) context.getBean("teamDaoImpl");

    @Test
    public void save() {
        Team team = new Team();
        team.setId(10);
        team.setName("Lakers");
        team.setNum(2);

        Match match = new Match();
        match.setId(1);
        team.setMatch(match);

        teamDao.save(team);
    }

    @Test
    public void findById() {
        Team retrievedTeam = teamDao.findById(1);
        System.out.println("Retrieved Team: " + retrievedTeam);
    }

    @Test
    public void update() {
        Team retrievedTeam = teamDao.findById(10);
        retrievedTeam.setName("Lakers Updated");
        teamDao.update(retrievedTeam);
    }

    @Test
    public void findAll() {
        List<Team> teams = teamDao.findAll();
        for (Team t : teams) {
            System.out.println("Team: " + t);
        }
    }

    @Test
    public void delete() {
        teamDao.delete(10);
    }
}
