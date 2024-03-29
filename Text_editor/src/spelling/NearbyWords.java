/**
 * 
 */
package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	public NearbyWords (Dictionary dict) 
	{
		this.dict = dict;
	}

	/** Return the list of Strings that are one modification away
	 * from the input string.  
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {

		   List<String> retList = new ArrayList<String>();
		   insertions(s, retList, wordsOnly);
		   substitution(s, retList, wordsOnly);
		   deletions(s, retList, wordsOnly);
	//	   System.out.println("***********total***********************");

//	  Iterator itr = retList.iterator();
	//	  while(itr.hasNext()) {
		//	 System.out.println(itr.next());
		  // }

		   return retList;
	}

	
	/** Add to the currentList Strings that are one character mutation away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/** Add to the currentList Strings that are one character insertion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method  

		for(int index = 0; index <=s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String
				StringBuffer sb = new StringBuffer();
				sb.append(s.substring(0, index));
				sb.append((char)charCode);
				sb.append(s.substring(index ,s.length()));
				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/**Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 * @return
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// TODO: Implement this method
		 System.out.println("deletion******************");

		for(int indexi = 0; indexi <s.length(); indexi++){
		//	for(int indexj = indexi; indexj<s.length(); indexj++) {
				// use StringBuffer for an easy interface to permuting the 
				// letters in the String// 'hmatrimony
		//		if(indexi != indexj) {
				StringBuffer sb = new StringBuffer();
			//	sb.append(s.substring(indexi ,indexj));
			
				sb.append(s.substring(0,indexi));
				sb.append(s.substring(indexi + 1 ,s.length()));
               System.out.println(sb);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				 }
		}
	}
	/*Input:  word for which to provide number of spelling suggestions
Input:  number of maximum suggestions to provide
Output: list of spelling suggestions

Create a queue to hold words to explore
Create a visited set to avoid looking at the same String repeatedly
Create list of real words to return when finished

Add the initial word to the queue and visited 

while the queue has elements and we need more suggestions
  remove the word from the start of the queue and assign to curr
  get a list of neighbors (strings one mutation away from curr)
  for each n in the list of neighbors
       if n is not visited
       add n to the visited set
       add n to the back of the queue
       if n is a word in the dictionary
       add n to the list of words to return
return the  of real words
*/
	@Override
	public List<String> suggestions(String word, int numSuggestions) {
		// initial variables
		
		List<String> queue = new LinkedList<String>();     // String to explore
		HashSet<String> visited = new HashSet<String>();   // to avoid exploring the same  
														   // string multiple times
		List<String> retList = new LinkedList<String>();   // words to return
		// insert first node
		queue.add(word);
		
		visited.add(word);
					
		// TODO: Implement the remainder of this method, see assignment for algorithm
		while(! queue.isEmpty()&& retList.size() < numSuggestions) {
		//queue.remove();
			String curr = queue.remove(0);
			List<String> neighbours = this.distanceOne(curr, false);
		    Iterator itr = neighbours.iterator();
		    while(itr.hasNext()) {
		    	String s = (String)itr.next();
		    	if(!visited.contains(s)) {
		    	visited.add(s);
			     queue.add(queue.size(), s);
		    }
		    	if(dict.isWord(s)&&(!retList.contains(s))) {
		    		retList.add(s);
		    	}
		}
		
		}	
		
		return retList;

	}	

   public static void main(String[] args) {
	   /* basic testing code to get started
	   String word = "i";
	   // Pass NearbyWords any Dictionary implementation you prefer
	   Dictionary d = new DictionaryHashSet();
	   DictionaryLoader.loadDictionary(d, "data/dict.txt");
	   NearbyWords w = new NearbyWords(d);
	   List<String> l = w.distanceOne(word, true);
	   System.out.println("One away word Strings for for \""+word+"\" are:");
	   System.out.println(l+"\n");

	   word = "tailo";
	   List<String> suggest = w.suggestions(word, 10);
	   System.out.println("Spelling Suggestions for \""+word+"\" are:");
	   System.out.println(suggest);
	   */
   }

}
