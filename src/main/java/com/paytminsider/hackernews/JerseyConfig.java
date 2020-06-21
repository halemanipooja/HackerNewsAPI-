package com.paytminsider.hackernews;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.paytminsider.hackernews.controller.*;


@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(PaytmController.class);
	}

}
