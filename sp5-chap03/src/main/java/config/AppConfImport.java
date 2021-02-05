package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

@Configuration // 스프링 설정 클래스
@Import(AppConf2.class) // 함께 사용할 설정 클래스 지정
public class AppConfImport {
	
	@Bean // 해당 메서드가 생성한 객체를 스프링 빈으로 설정
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
}
