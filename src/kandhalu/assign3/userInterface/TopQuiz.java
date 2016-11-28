package kandhalu.assign3.userInterface;

/**
 * @author Preethi Kandhalu
 * Object Oriented Analysis, Design and Programming
 * Fall 2017
 * Assignment 3
 * 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import kandhalu.assign3.questions.*;

import java.io.IOException;


public class TopQuiz {

	private JFrame frame;
	private int totalAttempted, totalCorrect, totalPoints;
	private int phyCorrect, chemCorrect, bioCorrect;
	private QuestionBank qb;
	private Question questionCurrentlyDisplayed;
	private JLabel lblTotalattemptedResult, lblTotalCorrectResult, lblTotalPointsResult;
	private JRadioButton rdbtnPhysics, rdbtnChemistry, rdbtnBiology;
	private JPanel questionPanel;
	private ButtonGroup topicsGrp;
	private JTextField wordAnswer;
	private ButtonGroup mcAnswers;
	private JRadioButton choice1, choice2, choice3, choice4;
	private Timer timer;
	private int count;
	private JLabel timerLabel;
	private TimerListener timerListener;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopQuiz window = new TopQuiz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}}});}

	public TopQuiz() throws IOException {
		qb = new QuestionBank();
		qb.loadQuestions("questions.txt");
		questionCurrentlyDisplayed = qb.getQuestion("chemistry");
		this.totalAttempted=0;
		this.totalCorrect=0;
		this.totalPoints=0;
		this.phyCorrect=0;
		this.chemCorrect=0;
		this.bioCorrect=0;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("So You Think You Can Science");
		frame.setBounds(100, 100, 900, 600);
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		timerListener = new TimerListener();
		timer = new Timer(1000, timerListener);
		
		initializeTitlePanel();
		initializeQuestionPanel();
		initializeTopicsPanel();
		initializeSessionStatisticsPanel();
	}

	/**
	 * Initializes the title panel
	 */
	private void initializeTitlePanel(){
		JPanel title = new JPanel();
		frame.getContentPane().add(title, BorderLayout.NORTH);
		title.setBackground(Color.BLACK);
		JLabel titleLabel = new JLabel("So you think you can Science?");
		titleLabel.setForeground(new Color(0, 191, 255));
		titleLabel.setFont(new Font("Pristina", Font.BOLD, 120));
		title.add(titleLabel);
	}

	/**
	 * Topics Panel
	 * The topics of the quiz are listed in this panel
	 * Question will be displayed in the question panel based on the topic chosen in the Topics panel 
	 */
	private void initializeTopicsPanel(){
		JPanel topicsPanel = new JPanel();
		topicsPanel.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(topicsPanel, BorderLayout.WEST);
		rdbtnPhysics = new JRadioButton("Physics");
		rdbtnPhysics.setForeground(new Color(138, 43, 226));
		rdbtnPhysics.setFont(new Font("Pristina", Font.BOLD, 45));
		rdbtnPhysics.setBackground(new Color(0, 0, 0));
		rdbtnChemistry = new JRadioButton("Chemistry");
		rdbtnChemistry.setForeground(new Color(219, 112, 147));
		rdbtnChemistry.setFont(new Font("Pristina", Font.BOLD, 45));
		rdbtnChemistry.setBackground(new Color(0, 0, 0));
		rdbtnBiology = new JRadioButton("Biology");
		rdbtnBiology.setForeground(new Color(144, 238, 144));
		rdbtnBiology.setFont(new Font("Pristina", Font.BOLD, 45));
		rdbtnBiology.setBackground(new Color(0, 0, 0));
		rdbtnPhysics.setActionCommand("Physics");
		rdbtnChemistry.setActionCommand("Chemistry");
		rdbtnBiology.setActionCommand("Biology");
		topicsPanel.add(rdbtnPhysics);
		topicsPanel.add(rdbtnChemistry);
		topicsPanel.add(rdbtnBiology);
		topicsPanel.setPreferredSize(new Dimension(180,480));
		topicsGrp = new ButtonGroup();
		topicsGrp.add(rdbtnBiology);
		topicsGrp.add(rdbtnChemistry);
		topicsGrp.add(rdbtnPhysics);
		TopicButtonHandler handler = new TopicButtonHandler();
		rdbtnPhysics.addActionListener(handler);
		rdbtnChemistry.addActionListener(handler);
		rdbtnBiology.addActionListener(handler);
	}

	/**
	 * Question Panel
	 * Displays randomly chosen multiple choice/word questions depending on topic chosen in Topics panel
	 */
	private void initializeQuestionPanel(){	
		questionPanel = new JPanel();
		questionPanel.setBackground(Color.BLACK);
		JLabel pleaseChoose = new JLabel("Please choose a topic to start playing!");
		pleaseChoose.setForeground(new Color(178, 34, 34));
		pleaseChoose.setFont(new Font("Pristina", Font.BOLD, 38));
		
		JLabel rules = new JLabel("Remember you've 30 seconds for each question :)");
		rules.setForeground(new Color(178, 34, 34));
		rules.setFont(new Font("Pristina", Font.BOLD, 38));
		
		questionPanel.add(pleaseChoose);
		questionPanel.add(rules);
		frame.getContentPane().add(questionPanel, BorderLayout.CENTER);
	}

	/**
	 * Session Statistics Panel
	 * Panel displays number of questions answered, correct number of questions and total score
	 * Panel also has a button When pressed, shows a bar graph with 3 bars, each representing a topic, and 
	 * height of each bar represents the number of questions answered correctly in that category  
	 */
	private void initializeSessionStatisticsPanel(){
		
		JPanel SessionStatistics = new JPanel();
		SessionStatistics.setBackground(new Color(0, 0, 0));
		frame.getContentPane().add(SessionStatistics, BorderLayout.SOUTH);
		JButton barGraph = new JButton("Show bar graph");
		BarGraph bg = new BarGraph();
		barGraph.addActionListener(bg);
		JLabel lblTotalAttempted = new JLabel("Total Attempted:  ");
		lblTotalAttempted.setForeground(new Color(218, 165, 32));
		lblTotalAttempted.setFont(new Font("Pristina", Font.BOLD, 50));

		lblTotalattemptedResult = new JLabel("0");
		lblTotalattemptedResult.setForeground(new Color(218, 165, 32));
		lblTotalattemptedResult.setFont(new Font("Pristina", Font.BOLD, 50));

		JLabel lblTotalCorrect = new JLabel("    Total Correct:  ");
		lblTotalCorrect.setForeground(new Color(218, 165, 32));
		lblTotalCorrect.setFont(new Font("Pristina", Font.BOLD, 50));

		lblTotalCorrectResult = new JLabel("0");
		lblTotalCorrectResult.setForeground(new Color(218, 165, 32));
		lblTotalCorrectResult.setFont(new Font("Pristina", Font.BOLD, 50));

		JLabel lblTotalPoints = new JLabel("    Total Points:  ");
		lblTotalPoints.setForeground(new Color(218, 165, 32));
		lblTotalPoints.setFont(new Font("Pristina", Font.BOLD, 50));

		lblTotalPointsResult = new JLabel("0");
		lblTotalPointsResult.setForeground(new Color(218, 165, 32));
		lblTotalPointsResult.setFont(new Font("Pristina", Font.BOLD, 50));

		SessionStatistics.add(barGraph);
		SessionStatistics.add(lblTotalAttempted);
		SessionStatistics.add(lblTotalattemptedResult);
		SessionStatistics.add(lblTotalCorrect);
		SessionStatistics.add(lblTotalCorrectResult);
		SessionStatistics.add(lblTotalPoints);
		SessionStatistics.add(lblTotalPointsResult);

	}

	 /**
	  * Method takes care of dynamically displaying question on Question Panel based on
	  * @param question chosen on Topics panel 
	  */
	public void displayQuestion(Question question){
		if (timer!=null) timer.stop();
		
		questionPanel.removeAll();
		questionPanel.setBackground(Color.BLACK);
		questionPanel.setLayout(new GridBagLayout());
		frame.getContentPane().add(questionPanel, BorderLayout.CENTER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		JLabel questionLabel = new JLabel(question.getQuestion());
		questionLabel.setFont(new Font("Pristina", Font.BOLD, 40));
		questionLabel.setForeground(new Color(255,105,180));
		questionPanel.add(questionLabel, gbc);

		if (question.getType().equalsIgnoreCase("Word")){
			wordAnswer = new JTextField();
			wordAnswer.setColumns(10);
			gbc.gridy++;
			questionPanel.add(wordAnswer, gbc);
			wordAnswer.setFont(new Font("Pristina", Font.BOLD, 40));
		}

		if (question.getType().equalsIgnoreCase("MC")){
			String [] choices = new String[4];
			choices = ((MultipleChoiceQuestion)question).getChoices();
			choice1= new JRadioButton(choices[0]);
			choice1.setActionCommand(choices[0]);
			choice1.setFont(new Font("Pristina", Font.BOLD, 40));
			choice1.setForeground(new Color(255,105,180));
			choice1.setBackground(new Color(0, 0, 0));

			choice2= new JRadioButton(choices[1]);
			choice2.setActionCommand(choices[1]);
			choice2.setFont(new Font("Pristina", Font.BOLD, 40));
			choice2.setForeground(new Color(255,105,180));
			choice2.setBackground(new Color(0, 0, 0));

			choice3= new JRadioButton(choices[2]);
			choice3.setActionCommand(choices[2]);
			choice3.setFont(new Font("Pristina", Font.BOLD, 40));
			choice3.setForeground(new Color(255,105,180));
			choice3.setBackground(new Color(0, 0, 0));

			choice4= new JRadioButton(choices[3]);
			choice4.setActionCommand(choices[3]);
			choice4.setFont(new Font("Pristina", Font.BOLD, 40));
			choice4.setForeground(new Color(255,105,180));
			choice4.setBackground(new Color(0, 0, 0));

			mcAnswers = new ButtonGroup();
			mcAnswers.add(choice1);
			mcAnswers.add(choice2);
			mcAnswers.add(choice3);
			mcAnswers.add(choice4);
			gbc.gridy++;
			questionPanel.add(choice1, gbc);
			gbc.gridy++;
			questionPanel.add(choice2, gbc);
			gbc.gridy++;
			questionPanel.add(choice3, gbc);
			gbc.gridy++;
			questionPanel.add(choice4, gbc);
		}

		JButton btnNextQuestion = new JButton("Next Question");
		gbc.gridy++;
		questionPanel.add(btnNextQuestion, gbc);
		NextQuestion nextQuestion = new NextQuestion();
		btnNextQuestion.addActionListener(nextQuestion);

		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Pristina", Font.BOLD, 50));
		timerLabel.setForeground(Color.WHITE);

		count=30;

		timer.start();
		gbc.gridy++;
		questionPanel.add(timerLabel, gbc);
		questionPanel.revalidate();
		questionPanel.repaint();
		frame.setVisible(true);
	}	
	
	/**
	 * Inner class for timer
	 * @author Preethi Kandhalu
	 */
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count>0){
				timerLabel.setText("Time Remaining: " + String.valueOf(count));
				timerLabel.setForeground(Color.GREEN);
			}
			else {
				timerLabel.setText("Time Remaining: " + String.valueOf(count));
				timerLabel.setForeground(Color.RED);
			}
			count--;
		}
	}

	/**
	 * Inner class for handling events from Topics Panel
	 * @author Preethi Kandhalu
	 */
	private class TopicButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event ){
			String topic = event.getActionCommand();
			Question toDisplay = qb.getQuestion(topic);
			questionCurrentlyDisplayed = toDisplay;
			displayQuestion(toDisplay);
			qb.removeQuestion(toDisplay);
		}
	}

	/**
	 * Inner class for handling "next question" button 
	 * @author Preethi Kandhalu
	 */
	private class NextQuestion implements ActionListener {
		/**
		 * Processes current question in display and decides question to be displayed next
		 */
		public void actionPerformed(ActionEvent event ){
			if (count>0){		//Process the question if question was answered within alloted time
				processQuestion();
			}
			//Display the next question and update questionCurrentlyDisplayed variable
			Question questionToDisplay = qb.getQuestion(questionCurrentlyDisplayed.getTopic());
			displayQuestion(questionToDisplay);
			qb.removeQuestion(questionToDisplay);
			questionCurrentlyDisplayed = questionToDisplay;
		}

		/**
		 * Method processes currently displayed question and updates the Session Statistics Panel
		 * Also notes the topic of the currently displayed question, if correctly answered
		 * Data will be used to build bar graph
		 */
		private void processQuestion(){
			//Process the question and update the session statistics panel
			totalAttempted++;
			String answer="";
			//Get answer entered in text field/chosen depending on type of question
			if (questionCurrentlyDisplayed.getType().equalsIgnoreCase("MC")){
				if (choice1.isSelected()==true) answer = choice1.getActionCommand();
				if (choice2.isSelected()==true) answer = choice2.getActionCommand();
				if (choice3.isSelected()==true) answer = choice3.getActionCommand();
				if (choice4.isSelected()==true) answer = choice4.getActionCommand();
			}
			if (questionCurrentlyDisplayed.getType().equalsIgnoreCase("Word")){
				answer = wordAnswer.getText();
			}
			if (questionIsCorrect(answer)==true){
				totalCorrect++;
				totalPoints+=questionCurrentlyDisplayed.getPoints();
				if (questionCurrentlyDisplayed.getTopic().equalsIgnoreCase("Physics")) phyCorrect++;
				if (questionCurrentlyDisplayed.getTopic().equalsIgnoreCase("Chemistry")) chemCorrect++;
				if (questionCurrentlyDisplayed.getTopic().equalsIgnoreCase("Biology")) bioCorrect++;
			}
			//Update labels in session statistics panel
			lblTotalattemptedResult.setText(String.valueOf(totalAttempted));
			lblTotalCorrectResult.setText(String.valueOf(totalCorrect));
			lblTotalPointsResult.setText(String.valueOf(totalPoints));
		}

		/**
		 * Method to check if answer of question currently displayed is correct
		 */
		private boolean questionIsCorrect(String answer){
			if (questionCurrentlyDisplayed.getCorrectAnswer().equalsIgnoreCase(answer)){
				return true;
			}
			return false;
		}
	}

	/**
	 * Inner class for handing "show bar graph" button
	 * @author Preethi Kandhalu
	 */
	private class BarGraph implements ActionListener {
		public void actionPerformed(ActionEvent event){
			BarGraphCreator graph = new BarGraphCreator();
		}
	}

	/**
	 * Inner class for creating Bar Graph
	 * @author Preethi Kandhalu
	 */
	private class BarGraphCreator {
		private JFrame scoreFrame;

		public BarGraphCreator (){
			scoreFrame = new JFrame("Result by category");
			scoreFrame.setBounds(100, 100, 500, 500);
			
			BarChart chart = new BarChart();
			chart.setBackground(Color.WHITE);
			chart.setPreferredSize(new Dimension(300,400));
			chart.addBar(Color.PINK, phyCorrect );
			chart.addBar(Color.BLACK, chemCorrect);
			chart.addBar(Color.RED, bioCorrect );
			scoreFrame.getContentPane().add(chart, BorderLayout.CENTER);
	
			JPanel panelMark = new JPanel();
			panelMark.setLayout(new BoxLayout(panelMark, BoxLayout.PAGE_AXIS));
			panelMark.setBackground(Color.WHITE);
			JLabel label1 = new JLabel("Physics");
			label1.setFont(new Font("Pristina", Font.BOLD, 20));
			label1.setForeground(Color.PINK);
			panelMark.add(label1);

			JLabel label2 = new JLabel("Chemistry");
			label2.setFont(new Font("Pristina", Font.BOLD, 20));
			label2.setForeground(Color.BLACK);
			panelMark.add(label2);

			JLabel label3 =new JLabel("Biology");
			label3.setFont(new Font("Pristina", Font.BOLD, 20));
			label3.setForeground(Color.RED);
			panelMark.add(label3);
			scoreFrame.getContentPane().add(panelMark, BorderLayout.WEST);
			scoreFrame.setVisible(true);
			scoreFrame.pack();
		}
	}
}
