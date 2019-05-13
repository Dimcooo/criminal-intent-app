package com.dimco.criminalintent.activity;

import android.support.v4.app.Fragment;

import com.dimco.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
