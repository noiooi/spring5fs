package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;

	// 생성자를 통한 의존관계 주입
	public MemberRegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		
		if(member != null) {
			throw new DuplicateMemberException("duq email " + req.getEmail());
		}
		
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		memberDao.insert(member);
		return member.getId();
	}
}
