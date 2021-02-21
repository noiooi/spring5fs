package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberRegisterService;
import spring.MemberPrinter;
import spring.MemberListPrinter;
import spring.VersionPrinter;

@Configuration // 스프링 설정 클래스
public class AppCtx {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2); // 커넥션 풀 초기화 개수 지정
		ds.setMaxActive(10); // 커넥션 풀에서 가져올 수 있는 최대 커넥션 개수 지정
//		ds.setTestWhileIdle(true); // 커넥션이 풀에 유휴 상태(커넥션 반환, idle)로 있는 동안에 검사할지 유무
//		ds.setMinEvictableIdleTimeMillis(60000 * 3); // 커넥션 풀에 유휴 상태로 유지할 최소 시간을 밀리초 단위로 지정, 3분
//		ds.setTimeBetweenEvictionRunsMillis(10 * 1000); // 커넥션 풀의 유휴 커넥션을 검사할 주기를 밀리초 단위로 지정, 10초 주기
		return ds;
	}
	
	@Bean // 해당 메서드가 생성한 객체를 스프링 빈으로 설정
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
