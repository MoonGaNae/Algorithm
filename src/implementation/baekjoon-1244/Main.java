package baekjoon.스위치켜고꺼기_1244;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();					// 스위치의 갯수
		int arr[] = new int[N+1]; 				// 스위치의 상태저장배열
		
		for(int i =1; i<=N; i++) {
			arr[i] = sc.nextInt(); 				// 스위치 상태저장
		}
		
		int stu_num = sc.nextInt();				// 학생수
		
		for(int i=0; i<stu_num; i++) {
			int gender = sc.nextInt();			// 성별 1: 남자, 2: 여
			int num = sc.nextInt();				// 학생이 받은 수
			
			/*
			 * 1, 0 반전시키기
			 * ~1 -> ~0001 = 1110 = -2 => -2 + 2 = 0
			 * ~0 -> ~0000 = 1111 = -1 => -1 + 2 = 1
			 */
			if(gender == 1) {					// 남자라면?
				for(int j=num; j<=N ; j+=num) {	// 학생이 받은 수의 배수
					arr[j] = ~arr[j] + 2;		// 반전
				}
				
			} else {							// 여학생이라면?
				arr[num] = ~arr[num] + 2;		// 본인반전
				
				for(int j=1; j<=N; j++) {		// 1칸씩 좌우대칭 확인
					int left = num-j;			
					int right = num+j;
					
					if(left <= 0 ||right > N) break;	// 범위를 벗어나거나 
					if(arr[left] != arr[right]) break;	// 좌우대칭이 아니면 중단
					
					// 좌우반전
					arr[left] = ~arr[left] + 2;
					arr[right] = ~arr[right] + 2;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(arr[i] + " ");
			if(i % 20 == 0) 				// 20개 넘으면 개행
				sb.append("\n");
		}
		System.out.println(sb.toString());
		sc.close();
	}
}
