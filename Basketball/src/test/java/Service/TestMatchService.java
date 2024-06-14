package Service;

import Entity.Match;
import Service.impl.MatchServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMatchService {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    MatchService matchService = context.getBean(MatchService.class);

    @Test
    public void save() {
        // 创建并保存 Match
        Match match = new Match();
        match.setId(4);
        match.setName("NBA Finals");
        match.setNum(0);

        // 保存 Match
        matchService.save(match);
    }

    @Test
    public void findById() {
        // 根据 ID 查找 Match
        Match retrievedMatch = matchService.findById(4);
    }

    @Test
    public void update() {
        // 更新 Match 信息
        Match retrievedMatch = matchService.findById(4);
        retrievedMatch.setName("NBA Finals Updated");
        matchService.update(retrievedMatch);
    }

    @Test
    public void findAll() {
        // 获取所有 Matches
        List<Match> matches = matchService.findAll();
        for (Match m : matches) {
            System.out.println("Match: " + m);
        }
    }

    @Test
    public void delete() {
        // 删除 Match
        matchService.delete(4);
    }

    @Test
    public void testExists() {
        boolean exists = matchService.exists(1);
        System.out.println("Match exists: " + exists);
    }

    @Test
    public void testCanDelete() {
        boolean canDelete = matchService.canDelete(1);
        System.out.println("Match can be deleted: " + canDelete);
    }
}
