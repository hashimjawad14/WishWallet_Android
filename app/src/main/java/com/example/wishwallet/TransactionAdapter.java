package com.example.wishwallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.Viewholder> {

    ArrayList<TransactionModel> transactions = new ArrayList<>();
    Context context;
    int size;

    public TransactionAdapter(ArrayList<TransactionModel> transactions, Context context, int size) {
        this.transactions = transactions;
        this.context = context;
        this.size = size;
    }

    @NonNull
    @Override
    public TransactionAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.home_transaction_card, parent, false);
        Viewholder viewholder = new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.Viewholder holder, int position) {

        holder.img.setImageResource(transactions.get(position).img);
        holder.tag_txt.setText(transactions.get(position).tag);
        holder.cat_txt.setText(transactions.get(position).category);
        holder.sign_txt.setText(transactions.get(position).sign);
        holder.amt_txt.setText(transactions.get(position).amount);
        holder.date_txt.setText(transactions.get(position).date);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView tag_txt;
        TextView cat_txt;
        TextView sign_txt;
        TextView amt_txt;
        TextView date_txt;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.cat_img_home_transaction);
            tag_txt = itemView.findViewById(R.id.home_transation_tag);
            cat_txt = itemView.findViewById(R.id.home_transation_category);
            sign_txt = itemView.findViewById(R.id.home_transation_amount_sign);
            amt_txt = itemView.findViewById(R.id.home_transation_amount);
            date_txt = itemView.findViewById(R.id.home_transation_date);
        }
    }
}
