package stuff;

import java.util.List;

/** 
 * An example of implementing the DocumentHelper abstract class. 
 */
public class EditorDocument extends DocumentHelper {
	/** Create a new EditorDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public EditorDocument(String text){
		super(text);
	}
	
	/**
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumberOfWords(){ 
	    List<String> tokens = getTokens("[a-zA-Z]+");
	    return tokens.size();
	}
	
	/**
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumberOfSentences() {
	    List<String> tokens = getTokens("[^.!?]+");
	    return tokens.size();
	
	}
	
	/**
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumberOfSyllables() {

		List<String> tokens = getTokens("[a-zA-z]+");  
		int count = 0;   
		for (String token : tokens)  {     
		        count += countSyllables(token);   
		 }   
		return count;
	}
}