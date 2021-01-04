import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class TextModel {
	private HashMap<String, Bag> map;

	public TextModel() {
		map = new HashMap<String, Bag>();
	}

	private Bag getBagForWord(String word) {
		Bag b = null;
		if (map.containsKey(word)) {
			b = map.get(word);
		} else {
			b = new Bag();
			map.put(word, b);
		}
		
		return b;
	}
	
	// Adds the observed word pair word and followingWord to the Markov model data
	public void addWordPair(String word, String followingWord) {
		Bag b = getBagForWord(word);
		b.add(followingWord);
	}

	public void loadData(String filename) {
		Bag b;
		String text = getFileAsString(filename);
		String[] words = text.split(" ");
		
		for (int i = 0; i < words.length - 1; i++) {
			addWordPair(words[i], words[i + 1]);
		}
	}
	
	public String predictNextWord(String word) {
		Bag b = getBagForWord(word);
		return b.getRandomByFrequency();
	}

	public static String getFileAsString(String path) {
		StringBuilder b = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = br.readLine();
			while (line != null) {
				b.append(line);
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Something wrong: " + e.getMessage());
		}

		return b.toString();
	}
}
