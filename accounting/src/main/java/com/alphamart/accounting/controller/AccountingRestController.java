package com.alphamart.accounting.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountingRestController {

	@Value("${setting.accounting.dashboard.url:UNDEFINED}")
	private String dashboardUrl;

	@Value("${setting.accounting.dashboard.status:UNDEFINED}")
	private String dashboardStatus;

	@GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
	String hello() {
		return "Hello from accounting application";
	}

	@GetMapping(value = "/dashboard/info", produces = MediaType.TEXT_PLAIN_VALUE)
	String dashboardInfo() {
		return String.format("The accounting dashboard URL is on %s and current status is %s", dashboardUrl,
				dashboardStatus);
	}

}
