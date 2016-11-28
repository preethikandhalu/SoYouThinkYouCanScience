package kandhalu.assign3.questions;

/**
 * @author Preethi Kandhalu
 * Class for Multiple Choice questions
 */

public class MultipleChoiceQuestion extends Question{
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	
	/**
	 * Constructor takes all parameters to create a MultipleChoiceQuestion object
	 * @param type should be Multiple choice 
	 * @param topicArea 
	 * @param question
	 * @param correctAnswer
	 * @param points
	 * @param choices
	 * @param hasImage
	 * @param imagePath
	 */
	public MultipleChoiceQuestion(String type, String topicArea, String question, String correctAnswer, int points, String choices, boolean hasImage, String imagePath){
		super(type, question, topicArea, points, correctAnswer, hasImage, imagePath);
		String[] choiceToInsert = new String[4];
		choiceToInsert = choices.split(",");
		this.choice1=choiceToInsert[0];
		this.choice2=choiceToInsert[1];
		this.choice3=choiceToInsert[2];
		this.choice4=choiceToInsert[3];
	}
	
	/**
	 * @return first choice
	 */
	public String getChoice1() {
		return choice1;
	}

	/**
	 * @param choice1 first choice
	 */
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}

	/**
	 * @return second choice
	 */
	public String getChoice2() {
		return choice2;
	}

	/**
	 * @param choice2 second choice
	 */
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}

	/**
	 * @return third choice
	 */
	public String getChoice3() {
		return choice3;
	}

	/**
	 * @param choice3 third choice
	 */
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}

	/**
	 * @return fourth choice
	 */
	public String getChoice4() {
		return choice4;
	}

	/**
	 * @param choice4 fourth choice
	 */
	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}
	
	/**
	 * @return Returns all choices
	 */
	public String[] getChoices(){
		String[] toReturn = new String[4];
		toReturn[0]=this.choice1;
		toReturn[1]=this.choice2;
		toReturn[2]=this.choice3;
		toReturn[3]=this.choice4;
		return toReturn;
	}
	
}
