package com.auto.myte;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import com.auto.myte.config.PropertiesConfig;

@SpringBootApplication
@ImportResource({"classpath:beans.xml"})
@ServletComponentScan
@EnableCaching
@EnableConfigurationProperties(PropertiesConfig.class)
public class TomcatApplication extends SpringBootServletInitializer {
    private static Logger  logger = LoggerFactory.getLogger(TomcatApplication.class);

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("ServletContext initialized!!");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                logger.info("ServletContext destroyed!!");
            }

        };
    }

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TomcatApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TomcatApplication.class, args);
    }
}
