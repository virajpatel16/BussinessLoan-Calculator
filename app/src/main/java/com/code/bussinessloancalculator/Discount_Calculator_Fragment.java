package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;


public class Discount_Calculator_Fragment extends Fragment {

TextView editTextOriginalPrice, editTextDiscountPercentage;
    TextInputEditText  edt_monthly_emi, edt_t_interest;
    ImageButton btn_emi_calculate, btn_emi_reset;
    LinearLayout resultdiscount;
    ImageButton backdiscountimgbtn;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_discount__calculator_, container, false);
        resultdiscount=rootView.findViewById(R.id.resultdiscount);
        editTextOriginalPrice = rootView.findViewById(R.id.intialacccountEditText);
        editTextDiscountPercentage = rootView.findViewById(R.id.discountcalculateedittext);
        edt_monthly_emi = rootView.findViewById(R.id.edt_monthly_emi);
        edt_t_interest = rootView.findViewById(R.id.edt_t_interest);
        btn_emi_calculate = rootView.findViewById(R.id.btn_emi_calculate);
        btn_emi_reset = rootView.findViewById(R.id.btn_emi_reset);
        backdiscountimgbtn=rootView.findViewById(R.id.backdiscountimgbtn);
        resultdiscount.setVisibility(View.INVISIBLE);

        backdiscountimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              requireActivity().finish();

            }
        });
        btn_emi_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDiscount();
                resultdiscount.setVisibility(View.VISIBLE);
            }
        });

        btn_emi_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset values
                editTextOriginalPrice.setText("0");
                editTextDiscountPercentage.setText("0");
                edt_monthly_emi.setText("");
                edt_t_interest.setText("");
                resultdiscount.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
    private void calculateDiscount() {
        try {
            double originalPrice = Double.parseDouble(editTextOriginalPrice.getText().toString());
            double discountPercentage = Double.parseDouble(editTextDiscountPercentage.getText().toString());

            // Calculate discount
            double discountValue = originalPrice * (discountPercentage / 100);
            double discountedPrice = originalPrice - discountValue;

            // Set calculated values to views
            edt_monthly_emi.setText(String.valueOf(discountedPrice));
            edt_t_interest.setText(String.valueOf(discountValue));
        } catch (NumberFormatException e) {
            // Handle invalid input
            edt_monthly_emi.setText("Invalid input");
            edt_t_interest.setText("Invalid input");
        }
    }

}
