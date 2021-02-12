package baekjoon.오아시스재결합_3015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 골드1
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long ans = 0;
		/*
		 * 	생각해야할 것
		 * 		- 낮은 다수는 높은 한사람을과 연결이 가능하다.
		 * 			1112 -> 1인사람들과 2인 사람의 연결수는 3
		 * 		- 낮은 1명과 높은 다수는 연결이 불가능하다. => 가장 가까이에 있는 1명과 연결 가능
		 * 			1222 -> 1인 사람과 2인 사람들의 연결수는 1
		 */
		Stack<Person> s = new Stack<>();	// 사람을 줄세울 스택
		
		for(int i=0; i<N; i++) {
			int h = Integer.parseInt(br.readLine());	// 현재사람의 키 입력
			
			while(!s.isEmpty() && s.peek().h < h) {		// 이전 사람보다 크다면
				Person cur = s.pop();						// 이전 사람 줄에서 제거
				if(!s.isEmpty()) {							// 이전 사람보다 큰사람이 있다면			
					ans += cur.num;								// 이전사람들과 더 이전사람의 연결추가
				}
				
				ans += cur.num;							// 이전사람들과 현재사람과의 연결추가
			}
			
			if(!s.isEmpty() && s.peek().h == h) {		// 키가 같다면
				ans += s.peek().num;						// 같은사람끼리 연결 추가
				s.peek().num++;								// 줄에 추가
			} else {									// 키가 작다면
				s.push(new Person(h, 1));					// 줄에 추가
			}
		}
		// 남은사람 정리 -> 현재 스택에는 4/3/2/1 <-top 과 같이 내림차순으로 정렬되어있음
		while(!s.isEmpty()) {											
			Person cur = s.pop();
			if(!s.isEmpty()) ans += cur.num;	// 비어있지않으면 이전사람들과 더 이전사람의 연결추가
		}	
		
		System.out.println(ans);

	}

}
class Person{
	int h;		// 키
	int num;	// 같이 줄서있는 사람 수
	public Person(int h, int num) {
		super();
		this.h = h;
		this.num = num;
	}
	
	
}

