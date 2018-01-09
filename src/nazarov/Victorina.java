package nazarov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Victorina {
    public static void main(String[] args) {
        VictorinaFrame frame = new VictorinaFrame();
        frame.setVisible(true);
        frame.setSize(300, 350);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}
class VictorinaFrame extends JFrame{
    public VictorinaFrame(){
        setTitle("Арифметическая викторина");
        VictorinaPanel panel = new VictorinaPanel();
        add(panel);
    }
}
class VictorinaPanel extends JPanel{
    JPanel center;
    JButton question, answer, round;
    JTextField input;
    Font topFont = new Font("Calibri", Font.BOLD, 20);
    Font inputFont = new Font("Calibri", Font.BOLD, 60);
    Font buttonFont = new Font("Calibri", Font.BOLD, 30);
    private int countRounds;
    private int x, y, solution, lastSolution, operation, check;
    private String quest;
    int[] randomInt = new int [99];
    int[] randomOperations = {0, 1, 2};
    Random rand = new Random();

    public VictorinaPanel(){
        ActionListener victorina = new VictorinaListener();
        setLayout(new BorderLayout());

        center = new JPanel();
        center.setLayout(new GridLayout(4,1));

        question = new JButton();
        question.setEnabled(false);
        question.addActionListener(victorina);
        question.setText("Для игры нажмите Начать");
        question.setFont(topFont);
        center.add(question);

        input = new JTextField();
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setText(String.valueOf(0));
        input.setFont(inputFont);
        center.add(input);

        answer = new JButton();
        answer.setText("Начать");
        answer.setFont(buttonFont);
        answer.addActionListener(victorina);
        center.add(answer);

        round = new JButton();
        round.setEnabled(false);
        round.setText("Викторина");
        round.setFont(buttonFont);
        center.add(round);

        add(center, BorderLayout.CENTER);
    }
    private class VictorinaListener implements ActionListener{
        long startTime = System.currentTimeMillis();

        public void actionPerformed(ActionEvent e) {
        try {
            question.setFont(inputFont);
            for (int i = 0; i < randomInt.length; i++) {
                randomInt[i] = i;
            }
            x = randomInt[rand.nextInt(99)];
            y = randomInt[rand.nextInt(99)];

            operation = randomOperations[rand.nextInt(2)];//

            if (e.getSource() == answer) {

                check = Integer.parseInt(input.getText());
                lastSolution = solution;

                if (operation == 0) {
                    solution = x + y;
                    quest = x + " + " + y;
                } else if (operation == 1) {
                    solution = x - y;
                    quest = x + " - " + y;
                }
//                else if (operation == 2){
//                    solution = x * y;             //Если надо добавить в викторину умножения
//                    quest = x + " * " + y;
//                }
//                else if (operation == 3){
//                    solution = x / y;             //Если надо добавить в викторину деления
//                    quest = x + " / " + y;
//                }

                answer.setText("Ответить");
                question.setText(String.valueOf(quest));
                input.setText(String.valueOf(0));

                if (countRounds >= 1) {
                    if (check == lastSolution) {
                        JOptionPane.showMessageDialog(null, "Правильный ответ");
                        input.setText(String.valueOf(0));
                        round.setText("Раунд " + countRounds);
                    } else {
                        countRounds = 0;
                        JOptionPane.showMessageDialog(null, "<html><table width=150>" + "Неправильный ответ GAME OVER");
                        input.setText(String.valueOf(0));
                        round.setText("");
                    }
                    if (countRounds == 5) {
                        long timeSpent = System.currentTimeMillis() - startTime;
                        JOptionPane.showMessageDialog(null, "<html><table width=150>" + " VICTORY! " + " Вы прошли викторину за " + timeSpent / 1000 + " секунд");
                        countRounds = 0;
                        input.setText(String.valueOf(0));
                        round.setText("");
                    }
                }
                countRounds++;
                round.setText("Раунд " + countRounds);
            }
        }
             catch(NumberFormatException nfe){
                 JOptionPane.showMessageDialog(null,"Введите ответ");
            }
        }
    }
}