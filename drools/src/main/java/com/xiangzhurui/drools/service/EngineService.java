package com.xiangzhurui.drools.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiangzhurui.drools.engine.LifeCycle;

@Service
public class EngineService implements  InitializingBean{

	@Autowired
	private LifeCycle lifeCycle;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		lifeCycle.load();
	}
	
}
