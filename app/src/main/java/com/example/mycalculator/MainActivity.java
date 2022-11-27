package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.javia.arity.Symbols;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btndot, btnPlus, btnMinus, btnMulti, btnDivide, btnEqual, btnClear;
    TextView txtResult, txtProcess;
    Symbols symbols;

    double result;

    String number = "";
    boolean isDotUsed = false, isProcessing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btndot = (Button) findViewById(R.id.btn_dot);
        btnEqual = (Button) findViewById(R.id.btn_equal);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnMulti = (Button) findViewById(R.id.btn_multi);
        btnDivide = (Button) findViewById(R.id.btn_divide);

        txtResult = (TextView) findViewById(R.id.txt_result);
        txtProcess = (TextView) findViewById(R.id.txt_process);

        txtProcess.setText("");
        txtResult.setText("");

        symbols = new Symbols();



        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                if(!isProcessing){
                    txtProcess.setText("");
                    txtResult.setText("");
                    number ="";
                    isProcessing = true;
                }

                if(button.getText().equals(".") && isDotUsed)
                    return;
                else if(button.getText().equals(".") && number.length() <= 0)
                    return;
                else if(button.getText().equals("."))
                    isDotUsed= true;

                number += button.getText().toString();

                txtResult.setText(number);
            }
        };

        View.OnClickListener oprListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                if(button.getText().equals("C"))
                {
                    txtProcess.setText("");
                    txtResult.setText("");
                    number ="";
                    return;
                }
                if(number.length() <= 0) {
                    return;
                }
                if(button.getText().equals("="))
                {
                    try {
                        txtProcess.append(number);
                        result = symbols.eval(txtProcess.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    isDotUsed = false;
                    txtResult.setText(String.valueOf(result));
                    isProcessing = false;
                }
                else
                {
                    number += button.getText().toString();
                    txtProcess.append(number);
                    isDotUsed = false;
                }
                number = "";
            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btndot.setOnClickListener(listener);
        btnEqual.setOnClickListener(oprListener);
        btnClear.setOnClickListener(oprListener);
        btnPlus.setOnClickListener(oprListener);
        btnMinus.setOnClickListener(oprListener);
        btnMulti.setOnClickListener(oprListener);
        btnDivide.setOnClickListener(oprListener);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("Result", txtResult.getText().toString());
        outState.putString("Process", txtProcess.getText().toString());
        outState.putString("Number", number);
        outState.putBoolean("IsDotUsed",isDotUsed);
        outState.putBoolean("IsProcessing",isProcessing);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        txtResult.setText(savedInstanceState.getString("Result"));
        txtProcess.setText(savedInstanceState.getString("Process"));
        number =savedInstanceState.getString("Number");
        isDotUsed = savedInstanceState.getBoolean("IsDotUsed");
        isProcessing = savedInstanceState.getBoolean("IsProcessing");
    }
}