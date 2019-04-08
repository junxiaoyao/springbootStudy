import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: jxy
 * @Date: 2019/4/2 16:54
 * @Description:
 */
//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.jxy.*")
public class Appication {
    public static void main(String[] args) {
        SpringApplication.run(Appication.class,args);
    }
}
