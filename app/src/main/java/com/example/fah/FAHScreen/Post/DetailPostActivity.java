package com.example.fah.FAHScreen.Post;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.example.fah.R;

public class DetailPostActivity extends AppCompatActivity {

    TextView txtDescription1;
    TextView txtDescription2;
    TextView txtRequired1;
    TextView txtRequired2;
    TextView txtQuyenLoi2;
    TextView txtQuyenLoi1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        addControls();
    }

    private void addControls() {
        txtDescription1 = findViewById(R.id.txtDescription1);
        txtRequired1 = findViewById(R.id.txtRequired1);
        txtQuyenLoi1 = findViewById(R.id.txtQuyenLoi1);

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
            txtDescription1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtDescription2.setVisibility(View.VISIBLE);
            txtDescription1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }

    public void onToggleRequired(View v) {
        if (txtRequired2.isShown()) {
            txtRequired2.setVisibility(View.GONE);
            txtRequired1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtRequired2.setVisibility(View.VISIBLE);
            txtRequired1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }

    public void onToggleQuyenLoi(View v) {
        if (txtQuyenLoi2.isShown()) {
            txtQuyenLoi2.setVisibility(View.GONE);
            txtQuyenLoi1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtQuyenLoi2.setVisibility(View.VISIBLE);
            txtQuyenLoi1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }
}
