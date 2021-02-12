package swexpert.계산기3_1224;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			
			int N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			StringBuilder sik = new StringBuilder();
			Stack<Character> s = new Stack<Character>();
			for(int i=0; i<input.length(); i++) {
				char c = input.charAt(i);
				if(0<=c-'0' && c-'0' <= 9) {
					sik.append(c);
				} else if(c == '(') {
					s.push(c);
				} else if(c == ')') {
					
					while(s.peek() != '(') {
						sik.append(s.pop());
					}
					s.pop(); // pop '('
				} else {
					int cur_prior = getPrior(c);
					while(!s.empty()) {
						if(getPrior(s.peek()) < cur_prior) break;
						sik.append(s.pop());
					}
					s.push(c);
				}
			}
			while(!s.empty()) {
				sik.append(s.pop());
			}
			System.out.println(sik.toString());
			
			Stack<Integer> rs = new Stack<Integer>();
			for(int i=0; i<sik.length(); i++) {
				char c = sik.charAt(i);
				if(0<=c-'0' && c-'0' <= 9) {
					rs.push(c-'0');
				} else if(c == '*') {
					int a =  rs.pop();
					int b= rs.pop();
					rs.push(a*b);
				} else if(c == '+') {
					int a =  rs.pop();
					int b= rs.pop();
					rs.push(a+b);
				}
			}
			
			System.out.println("#"+t + " " + rs.pop());
		}
	}
	static int getPrior(char c) {
		if (c == '*')
			return 8;
		else if(c == '+')
			return 6;
		return -1;
	}
}
