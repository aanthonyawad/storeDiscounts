package com.store.discounts.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Properties {
	
	@Value("${executor.threads}")
	private int executorThreads;

	public int getExecutorThreads() {
		return executorThreads;
	} 
}
