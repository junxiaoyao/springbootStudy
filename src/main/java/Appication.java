import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

/**
 * @Auther: jxy
 * @Date: 2019/4/2 16:54
 * @Description:
 */
@EnableAutoConfiguration
@ComponentScan("com.jxy.**")
@EnableJms
public class Appication {
    public static void main(String[] args) {
        SpringApplication.run(Appication.class,args);
    }
}
