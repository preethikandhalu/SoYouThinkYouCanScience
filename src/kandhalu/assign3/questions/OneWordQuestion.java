package kandhalu.assign3.questions;

/**
 * @author Preethi Kandhalu
 * Class for One Word Questions
 */

public class OneWordQuestion extends Question {
	public OneWordQuestion(String type, String topicArea, String question, String correctAnswer, int points, boolean hasImage, String imagePath){
		super(type, question, topicArea, points, correctAnswer, hasImage, imagePath);
	}
}
