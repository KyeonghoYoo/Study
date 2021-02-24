import java.util.Arrays;

public class Solution {
    public boolean solution(String[] phone_book) {
        for (int i = 0; i < phone_book.length; i++) {
			String string = phone_book[i];
			boolean anyMatch = Arrays.stream(phone_book)
									.filter(phone -> !phone.equals(string))
									.anyMatch(phone -> phone.startsWith(string));
			if (anyMatch) {
				return false;
			}
		}
        
        return true;
    }
}
