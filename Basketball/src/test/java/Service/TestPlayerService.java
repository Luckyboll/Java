package Service;

import Entity.Player;
import Entity.Team;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPlayerService {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    PlayerService playerService = context.getBean(PlayerService.class);

    @Test
    public void save() {
        // 创建并保存Player
        Player player = new Player();
        player.setId(28);
        player.setName("LeBron James");
        player.setAge(36);
        Team team = new Team();
        team.setId(1);
        player.setTeam(team);
        playerService.save(player);
    }

    @Test
    public void findById() {
        // 获取Player
        Player retrievedPlayer = playerService.findById(1);
//        System.out.println("Retrieved Player: " + retrievedPlayer);
    }

    @Test
    public void update() {
        Player retrievedPlayer = playerService.findById(28);
        // 更新Player
        retrievedPlayer.setName("LeBron James Updated");
        playerService.update(retrievedPlayer);
    }

    @Test
    public void findAll() {
        // 获取所有Players
        List<Player> players = playerService.findAll();
//        for (Player p : players) {
//            System.out.println("Player: " + p);
//        }
    }

    @Test
    public void delete() {
        // 删除Player
        playerService.delete(28);
    }

    @Test
    public void testExists() {
        boolean exists = playerService.exists(1);
        System.out.println("Player exists: " + exists);
    }

    @Test
    public void testCanDelete() {
        boolean canDelete = playerService.canDelete(1);
        System.out.println("Player can be deleted: " + canDelete);
    }
}
