package com.example.uiprojectv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout moviesLayout, maritalLayout;
    private CheckBox harryPotterCB, lordOfTheRingsCB, theMatrixCB;
    private RadioGroup maritalStatusRG;
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instantiate layouts
        this.moviesLayout = findViewById(R.id.moviesLayout);
        this.maritalLayout = findViewById(R.id.maritalStatusLayout);
        //instantiate checkboxes
        this.harryPotterCB = findViewById(R.id.hpCheckbox);
        this.lordOfTheRingsCB = findViewById(R.id.ldCheckbox);
        this.theMatrixCB = findViewById(R.id.tmCheckbox);
        //instantiate radio buttons groups
        this.maritalStatusRG = findViewById(R.id.maritalStatus);
        //instantiate progress bar
        this.progressBar = findViewById(R.id.progressBar);

        //add action listener to check buttons
        for (CheckBox checkbox : new CheckBox[]{this.harryPotterCB, this.lordOfTheRingsCB, this.theMatrixCB}) {
            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if ((isChecked)) {
                        Toast.makeText(MainActivity.this, "You have watched " + compoundButton.getText(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "You NEED to watch " + compoundButton.getText(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        //add action listener to radio buttons group
        this.maritalStatusRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.marriedRadioButton:
                        Toast.makeText(MainActivity.this, "Married", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.singleRadioButton:
                        Toast.makeText(MainActivity.this, "Single", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.relationshipRadioButton:
                        Toast.makeText(MainActivity.this, "In a Relationship", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
        //TODO: Use this app as an example for threads.
        //hide buttons and show progress bar
        this.moviesLayout.setVisibility(View.GONE);
        this.maritalLayout.setVisibility(View.GONE);
        this.progressBar.setVisibility(View.VISIBLE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 10; i++) {
                    sum += 10;
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(500);
                }
                if(progressBar.getProgress()==100){
                    handler.post(new Runnable() {
                        public void run() {
                            makeThemVisible();
                        }
                    });
                }
            }
        });
        thread.start();

    }

    void makeThemVisible() {
        this.moviesLayout.setVisibility(View.VISIBLE);
        this.maritalLayout.setVisibility(View.VISIBLE);
        this.progressBar.setVisibility(View.GONE);
    }
}