package com.example.progressdialogue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView txt; // Declare the TextView variable
    int counter = 0;
    Button b1;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);

        b1 = findViewById(R.id.btn);
        progressDialog = new ProgressDialog(MainActivity.this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                progressDialog.setMessage("Loading....");
                progressDialog.setTitle("Progress Dialogue");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(5000);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();

                */

                // Start countdown timer
                progressDialog=ProgressDialog.show(MainActivity.this,"Please Wait","Processing...",true);
                new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        // Update the TextView with the countdown value
                        txt.setText(String.valueOf(counter));
                        counter++;
                    }

                    @Override
                    public void onFinish() {
                        txt.setText("Finish");
                        progressDialog.dismiss();

                    }
                }.start();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
