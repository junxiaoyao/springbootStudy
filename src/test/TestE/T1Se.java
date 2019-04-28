package TestE;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.Executors;

public class T1Se {
    public static void main(String[] args) {
        String password = "leo_epam";
        System.out.println(password + ": encrypt");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String p1= passwordEncoder.encode(password);
        String p2= passwordEncoder.encode(password);
        System.out.println(passwordEncoder.matches(p1,p2));
        Executors.newFixedThreadPool(2);

        int i = 0;
//        while(i < 10){
//            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//            String hashedPassword = passwordEncoder.encode(password);
//            System.out.println("encryptPassword:" + hashedPassword);
//            System.out.println("match result:" + passwordEncoder.matches(password,hashedPassword));
//            i ++;
//        }
    }

}
