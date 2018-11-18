package sanchez.isaac.temperatureapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ShareActivity extends AppCompatActivity {

    String celsius = "", fahrenheit = "",kelvin = "";
    TextView resultsCelsius, resultsFahrenheit, resultsKelvin, inputEmail;
    Button buttonBack, buttonSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        Bundle extras = getIntent().getExtras();
        celsius = extras.getString("celsius");
        fahrenheit = extras.getString("fahrenheit");
        kelvin = extras.getString("kelvin");
        Toast.makeText(getApplicationContext(),"Values are:" +
                "\nCelsius: "+celsius+
                "\nFahrenheit: "+fahrenheit+
                "\nKelvin: "+kelvin,
                Toast.LENGTH_LONG).show();


        resultsCelsius = findViewById(R.id.resultsCelsius);
        resultsFahrenheit = findViewById(R.id.resultsFahrenheit);
        resultsKelvin = findViewById(R.id.resultsKelvin);
        inputEmail = findViewById(R.id.inputEmail);

        resultsCelsius.setText(celsius + "° C");
        resultsFahrenheit.setText(fahrenheit + "° F");
        resultsKelvin.setText(kelvin + "° K");

        buttonBack = findViewById(R.id.buttonBack);
        buttonSendEmail = findViewById(R.id.buttonSendEmail);

        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainIntent.putExtra("result", "success!");
                startActivity(mainIntent);
            }
        });

        this.buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"))
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_EMAIL, new String[] { inputEmail.getText().toString() })
                    .putExtra(Intent.EXTRA_SUBJECT, "🌡 Temperature results! (Isaac's Temperature Converter)")
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

    String buildBody() {
        return "Your temperature results!\n" +
                "\n" + celsius + "° C" +
                "\n" + fahrenheit + "° F" +
                "\n" + kelvin + "° K";
    }
}
