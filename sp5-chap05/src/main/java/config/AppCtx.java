package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.VersionPrinter;

@Configuration // 스프링 설정 클래스
//@Component 애노테이션을 붙인 클래스를 스캔해서 스프링 빈으로 등록
// basePackages 속성값으로 스캔 대상 패키지 목록 지정
@ComponentScan(basePackages = {"spring"}) 
public class AppCtx {
	
	@Bean
	@Qualifier("printer") 
	// 타입이 같은 빈이 여러 개인 경우, 자동 주입할 빈을 지정
	// 빈의 한정 값으로 "printer" 지정
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter") 
	// 타입이 같은 빈이 여러 개인 경우, 자동 주입할 빈을 지정
	// 빈의 한정 값으로 "summaryPrinter" 지정
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
