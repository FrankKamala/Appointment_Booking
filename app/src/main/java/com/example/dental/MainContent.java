package com.example.dental;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import android.view.View;


public class MainContent extends AppCompatActivity implements View.OnClickListener {
    private CardView callCard, medCard, payCard, dateCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        callCard = findViewById(R.id.contact_card);
        medCard = findViewById(R.id.prescription_card);
        payCard = findViewById(R.id.payment_card);
        dateCard = findViewById(R.id.appointment_card);


////set
        callCard.setOnClickListener(this);
        medCard.setOnClickListener(this);
        payCard.setOnClickListener(this);
        dateCard.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent k;
        switch (v.getId()) {
            case R.id.contact_card:
               k = new Intent(getApplicationContext(),Contact.class);
               startActivity(k);
                break;
            case R.id.prescription_card:
                k = new Intent(getApplicationContext(), Meds.class);
                startActivity(k);
                break;
            case R.id.payment_card:
                k = new Intent(getApplicationContext(), Pay.class);
                startActivity(k);
                break;
            case R.id.appointment_card:
                k = new Intent(getApplicationContext(), Book.class);
                startActivity(k);
                break;
            default:
                break;
        }
    }
}
