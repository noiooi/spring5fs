package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import chap07.RecCalculator;
import config.AppCtx;

// 스프링이 생성한 프록시를 이용하여 AOP 구현
public class MainAspect {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		// "calculator" 빈의 실제 타입은 Calculator를 상속한 프록시 타입이므로 RecCalculator로 타입 변환을 할 수 없기 때문에 익셉션 발생
		// RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName()); // 스프링이 생성한 프록시 객체 출력
		ctx.close();
	}
}
