class Solution {
    public String solution(String new_id) {
        new_id = new_id
        	.toLowerCase() //(1)
        	.replaceAll("([^\\w\\.\\-_])", "") //(2)
        	.replaceAll("([\\.]+)", ".") //(3)
        	.replaceAll("^\\.|\\.$", ""); //(4)
        
        if(new_id.isEmpty()) //(5)
        	new_id = "a";
        
        if(new_id.length() > 15) //(6)
        	new_id = new_id.substring(0, 15)
        		.replaceAll("\\.$", "");
        
        while(new_id.length() < 3) //(7)
        	new_id = new_id.concat(String.valueOf(new_id.charAt(new_id.length() - 1)));
        
        return new_id;
    }
}