package kandhalu.assign3.questions;

/**
 * @author Preethi Kandhalu
 * Class serves as a bank of questions
 * Reads questions.txt from persistence and loads the questions into banks based on the topic
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionBank {
	//Question bank for each of the topics of the quiz
	private ArrayList<Question> physics;
	private ArrayList<Question> chemistry;
	private ArrayList<Question> biology;
	
	/**
	 * Constructor
	 */
	public QuestionBank(){
		physics = new ArrayList<Question>();
		chemistry = new ArrayList<Question>();
		biology = new ArrayList<Question>();
	}
	
	/**
	 * Parses through @param filename containing questions and loads the questions 
	 * into the question banks depending upon the topic
	 */
	public void loadQuestions (String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
	    String line;
	    Scanner s; 
	    String [] tokens;
	    while ((line = br.readLine()) != null) {
	    	s = new Scanner(line);
	   		s.useDelimiter(":");
	   		tokens = new String[8];
	   		int i=0;
	   		while (s.hasNext()){
		   		tokens[i]=s.next();
		   		i++;
	   		}
	   		
	   		if (tokens[0].equalsIgnoreCase("MC")){		//Question is of type multiple choice
		   		Question mcTemp = getMultipleChoiceQuestion(tokens);
		   		if (mcTemp.getTopic().equalsIgnoreCase("PHYSICS")){
		   			this.physics.add(mcTemp);
		   		}
		   		if (mcTemp.getTopic().equalsIgnoreCase("CHEMISTRY")){
		   			this.chemistry.add(mcTemp);
		   		}
		   		if (mcTemp.getTopic().equalsIgnoreCase("BIOLOGY")){
		   			this.biology.add(mcTemp);
		   		}
	   		}
	   		
	   		if (tokens[0].equalsIgnoreCase("word")){		//Question is of type one word
		   		Question oneWordTemp = getOneWordQuestion(tokens);
		   		if (oneWordTemp.getTopic().equalsIgnoreCase("PHYSICS")){
		   			this.physics.add(oneWordTemp);
		   		}
		   		if (oneWordTemp.getTopic().equalsIgnoreCase("CHEMISTRY")){
		   			this.chemistry.add(oneWordTemp);
		   		}
		   		if (oneWordTemp.getTopic().equalsIgnoreCase("BIOLOGY")){
		   			this.biology.add(oneWordTemp);
		   		}
	   		}
	   		s.close();
	    }
	    br.close();
	 }
	
	/**
	 * @return MultipleChoiceQuestion object using @param tokens
	 */
	private MultipleChoiceQuestion getMultipleChoiceQuestion(String[] tokens){
		String type = tokens[0];
		String topic= tokens[1];
		String question = tokens[2];
		String correctAnswer = tokens[3];
		int points = Integer.parseInt(tokens[4]);
		String choices = tokens[5];
		boolean hasImage = Boolean.parseBoolean(tokens[6]);
		String imagePath = tokens[7];
		MultipleChoiceQuestion temp = new MultipleChoiceQuestion(type, topic, question,correctAnswer, points, choices, hasImage, imagePath);
		return temp;
	}
	
	/**
	 * @return OneWordQuestion object using @param tokens
	 */
	private OneWordQuestion getOneWordQuestion(String[] tokens){
		String type = tokens[0];
		String topic = tokens[1];
		String question = tokens[2];
		String correctAnswer = tokens[3];
		int points = Integer.parseInt(tokens[4]);
		boolean hasImage = Boolean.parseBoolean(tokens[5]);
		String imagePath = tokens[6];
		OneWordQuestion temp = new OneWordQuestion(type, topic, question, correctAnswer, points, hasImage, imagePath);
		return temp;
	}
	
	/**
	 * Removes @param question from bank depending upon topic of question
	 */
	public void removeQuestion(Question question){
		if (question.getTopic().equalsIgnoreCase("Physics")){
			this.physics.remove(this.physics.indexOf(question));
		}
		if (question.getTopic().equalsIgnoreCase("Chemistry")){
			this.chemistry.remove(this.chemistry.indexOf(question));
		}
		if (question.getTopic().equalsIgnoreCase("Biology")){
			this.biology.remove(this.biology.indexOf(question));
		}
		
	}
	
	/**
	 * Methods prints out all the questions from the banks based on topic
	 */
	public void printQuestions(){
		System.out.println("======Physics questions======");
		for (Question each: this.physics){
			System.out.println(each.toString());
			System.out.println();
		}
		
		System.out.println("======Chemistry questions======");
		for (Question each: this.chemistry){
			System.out.println(each.toString());
			System.out.println();
		}
		
		System.out.println("======Biology questions======");
		for (Question each: this.biology){
			System.out.println(each.toString());
			System.out.println();
		}
	}
	
	/**
	 * @return random question based on @param topic
	 */
	public Question getQuestion(String topic){
		Question ret = new Question();
		if (topic.equalsIgnoreCase("PHYSICS")){
			int size = this.physics.size();
			Random rand = new Random();
			int randomNum = rand.nextInt(size);
			ret= this.physics.get(randomNum);
		}
		if (topic.equalsIgnoreCase("CHEMISTRY")){
			int size = this.chemistry.size();
			Random rand = new Random();
			int randomNum = rand.nextInt(size);
			ret= this.chemistry.get(randomNum);
		}
		if (topic.equalsIgnoreCase("BIOLOGY")){
			int size = this.biology.size();
			Random rand = new Random();
			int randomNum = rand.nextInt(size);
			ret= this.biology.get(randomNum);
		}
		return ret;
	}
}
