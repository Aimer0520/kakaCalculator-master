package com.example.yzbkaka.kakacaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button zero;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button point;
    Button clear;
    Button add;
    Button substraction;
    Button mulitipliction;
    Button division;
    Button root;
    Button plus_minus;
    Button equal;
    Button delete;
    TextView theFirstView;
    TextView theOptionView;
    TextView theSecondView;
    TextView theEqualView;
    TextView theAnswerView;
    String option = "";
    String str1 = "", str2 = "";
    double number1, number2;
    int answerFlag = 0;
    int point1 = 0;
    int point2 = 0;
    double number;
    double toggledNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();  //初始化布局
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zero:
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
                if (answerFlag == 0) {
                    if (option == "") {
                        str1 = str1 + ((Button) view).getText().toString();
                        theFirstView.setText(str1);
                    } else {
                        str2 = str2 + ((Button) view).getText().toString();
                        theSecondView.setText(str2);
                    }
                }
                if (answerFlag == 1) {
                    clear();
                    str1 = str1 + ((Button) view).getText().toString();
                    theFirstView.setText(str1);
                }
                break;

            case R.id.point:
                if (str1 != "" && option == "" && point1 == 0) {
                    str1 = str1 + ((Button) view).getText().toString();
                    theFirstView.setText(str1);
                    point1 = 1;
                }
                if (option != "" && str2 != "" && point2 == 0) {
                    str2 = str2 + ((Button) view).getText().toString();
                    theSecondView.setText(str2);
                    point2 = 1;
                }
                break;

            case R.id.plus_minus:
                if (str1 != "") {
                    if (option == "") {
                        number = Double.parseDouble(str1);
                        toggledNumber = -number;
                        str1 = String.valueOf(toggledNumber);
                        theFirstView.setText(String.valueOf(toggledNumber));
                    }
                    if (option != "" && str2 != "") {
                        number = Double.parseDouble(str2);
                        toggledNumber = -number;
                        str2 = String.valueOf(toggledNumber);
                        theSecondView.setText(String.valueOf(toggledNumber));
                    }
                }
                break;

            case R.id.clear:
                clear();
                break;

            case R.id.delete:
                if (theAnswerView.getText().toString() == "") {
                    if ((theSecondView.getText().toString()) != "") {
                        int length = str2.length();
                        if (length == 1) {
                            theSecondView.setText("");
                        } else {
                            str2 = str2.substring(0, length - 1);
                            theSecondView.setText(str2);
                        }
                        break;
                    }
                    if ((theSecondView.getText().toString()) == "" && (theOptionView.getText().toString()) != "") {
                        option = "";
                        theOptionView.setText("");
                        break;
                    }
                    if ((theSecondView.getText().toString()) == "" && (theAnswerView.getText().toString()) == "" && (theFirstView.getText().toString()) != "") {
                        int length = str1.length();
                        if (length == 1) {
                            theFirstView.setText("");
                        } else {
                            str1 = str1.substring(0, length - 1);
                            theFirstView.setText(str1);
                        }
                        break;
                    }
                }
                break;

            case R.id.equal:
                if (str2 != "" || option == "√") {
                    theEqualView.setText("=");
                    answerFlag = 1;
                    if (option == "+") {
                        number1 = Double.parseDouble(theFirstView.getText().toString());
                        number2 = Double.parseDouble(theSecondView.getText().toString());
                        theAnswerView.setText((number1 + number2) + "");
                    }
                    if (option == "-") {
                        number1 = Double.parseDouble(theFirstView.getText().toString());
                        number2 = Double.parseDouble(theSecondView.getText().toString());
                        theAnswerView.setText((number1 - number2) + "");
                    }
                    if (option == "×") {
                        number1 = Double.parseDouble(theFirstView.getText().toString());
                        number2 = Double.parseDouble(theSecondView.getText().toString());
                        theAnswerView.setText((number1 * number2) + "");
                    }
                    if (option.equals("÷")) {  // 注意这里使用 equals 方法比较字符串
                        number1 = Double.parseDouble(theFirstView.getText().toString());
                        number2 = Double.parseDouble(theSecondView.getText().toString());
                        if (number2 == 0) {
                            Toast.makeText(getApplicationContext(), "除数不能为0", Toast.LENGTH_SHORT).show();
                        } else {
                            theAnswerView.setText((number1 / number2) + "");
                        }
                    }
                    if (option == "√") {
                        number2 = Double.parseDouble(theSecondView.getText().toString());
                        theAnswerView.setText(Math.sqrt(number2) + "");
                    }
                }
                break;

            case R.id.add:
                if (str1 != "" && option == "") {
                    option = "+";
                    theOptionView.setText("+");
                }
                break;

            case R.id.substraction:
                if (str1 != "" && option == "") {
                    option = "-";
                    theOptionView.setText("-");
                }
                break;

            case R.id.mulitipliction:
                if (str1 != "" && option == "") {
                    option = "×";
                    theOptionView.setText("×");
                }
                break;

            case R.id.division:
                if (str1 != "" && option == "") {
                    option = "÷";
                    theOptionView.setText("÷");
                }
                break;

            case R.id.root:
                if (str1 == "") {
                    option = "√";
                    theOptionView.setText("√");
                }
                if (answerFlag == 1) {
                    clear();
                    option = "√";
                    theOptionView.setText("√");
                }
                break;
        }
    }

    public void initView() {
        zero = (Button) findViewById(R.id.zero);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        point = (Button) findViewById(R.id.point);
        clear = (Button) findViewById(R.id.clear);
        add = (Button) findViewById(R.id.add);
        substraction = (Button) findViewById(R.id.substraction);
        mulitipliction = (Button) findViewById(R.id.mulitipliction);
        division = (Button) findViewById(R.id.division);
        root = (Button) findViewById(R.id.root);
        plus_minus = (Button) findViewById(R.id.plus_minus);
        equal = (Button) findViewById(R.id.equal);
        delete = (Button) findViewById(R.id.delete);
        theFirstView = (TextView) findViewById(R.id.the_first_number);
        theOptionView = (TextView) findViewById(R.id.the_option);
        theSecondView = (TextView) findViewById(R.id.the_second_number);
        theEqualView = (TextView) findViewById(R.id.the_equal);
        theAnswerView = (TextView) findViewById(R.id.the_answer);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        point.setOnClickListener(this);
        clear.setOnClickListener(this);
        add.setOnClickListener(this);
        substraction.setOnClickListener(this);
        mulitipliction.setOnClickListener(this);
        division.setOnClickListener(this);
        root.setOnClickListener(this);
        plus_minus.setOnClickListener(this);
        equal.setOnClickListener(this);
        delete.setOnClickListener(this);
        theFirstView.setOnClickListener(this);
        theOptionView.setOnClickListener(this);
        theEqualView.setOnClickListener(this);
        theSecondView.setOnClickListener(this);
        theAnswerView.setOnClickListener(this);
    }

    public void clear() {
        str1 = "";
        str2 = "";
        theFirstView.setText("");
        theSecondView.setText("");
        theOptionView.setText("");
        theEqualView.setText("");
        theAnswerView.setText("");
        option = "";
        point1 = 0;
        point2 = 0;
        answerFlag = 0;
    }
}
