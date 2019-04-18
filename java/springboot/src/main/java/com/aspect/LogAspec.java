package com.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

//@Aspect
public class LogAspec {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(LogAspec.class);
}
