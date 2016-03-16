package stuff;

/**
 * This is a helper class that helps with calculating the number of words, the number 
 * of sentences, and the number of syllables in a document. 
 * This class is written as an abstract super class. So you need to write your own
 * class to extend it. I will provide such an example at below as well.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DocumentHelper {

	private String text;
	
	protected DocumentHelper(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the text string.
	 * @param pattern A regular expression string
	 * @return A List of tokens from the text that match the regex 
	 */
	protected List<String> getTokens(String pattern)
	{
		// This part uses the Pattern and Matcher classes, which will be 
		// explained at another time. Just use it for now.

		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
	
	/** This finds the number of syllables in a word.
	* Syllables are defined by vowels (aeiouy or AEIOUY). 
	* A syllable is either a single vowel separated by consonants (hi, hello, etc) 
	* or a series of contiguous vowels (thought, loud, Louis, etc). 
	* A silent e at the end of a word is not a syllable (like, love, etc)
	* Also note that y counts as a vowel here.
	*/

	protected int countSyllables(String word)
	{
		int num = 0;
		String pattern = "[aeiouyAEIOUY]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(word);
		String lastToken = "";
		while (m.find()) {
		num++;
		lastToken = m.group();
		}

		if(num > 1 && word.charAt(word.length()-1) == 'e' && lastToken.equals("e")) {
		num--;
		}

		return num;
	}
	
	
	/** Return the number of words in the document */
	public abstract int getNumberOfWords();
	
	/** Return the number of sentences in the document */
	public abstract int getNumberOfSentences();
	
	/** Return the number of syllables in the document */
	public abstract int getNumberOfSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability index */
	public double getFleschScore(double words, double sentences, double syllables)
	{
		return (206.835 - (1.015*(words/sentences)) - (84.6*(syllables/words)));
	}
}