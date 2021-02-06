package swexpert.계산기2_1223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// D4
public class Solution {
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for(int t=1; t<=10; t++) {
            int ans=0;
             
            int N = Integer.parseInt(br.readLine());
            char input[] = br.readLine().toCharArray();
             
            StringBuilder st = new StringBuilder();		// 후위연산식의 결과를 저장할 문자열
            Stack<Character> s = new Stack<>();			// 우선순위에 따라 연산자를 저장할 스택
            for(int i=0; i<N; i++) {
                char c = input[i];
                
                if(0 <= c - '0' && c- '0' <= 9) {		// 피연산자라면
                    st.append(c);						// 출력
                } else {								// 연산자라면
                	int cur_prior = getPrior(c);		// 현재 입력연산자의 우선순위구하기
                    while(!s.empty()) {								// 스택에서 현재 연산자보다 우선순위가 높거나 같은 연산자 모두 출력
                    	if(getPrior(s.peek()) < cur_prior) break;	
                    	st.append(s.pop());
                    }
                    s.push(c);										// 현재 입력연산자 스택에 push
                }
            }
            while(!s.empty()) {										// 스택에 남은 연산자 모두 출력
                st.append(s.pop());
            }
            
            Stack<Integer> rq = new Stack<>();			// 연산결과를 저장할 스택
            for(int i=0; i<N; i++) {
                char c = st.charAt(i);
                // 숫자라면
                if(0 <= c - '0' && c- '0' <= 9) {
                    rq.push(c-'0');
                } else if (c == '+'){					// +라면
                    int a = rq.pop();
                    int b = rq.pop();
                     
                    rq.push(a+b);
                } else if (c == '*'){					// *라면
                    int a = rq.pop();
                    int b = rq.pop();
                     
                    rq.push(a*b);
                }
            }
            System.out.println("#"+t +" " + rq.pop());
        }
    }
    // 연산자별 우선순위를 반환할 함수
    static int getPrior(char operator) {
        int priority = 0;
    	if('*' == operator)
        	priority = 8;
        else if('+' == operator)
        	priority = 6;
        
    	return priority;
    }
}
