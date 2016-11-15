package com.coawesome;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrRWebApplicationTests {

	@Test
	public void stringBufferTest() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("apple");
		System.out.println(stringBuffer.reverse());
		stringBuffer.append("<-> apple");
		System.out.println(stringBuffer);
	}

}
