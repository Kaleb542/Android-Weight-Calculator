package com.example.barbellapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView barbellWeightText;
    private RadioGroup barbellWeightRadioGroup;
    private RadioButton barbell25RadioButton;
    private RadioButton barbell45RadioButton;
    private TextView desiredWeightText;
    private EditText desiredWeightEditText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barbellWeightText = findViewById(R.id.barbell_weight_text);
        barbellWeightRadioGroup = findViewById(R.id.barbell_weight_radio_group);
        barbell25RadioButton = findViewById(R.id.barbell_25_radio_button);
        barbell45RadioButton = findViewById(R.id.barbell_45_radio_button);
        desiredWeightText = findViewById(R.id.desired_weight_text);
        desiredWeightEditText = findViewById(R.id.desired_weight_edit_text);
        calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the weight of the barbell from the selected RadioButton
                int barbellWeight;
                if (barbell25RadioButton.isChecked()) {
                    barbellWeight = 25;
                } else {
                    barbellWeight = 45;
                }

                // Get the desired weight from the EditText
                String desiredWeightString = desiredWeightEditText.getText().toString();
                if (desiredWeightString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a weight", Toast.LENGTH_LONG).show();
                    return;
                }

                double desiredWeight = Double.parseDouble(desiredWeightString);

                // Calculate the weight required on each side of the barbell
                double weightPerSide = (desiredWeight - barbellWeight) / 2;
                int[] weights = {45, 25, 10, 5, 2};  // Available weights
                int[] weightsNeeded = new int[weights.length];
                int remainingWeight = (int) Math.round(weightPerSide);

                for (int i = 0; i < weights.length; i++) {
                    if (remainingWeight >= weights[i]) {
                        weightsNeeded[i] = remainingWeight / weights[i];
                        remainingWeight = remainingWeight % weights[i];
                    }
                }

                // Display the weights required on each side of the barbell
                String weightsText = "";
                for (int i = 0; i < weights.length; i++) {
                    if (weightsNeeded[i] > 0) {
                        weightsText += weightsNeeded[i] + "x" + weights[i] + " ";
                    }
                }
                Toast.makeText(MainActivity.this, "Weights needed per side: " + weightsText, Toast.LENGTH_LONG).show();
            }
        });

    }

}
