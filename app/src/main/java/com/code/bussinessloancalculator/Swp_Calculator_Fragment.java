package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Swp_Calculator_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    ImageButton back1;
    private EditText swpAmountEditText;
    private EditText rateOfReturnEditText;
    private EditText tenureInEdittext;
    private EditText initialInvestmentEdittext;
     Spinner spinner;
    private TextView resultTotalWithdrawalTextView, resultTotalProfitTextView, resultEndBalanceTextView, resultNoOfWithdrawalsTextView;

    LinearLayout resulttotalwithdrawal, resulttotalprofit, resultendbalance, resultnoofwithdrawals;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swp__calculator_, container, false);
        resulttotalwithdrawal = view.findViewById(R.id.resulttotalwithdrawal);
        resulttotalprofit = view.findViewById(R.id.resulttotalprofit);
        resultendbalance = view.findViewById(R.id.resultendbalance);
        resultnoofwithdrawals = view.findViewById(R.id.resultnoofwithdrawals);
        resulttotalwithdrawal.setVisibility(View.INVISIBLE);
        resulttotalprofit.setVisibility(View.INVISIBLE);
        resultendbalance.setVisibility(View.INVISIBLE);
        resultnoofwithdrawals.setVisibility(View.INVISIBLE);
        spinner = view.findViewById(R.id.spinnerswp);
        swpAmountEditText = view.findViewById(R.id.swpEditText);
        rateOfReturnEditText = view.findViewById(R.id.durationswpEdittext);
        tenureInEdittext = view.findViewById(R.id.rateofreturnswpEdittext);
        initialInvestmentEdittext = view.findViewById(R.id.swpAmountEditText);
        back1 = view.findViewById(R.id.backswp);
        resultTotalWithdrawalTextView = view.findViewById(R.id.answertotalwithdrawal);
        resultTotalProfitTextView = view.findViewById(R.id.answertotalprofit);
        resultEndBalanceTextView = view.findViewById(R.id.answerendbalance);
        resultNoOfWithdrawalsTextView = view.findViewById(R.id.answernoofwithdrawal);

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonswp);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.Swp_frame, new Mutual_Funds_SIP_Fragment());
                fragmentTransaction.commit();requireActivity().finish();

            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSWP();
                resulttotalwithdrawal.setVisibility(View.VISIBLE);
                resulttotalprofit.setVisibility(View.VISIBLE);
                resultendbalance.setVisibility(View.VISIBLE);
                resultnoofwithdrawals.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonswp);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resulttotalwithdrawal.setVisibility(View.INVISIBLE);
                resulttotalprofit.setVisibility(View.INVISIBLE);
                resultendbalance.setVisibility(View.INVISIBLE);
                resultnoofwithdrawals.setVisibility(View.INVISIBLE);
            }
        });
        spinner = view.findViewById(R.id.spinnerswp);

        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Years");
        categories.add("Month");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, categories);

// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("DefaultLocale")

    private void calculateSWP() {
        String swpAmountStr = swpAmountEditText.getText().toString().trim();
        String rateOfReturnStr = rateOfReturnEditText.getText().toString().trim();
        String tenureInStr = tenureInEdittext.getText().toString().trim();
        String initialInvestmentStr = initialInvestmentEdittext.getText().toString().trim();

        // Perform validation checks here...

        try {
            double swpAmount = Double.parseDouble(swpAmountStr);
            double rateOfReturn = Double.parseDouble(rateOfReturnStr) / 100.0;
            int tenureInMonths;

            // Convert tenure to months based on spinner selection
            switch (spinner.getSelectedItemPosition()) {
                case 0: // Years
                    tenureInMonths = Integer.parseInt(tenureInStr) * 12;
                    break;
                case 1: // Months
                    tenureInMonths = Integer.parseInt(tenureInStr);
                    break;
                default:
                    // Handle unexpected selection
                    tenureInMonths = 0;
                    break;

            }

            double initialInvestment = Double.parseDouble(initialInvestmentStr);
            double totalWithdrawal = swpAmount * tenureInMonths;
            double totalProfit = totalWithdrawal - initialInvestment;
            double endBalance = initialInvestment + totalProfit;
            int numberOfWithdrawals = (int) (totalWithdrawal / swpAmount);

            resultTotalWithdrawalTextView.setText(String.format("%.2f", totalWithdrawal));
            resultTotalProfitTextView.setText(String.format("%.2f", totalProfit));
            resultEndBalanceTextView.setText(String.format("%.2f", endBalance));
            resultNoOfWithdrawalsTextView.setText(String.valueOf(numberOfWithdrawals));
            // Log the results or perform further actions
            Log.d("SWP_CALCULATION", "Total Withdrawal: " + totalWithdrawal);
            Log.d("SWP_CALCULATION", "Total Profit: " + totalProfit);
            Log.d("SWP_CALCULATION", "End Balance: " + endBalance);
            Log.d("SWP_CALCULATION", "Number of Withdrawals: " + numberOfWithdrawals);
        } catch (NumberFormatException e) {
            // Handle parsing errors
            Log.e("SWP_CALCULATION", "Error parsing input: " + e.getMessage());
        } catch (Exception e) {
            // Handle other unexpected errors
            Log.e("SWP_CALCULATION", "Unexpected error: " + e.getMessage());
        }

        // Update UI with calculated values

    }    private void resetFields() {
        swpAmountEditText.setText("");
        rateOfReturnEditText.setText("");
        tenureInEdittext.setText("");
        initialInvestmentEdittext.setText("");
        resultTotalWithdrawalTextView.setText("");
        resultTotalProfitTextView.setText("");
        resultEndBalanceTextView.setText("");
        resultNoOfWithdrawalsTextView.setText("");
    }

}