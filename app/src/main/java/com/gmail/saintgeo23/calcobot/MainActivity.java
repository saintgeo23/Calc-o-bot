package com.gmail.saintgeo23.calcobot;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.EnumMap;


public class MainActivity extends Activity {

    /**Переменная текстбокса*/
    private EditText display;

    /**Переменные кнопок*/
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button addition;
    private Button subtract;
    private Button multiply;
    private Button division;
    private Button equals;
    private Button comma;
    private Button allClear;
    private Button percent;

    private OperationType operType;

    private EnumMap<Symbol, Object> commands = new EnumMap<Symbol, Object>(Symbol.class);

    private ActionType lastAction;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (EditText) findViewById(R.id.displayView);

        one = (Button) findViewById(R.id.button1);
        two = (Button) findViewById(R.id.button2);
        three = (Button) findViewById(R.id.button3);
        four = (Button) findViewById(R.id.button4);
        five = (Button) findViewById(R.id.button5);
        six =(Button) findViewById(R.id.button6);
        seven = (Button) findViewById(R.id.button7);
        eight = (Button) findViewById(R.id.button8);
        nine = (Button) findViewById(R.id.button9);
        zero = (Button) findViewById(R.id.button0);
        addition = (Button) findViewById(R.id.buttonAdd);
        subtract = (Button) findViewById(R.id.buttonSubtract);
        multiply = (Button) findViewById(R.id.buttonMultiply);
        division =(Button) findViewById(R.id.buttonDivide);
        percent = (Button) findViewById(R.id.buttonPercent);
        comma = (Button) findViewById(R.id.buttonComma);
        allClear = (Button) findViewById(R.id.buttonClear);
        equals = (Button) findViewById(R.id.buttonEquals);

        display.setKeyListener(DigitsKeyListener.getInstance(true, true));

        addition.setTag(OperationType.ADD);
        subtract.setTag(OperationType.SUBTRACT);
        multiply.setTag(OperationType.MULTIPLY);
        division.setTag(OperationType.DIVIDE);
        percent.setTag(OperationType.PERCENT);


    }

    public void buttonClick(View view) {

        switch (view.getId()) {

            case R.id.buttonAdd:
            case R.id.buttonSubtract:
            case R.id.buttonMultiply:
            case R.id.buttonDivide: {
                operType = (OperationType) view.getTag();

                if (lastAction == ActionType.OPERATION) {
                    commands.put(Symbol.OPERATION, operType);
                    return;
                }

                if (!commands.containsKey(Symbol.OPERATION)) {

                    if (!commands.containsKey(Symbol.FIRST_DIGIT)) {
                        commands.put(Symbol.FIRST_DIGIT, display.getText());
                    }

                    commands.put(Symbol.OPERATION, operType);
                } else if (!commands.containsKey(Symbol.SECOND_DIGIT)) {
                    commands.put(Symbol.SECOND_DIGIT, display.getText());
                    doCalc();
                    commands.put(Symbol.OPERATION, operType);
                    commands.remove(Symbol.SECOND_DIGIT);

                }

                lastAction = ActionType.OPERATION;
                break;

            }

            case R.id.buttonPercent: {

                if (!commands.containsKey(Symbol.OPERATION)) {
                    commands.put(Symbol.OPERATION, operType);
                } else if (commands.get(Symbol.OPERATION).equals(OperationType.ADD)) {
                    commands.put(Symbol.SECOND_DIGIT, display.getText());
                    double result = CalcOps.percentAdd(getDouble(commands.get(Symbol.FIRST_DIGIT)), getDouble(commands.get(Symbol.SECOND_DIGIT)));

                    if (result % 1 == 0) {
                        display.setText(String.valueOf((int)result));
                    } else {
                        display.setText(String.valueOf(result));
                    }

                } else if (commands.get(Symbol.OPERATION).equals(OperationType.SUBTRACT)) {
                    commands.put(Symbol.SECOND_DIGIT, display.getText());
                    double result = CalcOps.percentSub(getDouble(commands.get(Symbol.FIRST_DIGIT)), getDouble(commands.get(Symbol.SECOND_DIGIT)));

                    if (result % 1 == 0) {
                        display.setText(String.valueOf((int)result));
                    } else {
                        display.setText(String.valueOf(result));
                    }

                }
                lastAction = ActionType.OPERATION;
            }

            case R.id.buttonClear: {

                display.setText("0");
                commands.clear();
                lastAction = ActionType.CLEAR;
                break;

            }

            case R.id.buttonEquals: {

                if (lastAction == ActionType.RESULT) return;

                if (commands.containsKey(Symbol.FIRST_DIGIT) && commands.containsKey(Symbol.OPERATION)) {

                    commands.put(Symbol.SECOND_DIGIT, display.getText());
                    doCalc();
                    commands.put(Symbol.OPERATION, operType);

                    commands.clear();


                }
                lastAction = ActionType.RESULT;
                break;


            }

            case R.id.buttonComma: {

                if (commands.containsKey(Symbol.FIRST_DIGIT) && getDouble(display.getText().toString()) == getDouble(commands.get(Symbol.FIRST_DIGIT).toString())) {
                    display.setText("0" + view.getContentDescription().toString());
                }
                if (!display.getText().toString().contains(",")) {
                    display.setText(display.getText() + ",");
                }
                lastAction = ActionType.COMMA;
                break;

            }

            default: {

                if (display.getText().toString().equals("0") || (commands.containsKey(Symbol.FIRST_DIGIT) && getDouble(display.getText()) == getDouble(commands.get(Symbol.FIRST_DIGIT))) || lastAction == ActionType.RESULT) {
                    display.setText(view.getContentDescription().toString());
                } else {
                    display.setText(display.getText() + view.getContentDescription().toString());
                }
                lastAction = ActionType.DIGIT;

            }
        }

    }


    private void doCalc() {

        OperationType opTypeTmp = (OperationType) commands.get(Symbol.OPERATION);

        double result = 0;

        try {
            result = calc(opTypeTmp,
                    getDouble(commands.get(Symbol.FIRST_DIGIT)),
                    getDouble(commands.get(Symbol.SECOND_DIGIT)));
        } catch (ArithmeticException e) {
            showToast(R.string.divisionZero);
            return;
        }
        if (result % 1 == 0) {
            display.setText(String.valueOf((int)result));
        } else {
            display.setText(String.valueOf(result));
        }

        commands.put(Symbol.FIRST_DIGIT, result);
    }

    private void showToast(int id) {

        Toast toast = Toast.makeText(this, id, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

    private double getDouble(Object value) {

        double result = 0;
        try {
            result = Double.valueOf(value.toString().replace(",", ".")).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }

        return result;

    }

    private Double calc(OperationType operType, double a, double b) {

        switch (operType) {

            case ADD: {
                return CalcOps.add(a, b);
            }
            case SUBTRACT: {
                return CalcOps.sub(a, b);
            }
            case MULTIPLY: {
                return CalcOps.mult(a, b);
            }
            case DIVIDE: {
                return CalcOps.div(a, b);
            }
            case PERCENT: {
                return CalcOps.percent(a, b);
            }


        }
        return null;

    }



}
