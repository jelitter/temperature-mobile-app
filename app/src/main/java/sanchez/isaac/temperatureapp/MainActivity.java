package sanchez.isaac.temperatureapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private Degree temp;
    EditText celsius, fahrenheit, kelvin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        temp = new Degree(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius = findViewById(R.id.editCelsius);
        fahrenheit = findViewById(R.id.editFahrenheit);
        kelvin = findViewById(R.id.editKelvin);

        setListeners();
    }

    public void setListeners() {

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

                temp.setCelsius(Double.parseDouble(s.toString()));
                fahrenheit.setText(temp.getFahrenheitToString());
                kelvin.setText(temp.getKelvinToString());
            }
        });

//        this.fahrenheit.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//            //
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//
//                temp.setFahrenheit(Double.parseDouble(s.toString()));
//                celsius.setText(temp.getCelsiusToString());
//                kelvin.setText(temp.getKelvinToString());
//            }
//        });
//
//        this.kelvin.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//            //
//            @Override
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//
//                temp.setKelvin(Double.parseDouble(s.toString()));
//                celsius.setText(temp.getCelsiusToString());
//                fahrenheit.setText(temp.getFahrenheitToString());
//            }
//        });


    }



}
