package chap07;

public class ExeTimeCalculator implements Calculator{
	// 프록시 객체를 사용하여 수행 시간 확인
	
	private Calculator delegate;
	
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		long start = System.nanoTime();
		long result = delegate.factorial(num); // 다른 객체에 factorial 실행 위임
		long end = System.nanoTime();
		
		System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end-start));
		return result;
	}

}
