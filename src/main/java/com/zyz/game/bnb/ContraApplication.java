package com.zyz.game.bnb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ContraApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ContraApplication.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
        GameFrame gf = context.getBean(GameFrame.class);

        while (true) {
            Thread.sleep(1000 / 60);
            gf.repaint();
        }
//        SpringApplication.run(ContraApplication.class, args);
    }

}
