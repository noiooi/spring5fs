package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.WrongIdPasswordException;

/* 자체적인 Assembler */
import assembler.Assembler;

public class MainForAssembler {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // System.in을 이용한 콘솔 입력
		
		while(true) {
			System.out.println("명령어를 입력하세요 : ");
			String command = reader.readLine(); // 콘솔에서 한 줄을 입력
			
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("종료합니다");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" ")); // " " 공백문자를 구분자로 하여 입력받은 문자열을 배열로 변경
				continue;
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
		
	}
	
	private static Assembler assembler = new Assembler();
	
	// 새로운 회원 정보 생성
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		
		MemberRegisterService regSvc = assembler.getMemberRegisterService();
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다");
		}
	}
	
	// 암호 변경 기능
	private static void processChangeCommand(String[] arg) {
		if (arg.length != 4) {
			printHelp();
			return;
		}
		
		ChangePasswordService changePwdSvc = assembler.getChangePasswordService();
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("암호를 변경했습니다");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다");
		}
	}
	
	// 도움말 출력
	private static void printHelp() {
		System.out.println();
		System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
		System.out.println("명령어 사용법 :");
		System.out.println("new 이메일 이름 암호 암호확인");
		System.out.println("change 이메일 현재비번 변경비번");
		System.out.println();
		
	}
}
