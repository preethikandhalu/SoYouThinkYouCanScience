package kandhalu.assign3.questions;
/**
 * @author Preethi Kandhalu
 * Tester class for testing code in package
 */
import java.io.IOException;

public class tester {
	public static void main(String[] args) throws IOException {
		QuestionBank questionBank = new QuestionBank();
		questionBank.loadQuestions("questions.txt");
		Question ret = questionBank.getQuestion("biology");
		System.out.println(ret.toString());
	}
}
