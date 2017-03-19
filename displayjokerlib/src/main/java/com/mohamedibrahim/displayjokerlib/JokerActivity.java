package com.mohamedibrahim.displayjokerlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokerActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA_STRING = "JOKE_EXTRA_STRING";
    @BindView(R2.id.tv_joke)
    TextView tvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joker);
        ButterKnife.bind(this);
        if (getIntent() != null && getIntent().hasExtra(JOKE_EXTRA_STRING)) {
            Bundle bundle = getIntent().getExtras();
            tvJoke.setText(bundle.getString(JOKE_EXTRA_STRING));
        }
    }
}
