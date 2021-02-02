import com.sunyue.shiro.SpringShiroApplication;
import com.sunyue.shiro.server.TestUserServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringShiroApplication.class)
class ShiroTest {
    @Autowired
    private TestUserServer testUserServer;

    @Test
    void contextLoads() {
        testUserServer.selectAll().forEach(testUser -> System.out.println(testUser.getUsername()));
    }

}
