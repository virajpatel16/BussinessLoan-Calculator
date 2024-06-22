package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Emi_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText principalAmountEditText;
    private EditText interestRateEditText;
    private EditText loanTenureEditText;
    private EditText firstEmiEditText;
    private TextView emiTextView;
    private TextView loanLastDateTextView;

    LinearLayout interestemi, emimonthly, loanlastdatecard;

    private TextView answerInvestmentAmountTextView;
    private TextView answerTotalInterestValueTextView;
    private TextView answerMaturityDateTextView;
    private TextView answerMaturityValueTextView;
    ImageButton backemi;
    EditText answertotalinterestemi;
    EditText answertotalprincipalemi;
    EditText answertotalpaymentemi;

    private Button shareButton;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emi_, container, false);

        backemi = view.findViewById(R.id.backemi);
        principalAmountEditText = view.findViewById(R.id.principalamountedittext);
        interestRateEditText = view.findViewById(R.id.interrestemiEditText);
        loanTenureEditText = view.findViewById(R.id.loantenureEdittext);
        firstEmiEditText = view.findViewById(R.id.FirstEmiEditext);
        emiTextView = view.findViewById(R.id.answerloanlastdate);
        loanLastDateTextView = view.findViewById(R.id.answerloanlastdateemi);
        shareButton = view.findViewById(R.id.sharebuttonemi);
        Button Save = view.findViewById(R.id.savebuttonemi);
        answertotalpaymentemi = view.findViewById(R.id.answertotalpaymentemi);
        answertotalprincipalemi = view.findViewById(R.id.answertotalprincipalemi);
        answertotalinterestemi = view.findViewById(R.id.answertotalinterestemi);
        interestemi = view.findViewById(R.id.interestemi);
        emimonthly = view.findViewById(R.id.emimonthly);
        loanlastdatecard = view.findViewById(R.id.loanlastdatecard);
        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonemi);

        interestemi.setVisibility(View.INVISIBLE);
        emimonthly.setVisibility(View.INVISIBLE);
        loanlastdatecard.setVisibility(View.INVISIBLE);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEmi();

                interestemi.setVisibility(View.VISIBLE);
                emimonthly.setVisibility(View.VISIBLE);
                loanlastdatecard.setVisibility(View.VISIBLE);
            }
        });

        backemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction

                        .replace(R.id.emi_frame, new Loan_Calculator_Fragment());
                fragmentTransaction.commit();requireActivity().finish();

            }
        });
        ImageButton resetbutton = view.findViewById(R.id.resetbuttonemi);
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
                interestemi.setVisibility(View.INVISIBLE);
                emimonthly.setVisibility(View.INVISIBLE);
                loanlastdatecard.setVisibility(View.INVISIBLE);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement share functionality
                shareEmiResult();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSaveButton();
            }
        });




        firstEmiEditText.setOnClickListener(new View.OnClickListener() {
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
                // Do something with the selected date
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                firstEmiEditText.setText(selectedDate);

            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void calculateEmi() {
        String principalAmountStr = principalAmountEditText.getText().toString();
        String interestRateStr = interestRateEditText.getText().toString();
        String loanTenureStr = loanTenureEditText.getText().toString();
        String firstEmiStr = firstEmiEditText.getText().toString();

        if (principalAmountStr.isEmpty() || interestRateStr.isEmpty() ||
                loanTenureStr.isEmpty() || firstEmiStr.isEmpty()) {
            Toast.makeText(requireActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double principalAmount = Double.parseDouble(principalAmountStr);
        double interestRate = Double.parseDouble(interestRateStr) / 100.0;
        int loanTenure = Integer.parseInt(loanTenureStr);

        // Calculate monthly interest rate
        double monthlyInterestRate = interestRate / 12.0;

        // Calculate EMI using the formula
        double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenure)) /
                (Math.pow(1 + monthlyInterestRate, loanTenure) - 1);

        // Set the calculated EMI in the corresponding TextView
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String emiStr = decimalFormat.format(emi);
        emiTextView.setText(emiStr);

        // Calculate total interest
        double totalInterest = (emi * loanTenure) - principalAmount;
        String totalInterestStr = decimalFormat.format(totalInterest);
        answertotalinterestemi.setText(totalInterestStr);

        // Calculate total principal
        double totalPrincipal = principalAmount;
        String totalPrincipalStr = decimalFormat.format(totalPrincipal);
        answertotalprincipalemi.setText(totalPrincipalStr);

        // Calculate total payment
        double totalPayment = emi * loanTenure;
        String totalPaymentStr = decimalFormat.format(totalPayment);
        answertotalpaymentemi.setText(totalPaymentStr);

        // Calculate and set loan last date
        calculateLoanLastDate(firstEmiStr, loanTenure);
    }

    private void shareEmiResult() {
        String emiResult = emiTextView.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "EMI Result: " + emiResult);
        startActivity(Intent.createChooser(shareIntent, "Share EMI Result"));
    }

    private void resetFields() {
        principalAmountEditText.setText("");
        interestRateEditText.setText("");
        loanTenureEditText.setText("");
        firstEmiEditText.setText("");
        emiTextView.setText("");
        loanLastDateTextView.setText("");
    }

    private void setupSaveButton() {


        // Create a dialog to prompt for the file name
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Save File");

        // Set up the input
        final EditText input = new EditText(requireActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
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

    private void saveToFile(String fileName) {
        // Retrieve the text from the result views
        String investmentAmount = answerInvestmentAmountTextView.getText().toString();
        String totalInterestValue = answerTotalInterestValueTextView.getText().toString();
        String maturityDate = answerMaturityDateTextView.getText().toString();
        String maturityValue = answerMaturityValueTextView.getText().toString();

        // Construct the content to be written to the file
        String fileContent = "Fixed Deposit Details:\n\n" +
                "Investment Amount: " + investmentAmount + "\n" +
                "Total Interest Value: " + totalInterestValue + "\n" +
                "Maturity Date: " + maturityDate + "\n" +
                "Maturity Value: " + maturityValue;

        try {
            // Create a FileWriter to write to the file
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

    private void calculateLoanLastDate(String firstEmiStr, int loanTenure) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();

        try {
            Date firstEmiDate = dateFormat.parse(firstEmiStr);
            calendar.setTime(firstEmiDate);
            calendar.add(Calendar.MONTH, loanTenure);
            Date lastLoanDate = calendar.getTime();

            // Format the last loan date and set it to the EditText
            String lastLoanDateStr = dateFormat.format(lastLoanDate);
            loanLastDateTextView.setText(lastLoanDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(requireActivity(), "Invalid date format for first EMI", Toast.LENGTH_SHORT).show();
        }
    }

}