package swexpert.괄호짝짓기_1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// D4
public class Solution {
	static char brackets [] = {'(','[','{','<','>','}',']',')'};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			int ans=1;
			
			int N = Integer.parseInt(br.readLine());
			char input[] = br.readLine().toCharArray();
			Stack<Integer> s = new Stack<>();
			
			
			for(int i=1; i<N; i++) {
				int curIdx = getIdx(input[i]);
				
				// 열린 문자라면
				if(curIdx < 4) {
					s.push(curIdx);
				}
				// 닫힌문자라면
				else {
					if(s.empty()) break;
					else if(s.peek() + curIdx != 7) break; 
					s.pop();
				}
			}
			if(!s.empty()) ans=0;
			
			System.out.println("#"+t+" "+ ans);
		}

	}
	
	static int getIdx(char bracket) {
		int idx = -1;
		for(int i=0; i<8; i++) {
			if(bracket == brackets[i]) {
				idx = i;
				break;
			}
		}
		return idx;
	}

}
