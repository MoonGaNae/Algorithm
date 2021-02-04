package baekjoon.탑_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 골드5
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		Stack<TOP> s = new Stack<>();
		for(int i=1; i<=N; i++) {
		
			int h = Integer.parseInt(st.nextToken());	// 현재 탑의 높이 입력
			
			while(!s.empty()) {
				if(s.peek().h > h) {					// 스택의 Top이 현재 높이보다 크다면
					sb.append(s.peek().i + " ");		// 레이저 수신완료
					break;
				}
				s.pop();								// 높이가 낮다면 수신하지 못하기때문에 pop
			}
			
			if(s.empty()) sb.append("0 ");				// 스택이 비어있음 == 아무도 수신못함
			
			s.push(new TOP(i,h));						// 현재 높이 push
		}
		
		System.out.println(sb.toString());
	}

}
class TOP{
	int i;
	int h;
	TOP(int i, int h){
		this.i = i; this.h = h;
	}
}
