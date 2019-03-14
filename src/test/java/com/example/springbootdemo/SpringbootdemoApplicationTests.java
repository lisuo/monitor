package com.example.monitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) throws UnsupportedEncodingException{
		// 编码
		String encode = Base64.getEncoder().encodeToString("So".getBytes("UTF-8"));
		System.out.println(encode);
		// 解码
		byte[] decode = Base64.getDecoder().decode(encode);
		System.out.println(new String(decode, "UTF-8"));

	}
}
