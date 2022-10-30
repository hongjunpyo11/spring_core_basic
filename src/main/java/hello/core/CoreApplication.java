package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	/**
	 * 참고: 프로젝트 환경설정을 편리하게 하려고 스프링 부트를 사용한 것이다.
	 * 지금은 스프링이 없는 순수한 자바로만 개발을 진행한다는 점을 꼭 기억하자
	 * 스프링 관련은 한참 뒤에 등장한다!!
	 */
}
