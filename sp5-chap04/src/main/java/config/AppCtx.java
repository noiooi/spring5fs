package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
public class AppCtx {
	
	@Bean // 해당 메서드가 생성한 객체를 스프링 빈으로 설정
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		// 자동 주입 기능을 사용하였기 때문에 세터 메소드를 통한 주입을 하지 않아도 됨
		// ChangePasswordService pwdSvc = new ChangePasswordService();
		// pwdSvc.setMemberDao(memberDao());
		return new ChangePasswordService();
	}
	
//	@Bean
//	public MemberPrinter memberPrinter() {
//		return new MemberPrinter();
//	}
	
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
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		// 자동 주입 기능을 사용하였기 때문에 세터 메소드를 통한 주입을 하지 않아도 됨
		// MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		// infoPrinter.setMemberDao(memberDao());
		// infoPrinter.setPrinter(memberPrinter());
		return new MemberInfoPrinter();
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
