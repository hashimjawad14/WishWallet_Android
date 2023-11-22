package com.example.wishwallet;

import static com.example.wishwallet.MainActivity.existing_username;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIncomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String DAY;
    String DATE;
    String MONTH;
    String YEAR;
    TransactionModel tModel = new TransactionModel();

    DatePickerDialog.OnDateSetListener dateSetListener;


    String category;
    ArrayList<String> categories = new ArrayList<>();

    public AddIncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddIncomeFragment newInstance(String param1, String param2) {
        AddIncomeFragment fragment = new AddIncomeFragment();
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

        View v = inflater.inflate(R.layout.fragment_add_income, container, false);
        Button button = v.findViewById(R.id.switch_btn);
        ImageView calender = v.findViewById(R.id.cal_pic);
        TextView date_tv = v.findViewById(R.id.date_tv);
        Button save = v.findViewById(R.id.save_expense_btn);
        EditText title_tag = v.findViewById(R.id.expense_title_input);
        EditText amount_input = v.findViewById(R.id.expense_amount_input);

        LocalDate localDate = LocalDate.now();

        DAY = String.valueOf(localDate.getDayOfMonth());
        MONTH = String.valueOf(localDate.getMonthValue());
        YEAR = String.valueOf(localDate.getYear());

        DATE = DAY + "-" + MONTH + "-" + YEAR;

        date_tv.setText(DATE);

        TransactionDB db = new TransactionDB(getContext());



        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();

                int day_num = calendar.get(Calendar.DAY_OF_MONTH);
                int month_num = calendar.get(Calendar.MONTH);
                int year_num = calendar.get(Calendar.YEAR);

                DAY = String.valueOf(day_num);
                MONTH = String.valueOf(month_num);
                YEAR = String.valueOf(year_num);


                DatePickerDialog dialog = new DatePickerDialog(requireContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, day_num, month_num, year_num);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        DAY = String.valueOf(dayOfMonth);
                        MONTH = String.valueOf(month+1);
                        YEAR = String.valueOf(year);

                        DATE = DAY + "-" + MONTH + "-" + YEAR;

                        date_tv.setText(DATE);
                    }
                };
            }
        });

        Spinner cat_spin = v.findViewById(R.id.category_spinner);


        categories.add("Salary");
        categories.add("Profit");
        categories.add("Rental Income");
        categories.add("Miscellaneous");



        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categories);

        cat_spin.setAdapter(adapter);

        cat_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                cat_spin.setSelection(position);
                category = cat_spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tModel.username = existing_username;
                tModel.tag = title_tag.getText().toString();
                tModel.category = category;
                tModel.type = "Income";
                tModel.amount = amount_input.getText().toString();
                tModel.sign = "+";
                tModel.date = DATE;
                tModel.day = DAY;
                tModel.month = MONTH;
                tModel.year = YEAR;

                db.addTransaction(tModel);


                Toast.makeText(getContext(), "Income Added", Toast.LENGTH_SHORT).show();

                amount_input.setText("");
                title_tag.setText("");


            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.add_container, new AddExpenseFragment());
                transaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}