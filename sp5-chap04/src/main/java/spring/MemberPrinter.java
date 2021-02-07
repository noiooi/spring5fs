package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	// 자동 주입 대상이 필수가 아닌 경우
	// 방법 1. required 속성 사용 - 필드
	// @Autowired(required = false)
	private DateTimeFormatter dateTimeFormatter;
	
	// 방법 1. required 속성 사용 - 메서드
	@Autowired(required = false)
	public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
		// 일치하는 빈이 존재하지 않을 때, 자동 주입 대상(ex. dateTimeFormatter)이 되는 필드나 메서드에 null을 전달하지 않음
		this.dateTimeFormatter = dateTimeFormatter;
	}
	
	public MemberPrinter() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
	}

	public void print(Member member) {
		if (dateTimeFormatter == null) {
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime()
			);
		} else {
			System.out.printf(
					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
					member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime())
			);
		}
	}
	
	// 방법 2. 자바 8의 Optional 사용 - 필드
//	@Autowired
//	private Optional<DateTimeFormatter> formatterOpt;
//	
//	public void print(Member member) {
//		DateTimeFormatter dateTimeFormatter = formatterOpt.orElse(null);
//		
//		if (dateTimeFormatter == null) {
//			System.out.printf(
//					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
//					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime()
//			);
//		} else {
//			System.out.printf(
//					"회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
//					member.getId(), member.getEmail(), member.getName(), dateTimeFormatter.format(member.getRegisterDateTime())
//			);
//		}
//	}
//	
//	// 방법 2. 자바 8의 Optional 사용 - 메서드
//	@Autowired
//	public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
//		if (formatterOpt.isPresent()) {
//			this.dateTimeFormatter = formatterOpt.get();
//		} else {
//			this.dateTimeFormatter = null;
//		}
//	}
	
	// 방법 3. @Nullable 애노테이션 사용 - 필드
	// 방법 1과의 차이점은 자동 주입할 빈이 존재하지 않아도 해당 메소드가 호출된다는 점(방법 1은 자동 주입할 빈이 존재하지 않으면 호출되지 않음)
//	@Autowired
//	@Nullable
//	private DateTimeFormatter dateTimeFormatter;
	
	// 방법 3. @Nullable 애노테이션 사용 - 메서드
//	@Autowired
//	public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
}
