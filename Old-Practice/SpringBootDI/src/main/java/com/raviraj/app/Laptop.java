package com.raviraj.app;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

	@Override
	public void compile() {
		System.out.println("Compiled by using Laptop");
	}

}
