package aspect;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1) // 어떤 Aspect가 먼저 적용될지는 스프링 프레임워크나 자바 버전에 따라 달라질 수 있기 때문에 순서 지정
public class CacheAspect {

	private Map<Long, Object> cache = new HashMap<>();
	
	// Pointcut 재사용을 위한 함수 명시
	// Pointcut 재사용을 하지 않는다면 @Around에서 execution 명시자를 이용하여 직접 명시 가능
	// @Around("execution(public * chap07..*(long))")
	@Pointcut("execution(public * chap07..*(long))")
	public void cacheTarget() {
		
	}
	
	@Around("cacheTarget()")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		Long num = (Long) joinPoint.getArgs()[0];
		if (cache.containsKey(num)) {
			System.out.printf("CacheAspect: Cache에서 구함[%d]\n", num);
			return cache.get(num);
		}
		
		Object result = joinPoint.proceed(); // 프록시 대상 객체 실행
		cache.put(num, result);
		System.out.printf("CacheAspect: Cache에 추가[%d]\n", num);
		return result;
	}
}
