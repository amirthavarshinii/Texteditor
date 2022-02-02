package document;

/** 
 * A class that represents a text document
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {
	
	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences 
	 * method here.  The reason we put countSyllables here because we'll 
	 * use it again next week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern 
	 * objects inside this method. Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	protected int countSyllables(String word)
	{ 
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument (module 2) and     // EfficientDocument (module 3).
	/*	int flag = 0;
		int count = 0;
         for( char c : word.toLowerCase().toCharArray())	{
        	 if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='y') {
        		flag =1; 
        	 } else if(flag == 1) {
        		 flag = 0;
        		 count = count +1;
        	 }
         } //segue
         if(flag == 1 &&((word.toLowerCase().charAt(word.length()-1) != 'e')||(count == 0))) {
        	 count =count +1;
         }
         if(word.length()>2) {
         char d = word.toLowerCase().charAt(word.length()-2);
          if((d=='a'||d=='e'||d=='i'||d=='o'||d=='u'||d=='y') &&(word.toLowerCase().charAt(word.length()-1)=='e')) {
        	 count = count +1;
         }
         } */
		int count =0;
		word = word.toLowerCase();
	     for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);//Sentence
			if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='y') {
				if( c != 'e'	|| (c  == 'e') && ( i != word.length()-1) || count == 0) {
					count = count + 1;
				}
			}
				while ( word.charAt(i) =='a'||word.charAt(i) =='e'||word.charAt(i) =='i'||word.charAt(i)=='o'||word.charAt(i)=='u'||word.charAt(i)=='y') {
				   	i++;
				   	if(i >= word.length()-1) {
				   		break ;
				   	}
				}
		} 
		
         return count;
		}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
	
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: You will play with this method in week 1, and 
		// then implement it in week 2
	 double numofwords =(double) getNumWords();
	 double numofsentences = (double)getNumSentences();
	 double numofsyllables =(double) getNumSyllables();
		double fleschscore = 0;
	    double secondarg = numofwords /numofsentences;
	    //System.out.println(secondarg);
	    secondarg = 1.015*secondarg;
	  	//    System.out.println(secondarg);
	  ///	    System.out.println(numofsyllables);
	  	//    System.out.println(numofwords);

	    double thirdarg = numofsyllables / numofwords;
	  //  System.out.println(thirdarg);
	    thirdarg = 84.6*thirdarg;
	//    System.out.println(thirdarg);
	    fleschscore = 206.835 -secondarg- thirdarg;
	    fleschscore = (Math.round(fleschscore *10.0))/10.0;
	    return fleschscore ;
	}
	
	
	
}
