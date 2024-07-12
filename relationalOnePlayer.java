import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class relationalOnePlayer extends JFrame implements ActionListener {
    int playerScore = 0;
    int questionCount = 0;
    boolean correctAnswer;
    String question = "";
    JTextField playerInput;
    JLabel questionLabel, resultLabel, playerLabel, scoreLabel;
    JButton nextButton;
    JButton restartButton;
    JButton quitButton;
    JButton enterButton;
    Random random = new Random();
    
    public relationalOnePlayer() {
        setTitle("Relational Quiz Game");
        setSize(500, 500);
        setLayout(new GridLayout(20, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        questionLabel = new JLabel("Question will appear here.");
        add(questionLabel);
        
        playerLabel = new JLabel("Player answer (true/false):");
        add(playerLabel);
        playerInput = new JTextField();
        add(playerInput);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
        add(enterButton);


        nextButton = new JButton("Skip Question");
        nextButton.addActionListener(this);
        add(nextButton);
        
        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
        add(restartButton);
        restartButton.setVisible(false);

        quitButton = new JButton("Quit Game");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(quitButton);

        resultLabel = new JLabel("");
        add(resultLabel);
        
        scoreLabel = new JLabel("");
        add(scoreLabel);
        
        nextQuestion();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (questionCount < 5) {
            nextQuestion();
        }
    }
    
    public void calculateResult() {
        if (!playerInput.getText().isEmpty()) {
              String input1 = playerInput.getText().trim().toLowerCase();
             
              if (!(input1.equals("true") || input1.equals("false"))) {
                resultLabel.setText("Please enter boolean values (true/false)");
                  return;
              }
      
              boolean p1 = Boolean.parseBoolean(input1);
        
              String result = "";
                
              if (p1 == correctAnswer) {
                  result += "Correct answer!! ";
                  playerScore++;
              } 
              else {
                  result += "Player your answer was incorrect: " + p1 + ". The correct answer is " + correctAnswer + ". ";
              }
                
              resultLabel.setText(result);
              questionCount++;
                
              if (questionCount < 5) {
                  nextQuestion();
              } else {
                  displayFinalScore();
                  enterButton.setEnabled(false);
                  nextButton.setEnabled(false);
                  restartButton.setVisible(true);
              }
                
            }
    } 
    
    
    
    public void nextQuestion() {
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        int operation = random.nextInt(6) + 1; 
        
        switch (operation) {
            case 1:
                correctAnswer = num1>num2;
                question = num1 + " > " + num2 + " ?";
                break;
            case 2:
                correctAnswer = num1<num2;
                question = num1 + " < " + num2 + " ?";
                break;
            case 3:
                correctAnswer = num1==num2;
                question = num1 + " == " + num2 + " ?";
                break;
            case 4:
                correctAnswer = num1!=num2;
                question = num1 + " != " + num2 + " ?";
                break;
            case 5:
                correctAnswer = num1>=num2;
                question = num1 + " >= " + num2 + " ?";
                break;
            case 6:
                correctAnswer = num1<=num2;
                question = num1 + " <= " + num2 + " ?";
                break;
        }
        
        questionLabel.setText("Question " + (questionCount + 1) + ": " + question);
        playerInput.setText("");
        
    }
    
    public void displayFinalScore() {
        String finalScore = "You got " + playerScore + " out of 5 questions correct"; 
        
        scoreLabel.setText(finalScore);
    }
    
    public void restartGame() {
        playerScore = 0;
        questionCount = 0;
        resultLabel.setText("");
        scoreLabel.setText("");
        nextButton.setEnabled(true);
        enterButton.setEnabled(true);
        restartButton.setVisible(false);
        nextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new relationalOnePlayer();
            }
        });
    }
}


