import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class solution {
    public int solution(String[] lines) throws ParseException {
		int answer = 0;
		int[][] answers = new int[lines.length][2];
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

		String[] s = new String[lines.length];
		long[] t = new long[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String e = lines[i];
			s[i] = e.substring(0, 23);
			String e_t = e.substring(24).replace("s", "");
			t[i] = (long) (Double.parseDouble(e_t) * 1000);
		}

		for (int i = 0; i < s.length; i++) {
			Date date_Parsed = dateFormat.parse(s[i]);
			long s_endtime = date_Parsed.getTime();
			long s_startTime = s_endtime - t[i] + 1;

			for (int j = 0; j < s.length; j++) {
				if (j != i) {
					date_Parsed = dateFormat.parse(s[j]);
					long s2_endtime = date_Parsed.getTime();
					long s2_startTime = s2_endtime - t[j] + 1;

					if ((s_startTime <= s2_startTime && s2_startTime <= s_startTime + 999)
							|| (s_startTime <= s2_endtime && s2_endtime <= s_startTime + 999)
							|| (s2_startTime <= s_startTime && s_startTime <= s2_endtime)
							|| (s2_startTime <= s_startTime + 999 && s_startTime + 999 <= s2_endtime)) {
						answers[i][0]++;
					}
					if ((s_endtime <= s2_startTime && s2_startTime <= s_endtime + 999)
							|| (s_endtime <= s2_endtime && s2_endtime <= s_endtime + 999)
							|| (s2_startTime <= s_endtime && s_endtime <= s2_endtime)
							|| (s2_startTime <= s_endtime + 999 && s_endtime + 999 <= s2_endtime)) {
						answers[i][1]++;
					}
				} else {
					answers[i][0]++;
					answers[i][1]++;
				}
            }
        }
        
		for (int[] e : answers) {
			int temp = e[0] > e[1] ? e[0] : e[1];
			answer = temp > answer ? temp : answer;
		}
		
		return answer;
    }
}