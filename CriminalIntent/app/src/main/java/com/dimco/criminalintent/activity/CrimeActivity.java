package com.dimco.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.dimco.criminalintent.fragment.CrimeFragment;

import java.util.UUID;

import static com.dimco.criminalintent.util.Constants.EXTRA_CRIME_ID;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
