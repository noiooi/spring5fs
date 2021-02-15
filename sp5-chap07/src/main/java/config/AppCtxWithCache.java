package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.CacheAspect;
import aspect.ExeTimeAspect;
import chap07.Calculator;
import chap07.RecCalculator;

@Configuration
@EnableAspectJAutoProxy // @Aspect 애노테이션을 붙인 클래스를 공통 기능으로 적용
public class AppCtxWithCache {

	// 캐시 구현 공통 기능
	@Bean
	public CacheAspect cacheAspect() {
		return new CacheAspect();
	}
	
	// 실행 시간 측정 공통 기능
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}

	// AOP 적용시 RecCalculator가 상속받은 Calculator 인터페이스를 이용해서 프록시 생성
	@Bean
	public Calculator calculator() {
		return new RecCalculator();
	}
}
