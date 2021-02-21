package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

// 스프링을 이용해서 DB를 연동하는 방법
public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		// RowMapper 임의 클래스 사용
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where EMAIL = ?",
				//쿼리 실행 결과를 한 행씩 자바 객체로 변환
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("EMAIL"),
								rs.getString("PASSWORD"),
								rs.getString("NAME"),
								rs.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(rs.getLong("ID"));
						return member;
					}
				}, 
				email); // 인덱스 파라미터(물음표)에 들어갈 값
		
		// 람다 사용
//		List<Member> results = jdbcTemplate.query(
//				"select * from MEMBER where EMAIL = ?",
//				(ResultSet rs, int rowNum) -> {
//					Member member = new Member(
//							rs.getString("EMAIL"),
//							rs.getString("PASSWORD"),
//							rs.getString("NAME"),
//							rs.getTimestamp("REGDATE").toLocalDateTime());
//					member.setId(rs.getLong("ID"));
//					return member;
//				}, 
//				email);

		return results.isEmpty() ? null : results.get(0);
	}
	
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from MEMBER",
				(ResultSet rs, int rowNum) -> {
					Member member = new Member(
							rs.getString("EMAIL"),
							rs.getString("PASSWORD"),
							rs.getString("NAME"),
							rs.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(rs.getLong("ID"));
					return member;
				});
		return results;
	}
	
	public int count() {
		// queryForObject는 전체 결과가 1행인 경우 사용
		Integer count = jdbcTemplate.queryForObject(
				"select count(*) from MEMBER", Integer.class);
		return count;
	}
	
	public void update(Member member) {
		jdbcTemplate.update(
				"update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?",
				member.getName(), member.getPassword(), member.getEmail());
	}
	
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder(); // 자동으로 생성된 키값을 구할 수 있음
		// PreparedStatementCreator를 이용한 쿼리 실행
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			@Override
//			public PreparedStatement createPreparedStatement(Connection con)
//					throws SQLException {
//				// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
//				PreparedStatement pstmt = con.prepareStatement(
//						"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
//						"values (?, ?, ?, ?)",
//						new String[] { "ID" }); // 자동 생성되는 키 칼럼 목록을 지정
//				// 인덱스 파라미터 값 설정
//				pstmt.setString(1, member.getEmail());
//				pstmt.setString(2, member.getPassword());
//				pstmt.setString(3, member.getName());
//				pstmt.setTimestamp(4,
//						Timestamp.valueOf(member.getRegisterDateTime()));
//				// 생성한 PreparedStatement 객체 리턴
//				return pstmt;
//			}
//		}, keyHolder);
		
		// 람다 사용
		jdbcTemplate.update((Connection con) -> {
			// 파라미터로 전달받은 Connection을 이용해서 PreparedStatement 생성
			PreparedStatement pstmt = con.prepareStatement(
					"insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) " +
					"values (?, ?, ?, ?)",
					new String[] { "ID" }); // 자동 생성되는 키 칼럼 목록을 지정
			// 인덱스 파라미터 값 설정
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4,
					Timestamp.valueOf(member.getRegisterDateTime()));
			// 생성한 PreparedStatement 객체 리턴
			return pstmt;
		}, keyHolder);
		
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
}
