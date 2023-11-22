package com.example.wishwallet;

import static com.example.wishwallet.MainActivity.existing_username;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String selected_year;

    int jan_total = 0;
    int feb_total = 0;
    int march_total = 0;
    int april_total = 0;
    int may_total = 0;
    int june_total = 0;
    int july_total = 0;
    int aug_total = 0;
    int sept_total = 0;
    int oct_total = 0;
    int nov_total = 0;
    int dec_total = 0;

    int total_income;

    ArrayList<TransactionModel> transactions = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();


    public IncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeFragment newInstance(String param1, String param2) {
        IncomeFragment fragment = new IncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_income, container, false);

        Button switch_btn = v.findViewById(R.id.switch_btn);
        TextView total_tv = v.findViewById(R.id.home_transation_total);

        View jan_graph = v.findViewById(R.id.jan_graph);
        View feb_graph = v.findViewById(R.id.feb_graph);
        View march_graph = v.findViewById(R.id.march_graph);
        View april_graph = v.findViewById(R.id.april_graph);
        View may_graph = v.findViewById(R.id.may_graph);
        View june_graph = v.findViewById(R.id.june_graph);
        View july_graph = v.findViewById(R.id.july_graph);
        View aug_graph = v.findViewById(R.id.aug_graph);
        View sept_graph = v.findViewById(R.id.sept_graph);
        View oct_graph = v.findViewById(R.id.oct_graph);
        View nov_graph = v.findViewById(R.id.nov_graph);
        View dec_graph = v.findViewById(R.id.dec_graph);


        TransactionDB db = new TransactionDB(getContext());

        transactions = db.getIncomeTransactions(existing_username);


        for (int i = 0; i < transactions.size(); i++) {
            if (years.isEmpty()) {
                years.add(transactions.get(i).year);
            } else if (!(years.contains(transactions.get(i).year))) {
                years.add(transactions.get(i).year);
            }
        }


        Spinner year_spinner = v.findViewById(R.id.year_spinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, years);
        year_spinner.setAdapter(adapter1);


        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                year_spinner.setSelection(position);
                selected_year = year_spinner.getSelectedItem().toString();


                for (int i = 0; i < transactions.size(); i++) {


                    if (transactions.get(i).month.equals("1") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            jan_total = jan_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("2") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            feb_total = feb_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("3") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            march_total = march_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("4") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            april_total = april_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("5") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            may_total = may_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("6") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            june_total = june_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("7") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            july_total = july_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("8") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            aug_total = aug_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("9") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            sept_total = sept_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("10") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            oct_total = oct_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("11") && transactions.get(i).year.equals(selected_year) ) {
                        try {
                            nov_total = nov_total + Integer.parseInt(transactions.get(i).amount);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (transactions.get(i).month.equals("12") && transactions.get(i).year.equals(selected_year)) {
                        try {
                            dec_total = dec_total + Integer.parseInt(transactions.get(i).amount);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                total_income = jan_total + feb_total + march_total + april_total + may_total +
                        june_total + july_total + aug_total + sept_total + oct_total + nov_total + dec_total;

                total_tv.setText(String.valueOf(total_income));


                jan_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int) (jan_total*0.00001*330)));
                feb_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(feb_total*0.00001*330)));
                march_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(march_total*0.00001*330)));
                april_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(april_total*0.00001*330)));
                may_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(may_total*0.00001*330)));
                june_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(june_total*0.00001*330)));
                july_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int) (july_total*0.00001*330)));
                aug_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(aug_total*0.00001*330)));
                sept_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(sept_total*0.00001*330)));
                oct_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(oct_total*0.00001*330)));
                nov_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(nov_total*0.00001*330)));
                dec_graph.setLayoutParams(new LinearLayout.LayoutParams(20, (int)(dec_total*0.00001*330)));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        RecyclerView recyclerView = v.findViewById(R.id.all_transactions_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TransactionAdapter adapter = new TransactionAdapter(transactions, getContext(), transactions.size());

        recyclerView.setAdapter(adapter);

        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.main_container, new TransactionFragment());
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}