package Dao;

import Entity.Player;
import Entity.Team;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;


public class TestPlayerDao {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    PlayerDao playerDao = (PlayerDao) context.getBean("playerDaoImpl");
    @Test
    public void save() {
        // 创建和保存Player
        Player player = new Player();
        player.setId(28);
        player.setName("LeBron James");
        player.setAge(36);

        Team team = new Team();
        team.setId(1);
        player.setTeam(team);

        playerDao.save(player);
    }
    @Test
    public void findById() {
        // 获取Player
        Player retrievedPlayer = playerDao.findById(1);
        System.out.println("Retrieved Player: " + retrievedPlayer);
    }
    @Test
    public void update() {
        Player retrievedPlayer = playerDao.findById(28);
        // 更新Player
        retrievedPlayer.setName("LeBron James Updated");
        playerDao.update(retrievedPlayer);
    }
    @Test
    public void findAll() {
        // 获取所有Players
        List<Player> players = playerDao.findAll();
        for (Player p : players) {
            System.out.println("Player: " + p);
        }
    }
    @Test
    public void delete(){
        // 删除Player
        playerDao.delete(28);
    }

}
