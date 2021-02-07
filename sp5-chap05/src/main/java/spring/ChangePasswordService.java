package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordService {

	@Autowired // 자동 주입
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
	
	// 세터 메서드를 통한 DI 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}
