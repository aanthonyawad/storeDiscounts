package com.store.discounts.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.discounts.configurations.Properties;
import com.store.discounts.jpa.model.Customer;
import com.store.discounts.jpa.model.CustomerBillHistory;
import com.store.discounts.jpa.model.Item;
import com.store.discounts.jpa.repository.CustomerBillHistoryRepository;
import com.store.discounts.jpa.repository.CustomerRepository;
import com.store.discounts.jpa.repository.ItemRepository;
import com.store.discounts.models.FinishedTask;
import com.store.discounts.models.LoggingTask;
import com.store.discounts.service.LogService;
import com.store.discounts.utils.Constants;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private Properties properties;
	private ExecutorService executor;
	private Set<Future<FinishedTask>> setExecutions;
	private Object syncho;

	@Autowired
	private CustomerBillHistoryRepository customerbillHistoryRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	void construct() {
		this.executor = Executors.newFixedThreadPool(properties.getExecutorThreads());
		this.syncho = new Object();
		this.setExecutions = new HashSet<>();
		this.startListener();
	}

	private void startListener() {
		// run the thread that will listen to finished events
		this.executor.execute(() -> {
			while (true) {
				synchronized (syncho) {
					if (!this.setExecutions.isEmpty()) {
						Iterator<Future<FinishedTask>> it = this.setExecutions.iterator();
						// get finished events
						while (it.hasNext()) {
							Future<FinishedTask> task = it.next();
							// remove cancelled items from list
							if (task.isCancelled()) {
								it.remove();
								continue;
							}
							// work on finished items
							if (task.isDone()) {
								try {
									FinishedTask result = task.get();
									if (result.getTaskType() == Constants.LOG_BILL) {
										LoggingTask lt = (LoggingTask) result;
										Customer customer = customerRepository.save(lt.getCustomer());
										for (Item item : lt.getItems()) {
											Item savedItem = itemRepository.save(item);
											CustomerBillHistory cbh = new CustomerBillHistory();
											cbh.setCustomer(customer);
											cbh.setItem(savedItem);
											customerbillHistoryRepository.save(cbh);
										}
									}
									it.remove();
								} catch (Exception e) {
									e.printStackTrace();
									it.remove();
								}
							}
						}
					}
				}
			}
		});
	}

	@Override
	public ExecutorService getExecutor() {
		return executor;
	}

	@Override
	public void addExecutor(Future<FinishedTask> item) {
		synchronized (syncho) {
			this.setExecutions.add(item);
		}
	}

}
