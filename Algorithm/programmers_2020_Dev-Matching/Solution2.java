import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// 데브매칭 2번 문제 풀이
public class Solution2 {
	public static void main(String[] args) throws ParseException {
		System.out.println("solution1 ---");
		System.out.println(solution("PM 01:00:00", 10));
		System.out.println();
		System.out.println("solution2 ---");
		System.out.println(solution("PM 11:59:59", 1));
		System.out.println();
		System.out.println("solution2 ---");
		System.out.println(solution("AM 12:59:59", 11));
		System.out.println();
		System.out.println("solution2 ---");
		System.out.println(solution("PM 12:59:59", 11));
		System.out.println();

	}

	public static String solution(String p, int n) throws ParseException {
		String answer = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String[] ps = p.split(" ");
		String[] times = ps[1].split(":");
		
		if (ps[0].equals("PM")) {
			times[0] = times[0].equals("12") ? times[0] : String.valueOf(Integer.parseInt(times[0]) + 12);
		} else {
			times[0] = times[0].equals("12") ? "00" : times[0];
		}
		Date date = dateFormat.parse(times[0] + ":" + times[1] + ":" + times[2]);
		System.out.println(times[0] + ":" + times[1] + ":" + times[2]);

		answer = dateFormat.format(new Date(date.getTime() + (n * 1000)));
		return answer;
	}

}
