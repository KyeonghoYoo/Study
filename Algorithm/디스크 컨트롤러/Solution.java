import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {
	public int solution(int[][] jobs) {
		int answer = 0;
		int taskTime = 0;

		Arrays.sort(jobs, (job1, job2) -> job1[0] - job2[0]);

		ArrayDeque<int[]> jobsQueue = new ArrayDeque<>(Arrays.stream(jobs).collect(Collectors.toList()));
		PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((job1, job2) -> job1[1] - job2[1]);
		
		while (!jobsQueue.isEmpty()) {
			while (!jobsQueue.isEmpty() && jobsQueue.peek()[0] <= taskTime) {
				priorityQueue.add(jobsQueue.poll());
			}
			if(!priorityQueue.isEmpty()) {
				int[] prirorityJob = priorityQueue.poll();
				taskTime += prirorityJob[1];
				answer += taskTime - prirorityJob[0];
			} else {
				taskTime = jobsQueue.peek()[0];
			}

		}
		
		while(!priorityQueue.isEmpty()) {
			int[] prirorityJob = priorityQueue.poll();
			taskTime += prirorityJob[1];
			answer += taskTime - prirorityJob[0];
		}

		return answer / jobs.length;
	}
}
