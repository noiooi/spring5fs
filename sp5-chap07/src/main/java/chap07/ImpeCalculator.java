package chap07;

public class ImpeCalculator implements Calculator {
	// for문(반복문)을 통한 factorial 구현
	@Override
	public long factorial(long num) {
		// 수행 시간 확인
		long start = System.currentTimeMillis();
		long result = 1;
		for (long i = 1; i <= num; i++) {
			result *= i;
		}
//		long end = System.currentTimeMillis();
//		System.out.printf("ImpeCalculator.factorial(%d) 실행 시간 = %d\n", num, (end-start));
		return result;
	}

}
