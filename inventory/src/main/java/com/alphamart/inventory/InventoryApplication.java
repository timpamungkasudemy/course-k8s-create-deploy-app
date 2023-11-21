package com.alphamart.inventory;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication(exclude = { WebMvcAutoConfiguration.class })
public class InventoryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Value("${setting.inventory.report.path:./usr/reports/}")
	private String reportPath;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Generating all reports : START");

		System.out.println("Generating Inventory summary report : START");
		TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong(2, 6));
		System.out.println("Generating Inventory summary report : FINISH");

		TimeUnit.SECONDS.sleep(1);

		System.out.println("Generating Inventory movement report : START");
		TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong(2, 6));
		System.out.println("Generating Inventory movement report : FINISH");

		TimeUnit.SECONDS.sleep(1);

		System.out.println("Generating Inventory detail report : START");
		TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextLong(2, 6));
		System.out.println("Generating Inventory detail report : FINISH");

		System.out.println("Generating all reports : FINISH");

		System.out.println("Reports saved on : " + reportPath);

		System.exit(0);
	}

}
