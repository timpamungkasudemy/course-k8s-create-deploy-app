package com.alphamart.inventory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryRestController {

	@Value("${setting.inventory.dashboard.url:UNDEFINED}")
	private String dashboardUrl;

	@Value("${setting.inventory.dashboard.status:UNDEFINED}")
	private String dashboardStatus;

	@GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	String hello() {
		return "Hello from inventory application";
	}

	@GetMapping(value = "/dashboard/info", produces = MediaType.TEXT_PLAIN_VALUE)
	String dashboardInfo() {
		return String.format("The dashboard URL is on %s and current status is %s", dashboardUrl, dashboardStatus);
	}

}
