package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Calendar;

public class Return_On_Investment_Fragment extends Fragment {


    EditText FromdateEdittext, todateEdittext;
    private EditText startingInvestmentEditText;
    private EditText lastInvestmentEditText;
    private EditText periodYearEditText;
    private EditText periodMonthEditText;
    private ImageButton calculateButton;

    ImageButton backreturnoninvest;

    private TextView answerInvestmentPeriodTextView;
    private TextView answerGainOrLossTextView;
    private TextView answerROITextView;
    private TextView answerSimpleAnnualROITextView;
    private TextView answerCompoundAnnualROITextView;
    LinearLayout resultinvestmentperiod;

    ImageButton resetbutton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_return__on__investment_, container, false);
        resultinvestmentperiod = view.findViewById(R.id.resultinvestmentperiod);
resetbutton=view.findViewById(R.id.resetbuttonreturnoninvestment);
        backreturnoninvest=view.findViewById(R.id.backreturn);



        startingInvestmentEditText = view.findViewById(R.id.startinginvestmentedittext);
        lastInvestmentEditText = view.findViewById(R.id.lastinvetsmentEditText);
        periodYearEditText = view.findViewById(R.id.periodyearEdittext);
        periodMonthEditText = view.findViewById(R.id.periodmonthEditext);
        calculateButton = view.findViewById(R.id.calculatebuttonreturnoninvestment);
        answerInvestmentPeriodTextView = view.findViewById(R.id.answerinvestmentperiod);
        answerGainOrLossTextView = view.findViewById(R.id.answergainorloass);
        answerROITextView = view.findViewById(R.id.answerreturnoninvestment);
        answerSimpleAnnualROITextView = view.findViewById(R.id.answersimpleannualroi);
        answerCompoundAnnualROITextView = view.findViewById(R.id.answercompoundannualroi);
        resultinvestmentperiod.setVisibility(View.INVISIBLE);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateROI();
                resultinvestmentperiod.setVisibility(View.VISIBLE);
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultinvestmentperiod.setVisibility(View.INVISIBLE);

            }
        });

        backreturnoninvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.return_frame, new Loan_Calculator_Fragment());
                fragmentTransaction.commit();requireActivity().finish();

            }
        });



        resultinvestmentperiod.setVisibility(View.INVISIBLE);
        resultinvestmentperiod.setVisibility(View.VISIBLE);





        return view;
    }

    private CharSequence showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), (view, year1, monthOfYear, dayOfMonth1) -> {
            // Do something with the selected date
            String selectedDate = dayOfMonth1 + "/" + (monthOfYear + 1) + "/" + year1;
            editText.setText(selectedDate);
        }, year, month, dayOfMonth);

        datePickerDialog.show();
        return null;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateROI() {
        // Get input values
        double startingInvestment = parseDouble(startingInvestmentEditText.getText().toString());
        double lastInvestment = parseDouble(lastInvestmentEditText.getText().toString());
        int periodYear = parseInt(periodYearEditText.getText().toString());
        int periodMonth = parseInt(periodMonthEditText.getText().toString());

        // Calculate ROI and other values
        double gainOrLoss = lastInvestment - startingInvestment;
        double ROI = (gainOrLoss / startingInvestment) * 100;
        double totalMonths = periodYear * 12 + periodMonth;
        double simpleAnnualROI = (ROI / totalMonths) * 12;
        double compoundAnnualROI = Math.pow((lastInvestment / startingInvestment), (1.0 / totalMonths)) - 1;

        // Display results
        answerInvestmentPeriodTextView.setText(periodYear + " years " + periodMonth + " months");
        answerGainOrLossTextView.setText(String.format("%.2f", gainOrLoss));
        answerROITextView.setText(String.format("%.2f", ROI) + "%");
        answerSimpleAnnualROITextView.setText(String.format("%.2f", simpleAnnualROI) + "%");
        answerCompoundAnnualROITextView.setText(String.format("%.2f", compoundAnnualROI * 100) + "%");

    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // Default value in case of parsing failure
        }
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Default value in case of parsing failure
        }
    }
}
