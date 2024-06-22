package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Tax_Fragment extends Fragment {


    EditText selectgstEdittext;

    LinearLayout cardviewbutton, paymentamountcard;
    private EditText amountEditText,resultNetAmount, resultGSTAmount, resultTotalPaymentAmount;
    private EditText resultNetAmountEditText;
    private EditText resultGSTAmountEditText;
    private EditText resultTotalPaymentAmountEditText;
    private EditText rateOfTaxEditText;
    private EditText gstTypeEditText;
    private EditText netAmountEditText;
    private EditText gstAmountEditText;
    private EditText totalPaymentAmountEditText;

    ImageButton backtax;

    ImageButton resetbuttontax;
    @SuppressLint({"MissingInflatedId", "CutPasteId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tax_, container, false);

backtax=view.findViewById(R.id.backtax);
        resetbuttontax=view.findViewById(R.id.resetbuttontax);
        cardviewbutton = view.findViewById(R.id.cardviewbutton);
        paymentamountcard = view.findViewById(R.id.paymentamountcard);
        amountEditText = view.findViewById(R.id.AmounttaxEditText);
        rateOfTaxEditText = view.findViewById(R.id.rateoftaxEdittext);
        gstTypeEditText = view.findViewById(R.id.selectgstEdittext);
        netAmountEditText = view.findViewById(R.id.resultnetamount);
        gstAmountEditText = view.findViewById(R.id.resultgstamount);
        totalPaymentAmountEditText = view.findViewById(R.id.resulttotalpaymentamount);
        resultNetAmount = view.findViewById(R.id.resultnetamount);
        resultGSTAmount = view.findViewById(R.id.resultgstamount);
        resultTotalPaymentAmount = view.findViewById(R.id.resulttotalpaymentamount);
        resultNetAmountEditText = view.findViewById(R.id.resultnetamount);
        resultGSTAmountEditText = view.findViewById(R.id.resultgstamount);
        resultTotalPaymentAmountEditText = view.findViewById(R.id.resulttotalpaymentamount);

        cardviewbutton.setVisibility(View.INVISIBLE);
        paymentamountcard.setVisibility(View.INVISIBLE);
        // Calculate button click listener
        ImageButton calculateButton = view.findViewById(R.id.calculatebutontax);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTax();

                cardviewbutton.setVisibility(View.VISIBLE);
                paymentamountcard.setVisibility(View.VISIBLE);
            }
        });

        resetbuttontax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardviewbutton.setVisibility(View.INVISIBLE);
                paymentamountcard.setVisibility(View.INVISIBLE);
            }
        });
        backtax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction
                // Optional: Add transaction to back stack
                        .replace(R.id.tax_frame, new Loan_Calculator_Fragment());
                fragmentTransaction.commit();
                requireActivity().finish();
            }
        });

        // Share button click listener
        Button shareButton = view.findViewById(R.id.sharebuttontax);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTaxDetails();
            }
        });

        selectgstEdittext = view.findViewById(R.id.selectgstEdittext);
        PopupMenu popupMenu = new PopupMenu(requireContext(), selectgstEdittext); // Use selectgstEdittext instead of select
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        selectgstEdittext.setOnClickListener(v -> popupMenu.show());
        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_item_1) {
                selectgstEdittext.setText("GST is Added");
                return true;
            } else if (itemId == R.id.menu_item_2) {
                selectgstEdittext.setText("GST is Removed");
                calculateGSTAdded();
                return true;
            }
            // Handle more menu items if needed
            return  false;
        });


        return view;
    }

    private void calculateTax() {
        // Get input values
        double amount = Double.parseDouble(amountEditText.getText().toString());
        double rateOfTax = Double.parseDouble(rateOfTaxEditText.getText().toString());
        String gstType = gstTypeEditText.getText().toString();

        // Calculate tax
        double netAmount = amount * (1 + (rateOfTax / 100));
        double gstAmount = amount * (rateOfTax / 100);
        double totalPaymentAmount = amount + gstAmount;

        // Set calculated values to EditText fields
        netAmountEditText.setText(String.valueOf(netAmount));
        gstAmountEditText.setText(String.valueOf(gstAmount));
        totalPaymentAmountEditText.setText(String.valueOf(totalPaymentAmount));
    }

    private void shareTaxDetails() {
        // Get values from EditText fields
        String netAmount = netAmountEditText.getText().toString();
        String gstAmount = gstAmountEditText.getText().toString();
        String totalPaymentAmount = totalPaymentAmountEditText.getText().toString();

        // Create message to share
        String message = "NET Amount: " + netAmount + "\n" +
                "GST Amount: " + gstAmount + "\n" +
                "Total Payment Amount: " + totalPaymentAmount;

        // Create intent to share message
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(shareIntent, "Share Tax Calculation Details"));
    }
    private void calculateGSTAdded() {
        // Get text from EditText fields
        String amountText = amountEditText.getText().toString();
        String rateOfTaxText = rateOfTaxEditText.getText().toString();

        // Check if EditText fields are empty
        if (amountText.isEmpty() || rateOfTaxText.isEmpty()) {
            // Show an error message if any of the EditText fields is empty
            Toast.makeText(requireContext(), "Please enter both amount and rate of tax.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse amount and rate of tax from EditText fields
        double amount = Double.parseDouble(amountText);
        double rateOfTax = Double.parseDouble(rateOfTaxText);

        // Calculate GST amount
        double gstAmount = (amount * rateOfTax) / 100;

        // Calculate total payment (Original amount + GST amount)
        double totalPayment = amount + gstAmount;

        // Display the results in EditText fields
        resultNetAmount.setText(String.valueOf(amount));
        resultGSTAmount.setText(String.valueOf(gstAmount));
        resultTotalPaymentAmount.setText(String.valueOf(totalPayment));
    }

    private void calculateTotalPayment() {
        // Retrieve the amount and GST rate from EditText fields
        double amount = Double.parseDouble(amountEditText.getText().toString());
        double rateOfTax = Double.parseDouble(rateOfTaxEditText.getText().toString());

        // Calculate the GST amount
        double gstAmount = (amount * rateOfTax) / 100;

        // Calculate the net amount (Amount - GST amount)
        double netAmount = amount - gstAmount;

        // Display the calculated net amount and GST amount
        resultNetAmountEditText.setText(String.valueOf(netAmount));
        resultGSTAmountEditText.setText(String.valueOf(gstAmount));

        // Display the total payment amount (Amount without GST)
        resultTotalPaymentAmountEditText.setText(String.valueOf(amount));
    }

}