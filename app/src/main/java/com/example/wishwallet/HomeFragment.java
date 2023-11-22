package com.example.wishwallet;

import static com.example.wishwallet.All_Transactions.transactions;
import static com.example.wishwallet.MainActivity.existing_username;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static int total = 0;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textView = v.findViewById(R.id.all_transactions);
        TextView display_name = v.findViewById(R.id.display_name);
        TextView balance = v.findViewById(R.id.Total_Balance);
        TextView t_expense = v.findViewById(R.id.total_expenses);
        TextView t_income = v.findViewById(R.id.total_income);
        LinearLayout income_section = v.findViewById(R.id.income_section);
        LinearLayout expense_section = v.findViewById(R.id.expense_section);


        income_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, new IncomeFragment());
                ft.commit();
            }
        });

        expense_section.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.main_container, new TransactionFragment());
                ft.commit();
            }
        });

        int total_expense = 0;
        int total_income = 0;



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), All_Transactions.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.recent_transactions_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<TransactionModel> models = new ArrayList<>();

        TransactionDB db = new TransactionDB(getContext());

        ArrayList<TransactionModel> transactionModels = new ArrayList<>();

        transactionModels = db.getExpenseTransactions(existing_username);

        for (int i=0; i<transactionModels.size(); i++)
        {

            try{
                total_expense = total_expense + Integer.parseInt(transactionModels.get(i).amount);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


        }

        transactionModels = db.getIncomeTransactions(existing_username);

        for (int i=0; i<transactionModels.size(); i++)
        {
            try{
                total_income = total_income + Integer.parseInt(transactionModels.get(i).amount);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        total = total_income - total_expense;

        balance.setText(String.valueOf(total));
        t_income.setText(String.valueOf(total_income));
        t_expense.setText(String.valueOf(total_expense));





        UserDB Udb = new UserDB(getContext());

        ArrayList<UserModel> users = new ArrayList<>();

        users = Udb.getUser();

        models = db.getTransactions(existing_username);

        UserModel userModel = new UserModel();

        String Name = null;
        for (int i = 0; i < users.size(); i++) {
            userModel = users.get(i);

            if (userModel.username.equals(existing_username)) {
                Name = userModel.name;
            }
        }

        display_name.setText(Name);


        TransactionAdapter adapter = new TransactionAdapter(models, getContext(), models.size());
        recyclerView.setAdapter(adapter);


        // Inflate the layout for this fragment
        return v;
    }
}