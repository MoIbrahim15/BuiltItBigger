package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mohamedibrahim.displayjokerlib.JokerActivity;
import com.udacity.gradle.builditbigger.JokeAsyncTask;
import com.udacity.gradle.builditbigger.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mohamedibrahim.displayjokerlib.JokerActivity.JOKE_EXTRA_STRING;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.btn_tell_joke)
    Button btnTellJoke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        setLoading(true);
        final Intent intent = new Intent(getActivity(), JokerActivity.class);
        new JokeAsyncTask() {
            @Override
            protected void onPostExecute(String joke) {
                setLoading(false);
                intent.putExtra(JOKE_EXTRA_STRING, joke);
                startActivity(intent);
            }
        }.execute();
    }


    void setLoading(boolean isLoading) {
        if (isLoading) {
            btnTellJoke.setEnabled(false);
            progress.setVisibility(View.VISIBLE);
        } else {
            btnTellJoke.setEnabled(true);
            progress.setVisibility(View.GONE);
        }
    }
}
