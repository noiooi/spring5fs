package main;

import chap07.ExeTimeCalculator;
import chap07.ImpeCalculator;
import chap07.RecCalculator;

// 자바단에서 프록시 클래스(ExeTimeCalculator)를 생성해서 구현
public class MainProxy {
	
	public static void main(String[] args) {
		ExeTimeCalculator ttCal1 = new ExeTimeCalculator(new ImpeCalculator());
		System.out.println(ttCal1.factorial(20));
		
		ExeTimeCalculator ttCal2 = new ExeTimeCalculator(new RecCalculator());
		System.out.println(ttCal2.factorial(20));
	}
}
