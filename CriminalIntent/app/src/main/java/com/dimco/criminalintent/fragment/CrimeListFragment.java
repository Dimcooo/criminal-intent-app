package com.dimco.criminalintent.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.dimco.criminalintent.R;
import com.dimco.criminalintent.entity.Crime;
import com.dimco.criminalintent.entity.CrimeLab;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView crimeRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUi();
        return view;
    }

    private void updateUi() {
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        CrimeAdapter adapter = new CrimeAdapter(crimes);
        crimeRecyclerView.setAdapter(adapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleView;
        private TextView dateTextView;
        private CheckBox solvedCheckBox;

        private Crime crime;

        public CrimeHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleView = itemView.findViewById(R.id.list_item_crime_title_text_view);
            dateTextView = itemView.findViewById(R.id.list_item_crime_date_text_view);
            solvedCheckBox = itemView.findViewById(R.id.list_item_crime_solved_checkbox);
        }

        public void bindCrime(Crime crime) {
            this.crime = crime;

            titleView.setText(crime.getTitle());
            dateTextView.setText(DateFormat.format("MMM dd, yyyy", crime.getDate()));
            solvedCheckBox.setChecked(crime.isSolved());
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(),
                    crime.getTitle() + " clicked!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> crimes;

        public CrimeAdapter(List<Crime> crimes) {
            this.crimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, viewGroup, false);

            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder crimeHolder, int position) {
            Crime crime = crimes.get(position);
            crimeHolder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return crimes.size();
        }
    }
}
