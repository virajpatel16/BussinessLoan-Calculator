package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Recurring_Deposite_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    EditText FirstEmieditext;
    ImageButton backRecurring;

    ImageButton resetbuttonrecurring;

    private EditText investmentAmountEditText, expectRateOfInterestEditText,
            tenureEditText, firstEmiEditText;
    private TextView answerInvestmentAmountTextView, answerTotalInterestValueTextView,
            answerMaturityDateTextView, answerMaturityValueTextView;

    LinearLayout resultmaturityrecurring, resultinvestmentamountrecurring;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recurring__deposite_, container, false);
        FirstEmieditext = view.findViewById(R.id.FirstEmiReccurringEditext);
        backRecurring = view.findViewById(R.id.backRecurring);
        resultmaturityrecurring = view.findViewById(R.id.resultmaturityrecurring);
        resultinvestmentamountrecurring = view.findViewById(R.id.resultinvestmentamountrecurring);
        resetbuttonrecurring=view.findViewById(R.id.resetbuttonrecurring);
        resultmaturityrecurring.setVisibility(View.INVISIBLE);
        resultinvestmentamountrecurring.setVisibility(View.INVISIBLE);

        investmentAmountEditText = view.findViewById(R.id.InvestmentAmountedittext);
        expectRateOfInterestEditText = view.findViewById(R.id.ExpectrateofinterestRecurringEditText);
        tenureEditText = view.findViewById(R.id.TenureRecurringEdittext);

        answerInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountrecurring);
        answerTotalInterestValueTextView = view.findViewById(R.id.answertotalinterestvaluerecurring);
        answerMaturityDateTextView = view.findViewById(R.id.answermatururitydaterecurring);
        answerMaturityValueTextView = view.findViewById(R.id.answermatururityvaluerecurring);

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonrecurring);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRecurring();
                resultmaturityrecurring.setVisibility(View.VISIBLE);
                resultinvestmentamountrecurring.setVisibility(View.VISIBLE);
            }
        });
        resetbuttonrecurring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the text of the EditText objects
                investmentAmountEditText.setText("");
                expectRateOfInterestEditText.setText("");
                tenureEditText.setText("");
                FirstEmieditext.setText("");

                // Optionally, hide any result views if needed
                resultmaturityrecurring.setVisibility(View.INVISIBLE);
                resultinvestmentamountrecurring.setVisibility(View.INVISIBLE);

                // Clear any previously calculated results
                answerInvestmentAmountTextView.setText("");
                answerTotalInterestValueTextView.setText("");
                answerMaturityDateTextView.setText("");
                answerMaturityValueTextView.setText("");
            }
        });


        Button saveButton = view.findViewById(R.id.saverecurring);
        Button Sharebtnrecurring = view.findViewById(R.id.Sharebtnrecurring);
        backRecurring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.recurring_frame, new Banking__Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        Sharebtnrecurring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupShareButton();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSaveButton();
            }
        });


        FirstEmieditext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
        return view;
    }

    public void showDatePickerDialog(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                FirstEmieditext.setText(selectedDate);
            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void calculateRecurring() {
        String investmentAmountStr = investmentAmountEditText.getText().toString().trim();
        String rateOfInterestStr = expectRateOfInterestEditText.getText().toString().trim();
        String tenureStr = tenureEditText.getText().toString().trim();
        String firstEmiStr = FirstEmieditext.getText().toString().trim();

        if (TextUtils.isEmpty(investmentAmountStr) || TextUtils.isEmpty(rateOfInterestStr) ||
                TextUtils.isEmpty(tenureStr) || TextUtils.isEmpty(firstEmiStr)) {
            return;
        }

        double investmentAmount = Double.parseDouble(investmentAmountStr);
        double rateOfInterest = Double.parseDouble(rateOfInterestStr);
        int tenure = Integer.parseInt(tenureStr);

        double interestRate = rateOfInterest / 100.0;
        double totalInterest = investmentAmount * interestRate * tenure;
        double maturityValue = investmentAmount + totalInterest;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            calendar.setTime(sdf.parse(firstEmiStr));
            calendar.add(Calendar.MONTH, tenure);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String maturityDate = sdf.format(calendar.getTime());

        answerInvestmentAmountTextView.setText(String.format("%.2f", investmentAmount));
        answerTotalInterestValueTextView.setText(String.format("%.2f", totalInterest));
        answerMaturityDateTextView.setText(maturityDate);
        answerMaturityValueTextView.setText(String.format("%.2f", maturityValue));
    }

    private void saveToFile(String fileName) {
        String investmentAmount = answerInvestmentAmountTextView.getText().toString();
        String totalInterestValue = answerTotalInterestValueTextView.getText().toString();
        String maturityDate = answerMaturityDateTextView.getText().toString();
        String maturityValue = answerMaturityValueTextView.getText().toString();

        String fileContent = "Fixed Deposit Details:\n\n" +
                "Investment Amount: " + investmentAmount + "\n" +
                "Total Interest Value: " + totalInterestValue + "\n" +
                "Maturity Date: " + maturityDate + "\n" +
                "Maturity Value: " + maturityValue;

        try {
            FileWriter writer = new FileWriter(getContext().getFilesDir() + "/" + fileName + ".txt");
            writer.append(fileContent);
            writer.flush();
            writer.close();
            Toast.makeText(requireActivity(), "File saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireActivity(), "Failed to save file!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupShareButton() {
        String investmentAmount = answerInvestmentAmountTextView.getText().toString();
        String totalInterestValue = answerTotalInterestValueTextView.getText().toString();
        String maturityDate = answerMaturityDateTextView.getText().toString();
        String maturityValue = answerMaturityValueTextView.getText().toString();

        String shareMessage = "Fixed Deposit Details:\n\n" +
                "Investment Amount: " + investmentAmount + "\n" +
                "Total Interest Value: " + totalInterestValue + "\n" +
                "Maturity Date: " + maturityDate + "\n" +
                "Maturity Value: " + maturityValue;

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void setupSaveButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Save File");
        final EditText input = new EditText(requireActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fileName = input.getText().toString();
                saveToFile(fileName);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
