package com.store.discounts.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.store.discounts.models.FinishedTask;

public interface LogService {
	ExecutorService getExecutor();

	void addExecutor(Future<FinishedTask> item);
}
