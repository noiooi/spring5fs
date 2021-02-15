package aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect // Aspect(공통 기능)로 사용할 클래스 지정
@Order(2)
public class ExeTimeAspect {

	// 공통 기능을 적용할 대상을 설정
	// Pointcut 재사용을 위한 함수 명시
	// Pointcut 재사용을 하지 않는다면 @Around에서 execution 명시자를 이용하여 직접 명시 가능
	// @Around("execution(public * chap07..*(long))")
	// execution 명시자는 Advice를 적용할 메서드를 지정할 때 사용
	@Pointcut("execution(public * chap07..*(..))") 
	private void publicTarget() {
		
	}

	// publicTarget() 메서드에 정의한 Pointcut에 공통 기능을 적용
	// 즉, chap07 패키지나 그 하위 패키지에 속한 빈 객체의 public 메서드에 @Around가 붙은 measure() 메서드를 적용
	@Around("publicTarget()") 
	// ProceedingJoinPoint 파라미터는 프록시 대상 객체의 메서드를 호출할 때 사용
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed(); // 실제 대상 객체의 메서드를 호출
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature(); // 호출되는 메서드에 대한 정보를 구함
			// 대상 객체 이름, 메서드 이름, 메서드 파라미터를 구함
			System.out.printf("%s.%s(%s) 실행 시간 : %d ns \n", joinPoint.getTarget().getClass().getSimpleName(), 
					sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish-start));
		}
	}
}
