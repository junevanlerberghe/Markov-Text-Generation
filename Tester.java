public class Tester {

	private static final int NUM_WORDS_TO_GENERATE = 100;

	public static void main(String[] args) {
		HigherOrderTextModel model = new HigherOrderTextModel(); // create your model
		model.loadData("shakespeare.txt"); // load the data

		String generatedOutput = "";

		String currentWord = "From fairest"; // Choose starting word
		generatedOutput += currentWord;

		for (int i = 0; i < NUM_WORDS_TO_GENERATE; i++) {
			String nextWord = model.predictNextWord(currentWord);
			String[] words = nextWord.split(" ");

			generatedOutput += " " + words[0];

			if (i % 8 == 0)
				generatedOutput += "\n"; // add some line breaks in the output

			currentWord = nextWord;
		}

		System.out.println(generatedOutput);
	}
}

