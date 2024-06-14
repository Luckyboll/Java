import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcTest {
    @Test
    public void jdbc(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:jdbc.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("JdbcTemplate", JdbcTemplate.class);
        jdbcTemplate.update("insert into student value('24', 'stu10', '22', '1')");
    }
}
