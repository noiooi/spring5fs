package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MemberInfoPrinter {
	
	private MemberDao memberDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memberDao.selectByEmail(email);
		
		if(member == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	
	// 메소드에도 @Autowired 애노테이션 사용 가능
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	@Autowired
	@Qualifier("printer") 
	// 타입이 같은 빈이 여러 개인 경우, 자동 주입할 빈을 지정
	// 빈의 한정 값으로 "printer" 지정
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
	
}
