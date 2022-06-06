import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Time Complexity : Exponential. Approx O(k^m); 
//where m is total no. of blocks i.e. n/k where n = length of input string and k is avg no. of chars in each block.
//Space Complexity : O(m)
public class BraceExpansion { 
	/**Approach: DFS + Backtracking **/
	List<String> res;
    public String[] expand(String s) {
        if(s == null || s.length() == 0) return new String[0];   
        res= new ArrayList<>();
        
        //Prepare blocks list
        List<List<Character>> blocks = new ArrayList<>();
        int i=0;
        while(i < s.length()){
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if(c == '{'){
                i++;
                while(s.charAt(i) != '}'){
                    if(s.charAt(i) != ','){
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            }else{
                block.add(c);
            }
            i++;
            Collections.sort(block);
            blocks.add(block);
        }
        //Call dfs on blocks to prepare desired combinations        
        backtrack(blocks, 0, new StringBuilder());
        String[] result = res.toArray(new String[0]);
        return result;        
    }
    
    private void backtrack(List<List<Character>> blocks, int idx, StringBuilder sb){
        //base
        if(idx == blocks.size()){
            res.add(sb.toString());
            return;
        }
        //logic
        List<Character> block = blocks.get(idx);
        for(int i=0; i<block.size(); i++){
            char c = block.get(i);
            //action
            sb.append(c);
            //recurse
            backtrack(blocks, idx+1, sb);
            //backtrack
            sb.setLength(sb.length() - 1);
        }
    }
    
	// Driver code to test above
	public static void main (String[] args) {	
		BraceExpansion ob  = new BraceExpansion();	
		String s = "{a,b}c{d,e}f";
		System.out.println("All words that can be formed are:"+ Arrays.asList(ob.expand(s)));         
	}
}
