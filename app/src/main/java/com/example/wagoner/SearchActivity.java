package com.example.wagoner;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    private TextInputLayout textInputWheelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textInputWheelID = findViewById(R.id.text_input_WheelID);

        //Create new wheel fab
        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Submit Wheel", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openScrollingActivity();
            }
        });

        Button submitButton = (Button) findViewById(R.id.button_submit_search);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateSearchInput()) {
                    openWheelActivity(textInputWheelID.getEditText().getText().toString().trim());
                }
            }
        });
    }

    public void openScrollingActivity() {
        Intent intent = new Intent(this, ScrollingActivity.class);
        startActivity(intent);
    }

    public void openWheelActivity(String wheelID) {
        Intent intent = new Intent(this, WheelActivity.class);
        WheelActivity.setWheelID(wheelID);
        startActivity(intent);
    }

    public void confirmInput(View v) {
        if(!validateSearchInput()) {
            return;
        }
    }

    private boolean validateSearchInput() {
        String searchInput = textInputWheelID.getEditText().getText().toString().trim();

        if (searchInput.isEmpty()) {
            textInputWheelID.setError("Field can't be empty");
            return false;
        }
        else if(!searchWheels(searchInput)) {
            textInputWheelID.setError("Wheel not found");
            return false;
        }
        else {
            textInputWheelID.setError(null);
            return true;
        }
    }

    private boolean searchWheels(String input){
        Wheel[] wheels = new Wheel[ScrollingActivity.getWheels().length];
        for (int x = 0; x < wheels.length; x++) {
            wheels[x] = ScrollingActivity.getWheel(x);
            if (input == wheels[x].getID()) {
                return true;
            }
        }
        return false;
    }

}
