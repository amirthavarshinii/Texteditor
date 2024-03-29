package spelling;

import java.util.TreeSet;

/**
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
    // TODO: Implement the dictionary interface using a TreeSet.  
 	// You'll need a constructor here
  public  DictionaryBST (){
	  dict = new TreeSet<String>();
   }
 
    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// do tree take duplicate values , do we really need to check if the tree contains the value or not
    	// TODO: Implement this method
    	word = word.toLowerCase();
   	 if(dict.contains(word)) {
    	        return false;
    	 } else {
    		 dict.add(word);
    		 return true;
    	 } 
 //    return	dict.add(word.toLowerCase());
    			
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	// TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	//TODO: Implement this method
    	 if(dict.contains(s.toLowerCase())) {
 	        return true;
 	       } else {
 		 return false;
 	 }
    }

}
