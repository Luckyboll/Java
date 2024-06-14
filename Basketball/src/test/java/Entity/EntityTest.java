package Entity;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EntityTest {
    @Test
    public void player_test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Match match = (Match) context.getBean("match");
        Player player = (Player) context.getBean("player1");
        Team team = (Team) context.getBean("team");
        System.out.println(match);
        System.out.println(player);
        System.out.println(team);
    }
}
