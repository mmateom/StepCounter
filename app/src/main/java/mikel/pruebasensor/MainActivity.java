package mikel.pruebasensor;

import android.support.v7.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.TextView;

import java.util.Map;

import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.StepCounter;


public class MainActivity extends AppCompatActivity {


    StepCounter stepCounter;

    TextView textView;
    String strValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);



        //Create new Observer
        stepCounter = new StepCounter(getApplicationContext());

        //register receiver
        stepCounter.addContextReceiver(new IContextReceiver() {


            @Override
            public void newContextValue(String name, long value) {

                strValue = String.valueOf(value);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        textView.setText(strValue);

                    }

                    });
            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }
        });
    }





    @Override
    protected void onResume() {
        super.onResume();
        stepCounter.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        stepCounter.stop();

    }

}

