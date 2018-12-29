package com.dsq.dsq.core.config;

import org.springframework.stereotype.Component;

import coo.core.hibernate.AbstractLocalSessionSettings;

/**
 * SessionFactory设置。
 */
@Component("com.dsq.dsq.core.config.LocalSessionSettings")
public class LocalSessionSettings extends AbstractLocalSessionSettings {
  /**
   * 构造方法。
   */
  public LocalSessionSettings() {
    addPackageToScan("com.dsq.dsq.core.entity");
  }
}
