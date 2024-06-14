import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
@Aspect
public class TransactionMangerHandler {
    @Pointcut("execution(public void Dao.impl.PlayerDaoImpl.save())")
    public void pointcut(){}
    @Before("pointcut()")
    public void begin(){
        System.out.println("开启事务");
    }
    @AfterReturning("pointcut()")
    public void commit(){
        System.out.println("提交事务");
    }
    @AfterThrowing("pointcut()")
    public void rollback(){
        System.out.println("回滚事务");
    }
    @After("pointcut()")
    public void closeSession(){
        System.out.println("关闭连接");
    }
}
