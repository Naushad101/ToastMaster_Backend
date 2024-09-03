package com.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ToastMasterAppApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context=SpringApplication.run(ToastMasterAppApplication.class, args);
		// Thread.sleep(30000);
        // context.close();
	}

}
