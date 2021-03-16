import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class Solution {
    public String solution(int n, int t, int m, String[] timetable) throws ParseException {
    	String answer = "";
    	// given
    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm");
    	
    	final LocalTime MINIMUN_TIME = LocalTime.parse("00:00", dateFormat);
    	final LocalTime MAXIMUM_TIME = LocalTime.parse("23:59", dateFormat);
    	final LocalTime BUS_START_TIME = LocalTime.parse("09:00", dateFormat);
    	
        
        LocalTime busArriveTime = LocalTime.parse("09:00", dateFormat);
        Stack<LocalTime> stack = new Stack<>();
        int gettingCount = 0;

        LinkedBlockingQueue<LocalTime> collect = Arrays.stream(timetable)
        		.map(time -> {
        			if(time.equals("24:00")) 
        				return LocalTime.parse("23:59");
        			if(time.equals("00:00"))
        				return LocalTime.parse("00:01");
        			return LocalTime.parse(time, dateFormat);
        		})
        		.sorted(LocalTime::compareTo)
        		.collect(Collectors.toCollection(LinkedBlockingQueue::new));
        System.out.println(collect.toString());
        // when
        for(int i = 0; i < n; i++) {
        	gettingCount = 0;
        	stack.removeAllElements();
        	while(!collect.isEmpty() && collect.peek().compareTo(busArriveTime) < 1) {
        		if(gettingCount < m) {
            		stack.push(collect.poll());
            		gettingCount++;
        			
        		} else {
        			break;
        		}
        	}
        	busArriveTime = busArriveTime.plusMinutes(t);
        	System.out.println("busArriveTime = " + busArriveTime.toString());
        }
        busArriveTime = busArriveTime.minusMinutes(t);
        System.out.println("busArriveTime = " + busArriveTime.toString());
        
        // then
        if(stack.isEmpty()) {
        
        	answer = busArriveTime.format(dateFormat);
        } else {
        	if(gettingCount < m) {
        		answer =  busArriveTime.format(dateFormat);
        	} else {
        		System.out.println("gettingCount = " + gettingCount);
        		LocalTime pre = stack.pop();
				pre = pre.minusMinutes(1); 
				answer = pre.toString();
        	}
        }
        
        return answer;
    }
}
