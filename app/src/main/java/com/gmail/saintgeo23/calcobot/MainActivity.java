package com.gmail.saintgeo23.calcobot;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    /**Переменная текстбокса*/
    EditText display;

    /**Переменные кнопок*/
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView zero;
    TextView addition;
    TextView subtract;
    TextView multiply;
    TextView division;
    TextView equals;
    TextView comma;
    TextView allClear;
    TextView percent;




    /**Результат который заносится в масив для обработки*/
    ArrayList<Float> result = new ArrayList<Float>();

    /**Первое введенное число*/
    float num1;

    /**Второе введенное число*/
    float num2;

    int curOp = 0;
    int nextOp;

    /**Прибавление*/
    final static int ADD = 1;

    /**Вычитание*/
    final static int SUBTRACT = 2;

    /**Умножение*/
    final static int MULTIPLY = 3;

    /**Деление*/
    final static int DIVISION = 4;

    /**Проценты*/
    final static int PERCENT = 5;

    /**Равно*/
    final static int EQUALS = 6;


    final static int CLEAR = 1;
    final static int NOT_CLEAR = 0;
    int clearDisplay = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (TextView) findViewById(R.id.button1);
        two = (TextView) findViewById(R.id.button2);
        three = (TextView) findViewById(R.id.button3);
        four = (TextView) findViewById(R.id.button4);
        five = (TextView) findViewById(R.id.button5);
        six =(TextView) findViewById(R.id.button6);
        seven = (TextView) findViewById(R.id.button7);
        eight = (TextView) findViewById(R.id.button8);
        nine = (TextView) findViewById(R.id.button9);
        zero = (TextView) findViewById(R.id.button0);
        addition = (TextView) findViewById(R.id.buttonAdd);
        subtract = (TextView) findViewById(R.id.buttonSubtract);
        multiply = (TextView) findViewById(R.id.buttonMultiply);
        division =(TextView) findViewById(R.id.buttonDivide);
        percent = (TextView) findViewById(R.id.buttonPercent);
        display = (EditText) findViewById(R.id.displayView);
        comma = (TextView) findViewById(R.id.buttonComma);
        allClear = (TextView) findViewById(R.id.buttonClear);
        equals = (TextView) findViewById(R.id.buttonEquals);

        display.setKeyListener(DigitsKeyListener.getInstance(true, true));

        registerListeners();
    }

    /*Обработка нажатия на экран*/
    public void registerListeners () {

        one.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("1");

            }
        });

        two.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("2");

            }
        });

        three.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("3");

            }
        });

        four.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("4");

            }
        });

        five.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("5");

            }
        });

        six.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("8");

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("9");

            }
        });

        zero.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append("0");
            }
        });

        comma.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clearDisplay == CLEAR) {
                    display.setText("");
                }
                clearDisplay = NOT_CLEAR;
                display.append(".");
            }
        });

        addition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(ADD);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(SUBTRACT);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(MULTIPLY);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(DIVISION);
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (curOp == 0)
                    calcLogic(PERCENT);
                else if (curOp == ADD) {

                    result.add(Float.parseFloat(display.getText().toString()));

                    num1 = result.get(0);
                    num2 = result.get(1);

                    result.removeAll(result);

                    result.add(num2 * (1 + num1/100));

                    display.setText(String.format("%.0f", result.get(0)));

                    clearDisplay = CLEAR;

                    curOp = 0;
                    nextOp = 0;

                    result.removeAll(result);
                    }

                else if (curOp == SUBTRACT) {

                    result.add(Float.parseFloat(display.getText().toString()));

                    num1 = result.get(0);
                    num2 = result.get(1);

                    result.removeAll(result);

                    result.add(num2 * (1 - num1/100));

                    display.setText(String.format("%.0f", result.get(0)));

                    clearDisplay = CLEAR;

                    curOp = 0;
                    nextOp = 0;

                    result.removeAll(result);
                }

                else
                    calcLogic(EQUALS);

            }
        });

        equals.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calcLogic(EQUALS);

            }
        });

        allClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                display.setText("");
                num1 = 0;
                num2 = 0;
                result.removeAll(result);
                curOp = 0;
                nextOp = 0;
            }
        });


    }

    /*Функция расчета введенных значений*/
    private void calcLogic(int operator) {

        result.add(Float.parseFloat(display.getText().toString()));

        if (operator != EQUALS) {
            nextOp = operator;
        }else if (operator == EQUALS){
            nextOp = 0;
        }

        switch (curOp) {

            /*Прибавление*/
            case ADD:
                num1 = result.get(0);
                num2 = result.get(1);

                result.removeAll(result);

                result.add(num1 + num2);

                display.setText(String.format("%.0f", result.get(0)));
                break;

            /*Вычитание*/
            case SUBTRACT:
                num1 = result.get(0);
                num2 = result.get(1);

                result.removeAll(result);

                result.add(num1 - num2);

                display.setText(String.format("%.0f", result.get(0)));
                break;

            /*Умножение*/
            case MULTIPLY:
                num1 = result.get(0);
                num2 = result.get(1);

                result.removeAll(result);

                result.add(num1 * num2);

                display.setText(String.format("%.0f", result.get(0)));
                break;
            /*Деление*/
            case DIVISION:
                num1 = result.get(0);
                num2 = result.get(1);

                result.removeAll(result);

                result.add(num1 / num2);

                display.setText(String.format("%.0f", result.get(0)));
                break;

            /*Проценты*/
            case PERCENT:
                num1 = result.get(0);
                num2 = result.get(1);

                result.removeAll(result);

                result.add(num2 * (num1/100));

                display.setText(String.format("%.0f", result.get(0)));
                break;


        }


        clearDisplay = CLEAR;
        curOp = nextOp;
        if (operator == EQUALS) {
            num1 = 0;
            num2 = 0;
            result.removeAll(result);
        }
    }

}
