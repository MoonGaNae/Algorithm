package swexpert.암호생성기_1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// D3
public class Solution {
	static int t;				// 테스트케이스 번호
	static Queue<Integer> q;	// 데이터를 저장할 큐
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		q = new LinkedList<>();							// 큐 내부적으로 LinkedList 사용
		while(true) {
			String input = br.readLine();			
			if(input == null) break;					// 입력이 null이면 종료
			
			t = Integer.parseInt(input);				// 테스트케이스 번호입력
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));	// 큐에 8자리 데이터저장
			}

			int minus = 0;
			while(true) {		
				minus = minus%5 + 1;		// 1, 2, 3, 4, 5 순으로 반복
				
				int cur = q.poll() - minus;	// 맨앞의 수 감소
				if(cur <= 0) {				// 0보다 같거나 작을 경우
					q.offer(0);				// 맨뒤에 0으로 보내기
					break;					// 종료
				}								
				q.offer(cur);				// 아니라면 맨뒤로 보내기 
			}
			
			System.out.print("#"+t + " ");
			for(int val : q)
				System.out.print(val + " ");
			System.out.println();	
			q.clear();						// 다음 테스트를 위한 Queue 초기화
		}
	}
}
