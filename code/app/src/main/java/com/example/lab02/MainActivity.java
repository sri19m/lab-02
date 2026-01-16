package com.example.lab02;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    EditText cityInput;
    Button btnAdd;
    Button btnDelete;
    Button btnConfirm;

    // Track which city is selected (-1 means nothing selected)
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize the UI elements
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.editText_name);
        btnAdd = findViewById(R.id.button_add);
        btnDelete = findViewById(R.id.button_delete);
        btnConfirm = findViewById(R.id.button_confirm);

        // 2. Set up the Data List
        String[] cities = {"Edmonton", "Hyderabad"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // 3. Connect the Adapter
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // 4. "ADD CITY" Button Logic
        // Since the box is always visible, this button just helps the user focus on it
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityInput.requestFocus();
            }
        });

        // 5. "CONFIRM" Button Logic (Adds the city)
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString();

                // Only add if the name is not empty
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged(); // Updates the screen
                    cityInput.setText(""); // Clear the text box
                }
            }
        });

        // 6. List Click Logic (Selects a city)
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position; // Save which row was clicked
            }
        });

        // 7. "DELETE CITY" Button Logic
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if a valid position is selected
                if (selectedPosition != -1) {
                    dataList.remove(selectedPosition);
                    cityAdapter.notifyDataSetChanged(); // Updates the screen
                    selectedPosition = -1; // Reset selection so we don't delete wrong thing next time
                }
            }
        });
    }
}