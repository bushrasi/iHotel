/**
        * Developer: Bushra Al Sibai
        * App: i Hotel
        * application tested on 7.0,1200 x 1920, xhdpi(Nexus7)
        */
package com.hotelintheground.ihotel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_constraint);


        Button button = findViewById(R.id.reserve_button);
        button.setOnClickListener(this);
        button = findViewById(R.id.reset_button);
        button.setOnClickListener(this);
        button = findViewById(R.id.about_button);
        button.setOnClickListener(this);

        /*RadioGroup bedSize = (RadioGroup) findViewById(R.id.bedSize_radioGroup);
        bedSize.setOnCheckedChangeListener(this);
        int selectedId = bedSize.getCheckedRadioButtonId();
        RadioGroup wifi = (RadioGroup) findViewById(R.id.wifi_radioGroup);
        wifi.setOnCheckedChangeListener(this);*/
    }

    /*@Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int bedSizeCost = 0;
        if (i == R.id.single_radioButton) {
            bedSizeCost = 20;
        } else if (i == R.id.twoS_radioButton) {
            bedSizeCost = 30;
        } else if (i == R.id.double_radioButton) {
            bedSizeCost = 40;
        } else if (i == R.id.twoD_radioButton) {
            bedSizeCost = 50;
        } else if (i == R.id.king_radioButton) {
            bedSizeCost = 60;
        }

    }*/

    @Override
    public void onClick(View view) {
        TextView subtotal_tv = (TextView) findViewById(R.id.subtotal);
        TextView tax_tv = (TextView) findViewById(R.id.tax);
        TextView total_tv = (TextView) findViewById(R.id.total);
        double subTotal, tax, total, cost = 0;
        int dayCostPerAdult = 20;
        int numOfAdults = 0;
        int numOfNights = 0;
        //getting the adults number:
        EditText adultsNum = (EditText) findViewById(R.id.a_editText);
        String adultsNumString = adultsNum.getText().toString();

        EditText nightsNum = (EditText) findViewById(R.id.n_editText);
        String nightsNumString = nightsNum.getText().toString();

        if (view.getId() == R.id.about_button) {
            Toast.makeText(getApplicationContext(), "iHotel, Programmed By: Bushra Al Sibai", Toast.LENGTH_LONG).show();
        }

        else if (adultsNumString.length() > 0 && nightsNumString.length() > 0) {
            numOfAdults = Integer.valueOf(adultsNumString);
            numOfNights = Integer.valueOf(nightsNumString);
            cost = (dayCostPerAdult * numOfAdults) * numOfNights;
            // get selected radio button from bedSize radioGroup
            RadioGroup bedSize = (RadioGroup) findViewById(R.id.bedSize_radioGroup);
            int i = bedSize.getCheckedRadioButtonId();
            int bedSizeCost = 0;
            if (i == R.id.single_radioButton) {
                bedSizeCost = 20;
            } else if (i == R.id.twoS_radioButton) {
                bedSizeCost = 30;
            } else if (i == R.id.double_radioButton) {
                bedSizeCost = 40;
            } else if (i == R.id.twoD_radioButton) {
                bedSizeCost = 50;
            } else if (i == R.id.king_radioButton) {
                bedSizeCost = 60;
            }
            subTotal = cost +(bedSizeCost*numOfNights) ;
            // get the checked boxes:
            CheckBox radio = (CheckBox) findViewById(R.id.radio_checkBox);
            CheckBox tv = (CheckBox) findViewById(R.id.tv_checkBox);
            CheckBox computer = (CheckBox) findViewById(R.id.comp_checkBox);
            CheckBox inRoomBreakfast = (CheckBox) findViewById(R.id.in_room_breakfast_checkBox);

            int additionalCost = 0;
            if (radio.isChecked()) {
                additionalCost += 10;
            }
            if (tv.isChecked()) {
                additionalCost += 20;
            }
            if (computer.isChecked()) {
                additionalCost += 25;
            }
            if (inRoomBreakfast.isChecked()) {
                additionalCost += (10*numOfNights);
            }

            // get selected radio button from wifi radioGroup
            RadioGroup wifi = (RadioGroup) findViewById(R.id.wifi_radioGroup);
            int selected = wifi.getCheckedRadioButtonId();
            if (selected == R.id.basic_radioButton) {
                additionalCost += 0;
            } else if (selected == R.id.medium_radioButton) {
                additionalCost += (10*numOfNights);
            } else if (selected == R.id.high_radioButton) {
                additionalCost += (15*numOfNights);
            }
            subTotal += additionalCost;
            tax = subTotal * 0.1;
            total = subTotal + tax;

            if (view.getId() == R.id.reserve_button) {
                subtotal_tv.setText(String.format("SUBTOTAL:  $%.2f", subTotal));
                tax_tv.setText(String.format("TAX:  $%.2f", tax));
                total_tv.setText(String.format("TOTAL:  $%.2f", total));
                Toast.makeText(getApplicationContext(), "Thank you for placing the reservation, You are all set!"+"\n"
                        + "subtotal: "+subTotal+"\n"+"Tax: "+tax+"\n"+"Total: "+total, Toast.LENGTH_LONG).show();

            } else if (view.getId() == R.id.reset_button) {
                subtotal_tv.setText("");
                tax_tv.setText("");
                total_tv.setText("");
                adultsNum.setText("");
                nightsNum.setText("");
                RadioButton single = findViewById(R.id.single_radioButton);
                single.setChecked(true);
                RadioButton basic = findViewById(R.id.basic_radioButton);
                basic.setChecked(true);
                int[]ids={
                        R.id.radio_checkBox,R.id.tv_checkBox,R.id.comp_checkBox,R.id.in_room_breakfast_checkBox,R.id.newspaper_checkBox
                };


                for(int k =0; k <ids.length;k++){
                    int id = ids[k];
                    CheckBox checkBox = findViewById(id);
                    checkBox.setChecked(false);
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Should select a value for the number of Adults and number of Nights", Toast.LENGTH_LONG).show();
        }


    }
}
