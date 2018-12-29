package com.dsq.dsq.api.config;

import org.springframework.stereotype.Component;

import coo.mvc.config.AbstractFreeMarkerSettings;

/**
 * FreeMarker设置组件。
 */
@Component("com.dsq.dsq.api.config.FreeMarkerSettings")
public class FreeMarkerSettings extends AbstractFreeMarkerSettings {
  /**
   * 构造方法。
   */
  public FreeMarkerSettings() {
    setOrder(100);
    addTemplatePath("classpath:/com/dsq/dsq/api/actions/");
  }
}
