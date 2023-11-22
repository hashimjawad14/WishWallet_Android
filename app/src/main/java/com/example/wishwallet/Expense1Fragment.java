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
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Expense1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Expense1Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Expense1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Expense1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Expense1Fragment newInstance(String param1, String param2) {
        Expense1Fragment fragment = new Expense1Fragment();
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
        View v = inflater.inflate(R.layout.fragment_expense1, container, false);

        Button switch_btn = v.findViewById(R.id.switch_btn);
        ArrayList<TransactionModel> transactions = new ArrayList<>();

        TransactionDB db = new TransactionDB(getContext());

        transactions = db.getExpenseTransactions(existing_username);


        RecyclerView recyclerView = v.findViewById(R.id.all_transactions_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        TransactionAdapter adapter = new TransactionAdapter(transactions, getContext(), transactions.size());

        recyclerView.setAdapter(adapter);


        switch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.container, new Income1Fragment());
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}