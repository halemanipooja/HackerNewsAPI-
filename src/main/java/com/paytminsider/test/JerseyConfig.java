package com.paytminsider.test;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.paytminsider.test.controller.PaytmController;


@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(PaytmController.class);
	}

}
