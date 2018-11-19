package sanchez.isaac.temperatureapp;

public class Degree {

    // Fields celsius, fahrenheit and kelvin are always in sync
    // Setting any of them will update the other two
    // All of their "toString" methods return their value rounded down to 2 decimals

    public double celsius;
    public double fahrenheit;
    public double kelvin;


    public Degree(double celsius) {
        this.setCelsius(celsius);
    }

    public double getCelsius() {
        return celsius;
    }

    public String getCelsiusToString() {
        return Double.toString(Math.round(celsius * 100d) / 100d);
    }

    public void setCelsius(double celsius) {
        this.celsius = celsius;
        this.fahrenheit = (celsius * 9 / 5) + 32;
        this.kelvin = celsius + 273.15;
    }

    public double getFahrenheit() {
        return fahrenheit;
    }

    public String getFahrenheitToString() {
        return Double.toString(Math.round(fahrenheit * 100d) / 100d);
    }

    public void setFahrenheit(double fahrenheit) {
        this.fahrenheit = fahrenheit;
        this.celsius = (fahrenheit - 32) * 5 / 9;
        this.kelvin = (fahrenheit - 32) * 5 / 9 + 273.15;
    }

    public double getKelvin() {
        return kelvin;
    }

    public String getKelvinToString() {
        return Double.toString(Math.round(kelvin * 100d) / 100d);
    }

    public void setKelvin(double kelvin) {
        this.kelvin = kelvin;
        this.celsius = kelvin - 273.15;
        this.fahrenheit = (kelvin - 273.15) * 9 / 5 + 32;
    }


    public String toString() {
        return getCelsiusToString() + " C, " + getKelvinToString() + " K, " + getFahrenheitToString() + " F, ";
    }
}
