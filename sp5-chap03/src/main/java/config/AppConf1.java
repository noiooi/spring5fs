package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration // 스프링 설정 클래스
public class AppConf1 {
	
	@Bean // 해당 메서드가 생성한 객체를 스프링 빈으로 설정
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}

}
