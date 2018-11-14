package sanchez.isaac.temperatureapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Degree temp;
    EditText celsius, fahrenheit, kelvin;
    EditText lastFocusedField;
    Button buttonUp, buttonDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        temp = new Degree(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius = findViewById(R.id.editCelsius);
        fahrenheit = findViewById(R.id.editFahrenheit);
        kelvin = findViewById(R.id.editKelvin);

        buttonUp = findViewById(R.id.buttonUp);
        buttonDown = findViewById(R.id.buttonDown);

        // Starting at 0 Celsius degrees
        temp.setCelsius(0);
        refreshFields();
        lastFocusedField = celsius;

        setListeners();
    }

    public void setListeners() {

        this.buttonUp.setOnClickListener(createSpinnerListener(1));
        this.buttonDown.setOnClickListener(createSpinnerListener(-1));


        celsius.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    lastFocusedField = celsius;
            }
        });
        fahrenheit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    lastFocusedField = fahrenheit;
            }
        });
        kelvin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                    lastFocusedField = kelvin;
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

    private View.OnClickListener createSpinnerListener(final int increment) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if (lastFocusedField ==  fahrenheit) {
                    temp.setFahrenheit(temp.getFahrenheit()+increment);
                }
                else if (lastFocusedField ==  kelvin) {
                    temp.setKelvin(temp.getKelvin()+increment);
                } else {
                    temp.setCelsius(temp.getCelsius()+increment);
                }

                refreshFields();
            }
        };
    }

    private void refreshFields() {
        celsius.setText(temp.getCelsiusToString());
        fahrenheit.setText(temp.getFahrenheitToString());
        kelvin.setText(temp.getKelvinToString());
    }

}
