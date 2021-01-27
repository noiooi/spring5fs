package spring;

import java.util.HashMap;
import java.util.Map;

// 스프링을 이용해서 DB를 연동하는 법을 배우지 않았기 때문에 자바 Map을 통해 구현
public class MemberDao {
	
	private static long nextId = 0;
	
	private Map<String, Member> map = new HashMap<>();
	
	public Member selectByEmail(String email) {
		return map.get(email);
	}
	
	public void insert(Member member) {
		member.setId(nextId++);
		map.put(member.getEmail(), member);
	}
	
	public void update(Member member) {
		map.put(member.getEmail(), member);
	}
}
