package com.soham.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton btndel,btndiv,btnmul,btnadd,btnsub,btnequ,btnac,btndec,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textViewResult);

        assignId(btndel,R.id.buttonDelete);
        assignId(btndiv,R.id.buttonDiv);
        assignId(btnmul,R.id.buttonMul);
        assignId(btnadd,R.id.buttonAdd);
        assignId(btnsub,R.id.buttonSub);
        assignId(btnequ,R.id.buttonEqu);
        assignId(btnac,R.id.buttonAC);
        assignId(btndec,R.id.buttonDec);
        assignId(btn1,R.id.button1);
        assignId(btn2,R.id.button2);
        assignId(btn3,R.id.button3);
        assignId(btn4,R.id.button4);
        assignId(btn5,R.id.button5);
        assignId(btn6,R.id.button6);
        assignId(btn7,R.id.button7);
        assignId(btn8,R.id.button8);
        assignId(btn9,R.id.button9);
        assignId(btn0,R.id.button0);
    }

    void assignId(MaterialButton btn,int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btntext = btn.getText().toString();
        String data = tv.getText().toString();

        if(btntext.equals("AC"))
        {
            tv.setText("0");
            return;
        }

        if(btntext.equals("="))
        {
            String finalResult = getResult(data);

            if(!finalResult.equals("Error"))
                tv.setText(finalResult);
            return;
        }

        if(btntext.equals("DELETE"))
        {
            data = data.substring(0,data.length()-1);
        }
        else
        {
            data = data + btntext;
        }
        tv.setText(data);


    }

    String getResult(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascipt",1,null).toString();
            if(result.endsWith(".0"))
                result = result.replace(".0","");
            return result;
        }
        catch (Exception e )
        {
            return "Error";
        }
    }
}