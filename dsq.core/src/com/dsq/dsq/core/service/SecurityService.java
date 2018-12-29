package com.dsq.dsq.core.service;

import org.springframework.stereotype.Service;

import com.dsq.dsq.core.entity.Actor;
import com.dsq.dsq.core.entity.Organ;
import com.dsq.dsq.core.entity.Role;
import com.dsq.dsq.core.entity.User;
import coo.core.security.service.AbstractSecurityService;

/**
 * 安全服务。
 */
@Service
public class SecurityService extends AbstractSecurityService<Organ, User, Role, Actor> {
}
