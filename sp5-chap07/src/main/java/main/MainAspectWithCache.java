package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import chap07.Calculator;
import config.AppCtxWithCache;

public class MainAspectWithCache {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtxWithCache.class);

		// cal 빈은 CacheAspect 프록시 객체
		Calculator cal = ctx.getBean("calculator", Calculator.class);
		cal.factorial(7); // CacheAspect 실행 -> ExeTimeAspect 실행 -> 대상 객체 실행
		cal.factorial(7);
		cal.factorial(5);
		cal.factorial(5);
		
		ctx.close();
	}
}
