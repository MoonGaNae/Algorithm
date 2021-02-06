package baekjoon.재귀함수가뭔가요_17478;

import java.util.Scanner;

public class Main {
	static int N = 2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		m(N);
	}
	static void m(int n) {
		// 언더바 설정하기
		StringBuilder under_bar = new StringBuilder("");
		for(int i=n; i<N; i++) {
			under_bar.append("____");
		}
		
		// 질문
		System.out.println(under_bar.toString() + "\"재귀함수가 뭔가요?\"");
		// 기저조건 -> 답변하기
		if (n == 0) {
			System.out.println(under_bar.toString() + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
		}else {
			System.out.println(under_bar.toString() + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			System.out.println(under_bar.toString() + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			System.out.println(under_bar.toString() + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			m(n-1);
		}
		// 답변 마무리
		System.out.println(under_bar.toString() + "라고 답변하였지.");
	}

}
