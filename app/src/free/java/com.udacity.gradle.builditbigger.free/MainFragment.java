package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
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
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.btn_tell_joke)
    Button btnTellJoke;

    InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("F2998E6F789E2E4227461CB9139AB42C")
                // TODO:add your test device
                .build();
        mAdView.loadAd(adRequest);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-1763493570283349/2142898915");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                goJokerActivity();
            }
        });

        return root;
    }

    @OnClick(R.id.btn_tell_joke)
    void tellJoke() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            goJokerActivity();
        }
    }

    private void goJokerActivity() {
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


    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("F2998E6F789E2E4227461CB9139AB42C")
                // TODO:add your test device
                .build();

        mInterstitialAd.loadAd(adRequest);
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
