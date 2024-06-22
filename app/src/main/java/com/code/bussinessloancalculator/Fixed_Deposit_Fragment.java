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
import android.view.inputmethod.EditorInfo;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class Fixed_Deposit_Fragment extends Fragment  {

    EditText FirstEmieditext;

    LinearLayout resultinvestmentamountfixed, resultmaturityfixed;
    ImageButton backfixeddeposite;


    private EditText investmentAmountEditText;
    private EditText expectRateOfInterestEditText;
    private EditText tenureEditText;
    private EditText firstEmiEditText;

    private ImageButton resetButton;
    private ImageButton calculateButton;


    private TextView answerInvestmentAmountTextView;
    private TextView answerTotalInterestValueTextView;
    private TextView answerMaturityDateTextView;
    private TextView answerMaturityValueTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fixed__deposit_, container, false);

        FirstEmieditext = view.findViewById(R.id.FirstEmieditext);
        backfixeddeposite = view.findViewById(R.id.backfixeddeposite);
        resultinvestmentamountfixed = view.findViewById(R.id.resultinvestmentamountfixed);
        resultmaturityfixed = view.findViewById(R.id.resultmaturityfixed);

        resultinvestmentamountfixed.setVisibility(View.INVISIBLE);
        resultmaturityfixed.setVisibility(View.INVISIBLE);


        investmentAmountEditText = view.findViewById(R.id.InvestmentAmountittext);
        expectRateOfInterestEditText = view.findViewById(R.id.ExpectrateofinterestEditText);
        tenureEditText = view.findViewById(R.id.TenurefixedEdittext);

        backfixeddeposite = view.findViewById(R.id.backfixeddeposite);
        resetButton = view.findViewById(R.id.resetbuttonfixed);
        calculateButton = view.findViewById(R.id.calculatebuttonfixed);

        answerInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountfixed);
        answerTotalInterestValueTextView = view.findViewById(R.id.answertotalinterestvalue);
        answerMaturityDateTextView = view.findViewById(R.id.answermatururitydatefixed);
        answerMaturityValueTextView = view.findViewById(R.id.answermatururityvaluefixed);
        Button shareButton = view.findViewById(R.id.Sharebtnfixed);
        Button saveButton = view.findViewById(R.id.save);


        resultinvestmentamountfixed.setVisibility(View.INVISIBLE);
        resultmaturityfixed.setVisibility(View.INVISIBLE);
        backfixeddeposite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        // Optional: Add transaction to back stack
                        .replace(R.id.fixed_frame, new Banking__Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupSaveButton();
            }
        });


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupShareButton();
            }
        });
        // Set click listeners for buttons
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (investmentAmountEditText != null &&FirstEmieditext!= null && expectRateOfInterestEditText != null && tenureEditText != null) {
                    investmentAmountEditText.setText("");
                    expectRateOfInterestEditText.setText("");
                    tenureEditText.setText("");
                    FirstEmieditext.setText("");
                }



                resultinvestmentamountfixed.setVisibility(View.INVISIBLE);
                resultmaturityfixed.setVisibility(View.INVISIBLE);
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDeposit();
                resultinvestmentamountfixed.setVisibility(View.VISIBLE);
                resultmaturityfixed.setVisibility(View.VISIBLE);
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
                // Do something with the selected date
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                FirstEmieditext.setText(selectedDate);

            }
        }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }




    @SuppressLint("DefaultLocale")
    private void calculateDeposit() {
        // Check if input fields are empty
        if (investmentAmountEditText.getText().toString().isEmpty() ||

                expectRateOfInterestEditText.getText().toString().isEmpty() ||
                tenureEditText.getText().toString().isEmpty()) {
            Toast.makeText(requireContext(), "Please enter all inputs", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Retrieve values entered by the user
            double principal = Double.parseDouble(investmentAmountEditText.getText().toString());
            double rate = Double.parseDouble(expectRateOfInterestEditText.getText().toString());
            double time = Double.parseDouble(tenureEditText.getText().toString());
            String tenureStr = tenureEditText.getText().toString().trim();

            // Check if time is provided in years or months

            // If time is provided in years, convert it to months


            // Check if rate is zero
            if (principal == 0 || rate == 0 || time == 0) {
                // Display appropriate message and return
                Toast.makeText(requireContext(), "Input values must be greater than 0", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calculate total interest using compound interest formula
            double annualRate = rate / 100; // Convert percentage to decimal
            double totalInterest = principal * (Math.pow(1 + (annualRate / 12), time) - 1);
            double maturityAmount = principal + totalInterest;
            int tenure = Integer.parseInt(tenureStr);

            // Format the results to display with two decimal places
            DecimalFormat decimalFormat = new DecimalFormat("#.##");

            // Display the formatted results
            answerInvestmentAmountTextView.setText(String.format("₹%.2f", principal));
            answerTotalInterestValueTextView.setText(String.format("₹%.2f", totalInterest));
            answerMaturityValueTextView.setText(String.format("₹%.2f", maturityAmount));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, tenure);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
            String maturityDate = dateFormat.format(calendar.getTime());
            answerMaturityDateTextView.setText(maturityDate);

        } catch (NumberFormatException e) {
            // Handle NumberFormatException (e.g., if input is not a valid number)
            Toast.makeText(requireContext(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Handle other exceptions
            Toast.makeText(requireContext(), "An error occurred", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void setupShareButton() {


        // Retrieve the text from the result views
        String investmentAmount = answerInvestmentAmountTextView.getText().toString();
        String totalInterestValue = answerTotalInterestValueTextView.getText().toString();
        String maturityDate = answerMaturityDateTextView.getText().toString();
        String maturityValue = answerMaturityValueTextView.getText().toString();

        // Create the share message
        String shareMessage = "Fixed Deposit Details:\n\n" +
                "Investment Amount: " + investmentAmount + "\n" +
                "Total Interest Value: " + totalInterestValue + "\n" +
                "Maturity Date: " + maturityDate + "\n" +
                "Maturity Value: " + maturityValue;

        // Create an intent to share the message
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        // Start the chooser activity to select an app to share the message
        startActivity(Intent.createChooser(shareIntent, "Share via"));

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

}