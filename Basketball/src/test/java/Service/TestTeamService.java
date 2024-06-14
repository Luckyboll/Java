package Service;

import Entity.Match;
import Entity.Team;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestTeamService {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    TeamService teamService = (TeamService) context.getBean("teamServiceImpl");

    @Test
    public void save() {
        // 创建并保存 Team
        Team team = new Team();
        team.setId(10);
        team.setName("Los Angeles Lakers");
        team.setNum(0);
        Match match = new Match();
        match.setId(1);
        team.setMatch(match);

        // 保存 Team
        teamService.save(team);
    }

    @Test
    public void findById() {
        // 根据 ID 查找 Team
        Team retrievedTeam = teamService.findById(1);
    }

    @Test
    public void update() {
        // 更新 Team 信息
        Team retrievedTeam = teamService.findById(10);
        retrievedTeam.setName("Los Angeles Lakers Updated");
        teamService.update(retrievedTeam);
    }

    @Test
    public void findAll() {
        // 获取所有 Teams
        List<Team> teams = teamService.findAll();
    }

    @Test
    public void delete() {
        // 删除 Team
        teamService.delete(10);
    }

    @Test
    public void testExists() {
        boolean exists = teamService.exists(1);
        System.out.println("Team exists: " + exists);
    }

    @Test
    public void testCanDelete() {
        boolean canDelete = teamService.canDelete(1);
        System.out.println("Team can be deleted: " + canDelete);
    }
}
