import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public String[] solution(String[][] tickets) {
		//given
		List<String> answer = new ArrayList<>();

		List<String[]> ticketList = Arrays.stream(tickets).sorted((ticket1, ticket2) -> {
			if(ticket1[0].equals(ticket2[0])) {
				return ticket1[1].compareTo(ticket2[1]);
			}
			return ticket1[0].compareTo(ticket2[0]);
		}).collect(Collectors.toList());
		
		//when
		String[][] icnRoutes = ticketList.stream().filter(ticket -> ticket[0].equals("ICN")).toArray(String[][]::new);
		
		for (String[] icnRoute : icnRoutes) {
			ArrayList<String[]> dumy_ticketList = new ArrayList<>(ticketList);	
			
			answer = dfs(icnRoute, dumy_ticketList, new ArrayList<String>(List.of(icnRoute[0])));
			if(answer != null) {
				break;
			}
		}
		
		
		//then
		return answer.toArray(String[]::new);
	}
	
	public List<String> dfs(String[] pre, List<String[]> ticketList, List<String> route) {
		
		route.add(pre[1]);
		int indexOf = ticketList.indexOf(pre);
		ticketList.remove(indexOf);
		System.out.println(route.toString());
		String[] dumy_pre = pre;
		String[][] preRoutes = ticketList.stream().filter(ticket -> ticket[0].equals(dumy_pre[1])).toArray(String[][]::new);
		System.out.println("preRoutes.length = " + preRoutes.length);
		System.out.println("ticketList.size() = " + ticketList.size());
		System.out.println("ticketList.isEmpty() = " + ticketList.isEmpty());
		for (String[] preRoute : preRoutes) {
			System.out.println("preRoute = " + Arrays.toString(preRoute));
			List<String> result = dfs(preRoute, new ArrayList<>(ticketList), new ArrayList<>(route));
			if(result != null) {
				return result;
			}
		}
		
		if(ticketList.isEmpty())
			return route;
		else
			return null;
	}
}
