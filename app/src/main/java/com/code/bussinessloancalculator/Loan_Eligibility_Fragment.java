package com.code.bussinessloancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Loan_Eligibility_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    ImageButton backloan;

    LinearLayout resulteligibleloan, resultEmieligible;

    private EditText grossMonthlyIncomeEditText;
    private EditText totalMonthlyEMIEditText;
    private EditText loanAmountEditText;
    private EditText annualInterestRateEditText;
    private EditText tenureEMIEditText;
    private EditText eligibleLoanAmountEditText;
    private EditText emiLoanEligibleEditText;
    private EditText loanAmountEligibleEditText;
    private EditText emiEligibleEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loan__eligibility_, container, false);


        backloan = view.findViewById(R.id.backloan);
        grossMonthlyIncomeEditText = view.findViewById(R.id.grossmonthlyincomeeditext);
        totalMonthlyEMIEditText = view.findViewById(R.id.totalmonthlyemiEditText);
        loanAmountEditText = view.findViewById(R.id.loanamountEdittext);
        annualInterestRateEditText = view.findViewById(R.id.AnnualinterestrateemiEditext);
        tenureEMIEditText = view.findViewById(R.id.tenureeemiEditext);
        eligibleLoanAmountEditText = view.findViewById(R.id.answereligibleloan);
        emiLoanEligibleEditText = view.findViewById(R.id.answeremiloanloaneligible);
        loanAmountEligibleEditText = view.findViewById(R.id.answerloanamounteligible);
        emiEligibleEditText = view.findViewById(R.id.answeremieligible);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonloaneligibility);
        resulteligibleloan = view.findViewById(R.id.resulteligibleloan);
        resultEmieligible = view.findViewById(R.id.resultEmieligible);

        resulteligibleloan.setVisibility(View.INVISIBLE);
        resultEmieligible.setVisibility(View.INVISIBLE);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanEligibility();


                resulteligibleloan.setVisibility(View.VISIBLE);
                resultEmieligible.setVisibility(View.VISIBLE);
            }
        });

        ImageButton resetButton = view.findViewById(R.id.resetbuttonloaneligibility);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                resulteligibleloan.setVisibility(View.INVISIBLE);
                resultEmieligible.setVisibility(View.INVISIBLE);
            }
        });

        backloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        // Optional: Add transaction to back stack
                        .replace(R.id.loaneligibiliti_frame, new Loan_Calculator_Fragment());
                fragmentTransaction.commit();requireActivity().finish();

            }

        });

        Button shareButton = view.findViewById(R.id.Sharebtnloan);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareLoanDetails();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }





    private void calculateLoanEligibility() {
        String grossMonthlyIncomeStr = grossMonthlyIncomeEditText.getText().toString();
        String totalMonthlyEMIStr = totalMonthlyEMIEditText.getText().toString();
        String loanAmountStr = loanAmountEditText.getText().toString();
        String annualInterestRateStr = annualInterestRateEditText.getText().toString();
        String tenureEMIStr = tenureEMIEditText.getText().toString();

        // Check if any input field is empty
        if (TextUtils.isEmpty(grossMonthlyIncomeStr) || TextUtils.isEmpty(totalMonthlyEMIStr) ||
                TextUtils.isEmpty(loanAmountStr) || TextUtils.isEmpty(annualInterestRateStr) ||
                TextUtils.isEmpty(tenureEMIStr)) {
            // Display error message or handle the empty fields appropriately
            return;
        }

        try {
            // Convert input strings to respective numeric values
            double grossMonthlyIncome = Double.parseDouble(grossMonthlyIncomeStr);
            double totalMonthlyEMI = Double.parseDouble(totalMonthlyEMIStr);
            double loanAmount = Double.parseDouble(loanAmountStr);
            double annualInterestRate = Double.parseDouble(annualInterestRateStr) / 100.0;
            int tenureEMI = Integer.parseInt(tenureEMIStr);

            // Calculate monthly gross income
            double monthlyGrossIncome = grossMonthlyIncome / 12;

            // Calculate eligible loan amount based on gross monthly income and total monthly debt payments
            double eligibleLoanAmount = Math.max(0, monthlyGrossIncome - totalMonthlyEMI);

            // Calculate monthly interest rate
            double monthlyInterestRate = annualInterestRate / 12;

            // Calculate EMI of the loan
            double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureEMI))
                    / (Math.pow(1 + monthlyInterestRate, tenureEMI) - 1);

            // Calculate loan amount eligible for the given EMI
            double loanAmountEligible = (monthlyGrossIncome * tenureEMI) / (1 + (monthlyInterestRate * tenureEMI));

            // Calculate EMI eligible for the loan amount
            double emiEligible = (loanAmountEligible * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureEMI))
                    / (Math.pow(1 + monthlyInterestRate, tenureEMI) - 1);

            // Format the calculated values and set them to respective EditText fields
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            eligibleLoanAmountEditText.setText(decimalFormat.format(eligibleLoanAmount));
            emiLoanEligibleEditText.setText(decimalFormat.format(emi));
            loanAmountEligibleEditText.setText(decimalFormat.format(loanAmountEligible));
            emiEligibleEditText.setText(decimalFormat.format(emiEligible));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the case where parsing of input strings to numeric values fails
            // Display appropriate error message or handle the error as required
        }
    }

    private void resetFields() {
        grossMonthlyIncomeEditText.setText("");
        totalMonthlyEMIEditText.setText("");
        loanAmountEditText.setText("");
        annualInterestRateEditText.setText("");
        tenureEMIEditText.setText("");
        eligibleLoanAmountEditText.setText("");
        emiLoanEligibleEditText.setText("");
        loanAmountEligibleEditText.setText("");
        emiEligibleEditText.setText("");
    }

    private void shareLoanDetails() {
        // Get the text from EditText fields
        String grossMonthlyIncome = grossMonthlyIncomeEditText.getText().toString();
        String totalMonthlyEMI = totalMonthlyEMIEditText.getText().toString();
        String loanAmount = loanAmountEditText.getText().toString();
        String annualInterestRate = annualInterestRateEditText.getText().toString();
        String tenureEMI = tenureEMIEditText.getText().toString();
        String eligibleLoanAmount = eligibleLoanAmountEditText.getText().toString();
        String emiLoanEligible = emiLoanEligibleEditText.getText().toString();
        String loanAmountEligible = loanAmountEligibleEditText.getText().toString();
        String emiEligible = emiEligibleEditText.getText().toString();

        // Create the message to share
        String shareMessage = "Gross Monthly Income: " + grossMonthlyIncome + "\n" +
                "Total Monthly EMI: " + totalMonthlyEMI + "\n" +
                "Loan Amount: " + loanAmount + "\n" +
                "Annual Interest Rate: " + annualInterestRate + "\n" +
                "Tenure: " + tenureEMI + "\n" +
                "Eligible Loan Amount: " + eligibleLoanAmount + "\n" +
                "EMI of the Loan: " + emiLoanEligible + "\n" +
                "Loan Amount Eligible: " + loanAmountEligible + "\n" +
                "EMI Eligible: " + emiEligible;

        // Create an Intent to share the message
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Loan Eligibility Details");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        // Start the activity to share
        startActivity(Intent.createChooser(shareIntent, "Share Loan Eligibility Details"));
    }


}