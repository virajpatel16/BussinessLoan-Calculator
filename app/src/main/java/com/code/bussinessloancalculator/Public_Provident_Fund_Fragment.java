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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Public_Provident_Fund_Fragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private EditText investmentAmountEditText;
    private EditText rateOfInterestEditText;
    private EditText tenureEditText;
    private EditText firstEmiEditText;

    private TextView answerInvestmentAmountTextView;
    private TextView answerTotalInterestValueTextView;
    private TextView answerMaturityDateTextView;
    private TextView answerMaturityValueTextView;

    LinearLayout resultinvestmentamountpublic, resultmaturitypublic;
    EditText FirstEmiPublicEditext;

    ImageButton backpublick;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_public__provident__fund_, container, false);
        resultinvestmentamountpublic = view.findViewById(R.id.resultinvestmentamountpublic);
        resultmaturitypublic = view.findViewById(R.id.resultmaturitypublic);
        FirstEmiPublicEditext = view.findViewById(R.id.FirstEmiPublicEditext);

        // Initialize UI components
        investmentAmountEditText = view.findViewById(R.id.InvestmentAmountedPublicittext);
        rateOfInterestEditText = view.findViewById(R.id.ExpectrateofinterestPublicEditText);
        tenureEditText = view.findViewById(R.id.TenurePublicEdittext);
      // Initialize firstEmiEditText
        backpublick = view.findViewById(R.id.backpublic);
        answerInvestmentAmountTextView = view.findViewById(R.id.answerinvestmentamountpublic);
        answerTotalInterestValueTextView = view.findViewById(R.id.answertotalinterestvaluepublic);
        answerMaturityDateTextView = view.findViewById(R.id.answermatururitydatepublic);
        answerMaturityValueTextView = view.findViewById(R.id.answermatururityvaluepublic);

        ImageButton calculateButton = view.findViewById(R.id.calculatebuttonpublic);
        ImageButton resetButton = view.findViewById(R.id.resetbuttonpublic);
        Button saveButton = view.findViewById(R.id.savepublic);
        Button shareButton = view.findViewById(R.id.Sharebtnpublicprovident);

        resultinvestmentamountpublic.setVisibility(View.INVISIBLE);
        resultmaturitypublic.setVisibility(View.INVISIBLE);
        resultinvestmentamountpublic.setVisibility(View.INVISIBLE);
        backpublick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                        // Optional: Add transaction to back stack
                        .replace(R.id.public_frame, new Banking__Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();

            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePublicProvidentFund();

                resultinvestmentamountpublic.setVisibility(View.VISIBLE);
                resultmaturitypublic.setVisibility(View.VISIBLE);
                resultinvestmentamountpublic.setVisibility(View.VISIBLE);
            }
        });


        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                resultinvestmentamountpublic.setVisibility(View.INVISIBLE);
                resultmaturitypublic.setVisibility(View.INVISIBLE);
                resultinvestmentamountpublic.setVisibility(View.INVISIBLE);
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
        resultinvestmentamountpublic.setVisibility(View.INVISIBLE);
        resultmaturitypublic.setVisibility(View.INVISIBLE);
        resultinvestmentamountpublic.setVisibility(View.VISIBLE);
        resultmaturitypublic.setVisibility(View.VISIBLE);

        FirstEmiPublicEditext.setOnClickListener(new View.OnClickListener() {
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
                FirstEmiPublicEditext.setText(selectedDate);

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



    @SuppressLint("DefaultLocale")
    private void calculatePublicProvidentFund() {
        try {
            // Retrieve input values
            String investmentAmountStr = investmentAmountEditText.getText().toString().trim();
            String rateOfInterestStr = rateOfInterestEditText.getText().toString().trim();
            String tenureStr = tenureEditText.getText().toString().trim();

            // Convert input strings to appropriate data types
            double investmentAmount = Double.parseDouble(investmentAmountStr);
            double rateOfInterest = Double.parseDouble(rateOfInterestStr) / 100; // Convert percentage to decimal
            int tenure = Integer.parseInt(tenureStr);
            // Assuming firstEmi is a date or some specific value you want to use in calculation
            // You can convert it to appropriate data type accordingly

            // Perform recurring calculation here
            double maturityValue = investmentAmount * Math.pow(1 + rateOfInterest, tenure);

            // Calculate total interest earned
            double totalInterest = maturityValue - investmentAmount;

            // Calculate maturity date based on current date and tenure
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, tenure);
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String maturityDate = dateFormat.format(calendar.getTime());

            // Display the calculated values
            answerInvestmentAmountTextView.setText(String.format("%.2f", investmentAmount));
            answerTotalInterestValueTextView.setText(String.format("%.2f", totalInterest));
            answerMaturityDateTextView.setText(maturityDate);
            answerMaturityValueTextView.setText(String.format("%.2f", maturityValue));
        } catch (NumberFormatException e) {
            // Handle conversion errors
            e.printStackTrace();
            Toast.makeText(requireContext(), "Invalid input. Please enter valid numbers.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
            Toast.makeText(requireContext(), "An error occurred while calculating. Please try again.", Toast.LENGTH_SHORT).show();
        }
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
}