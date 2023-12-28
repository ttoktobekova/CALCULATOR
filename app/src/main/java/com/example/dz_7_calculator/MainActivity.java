package com.example.dz_7_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Double firstOperand, secondOperand, result;
    private String currentOperator;
    private Boolean isFinishOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
    }

    public void onNumberClick(View view) {
        String number = ((MaterialButton) view).getText().toString();

        if (view.getId() == R.id.btn_clear) {
            clearCalculator();
        } else if (textView.getText().toString().equals("0") || isFinishOperation) {
            textView.setText(number);
        } else {
            textView.append(number);
        }
        isFinishOperation = false;
    }

    public void onOperationClick(View view) {
        String operator = ((MaterialButton) view).getText().toString();

        if (view.getId() == R.id.btn_equal) {
            handleEqualsClick();
        } else if (view.getId() == R.id.btn_plus_minus) {
            handlePlusMinusClick();
        } else if (view.getId() == R.id.percent) {
            handlePercentClick();
        } else {
            handleOperatorClick(operator);
        }
    }

    private void handleOperatorClick(String operator) {
        if (!isFinishOperation) {
            if (firstOperand == null) {
                firstOperand = Double.valueOf(textView.getText().toString());
            } else {
                handleEqualsClick();
            }

            textView.append(operator);
            currentOperator = operator;
            isFinishOperation = false;
        }
    }

    private void handleEqualsClick() {
        if (firstOperand != null) {
            String[] numbers = textView.getText().toString().split("\\+|-|\\*|/|%");
            secondOperand = Double.valueOf(numbers[1]);

            switch (currentOperator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    result = firstOperand / secondOperand;
                    break;
                case "%":
                    result = firstOperand * (secondOperand / 100);
                    break;
            }

            textView.setText(result.toString());
            firstOperand = null; // Сбросим первый операнд после выполнения операции
            isFinishOperation = true;
        }
    }

    private void handlePlusMinusClick() {
        if (!isFinishOperation) {
            double currentValue = Double.parseDouble(textView.getText().toString());
            double negatedValue = -currentValue;
            textView.setText(Double.toString(negatedValue));
        }
    }

    private void handlePercentClick() {
        if (!isFinishOperation) {
            double currentValue = Double.parseDouble(textView.getText().toString());
            double percentValue = currentValue / 100;
            textView.setText(Double.toString(percentValue));
            isFinishOperation = true;
        }
    }

    private void clearCalculator() {
        textView.setText("0");
        firstOperand = null;
        secondOperand = null;
        result = null;
        isFinishOperation = false;
    }
}
