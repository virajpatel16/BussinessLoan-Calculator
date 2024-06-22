package com.code.bussinessloancalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Bussiness_Loan_CalculatorFragment extends Fragment {

    LinearLayout Discount;
    LinearLayout Price;
    ImageButton backbussiness;
    LinearLayout operating;
    LinearLayout Break;

    LinearLayout Markup;

    LinearLayout Margin;

    LinearLayout Marginwithsale;

    LinearLayout Cumulative;

    LinearLayout Inflation;

    LinearLayout Sales;

    LinearLayout Vat;

    LinearLayout Gross;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bussiness__loan__calculator, container, false);
        Discount = view.findViewById(R.id.cardview);
        Price = view.findViewById(R.id.cardview2);
        operating=view.findViewById(R.id.cardview3);
        Break=view.findViewById(R.id.cardview4);
        Markup=view.findViewById(R.id.cardview5);
        Margin=view.findViewById(R.id.cardview6);
        Marginwithsale=view.findViewById(R.id.cardview7);
        Cumulative = view.findViewById(R.id.cardview8);
        Inflation=view.findViewById(R.id.cardview9);
        Sales=view.findViewById(R.id.cardview10);
        Vat=view.findViewById(R.id.cardview11);
        backbussiness=view.findViewById(R.id.backbussiness);
        Gross=view.findViewById(R.id.cardview12);
        Discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Discount_Calculator.class);
                startActivity(intent);
               
            }
        });
        backbussiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), MainScreen.class);
                startActivity(intent);

            }
        });
        Gross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Gross.class);
                startActivity(intent);

                
            }
        });
        Vat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Vat.class);
                startActivity(intent);

                
            }
        });
        Sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Sales.class);
                startActivity(intent);

                
            }
        });
        Inflation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Inflation.class);
                startActivity(intent);

                
            }
        });
        Cumulative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Cumulative.class);
                startActivity(intent);

                
            }
        });
        Marginwithsale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Margin_with_sales.class);
                startActivity(intent);

                
            }
        });
        Margin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Margin.class);
                startActivity(intent);

                
            }
        });
    Markup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Markup.class);
                startActivity(intent);

                
            }
        });

        Break.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Break_Event.class);
                startActivity(intent);

                
            }
        });

        operating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Operating.class);
                startActivity(intent);

                
            }
        });

        Price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Price.class);
                startActivity(intent);

                

            }
        });

        return view;
    }
}