package com.example.wishwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TransactionDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TransactionDB";
    private static final int VERSION = 1;
    private static final String TRANSACTION_TABLE = "transactions_TB";
    private static final String ID = "transaction_id";
    private static final String TAG = "transaction_tag";
    private static final String CATEGORY = "transaction_category";
    private static final String SIGN = "transaction_sign";
    private static final String TYPE = "transaction_type";
    private static final String DATE = "transaction_date";
    private static final String AMOUNT = "transaction_amount";
    private static final String DAY = "transaction_day";
    private static final String MONTH = "transaction_month";
    private static final String YEAR = "transaction_year";
    private static final String USERNAME = "username";

    public TransactionDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TRANSACTION_TABLE + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TAG + " TEXT NOT NULL, " +
                CATEGORY + " TEXT NOT NULL, " +
                SIGN + " TEXT NOT NULL, " +
                TYPE + " TEXT NOT NULL, " +
                DATE + " TEXT NOT NULL, " +
                AMOUNT + " TEXT NOT NULL," +
                DAY + " TEXT NOT NULL," +
                MONTH + " TEXT NOT NULL," +
                YEAR + " TEXT NOT NULL, " +
                USERNAME + " TEXT NOT NULL )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TABLE);

        onCreate(db);

    }

    public void addTransaction(TransactionModel transactionModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TAG, transactionModel.tag);
        values.put(CATEGORY, transactionModel.category);
        values.put(SIGN, transactionModel.sign);
        values.put(TYPE, transactionModel.type);
        values.put(DATE, transactionModel.date);
        values.put(AMOUNT, transactionModel.amount);
        values.put(DAY, transactionModel.day);
        values.put(MONTH, transactionModel.month);
        values.put(YEAR, transactionModel.year);
        values.put(USERNAME, transactionModel.username);

        db.insert(TRANSACTION_TABLE, null, values);
    }

    public ArrayList<TransactionModel> getTransactions(String username){

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<TransactionModel> transactions = new ArrayList<>();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSACTION_TABLE, null);

        while (cursor.moveToNext())
        {
            TransactionModel model = new TransactionModel();

            model.username = cursor.getString(10);

            if (model.username.equals(username)) {

                model.id = cursor.getInt(0);
                model.tag = cursor.getString(1);
                model.category = cursor.getString(2);

                if (model.category.equals("Salary")) {
                    model.img = R.drawable.salary;
                } else if (model.category.equals("Profit")) {
                    model.img = R.drawable.profit;
                } else if (model.category.equals("Rental Income")) {
                    model.img = R.drawable.housing;
                } else if (model.category.equals("Miscellaneous")) {
                    model.img = R.drawable.other;
                } else if (model.category.equals("Food")) {
                    model.img = R.drawable.food;
                } else if (model.category.equals("Rent")) {
                    model.img = R.drawable.housing;
                } else if (model.category.equals("Utilities")) {
                    model.img = R.drawable.utilities;
                } else if (model.category.equals("Healthcare")) {
                    model.img = R.drawable.healthcare;
                } else if (model.category.equals("Fuel")) {
                    model.img = R.drawable.fuel;
                } else if (model.category.equals("Phone/Internet")) {
                    model.img = R.drawable.phone;
                } else if (model.category.equals("Entertainment")) {
                    model.img = R.drawable.entertainment;
                } else if (model.category.equals("Education")) {
                    model.img = R.drawable.education;
                } else if (model.category.equals("Shopping")) {
                    model.img = R.drawable.shopping;
                } else if (model.category.equals("Travel")) {
                    model.img = R.drawable.travel;
                }


                model.sign = cursor.getString(3);
                model.type = cursor.getString(4);
                model.date = cursor.getString(5);
                model.amount = cursor.getString(6);
                model.day = cursor.getString(7);
                model.month = cursor.getString(8);
                model.year = cursor.getString(9);

                transactions.add(model);

            }
        }

        return transactions;
    }

    public ArrayList<TransactionModel> getExpenseTransactions(String username){

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<TransactionModel> transactions = new ArrayList<>();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSACTION_TABLE, null);

        while (cursor.moveToNext())
        {
            TransactionModel model = new TransactionModel();

            model.username = cursor.getString(10);

            if (model.username.equals(username)) {

                model.id = cursor.getInt(0);
                model.tag = cursor.getString(1);
                model.category = cursor.getString(2);

                if (model.category.equals("Miscellaneous")) {
                    model.img = R.drawable.other;
                } else if (model.category.equals("Food")) {
                    model.img = R.drawable.food;
                } else if (model.category.equals("Rent")) {
                    model.img = R.drawable.housing;
                } else if (model.category.equals("Utilities")) {
                    model.img = R.drawable.utilities;
                } else if (model.category.equals("Healthcare")) {
                    model.img = R.drawable.healthcare;
                } else if (model.category.equals("Fuel")) {
                    model.img = R.drawable.fuel;
                } else if (model.category.equals("Phone/Internet")) {
                    model.img = R.drawable.phone;
                } else if (model.category.equals("Entertainment")) {
                    model.img = R.drawable.entertainment;
                } else if (model.category.equals("Education")) {
                    model.img = R.drawable.education;
                } else if (model.category.equals("Shopping")) {
                    model.img = R.drawable.shopping;
                } else if (model.category.equals("Travel")) {
                    model.img = R.drawable.travel;
                }

                model.sign = cursor.getString(3);
                model.type = cursor.getString(4);
                model.date = cursor.getString(5);
                model.amount = cursor.getString(6);
                model.day = cursor.getString(7);
                model.month = cursor.getString(8);
                model.year = cursor.getString(9);


                if (model.type.equals("Expense")) {
                    transactions.add(model);
                }
            }

        }

        return transactions;
    }

    public ArrayList<TransactionModel> getIncomeTransactions(String username){

        SQLiteDatabase db = getReadableDatabase();

        ArrayList<TransactionModel> transactions = new ArrayList<>();



        Cursor cursor = db.rawQuery("SELECT * FROM " + TRANSACTION_TABLE, null);

        while (cursor.moveToNext()) {
            TransactionModel model = new TransactionModel();

            model.username = cursor.getString(10);

            if (model.username.equals(username)) {

                model.id = cursor.getInt(0);
                model.tag = cursor.getString(1);
                model.category = cursor.getString(2);

                if (model.category.equals("Salary")) {
                    model.img = R.drawable.salary;
                } else if (model.category.equals("Profit")) {
                    model.img = R.drawable.profit;
                } else if (model.category.equals("Rental Income")) {
                    model.img = R.drawable.housing;
                } else if (model.category.equals("Miscellaneous")) {
                    model.img = R.drawable.other;
                }

                model.sign = cursor.getString(3);
                model.type = cursor.getString(4);
                model.date = cursor.getString(5);
                model.amount = cursor.getString(6);
                model.day = cursor.getString(7);
                model.month = cursor.getString(8);
                model.year = cursor.getString(9);

                if (model.type.equals("Income")) {
                    transactions.add(model);
                }
            }

        }

        return transactions;
    }

}
