package spring;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRegisterService {
	@Autowired
	private MemberDao memberDao;

	// 빈 생성자, @Autowired로 자동 주입을 해주므로 생성자를 통한 주입을 하지 않아도 됨
	// 설정 클래스에서 기본 생성자를 통해 객체를 생성하기 때문에 생성
	public MemberRegisterService() {
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException("duq email " + req.getEmail());
		}
		
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(newMember);
		return newMember.getId();
	}
}
