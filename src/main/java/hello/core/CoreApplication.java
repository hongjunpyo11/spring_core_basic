package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	/**
	 * 스프링으로 전환하기
	 * 지금까지 순수한 자바 코드만으로 DI를 적용했다. 이제 스프링을 사용해보자.
	 */
}
