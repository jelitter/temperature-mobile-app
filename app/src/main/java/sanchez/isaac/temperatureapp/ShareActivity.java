package sanchez.isaac.temperatureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ShareActivity extends AppCompatActivity {

    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);


        Bundle extras = getIntent().getExtras();
        String celsius = extras.getString("celsius");
        String fahrenheit = extras.getString("fahrenheit");
        String kelvin = extras.getString("kelvin");
        Toast.makeText(getApplicationContext(),"Values are:" +
                "\nCelsius: "+celsius+
                "\nFahrenheit: "+fahrenheit+
                "\nKelvin: "+kelvin,
                Toast.LENGTH_LONG).show();

        buttonBack = findViewById(R.id.buttonBack);

        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
//                mainIntent.putExtra("celsius", temp.getCelsiusToString());
//                mainIntent.putExtra("fahrenheit", temp.getFahrenheitToString());
//                mainIntent.putExtra("kelvin", temp.getKelvinToString());
                mainIntent.putExtra("result", "success!");
                startActivity(mainIntent);
            }
        });
    }
}
