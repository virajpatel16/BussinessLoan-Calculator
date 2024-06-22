package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Margin_with_sale_Fragment extends Fragment {

    ImageButton backmarginwithsale;
    private EditText costMarginWithSalesEditText, revenueMarginWithSalesEditText, salesTaxMarginWithSalesEditText;
    private TextView resultMarginWithSalesTextView, resultMarkupMarginWithSalesTextView,
            resultNetPriceMarginWithSalesTextView, resultProfitMarginWithSalesTextView;


    LinearLayout resultprofitmarginwithsalescard, resultnetpricemarginwithsalescard, resultmarkupmarginwithsalescard, resultmarginwithsalescard;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_margin_with_sale_, container, false);
        costMarginWithSalesEditText = view.findViewById(R.id.CostmarginwithsalesEditText);
        revenueMarginWithSalesEditText = view.findViewById(R.id.RevenuemarginwithsalesEdittext);
        salesTaxMarginWithSalesEditText = view.findViewById(R.id.SalestaxmarginwithsaleEdittext);
        resultMarginWithSalesTextView = view.findViewById(R.id.answermarginwithsales);
        resultMarkupMarginWithSalesTextView = view.findViewById(R.id.answermarkupmarginwithsales);
        resultNetPriceMarginWithSalesTextView = view.findViewById(R.id.answernetpricemarginwithsales);
        resultProfitMarginWithSalesTextView = view.findViewById(R.id.answerprofitmarginwithsales);
        resultprofitmarginwithsalescard = view.findViewById(R.id.resultprofitmarginwithsalescard);
        resultnetpricemarginwithsalescard = view.findViewById(R.id.resultnetpricemarginwithsalescard);
        resultmarkupmarginwithsalescard = view.findViewById(R.id.resultmarkupmarginwithsalescard);
        resultmarginwithsalescard = view.findViewById(R.id.resultmarginwithsalescard);

        backmarginwithsale = view.findViewById(R.id.backmarginwithssales);

        backmarginwithsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().finish();

            }
        });
        resultprofitmarginwithsalescard.setVisibility(View.INVISIBLE); resultnetpricemarginwithsalescard.setVisibility(View.INVISIBLE);resultmarkupmarginwithsalescard.setVisibility(View.INVISIBLE); resultmarginwithsalescard.setVisibility(View.INVISIBLE);

        ImageButton calculateMarginWithSalesButton = view.findViewById(R.id.calculatemarginwithsalesbutton);
        calculateMarginWithSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMarginWithSales();
                resultprofitmarginwithsalescard.setVisibility(View.VISIBLE); resultnetpricemarginwithsalescard.setVisibility(View.VISIBLE);resultmarkupmarginwithsalescard.setVisibility(View.VISIBLE); resultmarginwithsalescard.setVisibility(View.VISIBLE);

            }
        });

        ImageButton resetMarginWithSalesButton = view.findViewById(R.id.resetbuttonmarginwithsales);
        resetMarginWithSalesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMarginWithSalesFields();
                resultprofitmarginwithsalescard.setVisibility(View.INVISIBLE); resultnetpricemarginwithsalescard.setVisibility(View.INVISIBLE);resultmarkupmarginwithsalescard.setVisibility(View.INVISIBLE); resultmarginwithsalescard.setVisibility(View.INVISIBLE);

            }
        });
        return view;
    }
    @SuppressLint("DefaultLocale")
    private void calculateMarginWithSales() {
        String costMarginStr = costMarginWithSalesEditText.getText().toString().trim();
        String revenueMarginStr = revenueMarginWithSalesEditText.getText().toString().trim();
        String salesTaxMarginStr = salesTaxMarginWithSalesEditText.getText().toString().trim();

        if (TextUtils.isEmpty(costMarginStr)) {
            costMarginWithSalesEditText.setError("Enter Cost");
            costMarginWithSalesEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(revenueMarginStr)) {
            revenueMarginWithSalesEditText.setError("Enter Revenue");
            revenueMarginWithSalesEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(salesTaxMarginStr)) {
            salesTaxMarginWithSalesEditText.setError("Enter Sales Tax");
            salesTaxMarginWithSalesEditText.requestFocus();
            return;
        }



        double costMargin = Double.parseDouble(costMarginStr);
        double revenueMargin = Double.parseDouble(revenueMarginStr);
        double salesTaxMargin = Double.parseDouble(salesTaxMarginStr);

        // Calculate margin percentage
        double margin = (((revenueMargin - costMargin) / revenueMargin) * 100) - salesTaxMargin;

        // Calculate markup percentage
        double markup = ((revenueMargin - costMargin) / costMargin) * 100;

        // Calculate net price
        double netPrice = revenueMargin - salesTaxMargin;

        // Calculate profit
        double profit = netPrice - costMargin;

        // Display the result
        resultMarginWithSalesTextView.setText(String.format("%.2f", margin));
        resultMarkupMarginWithSalesTextView.setText(String.format("%.2f", markup));
        resultNetPriceMarginWithSalesTextView.setText(String.format("%.2f", netPrice));
        resultProfitMarginWithSalesTextView.setText(String.format("%.2f", profit));
    }

    private void resetMarginWithSalesFields() {
        costMarginWithSalesEditText.setText("");
        revenueMarginWithSalesEditText.setText("");
        salesTaxMarginWithSalesEditText.setText("");
        resultMarginWithSalesTextView.setText("");
        resultMarkupMarginWithSalesTextView.setText("");
        resultNetPriceMarginWithSalesTextView.setText("");
        resultProfitMarginWithSalesTextView.setText("");
    }
}