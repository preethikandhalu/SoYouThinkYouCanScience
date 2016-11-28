package kandhalu.assign3.questions;

/**
 * @author Preethi Kandhalu
 * Class for questions
 */

public class Question {
	private String type;
	private String question;
	private String topicArea;
	private int points;
	private String correctAnswer;
	private boolean hasImage;
	private String imagePath;
	
	public Question(){
		this.type="";
		this.question="";
		this.topicArea="";
		this.points=0;
		this.correctAnswer="";
		this.hasImage=false;
		this.imagePath="";
	}
	
	/**
	 * Constructor takes all parameters to make a Question object
	 * @param type can be multiple choice or word
	 * @param question
	 * @param topicArea can be physics, chemistry or biology
	 * @param points points associated with the question 
	 * @param correctAnswer
	 * @param hasImage indicates whether question has image
	 * @param imagePath system path to image given question has image
	 */
	public Question(String type, String question, String topicArea, int points, String correctAnswer, boolean hasImage, String imagePath){
		this.type=type;
		this.question=question;
		this.topicArea=topicArea;
		this.points=points;
		this.correctAnswer=correctAnswer;
		this.hasImage=hasImage;
		this.imagePath=imagePath;
	}
	
	/**
	 * @return type of question. Can be Multiple Choice or Word
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * @return question
	 */
	public String getQuestion(){
		return this.question;
	}
	
	/**
	 * @return the topic of the question
	 */
	public String getTopic(){
		return this.topicArea;
	}
	
	/**
	 * @return points of the question
	 */
	public int getPoints(){
		return this.points;
	}
	
	/**
	 * @return the correct answer
	 */
	public String getCorrectAnswer(){
		return this.correctAnswer;
	}
	
	/**
	 * @return if the question has an image associated with it
	 */
	public boolean hasImage(){
		return this.hasImage;
	}
	
	/**
	 * @return image path given the question has an image associated with it
	 */
	public String getImagePath(){
		return this.imagePath;
	}
	
	/**
	 * @override
	 */
	public String toString(){
		String toReturn = "Type: " + this.type +
						  "\nQuestion: " + this.question +
						  "\nTopic Area: " + this.topicArea +
						  "\nPoints: " + String.valueOf(this.points) +
						  "\nCorrect answer: " + this.correctAnswer +
						  "\nhasImage: " + String.valueOf(this.hasImage) +
						  "\nImage Path: " + this.imagePath; 
		return toReturn;
	}
}