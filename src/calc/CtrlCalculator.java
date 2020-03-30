package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CtrlCalculator implements ActionListener {
    private String pressKey  = "";
    private Calculator window;
    private int blockOperatorMinus = 0;
    private int blockZero = 0;
    private int blockOperatorsInitial = 1;
    private int blockNumbers = 0;
    int index = -1;

    public CtrlCalculator(Calculator window){
        this.window = window;
    }

    public void reset(){
        pressKey = "";
        blockOperatorMinus = 0;
        blockOperatorsInitial = 1;
        blockZero = 0;
        blockNumbers = 0;
    }

    /*public void enable(){
        blockOperatorMinus = 0;
        blockOperatorsInitial = 1;
        blockZero = 2;
    }*/

    public void blockNumbers(String number){

        if(number.equalsIgnoreCase("0")){
            if(blockZero == 0){
                pressKey = pressKey + number;
                window.setTextScreen(pressKey);
                blockZero = 1;
            }else{
                if(blockZero == 2){
                    pressKey = pressKey + number;
                    window.setTextScreen(pressKey);
                }
            }
        }else{
            if(blockNumbers == 0){
                blockOperatorsInitial = 0;
                blockOperatorMinus = 0;
                pressKey = pressKey + number;
                window.setTextScreen(pressKey);
            }
        }
    }

    public void blockOperator(String operator){
        if(operator.equalsIgnoreCase("-")){
            if(blockOperatorMinus == 0) {
                pressKey = pressKey + operator;
                window.setTextScreen(pressKey);
                blockOperatorMinus = 1;
            }
        }else{
            if(operator.equalsIgnoreCase(".")){
                if(blockOperatorsInitial == 0) {
                    blockNumbers = 0;
                    pressKey = pressKey + operator;
                    window.setTextScreen(pressKey);
                    blockOperatorsInitial = 1;
                }
            }else{
                if(blockOperatorsInitial == 0) {
                    pressKey = pressKey + operator;
                    window.setTextScreen(pressKey);
                    blockOperatorsInitial = 1;
                }
            }
        }
    }

    public int check_size_number(StringBuilder numbers){
        return numbers.length();
    }

    public int checkResult(String result){
        if(result.equalsIgnoreCase("Infinity")) return 1;
        return 0;
    }

    public StringBuilder formatterString(String antigua){
        int index = antigua.length()-1;
        char endCharacter = antigua.charAt(index);
        StringBuilder StringNew = new StringBuilder(antigua);

        if(!Character.isDigit(endCharacter)){
            StringNew.replace(index,index+1," ");
        }else{
            StringNew.append(' ');
        }
        return StringNew;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("AC")) {
            int codeClear = window.cleanScreen();
            if (codeClear == 1) {
                reset();
            }
        }

        if (event.getActionCommand().equalsIgnoreCase("B")) {
            if(!pressKey.isEmpty()){
                window.cleanScreen();
                index = pressKey.length() -1;
                StringBuilder temp = new StringBuilder(pressKey);
                temp.deleteCharAt(index);
                pressKey = temp.toString();
                window.setTextScreen(pressKey);
            }
        }

        if (event.getActionCommand().equalsIgnoreCase("x")) {
            blockOperator("x");
        }

        if (event.getActionCommand().equalsIgnoreCase("/")) {
            blockOperator("/");
        }

        if (event.getActionCommand().equalsIgnoreCase("+")) {
            blockOperator("+");
        }

        if (event.getActionCommand().equalsIgnoreCase("-")) {
            blockOperator("-");
        }

        if (event.getActionCommand().equalsIgnoreCase(".")) {
            blockOperator(".");
        }

        if (event.getActionCommand().equalsIgnoreCase("=")) {

            if(!pressKey.isEmpty()){
                StringBuilder StringFormatter = formatterString(pressKey);
                ArrayList<String> exprMath = new ArrayList<>();
                StringBuilder tempNumbers = new StringBuilder();
                int maxDigit = 12;
                int stop = 0;
                int index = StringFormatter.length()-1;

                for (int i = 0;i<StringFormatter.length();i++){
                    char character = StringFormatter.charAt(i);
                    if(Character.isDigit(character) || character == '.'){
                        tempNumbers.append(character);
                    }else{
                        if(check_size_number(tempNumbers) < maxDigit) {
                            if(i == 0){
                                exprMath.add(String.valueOf(character));
                            } else {
                                exprMath.add(tempNumbers.toString());
                                if(i != index) exprMath.add(String.valueOf(character));
                                tempNumbers = new StringBuilder();
                            }
                        }else{
                            stop = 1;
                            break;
                        }
                    }
                }

                if(stop != 1){
                    if(exprMath.size()>2){
                        window.cleanScreen();
                        if(exprMath.get(0).equalsIgnoreCase("-")){
                            ArrayList<String> result = new CalcLogic().calculator(exprMath);
                            String rs = result.get(0)+result.get(1);
                            if(checkResult(result.get(1)) != 1){ // No es infinity
                                window.setTextScreen(rs);
                                pressKey = rs;
                            }else{
                                window.setTextScreen(rs);
                                pressKey = "";
                            }

                        }else{
                            exprMath.add(0,"+");
                            ArrayList<String> result = new CalcLogic().calculator(exprMath);
                            if(checkResult(result.get(1)) != 1){
                                window.setTextScreen(result.get(1));
                                pressKey = result.get(1);
                            }else{
                                window.setTextScreen(result.get(1));
                                pressKey = "";
                            }
                        }
                    }else{
                        System.out.println("Debe ingresar la operacion completa num1 operador num2");
                    }
                }else{
                    System.out.println("La cantidad maxima de los numeros es 12 digitos!!");
                }
            }else{
                System.out.println("No ha ingresado nada");
            }
        }

        if (event.getActionCommand().equalsIgnoreCase("7")) {
            blockNumbers("7");
        }

        if (event.getActionCommand().equalsIgnoreCase("8")) {
            blockNumbers("8");
        }

        if (event.getActionCommand().equalsIgnoreCase("9")) {
            blockNumbers("9");
        }

        if (event.getActionCommand().equalsIgnoreCase("4")) {
            blockNumbers("4");
        }

        if (event.getActionCommand().equalsIgnoreCase("5")) {
            blockNumbers("5");
        }

        if (event.getActionCommand().equalsIgnoreCase("6")) {
            blockNumbers("6");
        }

        if (event.getActionCommand().equalsIgnoreCase("1")) {
            blockNumbers("1");
        }

        if (event.getActionCommand().equalsIgnoreCase("2")) {
            blockNumbers("2");
        }

        if (event.getActionCommand().equalsIgnoreCase("3")) {
            blockNumbers("3");
        }

        if (event.getActionCommand().equalsIgnoreCase("0")) {
            blockNumbers("0");
        }
    }
}
