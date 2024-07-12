import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class arithemticTwoPlayers extends JFrame implements ActionListener {
    int playerOneScore = 0;
    int playerTwoScore = 0;
    int questionCount = 0;
    float correctAnswer = 0;
    String question = "";
    JTextField playerOneInput, playerTwoInput;
    JLabel questionLabel, resultLabel, playerOneLabel, playerTwoLabel, scoreLabel;
    JButton nextButton;
    JButton backButton;
    JButton restartButton;
    JButton quitButton;
    JButton enterButton;
    CardLayout cardLayout;
    Random random = new Random();
    
    public arithemticTwoPlayers() {
        setTitle("Math Quiz Game");
        setSize(500, 500);
        setLayout(new GridLayout(20, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        questionLabel = new JLabel("Question will appear here.");
        add(questionLabel);
        
        playerOneLabel = new JLabel("Player 1 answer:");
        add(playerOneLabel);
        playerOneInput = new JTextField();
        add(playerOneInput);
        
        playerTwoLabel = new JLabel("Player 2 answer:");
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
            try {
                int p1 = Integer.parseInt(playerOneInput.getText());
                int p2 = Integer.parseInt(playerTwoInput.getText());
                
                String result = "";
                
                if (p1 == correctAnswer) {
                    result += "Correct answer by Player1! ";
                    playerOneScore++;
                } else {
                    result += "Player1 answer was incorrect: " + p1 + ". The correct answer is " + correctAnswer + ". ";
                }
                
                
                if (p2 == correctAnswer) {
                    result += "Correct answer by Player2!";
                    playerTwoScore++;
                } else {
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
                
            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers.");
            }
        }
    }
    
    public void nextQuestion() {
        int num1 = random.nextInt(10) + 1;
        int num2 = random.nextInt(10) + 1;
        int operation = random.nextInt(4) + 1; 
        
        switch (operation) {
            case 1:
                correctAnswer = num1 + num2;
                question = num1 + " + " + num2 + " = ?";
                break;
            case 2:
                correctAnswer = num1 - num2;
                question = num1 + " - " + num2 + " = ?";
                break;
            case 3:
                correctAnswer = num1 * num2;
                question = num1 + " * " + num2 + " = ?";
                break;
            case 4:
                correctAnswer = num1 / num2;
                question = num1 + " / " + num2 + " = ?";
                break;
        }
        
        questionLabel.setText("Question " + (questionCount + 1) + ": " + question);
        playerOneInput.setText("");
        playerTwoInput.setText("");
    }
    
    public void displayFinalScore() {
        String finalScore = "Player1 got " + playerOneScore + " out of 5 questions correct. Player2 got " + playerTwoScore + " out of 5 questions correct. ";
        
        if (playerOneScore > playerTwoScore) {
            finalScore += "The Player1! WON!!! the game with the highest score: " + playerOneScore;
        } else if (playerOneScore < playerTwoScore) {
            finalScore += "The Player2 WON!!! the game with the highest score: " + playerTwoScore;
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
        restartButton.setVisible(false);
        nextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new arithemticTwoPlayers();
            }
        });
    }
}
