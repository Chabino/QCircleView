package com.example.android.qcircleview.simpleweather;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.qcircleview.R;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherFragment extends Fragment {

    Typeface weatherFont;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    ImageButton gps;
    String day1;
    Boolean day;
    View rootView;

    Handler handler;

    public WeatherFragment() {
        handler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView) rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.updated_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
        /*gps = (ImageButton) rootView.findViewById(R.id.imageButton);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });*/


        weatherIcon.setTypeface(weatherFont);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        updateWeatherData(new CityPreference(getActivity()).getCity());
    }

    private void updateWeatherData(final String city) {
        new Thread() {
            public void run() {
                final JSONObject json = RemoteFetch.getJSON(getActivity(), city);
                if (json == null) {
                    handler.post(new Runnable() {
                        public void run() {
                            Toast toaster = Toast.makeText(getActivity(),
                                    getActivity().getString(R.string.place_not_found),
                                    Toast.LENGTH_LONG);
                            toaster.setGravity(Gravity.TOP, 0, 320);
                            toaster.show();
                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        public void run() {
                            renderWeather(json);
                        }
                    });
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json) {
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.getDefault()) +
                    ", " +
                    json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.getDefault()) +
                            "\n" + "Humidity: " + main.getString("humidity") + "%" +
                            "\n" + "Pressure: " + main.getString("pressure") + " hPa");

            currentTemperatureField.setText(
                    String.format("%.2f", main.getDouble("temp")) + " ℃");

            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt") * 1000));
            updatedField.setText("Last update: " + updatedOn);
            String day2 = details.getString("icon");
            day1 = day2.substring(2);
            day = day1.equals("n");
            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);
            String provider = Settings.Secure.getString(getActivity().getApplicationContext().getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);


            if (!provider.contains("gps")) { //if gps is disabled
            } else {
                Toast toaster1 = Toast.makeText(getActivity(),
                        getActivity().getString(R.string.turn_off_gps),
                        Toast.LENGTH_LONG);
                toaster1.setGravity(Gravity.TOP, 0, 320);
                toaster1.show();
            }

        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        Log.i("test", day.toString());
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
                Drawable ico = getResources().getDrawable(R.drawable.weather_clear_day);
                WeatherActivity.weath.setBackground(ico);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
                Drawable ico = getResources().getDrawable(R.drawable.weather_clear_night);
                WeatherActivity.weath.setBackground(ico);
            }
        } else {
            switch (id) {
                case 2:
                    icon = getActivity().getString(R.string.weather_thunder);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_thunder_night);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_thunder_day);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
                case 3:
                    icon = getActivity().getString(R.string.weather_drizzle);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_rainy_night);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_rainy_day);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
                case 7:
                    icon = getActivity().getString(R.string.weather_foggy);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.foggy_prom);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.foggy_prom);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
                case 8:
                    icon = getActivity().getString(R.string.weather_cloudy);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_cloudy_night);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_cloudy_day);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
                case 6:
                    icon = getActivity().getString(R.string.weather_snowy);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_snowy_night);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_snowy_day);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
                case 5:
                    icon = getActivity().getString(R.string.weather_rainy);
                    if (day) {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_rainy_night);
                        WeatherActivity.weath.setBackground(ico);
                    } else {
                        Drawable ico = getResources().getDrawable(R.drawable.weather_rainy_day);
                        WeatherActivity.weath.setBackground(ico);
                    }
                    break;
            }
        }
        weatherIcon.setText(icon);
    }

    public void changeCity(String city) {
        updateWeatherData(city);
    }

}
