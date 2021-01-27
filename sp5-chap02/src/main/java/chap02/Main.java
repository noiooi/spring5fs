package chap02;

// 자바 설정에서 정보를 읽어와 빈 객체를 생성하고 관리함
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// AppContext 클래스를 생성자 파라미터로 전달, AppContext에 정의한 @Bean 설정 정보를 읽어오고 Bean 객체를 생성하고 그 객체를 내부에 보관함
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		// bean 객체를 검색 및 제공, getBean(빈 객체 이름, 빈 객체 타입)
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greeter("스프링");
		
		System.out.println(msg);
		ctx.close();
	}
}
