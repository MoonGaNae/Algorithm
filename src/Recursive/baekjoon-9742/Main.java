package baekjoon.순열_9742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 실버 5
public class Main {
	
	static String src;
	static char tgt[];
	static boolean select[];
	static int N;
	static int searchIdx;
	static int COUNT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String input = br.readLine();
			
			if(input == null) break;
			
			StringTokenizer st = new StringTokenizer(input, " ");
			src = st.nextToken();
			N = src.length();
			searchIdx = Integer.parseInt(st.nextToken());
			
			tgt = new char[N];
			select = new boolean[N];
			COUNT = 0;
			
			int fac = 1;
			for(int i =1; i<=N; i++)
				fac *= i;
			
			if(searchIdx > fac) {
				System.out.print(src + " " + searchIdx + " = ");
				System.out.println("No permutation");
			}else {
				perm(0);
			}
			
			
		}
	}
	
	static void perm(int tIdx) {
		if(tIdx == N) {
			COUNT++;
			if(searchIdx == COUNT) {
				System.out.print(src + " " + searchIdx + " = ");
				for(int i=0; i<N;i++) {
					System.out.print(tgt[i]);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i=0; i < N; i++) {
			if(select[i])
				continue;
			
			tgt[tIdx] = src.charAt(i);
			select[i] = true;
			perm(tIdx+1);
			select[i]= false;
		}
	}

}
