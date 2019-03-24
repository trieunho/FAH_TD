package com.example.fah.FAHScreen.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.fah.R;

public class DetailPostActivity extends AppCompatActivity {

    TextView txtDescription2;
    TextView txtRequired2;
    TextView txtQuyenLoi2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_post);

        addControls();
    }

    private void addControls() {
        txtDescription2 = findViewById(R.id.txtDescription2);
        txtDescription2.setVisibility(View.GONE);
        txtRequired2 = findViewById(R.id.txtRequired2);
        txtRequired2.setVisibility(View.GONE);
        txtQuyenLoi2 = findViewById(R.id.txtQuyenLoi2);
        txtQuyenLoi2.setVisibility(View.GONE);
    }

    public void onToggleJob(View v) {
        if (txtDescription2.isShown()) {
            txtDescription2.setVisibility(View.GONE);
        } else {
            txtDescription2.setVisibility(View.VISIBLE);
        }
    }

    public void onToggleRequired(View v) {
        if (txtRequired2.isShown()) {
            txtRequired2.setVisibility(View.GONE);
        } else {
            txtRequired2.setVisibility(View.VISIBLE);
        }
    }

    public void onToggleQuyenLoi(View v) {
        if (txtQuyenLoi2.isShown()) {
            txtQuyenLoi2.setVisibility(View.GONE);
        } else {
            txtQuyenLoi2.setVisibility(View.VISIBLE);
        }
    }
}
