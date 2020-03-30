package calc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Calculator extends JFrame {
    private JTextField screen;
    CtrlCalculator ctrlCalc;

    public Calculator(){
        ctrlCalc= new CtrlCalculator(this);
    }

    public void ShowFrame() {
        setTitle("Calculator 1.0");
        setDefaultCloseOperation(Calculator.EXIT_ON_CLOSE);
        setSize(330, 400);
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        container.setBackground(new Color(108, 52, 131));


        HashMap<String, JButton> operatorMath = new HashMap<>();
        operatorMath.put("10", new CreateButton("AC",ctrlCalc));
        operatorMath.put("11", new CreateButton("/",ctrlCalc));
        operatorMath.put("12", new CreateButton("",ctrlCalc));

        operatorMath.put("20", new CreateButton("7",ctrlCalc));
        operatorMath.put("21", new CreateButton("8",ctrlCalc));
        operatorMath.put("22", new CreateButton("9",ctrlCalc));
        operatorMath.put("23", new CreateButton("x",ctrlCalc));

        operatorMath.put("30", new CreateButton("4",ctrlCalc));
        operatorMath.put("31", new CreateButton("5",ctrlCalc));
        operatorMath.put("32", new CreateButton("6",ctrlCalc));
        operatorMath.put("33", new CreateButton("-",ctrlCalc));

        operatorMath.put("40", new CreateButton("1",ctrlCalc));
        operatorMath.put("41", new CreateButton("2",ctrlCalc));
        operatorMath.put("42", new CreateButton("3",ctrlCalc));
        operatorMath.put("43", new CreateButton("+",ctrlCalc));

        operatorMath.put("50", new CreateButton("0",ctrlCalc));
        operatorMath.put("52", new CreateButton(".",ctrlCalc));
        operatorMath.put("53", new CreateButton("=",ctrlCalc));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 4, 4, 4);
        constraints.ipady = 16;
        constraints.ipadx = 16;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0) {
                    constraints.gridx = j;
                    constraints.gridy = i;
                    constraints.gridwidth = 4;
                    screen = new JTextField();
                    screen.setEditable(false);
                    screen.setFont(new Font("Arial",-1,18));
                    screen.setHorizontalAlignment(JTextField.RIGHT);
                    container.add(screen, constraints);
                    break;
                } else {
                    if(i == 1 && j == 2){
                        constraints.gridx = j;
                        constraints.gridy = i;
                        constraints.gridwidth = 2;
                        container.add(operatorMath.get("" + i + "" + j), constraints);
                        j = j + 1;
                    }else {
                        if (i == 5 && j == 0) {
                            constraints.gridx = j;
                            constraints.gridy = i;
                            constraints.gridwidth = 2;
                            container.add(operatorMath.get("" + i + "" + j), constraints);
                            j = j + 1;
                        } else {
                            constraints.gridx = j;
                            constraints.gridy = i;
                            constraints.gridwidth = 1;
                            container.add(operatorMath.get("" + i + "" + j), constraints);
                        }
                    }
                }
            }
        }

        getContentPane().add(container);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setTextScreen(String txt){
        this.screen.setText(txt);
    }

    public int cleanScreen(){
        this.screen.setText(null);
        return 1;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.ShowFrame();
    }
}
