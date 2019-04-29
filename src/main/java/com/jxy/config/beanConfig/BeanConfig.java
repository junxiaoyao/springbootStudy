package com.jxy.config.beanConfig;

import com.jxy.controller.HomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 定义一些bean
 * @Author: jxy
 * @Email: 1162474984@qq.com
 * @CreateDate: 2019/4/26 9:35
 * @UpdateUser: jxy
 * @Version: 1.0
 */
@Configuration
@ComponentScan("com.jxy.controller")
public class BeanConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MBeanExporter mBeanExporter(HomeController homeController,MethodNameBasedMBeanInfoAssembler assembler) {
        MBeanExporter exporter = new MBeanExporter();
        Map<String, Object> beans = new HashMap<>();
        beans.put("my:name=HomeController", homeController);
        exporter.setAssembler(assembler);
        exporter.setBeans(beans);
        return exporter;
    }
    @Bean
    public MethodNameBasedMBeanInfoAssembler mBeanInfoAssembler(){
        MethodNameBasedMBeanInfoAssembler assembler=new MethodNameBasedMBeanInfoAssembler();
        assembler.setManagedMethods(new String[]{"getSize","setSize"});
        return assembler;
    }

}
