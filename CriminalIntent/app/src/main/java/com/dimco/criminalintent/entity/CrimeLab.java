package com.dimco.criminalintent.entity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;

    public List<Crime> getCrimes() {
        return crimes;
    }

    private List<Crime> crimes;

    public static CrimeLab getCrimeLab(Context context) {
        return (crimeLab == null) ? new CrimeLab(context) : crimeLab;
    }

    private CrimeLab(Context context) {
        crimes = new ArrayList<>();
        Crime crime;
        for (int i = 0; i < 100; i++) {
            crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            crimes.add(crime);
        }
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : crimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }

        return null;
    }
}
