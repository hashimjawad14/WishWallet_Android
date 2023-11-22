package com.example.wishwallet;

import static com.example.wishwallet.MainActivity.existing_username;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class All_Transactions extends AppCompatActivity {

   public static ArrayList<TransactionModel> transactions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_transactions);

//        Button button = findViewById(R.id.switch_btn);
//        Button on_button = findViewById(R.id.on_btn);
//
//
//
//
//        TransactionDB db = new TransactionDB(this);
//
//        transactions = db.getExpenseTransactions(existing_username);
//
//
//        RecyclerView recyclerView = findViewById(R.id.all_transactions_recycler_view);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        TransactionAdapter adapter = new TransactionAdapter(transactions, this, transactions.size());
//
//        recyclerView.setAdapter(adapter);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.container, new Expense1Fragment());
        transaction.commit();




        statusbarColour();
    }
    private void statusbarColour(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_theme, this.getTheme()));
        }
    }
}