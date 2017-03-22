package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

    @BindView(R.id.adView)
    AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("F2998E6F789E2E4227461CB9139AB42C")
                .build();
        mAdView.loadAd(adRequest);

        return root;
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        final Intent intent = new Intent(getActivity(), JokerActivity.class);
        new JokeAsyncTask() {
            @Override
            protected void onPostExecute(String joke) {
                intent.putExtra(JOKE_EXTRA_STRING, joke);
                startActivity(intent);
            }
        }.execute();
    }
}
