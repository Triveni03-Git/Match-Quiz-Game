import java.awt.*;
import javax.swing.*;

public class start extends JFrame {
    private Button startButton;
    private Button onePlayerButton;
    private Button twoPlayerButton;
    private Button arithmeticQuizButton;
    private Button relationalQuizButton;
    private Button logicalQuizButton;
    private JPanel startPanel;
    private JPanel playerPanel;
    private JPanel onePlayerQuizPanel;
    private JPanel twoPlayerQuizPanel;
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public start() {
        init();
    }

    public void init() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Start Panel
        startPanel = new JPanel();
        startButton = new Button("Start!!");
        startButton.addActionListener(e -> showPlayerPanel());
        startPanel.add(startButton);

        // Player Selection Panel
        playerPanel = new JPanel();
        onePlayerButton = new Button("One Player");
        onePlayerButton.addActionListener(e -> showOnePlayerQuizPanel());

        twoPlayerButton = new Button("Two Players");
        twoPlayerButton.addActionListener(e -> showTwoPlayerQuizPanel());

        playerPanel.add(onePlayerButton);
        playerPanel.add(twoPlayerButton);

        // One Player Quiz Selection Panel
        onePlayerQuizPanel = new JPanel();
        arithmeticQuizButton = new Button("Arithmetic Quiz");
        arithmeticQuizButton.addActionListener(e -> startArithmeticOnePlayerQuiz());

        relationalQuizButton = new Button("Relational Quiz");
        relationalQuizButton.addActionListener(e -> startRelationalOnePlayerQuiz());

        logicalQuizButton = new Button("Logical Quiz");
        logicalQuizButton.addActionListener(e -> startLogicalOnePlayerQuiz());

        onePlayerQuizPanel.add(arithmeticQuizButton);
        onePlayerQuizPanel.add(relationalQuizButton);
        onePlayerQuizPanel.add(logicalQuizButton);

        // Two Player Quiz Selection Panel
        twoPlayerQuizPanel = new JPanel();
        arithmeticQuizButton = new Button("Arithmetic Quiz");
        arithmeticQuizButton.addActionListener(e -> startArithmeticTwoPlayersQuiz());

        relationalQuizButton = new Button("Relational Quiz");
        relationalQuizButton.addActionListener(e -> startRelationalTwoPlayersQuiz());

        logicalQuizButton = new Button("Logical Quiz");
        logicalQuizButton.addActionListener(e -> startLogicalTwoPlayersQuiz());

        twoPlayerQuizPanel.add(arithmeticQuizButton);
        twoPlayerQuizPanel.add(relationalQuizButton);
        twoPlayerQuizPanel.add(logicalQuizButton);

        // Adding panels to CardLayout
        mainPanel.add(startPanel, "Start Panel");
        mainPanel.add(playerPanel, "Player Panel");
        mainPanel.add(onePlayerQuizPanel, "One Player Quiz Panel");
        mainPanel.add(twoPlayerQuizPanel, "Two Player Quiz Panel");
        add(mainPanel);
        cardLayout.show(mainPanel, "Start Panel");

        // JFrame settings
        setTitle("Quiz Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showPlayerPanel() {
        cardLayout.show(mainPanel, "Player Panel");
    }

    private void showOnePlayerQuizPanel() {
        cardLayout.show(mainPanel, "One Player Quiz Panel");
    }

    private void showTwoPlayerQuizPanel() {
        cardLayout.show(mainPanel, "Two Player Quiz Panel");
    }

    private void startArithmeticOnePlayerQuiz() {
        new arithemticOnePlayer();
    }

    private void startRelationalOnePlayerQuiz() {
        new relationalOnePlayer();
    }

    private void startLogicalOnePlayerQuiz() {
      new logicalOnePlayer();
    }

    private void startArithmeticTwoPlayersQuiz() {
      new arithemticTwoPlayers();
    }

    private void startRelationalTwoPlayersQuiz() {
      new relationalTwoPlayers();
    }

    private void startLogicalTwoPlayersQuiz() {
      new logicalTwoPlayers();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(start::new);
    }
}
