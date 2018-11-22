package sanchez.isaac.temperatureapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ShareActivity extends AppCompatActivity {

    Double celsius, fahrenheit, kelvin;
    TextView resultsCelsius, resultsFahrenheit, resultsKelvin, inputEmail;
    Button buttonBack, buttonSendEmail;
    Degree temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        // Getting temperature details saved from Main activityØ
        Bundle extras = getIntent().getExtras();
        celsius = extras.getDouble("celsius", 0);

        temp = new Degree(celsius);


        resultsCelsius = findViewById(R.id.resultsCelsius);
        resultsFahrenheit = findViewById(R.id.resultsFahrenheit);
        resultsKelvin = findViewById(R.id.resultsKelvin);
        inputEmail = findViewById(R.id.inputEmail);

        resultsCelsius.setText(temp.getCelsiusToString() + "° C");
        resultsFahrenheit.setText(temp.getFahrenheitToString() + "° F");
        resultsKelvin.setText(temp.getKelvinToString() + "° K");

        buttonBack = findViewById(R.id.buttonBack);
        buttonSendEmail = findViewById(R.id.buttonSendEmail);
        buttonSendEmail.setEnabled(isValidEmail(""));

        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("celsius", temp.getCelsius());
                startActivity(mainIntent);
            }
        });

        this.inputEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            //
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonSendEmail.setEnabled(isValidEmail(s.toString()));
            }
        });

        this.buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"))
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_EMAIL, new String[]{inputEmail.getText().toString()})
                        .putExtra(Intent.EXTRA_SUBJECT, "🌡 Your temperature results!")
                        .putExtra(Intent.EXTRA_TEXT, buildBody());

                try {
                    // Send the email using default system app for email
                    startActivity(emailIntent);
                    finish();

                } catch (android.content.ActivityNotFoundException ex) {
                    // If there's no default app for email, let the user pick one
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));

                }
            }
        });


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble("celsius", celsius);
        super.onSaveInstanceState(outState);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    String buildBody() {
        return "Your temperature results!\n" +
                "\n" + temp.getCelsiusToString() + "° C" +
                "\n" + temp.getFahrenheitToString() + "° F" +
                "\n" + temp.getKelvinToString() + "° K" +
                "\n\n" + "Isaac's Temperature Converter";
    }
}
