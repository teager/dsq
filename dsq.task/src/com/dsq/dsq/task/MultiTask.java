package com.dsq.dsq.task;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 多线程任务。
 */
public class MultiTask implements Callable<Void> {
  private final Logger log = LoggerFactory.getLogger(getClass());
  private Integer ordinal;

  /**
   * 构造方法。
   * 
   * @param ordinal 任务序号
   */
  public MultiTask(Integer ordinal) {
    this.ordinal = ordinal;
  }

  @Override
  public Void call() throws Exception {
    try {
      Thread.sleep(1000);
      log.debug("任务[{}]已执行。", ordinal);
    } catch (Exception e) {
      log.error("执行任务[" + ordinal + "]时发生异常。", e);
    }
    return null;
  }
}
