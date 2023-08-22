package com.springMVC.app;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST) //500 -> 400
class MyException extends RuntimeException{
	public MyException(String msg) {
		super(msg);
		System.out.println(msg);
	}
	public MyException() {
		this("");
	}
}

@Controller
public class ExceptionController2 {

	@RequestMapping("/ex3")
	public String main() throws Exception {
		throw new FileNotFoundException("���ܰ� �߻��Ͽ����ϴ�.");
	}
	
	@RequestMapping("/ex4")
	public String main2() throws Exception {
		throw new MyException("���ܰ� �߻��Ͽ����ϴ�.");
	}
	
}


