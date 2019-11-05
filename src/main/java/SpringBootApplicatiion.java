import com.tan.controller.SpringBootFirstApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
public class SpringBootApplicatiion {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootFirstApplication.class, args);

    }


}
