package baekjoon.요세푸스문제_1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/* stack -> 메모리 : 295932KB  || 시간 : 584ms */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<=N; i++) {
			q.offer(i);
		}
		
		int k=1;
		while(!q.isEmpty()) {
			if(k==K) {
				sb.append(q.poll() + ", ");
				k=1;
				continue;
			}
			q.offer(q.poll());
			k++;
		}
		
		sb.delete(sb.length()-2, sb.length());
		sb.append(">");
		System.out.println(sb.toString());
	}
	/* array -> 메모리 : 19372KB  || 시간 : 732ms */
	// public static void main(String[] args) throws IOException {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	// 	StringTokenizer st = new StringTokenizer(br.readLine());
	// 	int N = Integer.parseInt(st.nextToken());
	// 	int K = Integer.parseInt(st.nextToken());
	// 	int input[] = new int[N+1];
	// 	StringBuilder sb = new StringBuilder();
	// 	sb.append("<");
		
	// 	for(int i=1; i<=N; i++) {
	// 		input[i] = i;
	// 	}
		
	// 	int count = 0;
	// 	int idx = 1;
	// 	int step = 1;
		
	// 	while(true) {
	// 		if(count == N) break;
			
	// 		if(input[idx] != 0) {
	// 			if( step % K == 0){
	// 				sb.append(input[idx] + ", ");
	// 				input[idx] = 0;
	// 				count++;
	// 			}
	// 			step++;
	// 		}
	// 		idx++;
	// 		if(idx > N) idx = 1;
	// 	}
	// 	sb.setLength(sb.length()-2);
	// 	sb.append('>');
	// 	System.out.println(sb.toString());
	// }
}
