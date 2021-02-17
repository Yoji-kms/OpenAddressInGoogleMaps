package com.yoji.openaddressingooglemaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button searchBtn;
    private EditText placeNameOrCoordinatesEdtTxt;

    private final View.OnClickListener searchBtnOnClickListener = v -> {
        Intent place = new Intent(Intent.ACTION_VIEW);
        String enteredString = placeNameOrCoordinatesEdtTxt.getText().toString().trim();
        place.setData(placeNameOrCoordinatesUri(enteredString));
        startActivity(place);
    };

    private final TextWatcher placeNameOrCoordinatesEdtTxtWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String enteredString = placeNameOrCoordinatesEdtTxt.getText().toString().trim();

            searchBtn.setEnabled(!enteredString.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        searchBtn = findViewById(R.id.searchBtnId);
        placeNameOrCoordinatesEdtTxt = findViewById(R.id.placeNameOrCoordinatesEdtTxtId);

        searchBtn.setOnClickListener(searchBtnOnClickListener);
        placeNameOrCoordinatesEdtTxt.addTextChangedListener(placeNameOrCoordinatesEdtTxtWatcher);
    }

    private Uri placeNameOrCoordinatesUri(String enteredString) {
        if (enteredString.matches("\\d+\\.\\d+, \\d+\\.\\d+")) {
            Log.d("Entered string", "Coordinates");
            return Uri.parse("geo:" + enteredString);
        } else {
            Log.d("Entered string", "Place name");
            return Uri.parse("geo:\\?q=" + enteredString);
        }
    }
}