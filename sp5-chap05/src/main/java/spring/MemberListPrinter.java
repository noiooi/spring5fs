package spring;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("listPrinter")
public class MemberListPrinter {

	private MemberDao memberDao;
	private MemberPrinter printer;
	
	// 빈 생성자, @Autowired로 자동 주입을 해주므로 생성자를 통한 주입을 하지 않아도 됨
	// 설정 클래스에서 기본 생성자를 통해 객체를 생성하기 때문에 생성
	public MemberListPrinter() {
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	// 타입이 같은 빈이 여러 개인 경우, 자동 주입할 빈을 지정
	// 방법 1. Qualifier 애노테이션을 통해 빈의 한정 값으로 "printer" 지정
	// 방법 2. 상속 관계인 경우, 명확한 부모 / 자식 클래스 지정
	@Autowired
	@Qualifier("summaryPrinter") 
	public void setMemberPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
