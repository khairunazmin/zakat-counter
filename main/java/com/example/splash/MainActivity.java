package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText ETgram, ETvalue;
    Button buttonCalc, buttonClr;
    TextView totValGold;
    TextView totPayable;
    TextView finalZakat;
    RadioGroup radioGroup;
    RadioButton radioWear, radioKeep, genderradioButton;
    float afterUruf, goldValue, totWeightValue, totGoldPay, totZakat;
    float uruf;
    String ETgramEmpty, ETvalueEmpty;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETgram = (EditText) findViewById(R.id.ETgram);
        ETvalue = (EditText) findViewById(R.id.ETvalue);
        buttonCalc = (Button) findViewById(R.id.buttonCalc);
        buttonClr = (Button) findViewById(R.id.buttonClr);
        totValGold = (TextView) findViewById(R.id.totValGold);
        totPayable = (TextView) findViewById(R.id.totPayable);
        finalZakat = (TextView) findViewById(R.id.finalZakat);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioKeep = (RadioButton) findViewById(R.id.radioKeep);
        radioWear = (RadioButton) findViewById(R.id.radioWear);

        sharedPref = this.getSharedPreferences( "Gram", Context.MODE_PRIVATE);
        sharedPref = this.getSharedPreferences("Current Value", Context.MODE_PRIVATE);

        ETgramEmpty = sharedPref.getString("Gram","0");
        ETvalueEmpty = sharedPref.getString("Current Value", "0");

    }

    public void onclickbuttonMethod(View v){
        int selestedid = radioGroup.getCheckedRadioButtonId();
        genderradioButton = (RadioButton) findViewById(selestedid);

        ETgramEmpty = ETgram.getText().toString();
        ETvalueEmpty = ETvalue.getText().toString();

        if ((ETgramEmpty.matches("")) || (ETvalueEmpty.matches("")) ){
            Toast.makeText(this, "You should fill in all details!", Toast.LENGTH_SHORT).show();
        }
        else if( (Float.parseFloat(ETgram.getText().toString()) == 0) || (Float.parseFloat(ETvalue.getText().toString()) ==0) ){
            Toast.makeText(this, "Your values cannot be 0!", Toast.LENGTH_SHORT).show();
        }
        else if( (Float.parseFloat(ETgram.getText().toString()) < 0) || (Float.parseFloat(ETvalue.getText().toString()) < 0) ){
            Toast.makeText(this, "Your values cannot be a negative number!", Toast.LENGTH_SHORT).show();
        }

        else{

            if(selestedid==-1){
                Toast.makeText(this, "Please choose Keep or Wear first!", Toast.LENGTH_LONG).show();
            }

            else{

                if (radioWear.isChecked()){

                    String berat = ETgram.getText().toString();
                    double uruf = 200;
                    double a = Double.parseDouble(ETgram.getText().toString());
                    double b = Double.parseDouble(ETvalue.getText().toString());
                    double c = a*b;
                    double d = (a-uruf)*b;
                    double e = d*0.025;

                    if ( (a - uruf) <= 0){
                        //totPayable = 0, finalZakat = 0;
                        totValGold.setText(String.format("Total Value of Gold: RM %.2f",c));
                        totPayable.setText(String.format("Total Gold Value (Zakat Payable): RM 0"));
                        finalZakat.setText(String.format("Total Zakat: RM 0"));
                    }
                    else{
                        totValGold.setText(String.format("Total Value of Gold: RM %.2f",c));
                        totPayable.setText(String.format("Total Gold Value (Zakat Payable): RM %.2f",d));
                        finalZakat.setText(String.format("Total Zakat: RM %.2f",e));
                    }

                }
                else if (radioKeep.isChecked()){

                    String berat = ETgram.getText().toString();
                    double uruf = 85;
                    double a = Double.parseDouble(ETgram.getText().toString());
                    double b = Double.parseDouble(ETvalue.getText().toString());
                    double c = a*b;
                    double d = (a-uruf)*b;
                    double e = d*0.025;

                    if ( (a - uruf) <= 0){
                        //totPayable = 0, finalZakat = 0;
                        totValGold.setText(String.format("Total Value of Gold: RM %.2f",c));
                        totPayable.setText(String.format("Total Gold Value (Zakat Payable): RM 0"));
                        finalZakat.setText(String.format("Total Zakat: RM 0"));
                    }
                    else{
                        totValGold.setText(String.format("Total Value of Gold: RM %.2f",c));
                        totPayable.setText(String.format("Total Gold Value (Zakat Payable): RM %.2f",d));
                        finalZakat.setText(String.format("Total Zakat: RM %.2f",e));
                    }
                    //saving the data
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("Gram", ETgramEmpty);
                    editor.putString("Current Value", ETvalueEmpty);
                    editor.apply();


                }

            }
        }


    }


    public void btnOnClick2(View view) {
        EditText ETgramClr = findViewById(R.id.ETgram);
        EditText ETvalueClr = findViewById(R.id.ETvalue);
        ETgramClr.getText().clear();
        ETvalueClr.getText().clear();
        radioKeep.setChecked(false);
        radioWear.setChecked(false);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.about:
                //Toast.makeText(this, "This is about us ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,AboutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}










