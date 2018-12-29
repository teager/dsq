package com.dsq.dsq.task;

import java.util.Date;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import coo.base.util.DateUtils;

@Component("com.dsq.dsq.task.service.TaskScheduler")
@Lazy(false)
public class TaskScheduler {
  private final Logger log = LoggerFactory.getLogger(getClass());
  @Resource
  private ExecutorService multiTaskExecutor;

  @Scheduled(initialDelay = 1000 * 10, fixedDelay = 1000 * 60 * 30)
  public void printCurrentTime() {
    log.debug("定时任务-打印当前时间：[{}]", DateUtils.format(new Date(), DateUtils.SECOND));
  }

  @Scheduled(initialDelay = 1000 * 20, fixedDelay = 1000 * 60 * 30)
  public void executeMultiTasks() {
    for (int i = 0; i < 25; i++) {
      multiTaskExecutor.submit(new MultiTask(i));
    }
  }
}
