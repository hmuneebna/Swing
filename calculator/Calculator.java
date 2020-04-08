
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Calculator {
    public String lastOperation;
    public int currentNumber;
    int previousNumber;
    private JLabel jlbl = new JLabel("0");
    boolean newNumber = true;
    boolean error = false; 
    Calculator() {
        
        lastOperation = "";
        currentNumber = 0;
        previousNumber = 0;
    
        Font font1 = new Font("Courier", Font.PLAIN, 20);
        JFrame frame = new JFrame("Calculator");
        frame.setLocationRelativeTo(null);
        jlbl.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        jlbl.setBackground(Color.BLACK);
        jlbl.setFont(font1);
        jlbl.setForeground(Color.RED);
        jlbl.setHorizontalAlignment(JLabel.RIGHT);
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        panel.setLayout(new BorderLayout());
        panel2.setLayout(new GridLayout(4, 4));
        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("Calculator.png").getImage());
        JButton jbtn1 = new JButton();
        JButton jbtn2 = new JButton();
        JButton jbtn3 = new JButton();
        JButton jbtn4 = new JButton();
        JButton jbtn5 = new JButton();
        JButton jbtn6 = new JButton();
        JButton jbtn7 = new JButton();
        JButton jbtn8 = new JButton();
        JButton jbtn9 = new JButton();
        JButton jbtn0 = new JButton();
        JButton jbtnA = new JButton();
        JButton jbtnS = new JButton();
        JButton jbtnD = new JButton();
        JButton jbtnM = new JButton();
        JButton jbtnC = new JButton();
        JButton jbtnE = new JButton();
        jbtn1.setIcon(new ImageIcon("1.png"));
        jbtn2.setIcon(new ImageIcon("2.png"));
        jbtn3.setIcon(new ImageIcon("3.png"));
        jbtn4.setIcon(new ImageIcon("4.png"));
        jbtn5.setIcon(new ImageIcon("5.png"));
        jbtn6.setIcon(new ImageIcon("6.png"));
        jbtn7.setIcon(new ImageIcon("7.png"));
        jbtn8.setIcon(new ImageIcon("8.png"));
        jbtn9.setIcon(new ImageIcon("9.png"));
        jbtn0.setIcon(new ImageIcon("0.png"));
        
        /**
         * Listeners for the buttons
         */
        jbtn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("1");
            }}
        );
        jbtn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("2");
            }}
        );
        jbtn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("3");
            }}
        );
        jbtn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("4");
            }}
        );
        jbtn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("5");
            }}
        );
        jbtn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("6");
            }}
        );
        jbtn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("7");
            }}
        );
        jbtn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("8");
            }}
        );
        jbtn9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("9");
            }}
        );
        jbtn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNumber("0");
            }}
        );
        
        
        //Listeners for operations
        //sum
        jbtnA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = currentNumber;
                currentNumber = Integer.parseInt(jlbl.getText());
                if (lastOperation!="") {
                    operate(previousNumber, currentNumber, lastOperation);
                }
                lastOperation = "add";
                setWholeNumber(""+currentNumber); 
                newNumber = true;
            }}
        );
        
        //substract
        jbtnS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = currentNumber;
                currentNumber = Integer.parseInt(jlbl.getText());
                if (lastOperation!="") {
                    operate(previousNumber, currentNumber, lastOperation);
                }
                lastOperation = "rest";
                setWholeNumber(""+currentNumber); 
                newNumber = true;
            }}
        );
        
        //multiply
        jbtnM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = currentNumber;
                currentNumber = Integer.parseInt(jlbl.getText());
                if (lastOperation!="") {
                    operate(previousNumber, currentNumber, lastOperation);
                }
                lastOperation = "mult";
                setWholeNumber(""+currentNumber); 
                newNumber = true;
            }}
        );
        
        //divide
        jbtnD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = currentNumber;
                currentNumber = Integer.parseInt(jlbl.getText());
                if (lastOperation!="") {
                    operate(previousNumber, currentNumber, lastOperation);
                }
                lastOperation = "div";
                setWholeNumber(""+currentNumber); 
                newNumber = true;
            }}
        );
        
        
        //Equal
        jbtnE.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = currentNumber;
                currentNumber = Integer.parseInt(jlbl.getText());
                if (lastOperation!="") {
                    operate(previousNumber, currentNumber, lastOperation);
                }
                setWholeNumber(""+currentNumber); 
                if (error == true) {
                    setWholeNumber("OVERFLOW"); 
                    error = false;
                }
                currentNumber = 0;  //after equal restart, but leave the number result in the calculator
                previousNumber = 0;
                lastOperation = "";
            }}
        );
        
        //
        jbtnC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                previousNumber = 0;
                currentNumber = 0;
                setWholeNumber("0"); 
            }}
        );
        
        
        jbtnA.setIcon(new ImageIcon("plus.png"));
        jbtnS.setIcon(new ImageIcon("minus.png"));
        jbtnD.setIcon(new ImageIcon("divide.png"));
        jbtnM.setIcon(new ImageIcon("multiply.png"));
        jbtnC.setIcon(new ImageIcon("clear.png"));
        jbtnE.setIcon(new ImageIcon("equal.png"));
        panel.add(jlbl);
        panel2.add(jbtn7);
        panel2.add(jbtn8);
        panel2.add(jbtn9);
        panel2.add(jbtnD);
        panel2.add(jbtn4);
        panel2.add(jbtn5);
        panel2.add(jbtn6);
        panel2.add(jbtnM);
        panel2.add(jbtn1);
        panel2.add(jbtn2);
        panel2.add(jbtn3);
        panel2.add(jbtnS);
        panel2.add(jbtn0);
        panel2.add(jbtnC);
        panel2.add(jbtnE);
        panel2.add(jbtnA);
        
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }

        });

    }
    public  void setNumber(String number){
        if (jlbl.getText()=="0" || newNumber) {  //if it is a single zero it is going to be replaced with the new number
            jlbl.setText(number);
            newNumber = false;
        } else {
            jlbl.setText(jlbl.getText() + number);
        }
    }
    
    public  void setWholeNumber(String number){
            jlbl.setText(number);
    }
    
    public void operate (int number1, int number2, String operator){
        int result = 0;
        switch (operator) {
            case "add":
                result = number1 + number2;
                break;
            case "rest":
                result = number1 - number2;
                break;
            case "mult":
                result = number1 * number2;
                break;
            case "div":
                if (number2==0) {
                    error = true;
                    newNumber = true;
                    currentNumber = 0;
                    previousNumber =0 ;
                    lastOperation = "";
                    return;
                } else {
                    result = number1 / number2;
                }
                break;

        }
        this.currentNumber = result;
    }
}
