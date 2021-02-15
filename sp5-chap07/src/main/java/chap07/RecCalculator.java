package chap07;

public class RecCalculator implements Calculator {
	// 재귀를 통한 factorial 구현
	@Override
	public long factorial(long num) {
		// 수행 시간 확인
		long start = System.currentTimeMillis();
		
		try {
			if (num == 0) {
				return 1;
			} else {
				return num * factorial(num - 1);
			}
		} finally {
			// 재귀함수인 경우 여러 번 호출되므로 매 함수마다 수행시간 출력
//			long end = System.currentTimeMillis();
//			System.out.printf("재귀 RecCalculator.factorial(%d) 실행 시간 = %d\n", num, (end-start));
		}
		
	}

}
