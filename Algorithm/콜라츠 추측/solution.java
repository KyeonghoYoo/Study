package me.koobin.algorism.programers;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class solution2 {
    public int[][] solution1(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr1[0].length];
        
        for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[0].length; j++) {
				answer[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
        
        return answer;
    }
    
	public int[] solution2(int[] answers) {
		int[] pgm1 = new int[answers.length]; // person (who) give up math
		int[] pgm2 = new int[answers.length];
		int[] pgm3 = new int[answers.length];
		int[] pgm3_pattern = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
		
		int j = 1;
		int k = 0;
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		for (int i = 0; i < answers.length; i++) {
				pgm1[i] = (i % 5) + 1;
				pgm3[i] = pgm3_pattern[k];
				
				if(i % 2 == 0) {
					pgm2[i] = 2;
				} else {
					pgm2[i] = j;
					j =  (j % 5) + 1;
				}
		}
		
		for (int i = 0; i < answers.length; i++) {
			if(pgm1[i] == answers[i]) {
				count1++;
			}
			if(pgm2[i] == answers[i]) {
				count2++;
			}
			if(pgm3[i] == answers[i]) {
				count3++;
			}
		}
		
		int[] answer;
		if (count1 > count2) {
			if(count1 > count3) {
				answer = new int[1];
				answer[0] = 1;
			} else if (count3 > count1) {
				answer = new int[1];
				answer[0] = 3;
			}else {
				answer = new int[2];
				answer[0] = 1;
				answer[1] = 3;
			}
		} else if (count2 > count1) {
			if(count2 > count3) {
				answer = new int[1];
				answer[0] = 2;
			} else if(count3 > count2) {
				answer = new int[1];
				answer[0] = 3;
			} else {
				answer = new int[2];
				answer[0] = 2;
				answer[1] = 3;
			}
		} else {
			if(count1 > count3) {
				answer = new int[2];
				answer[0] = 1;
				answer[1] = 2;
			} else if(count3 > count1) {
				answer = new int[1];
				answer[0] = 3;
			} else {
				answer = new int[3];
				answer[0] = 1;
				answer[1] = 2;
				answer[2] = 3;
			}
		}
		
		return answer;
	}
	/*
	 * 1937년 Collatz란 사람에 의해 제기된 이 추측은, 주어진 수가 1이 될때까지 다음 작업을 반복하면, 모든 수를 1로 만들 수 있다는 추측입니다. 작업은 다음과 같습니다.

1-1. 입력된 수가 짝수라면 2로 나눕니다. 
1-2. 입력된 수가 홀수라면 3을 곱하고 1을 더합니다.
2. 결과로 나온 수에 같은 작업을 1이 될 때까지 반복합니다.
예를 들어, 입력된 수가 6이라면 6→3→10→5→16→8→4→2→1 이 되어 총 8번 만에 1이 됩니다. 위 작업을 몇 번이나 반복해야하는지 반환하는 함수, solution을 완성해 주세요. 단, 작업을 500번을 반복해도 1이 되지 않는다면 –1을 반환해 주세요.

제한 사항
입력된 수, num은 1 이상 8000000 미만인 정수입니다.
n	result
6	8
16	4
626331	-1
	 * */
    public int solution3(int num) {
        int answer = 0;
        
        while(num != 1) {
        	if(answer > 500) {
        		return -1;
        	}
        	if(num % 2 ==0) {
        		num /= 2;
        	} else {
        		num = num * 3 + 1;
        	}
        	answer++;
        }
        return answer;
    }
    /*
     * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
completion의 길이는 participant의 길이보다 1 작습니다.
참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
참가자 중에는 동명이인이 있을 수 있습니다.
participant	completion	return
[leo, kiki, eden]	[eden, kiki]	leo
[marina, josipa, nikola, vinko, filipa]	[josipa, filipa, marina, nikola]	vinko
[mislav, stanko, mislav, ana]	[stanko, ana, mislav]	
     * */
    public String solution4(String[] participant, String[] completion) {
        String answer = "";
        Arrays.sort(participant);
        Arrays.sort(completion);
        for (int i = 0; i < participant.length; i++) {
			if(!participant[i].equals(completion[j])) {
				return participant[i];
			}
		}
        int len = completion.length;
        
        for (int i = 0; i < participant.length; i++) {
			for (int j = 0; j < len; j++) {
				if(participant[i].equals(completion[j])) {
					completion[j] = completion[len-- - 1];
					break;
				} else if (j == len - 1) {
					answer = participant[i];
				}
			}
		}
        
        return answer;
    }
}
