package com.dsq.dsq.core.service;

import org.springframework.stereotype.Service;

import com.dsq.dsq.core.entity.BnLog;
import coo.core.security.service.AbstractBnLogger;

/**
 * 业务日志组件。
 */
@Service
public class BnLogger extends AbstractBnLogger<BnLog> {
  @Override
  public BnLog newBnLog() {
    return new BnLog();
  }
}
