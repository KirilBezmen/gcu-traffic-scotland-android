package com.kirilbezmen.trafficscotland;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/** A temporary setup screen, not the implemented Traffic Scotland coursework. */
public class MainActivity extends AppCompatActivity {
    private TextView setupStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupStatus = findViewById(R.id.setupStatus);
        Button checkSetupButton = findViewById(R.id.checkSetupButton);
        checkSetupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupStatus.setText(R.string.setup_verified);
                Toast.makeText(MainActivity.this, R.string.setup_verified, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
