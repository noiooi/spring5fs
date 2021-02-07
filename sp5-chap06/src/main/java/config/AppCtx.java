package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.Client;
import spring.Client2;

@Configuration // 스프링 설정 클래스
public class AppCtx {
	
	@Bean // 기본적으로 싱글톤 범위의 빈 설정
	@Scope("singleton") // 명시적으로 지정도 가능
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		return client;
	}
	
//	@Bean
//	@Scope("prototype") // 프로토타입 범위의 빈 설정
//	public Client client() {
//		Client client = new Client();
//		client.setHost("host");
//		return client;
//	}
//	
	// 초기화, 소멸 메서드 지정
	@Bean(initMethod = "connect", destroyMethod = "close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}
}
