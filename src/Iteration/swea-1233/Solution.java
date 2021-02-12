package swexpert.사칙연산유효성검사_1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1; t<=10; t++) {
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			
			int ans = 1;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				if(ans == 0) continue;
				int num = Integer.parseInt(st.nextToken());
				int data = st.nextToken().charAt(0);
				
				if(st.hasMoreTokens()) {
					continue;
				} else if(data == '+' || data=='*' || data=='/' || data=='-') {
					ans=0;
				}
			}
			System.out.println("#"+t + " "+ ans);
		}
		

	}

}
