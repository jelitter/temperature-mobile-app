package sanchez.isaac.temperatureapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Degree temp;
    EditText celsius, fahrenheit, kelvin;
    Button buttonUp, buttonDown, buttonShare;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius = findViewById(R.id.editCelsius);
        fahrenheit = findViewById(R.id.editFahrenheit);
        kelvin = findViewById(R.id.editKelvin);

        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);
        buttonShare = findViewById(R.id.buttonShare);

        buttonUp.setBackgroundColor(0x88FF0000);  // argb
        buttonDown.setBackgroundColor(0x880044AA);  // argb


        double initialCelsius = 0;

        if (savedInstanceState != null) {
            initialCelsius = savedInstanceState.getDouble("celsius", 0);
            Toast.makeText(getApplicationContext(),"LOADED DATA\n"+initialCelsius+" C",
                    Toast.LENGTH_LONG).show();
        } else {
            celsius.requestFocus();
        }

        temp = new Degree(initialCelsius);
        fahrenheit.setText(temp.getFahrenheitToString());
        kelvin.setText(temp.getKelvinToString());

        refreshFields();
        setListeners();
    }

    public void setListeners() {

        this.buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(getApplicationContext(), ShareActivity.class);
                shareIntent.putExtra("celsius", temp.getCelsiusToString());
                shareIntent.putExtra("fahrenheit", temp.getFahrenheitToString());
                shareIntent.putExtra("kelvin", temp.getKelvinToString());
                startActivity(shareIntent);
            }
        });

        this.buttonUp.setOnClickListener(createUpDownListeners(1));
        this.buttonDown.setOnClickListener(createUpDownListeners(-1));


        celsius.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    buttonUp.setText(R.string.up_celsius);
                    buttonDown.setText(R.string.down_celsius);
            }
        });
        fahrenheit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    buttonUp.setText(R.string.up_fahrenheit);
                    buttonDown.setText(R.string.down_fahrenheit);
            }
        });
        kelvin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    buttonUp.setText(R.string.up_kelvin);
                    buttonDown.setText(R.string.down_kelvin);
            }
        });

        this.celsius.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
//
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (getCurrentFocus() == celsius) {
                    if (s.toString().trim().equals("")) {
                        fahrenheit.setText("");
                        kelvin.setText("");
                    } else {
                        temp.setCelsius(Double.parseDouble(s.toString()));
                        fahrenheit.setText(temp.getFahrenheitToString());
                        kelvin.setText(temp.getKelvinToString());
                    };
                }
            }
        });

        this.fahrenheit.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
            //
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (getCurrentFocus() == fahrenheit) {
                    if (s.toString().trim().equals("")) {
                        celsius.setText("");
                        kelvin.setText("");
                    } else {
                        temp.setFahrenheit(Double.parseDouble(s.toString()));
                        celsius.setText(temp.getCelsiusToString());
                        kelvin.setText(temp.getKelvinToString());
                    };
                }
            }
        });

        this.kelvin.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}
            //
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (getCurrentFocus() == kelvin) {
                    if (s.toString().trim().equals("")) {
                        fahrenheit.setText("");
                        celsius.setText("");
                    } else {
                        temp.setKelvin(Double.parseDouble(s.toString()));
                        fahrenheit.setText(temp.getFahrenheitToString());
                        celsius.setText(temp.getCelsiusToString());
                    };
                }
            }
        });
    }



    private View.OnClickListener createUpDownListeners(final int increment) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getCurrentFocus() ==  fahrenheit) {
                    temp.setFahrenheit(temp.getFahrenheit()+increment);
                }
                else if (getCurrentFocus() ==  kelvin) {
                    temp.setKelvin(temp.getKelvin()+increment);
                } else {
                    temp.setCelsius(temp.getCelsius()+increment);
                }

                refreshFields();
            }
        };
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble("celsius", temp.getCelsius());

        Toast.makeText(getApplicationContext(),"Saved data" +
                        "\nCelsius: "+outState.getDouble("celsius"),
                Toast.LENGTH_LONG).show();

        super.onSaveInstanceState(outState);
    }

    private void refreshFields() {
        celsius.setText(temp.getCelsiusToString());
        fahrenheit.setText(temp.getFahrenheitToString());
        kelvin.setText(temp.getKelvinToString());
    }

}
