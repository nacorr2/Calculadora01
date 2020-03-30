package calc;

import java.util.ArrayList;

public class CalcLogic {
    private String operator = "/";

    public int checkIndexOperator(ArrayList<String> em, String o) {
        ArrayList<String> copyExpr = new ArrayList<>(em);
        int index = -1;

        if(copyExpr.get(0).equalsIgnoreCase("-") || copyExpr.get(0).equalsIgnoreCase("+")){
            for (int i = 1; i < copyExpr.size(); i++) if (copyExpr.get(i).equalsIgnoreCase(o)) return i;
        }else{
            for (int i = 0; i < copyExpr.size(); i++) if (copyExpr.get(i).equalsIgnoreCase(o)) return i;
        }
        return index;
    }

    public ArrayList<String> calculator(ArrayList<String> em) {
        int indexOperator;
        ArrayList<String> ContMath = new ArrayList<>(em);//{+ 8 / 0}

        while (ContMath.size() > 2) {
            if (operator.equalsIgnoreCase("/")) {
                indexOperator = checkIndexOperator(ContMath, "/");//{+ 8 / 0},2
                if (indexOperator > -1) {
                    if(ContMath.get(0).equalsIgnoreCase("Infinity")){
                        break;
                    }else {
                        ContMath = new ArrayList<>(calcMathDiv(ContMath, indexOperator));
                    }
                } else {
                    operator = "x";
                }
            }

            if (operator.equalsIgnoreCase("x")) {
                indexOperator = checkIndexOperator(ContMath, "x");
                if (indexOperator > -1) {
                    ContMath = new ArrayList<>(calcMathProd(ContMath, indexOperator));
                } else {
                    operator = "+";
                }
            }

            if (operator.equalsIgnoreCase("+")) {
                indexOperator = checkIndexOperator(ContMath, "+");
                if (indexOperator > -1) {
                    ContMath = new ArrayList<>(calcMathAd(ContMath, indexOperator)); //{- 4 + 5},2
                } else {
                    operator = "-";
                }
            }

            if (operator.equalsIgnoreCase("-")) {
                indexOperator = checkIndexOperator(ContMath, "-");
                if (indexOperator > -1) {
                    ContMath = new ArrayList<>(calcMathSus(ContMath, indexOperator)); //{- 3 - 3 - 3},2 ; {- [6] - 3},2
                }
            }
        }
        return ContMath;
    }

    public ArrayList<String> calcMathProd(ArrayList<String> expMath, int index) {
        String antAnterior = expMath.get(index - 2);
        float anterior = Float.parseFloat(expMath.get(index - 1));
        float next = Float.parseFloat(expMath.get(index + 1));
        float response;  // 12

        if (antAnterior.equalsIgnoreCase("-")) {
            response = (anterior * next);
            expMath.set(index - 2, "-");
        } else {
            response = (anterior * next);
        }

        expMath.set(index, String.valueOf(response));
        expMath.set(index - 1, "B");
        expMath.set(index + 1, "B");
        expMath.removeIf(e -> (e.equalsIgnoreCase("B")));
        return expMath;
    }

    public ArrayList<String> calcMathDiv(ArrayList<String> expMath, int index) { // //{+ 8 [/] 0},2
        String antAnterior = expMath.get(index - 2); // +
        float anterior = Float.parseFloat(expMath.get(index - 1));// 8
        float next = Float.parseFloat(expMath.get(index + 1)); // 0
        float response;

        if(next != 0.0){
            if (antAnterior.equalsIgnoreCase("-")) {
                response = (anterior / next);
                expMath.set(index - 2, "-");
            } else {
                response = (anterior / next);
            }
            expMath.set(index, String.valueOf(response));
            expMath.set(index - 1, "B");
            expMath.set(index + 1, "B");
            expMath.removeIf(e -> (e.equalsIgnoreCase("B")));
        }else{
            expMath = new ArrayList<>();
            expMath.add("+/-");
            expMath.add("Infinity");
        }
        return expMath;
    }

    public ArrayList<String> calcMathAd(ArrayList<String> expMath, int index) {
        String antAnterior = expMath.get(index-2);
        float anterior = Float.parseFloat(expMath.get(index-1));
        float next = Float.parseFloat(expMath.get(index+1));
        float response;

        if (antAnterior.equalsIgnoreCase("-") && anterior>next) {
                response = (anterior - next); // 3
                expMath.set(index - 2, "-"); // -
        }else {
            if (antAnterior.equalsIgnoreCase("-") && anterior < next) {
                response = (next - anterior);
                expMath.set(index - 2, "+");
            } else {
                if(antAnterior.equalsIgnoreCase("-") && anterior == next){
                    response = (anterior - next);
                    expMath.set(index - 2, "+");
                }else {
                    response = (anterior + next);
                }
            }
        }

        expMath.set(index, String.valueOf(response));
        expMath.set(index - 1, "B");
        expMath.set(index + 1, "B");
        expMath.removeIf(e -> (e.equalsIgnoreCase("B")));
        return expMath;
    }

    public ArrayList<String> calcMathSus(ArrayList<String> expMath, int index) { //{+ 20 - 3}
        String antAnterior = expMath.get(index - 2); // [+]
        float anterior = Float.parseFloat(expMath.get(index - 1)); // 20
        float next = Integer.parseInt(expMath.get(index + 1)); // 3
        float response; //9

        if (antAnterior.equalsIgnoreCase("-")) {
            response = (anterior + next);
            expMath.set(index - 2, "-"); //
        }else { //
            if (anterior > next) {
                response = (anterior - next);
                expMath.set(index - 2, "+");
            } else { //
                response = (anterior - next);
                expMath.set(index - 2, "-");
            }
        }
        expMath.set(index, String.valueOf(response));//{- 6 [9] 3}
        expMath.set(index - 1, "B");//{- B [9] 3}
        expMath.set(index + 1, "B");//{- 6 [9] B}
        expMath.removeIf(e -> (e.equalsIgnoreCase("B"))); ///{- B [9] B}
        return expMath;
    }
}
