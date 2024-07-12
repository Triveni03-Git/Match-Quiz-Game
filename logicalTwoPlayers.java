import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class logicalTwoPlayers extends JFrame implements ActionListener {
    int playerOneScore = 0;
    int playerTwoScore = 0;
    int questionCount = 0;
    boolean correctAnswer;
    String question = "";
    JTextField playerOneInput, playerTwoInput;
    JLabel questionLabel, resultLabel, playerOneLabel, playerTwoLabel, scoreLabel;
    JButton nextButton;
    JButton restartButton;
    JButton quitButton;
    JButton enterButton;
    Random random = new Random();
    
    public logicalTwoPlayers() {
        setTitle("Logical Quiz Game");
        setSize(500, 500);
        setLayout(new GridLayout(20, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        questionLabel = new JLabel("Question will appear here.");
        add(questionLabel);
        
        playerOneLabel = new JLabel("Player 1 answer (true/false):");
        add(playerOneLabel);
        playerOneInput = new JTextField();
        add(playerOneInput);
        
        playerTwoLabel = new JLabel("Player 2 answer (true/false):");
        add(playerTwoLabel);
        playerTwoInput = new JTextField();
        add(playerTwoInput);

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
        if (!playerOneInput.getText().isEmpty() && !playerTwoInput.getText().isEmpty()) {
              String input1 = playerOneInput.getText().trim().toLowerCase();
              String input2 = playerTwoInput.getText().trim().toLowerCase();
    
              if (!(input1.equals("true") || input1.equals("false"))&&(!(input2.equals("true") || input2.equals("false")))) {
                resultLabel.setText("Player 1 and Player 2 Please enter boolean values (true/false)");
                return;
              }

              if (!(input1.equals("true") || input1.equals("false"))) {
                resultLabel.setText("Player 1: Please enter boolean values (true/false)");
                  return;
              }
              if (!(input2.equals("true") || input2.equals("false"))) {
                resultLabel.setText("Player 2: Please enter boolean values (true/false)");
                  return;
              }
              
      
              boolean p1 = Boolean.parseBoolean(input1);
              boolean p2 = Boolean.parseBoolean(input2);
              String result = "";
                
              if (p1 == correctAnswer) {
                  result += "Correct answer by Player1! ";
                  playerOneScore++;
              } 
              else {
                  result += "Player1 answer was incorrect: " + p1 + ". The correct answer is " + correctAnswer + ". ";
              }
                
                
              if (p2 == correctAnswer) {
                  result += "Correct answer by Player2!";
                  playerTwoScore++;
              }
              else {
                  result += "Player2 answer was incorrect: " + p2 + ". The correct answer is " + correctAnswer + ".";
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
      int operation = random.nextInt(2); // 0 to 4
        
      boolean num1 = random.nextBoolean();
      boolean num2 = random.nextBoolean();
      
      switch (operation) {
          case 0:
              correctAnswer = num1 & num2;
              question = num1 + " & " + num2 + " = ?";
              break;
          case 1:
              correctAnswer = num1 | num2;
              question = num1 + " | " + num2 + " = ?";
              break;
          case 2:
              correctAnswer = num1 ^ num2;
              question = num1 + " ^ " + num2 + " = ?";
              break;
      }

        questionLabel.setText("Question " + (questionCount + 1) + ": " + question);
        playerOneInput.setText("");
        playerTwoInput.setText("");
    }
    
    public void displayFinalScore() {
        String finalScore = "Player1 got " + playerOneScore + " out of 5 questions correct. Player2 got " + playerTwoScore + " out of 5 questions correct. ";
        
        if (playerOneScore > playerTwoScore) {
            finalScore += "The Player1! WON!!! with the highest score: " + playerOneScore;
        } else if (playerOneScore < playerTwoScore) {
            finalScore += "The Player2 WON!!! with the highest score: " + playerTwoScore;
        } else {
            finalScore += "Player1 and Player2 got TIED!!!: " + playerOneScore;
        }
        
        scoreLabel.setText(finalScore);
    }
    
    public void restartGame() {
        playerOneScore = 0;
        playerTwoScore = 0;
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
                new logicalTwoPlayers();
            }
        });
    }
}

