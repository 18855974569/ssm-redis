package com.wjx.aspect.impl;

/**
 * 测试Services
 */
import org.springframework.stereotype.Service;

import com.wjx.aspect.HelloWorld;

@Service
public class HelloWorldImpl implements HelloWorld {
	public void printHelloWorld() {
		System.out.println("Enter HelloWorldImpl.printHelloWorld()");
	}

	public void doPrint() {
		System.out.println("Enter HelloWorldImpl.doPrint()");
	}

	public String getReturn() {
		String str = "HelloWorldImpl";
		System.out.println("Enter HelloWorldImpl.getReturn()");
		return str;
	}
}
