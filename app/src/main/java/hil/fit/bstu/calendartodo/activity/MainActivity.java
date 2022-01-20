package hil.fit.bstu.calendartodo.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import hil.fit.bstu.calendartodo.AllActivity;
import hil.fit.bstu.calendartodo.BaseActivity;
import hil.fit.bstu.calendartodo.R;
import hil.fit.bstu.calendartodo.utils.ActivityType;

public class MainActivity extends AppCompatActivity
{

    //View
    Button selectedDateButton;
    TextView selectedDate;
    LocalDate localDate;
    Calendar calendar;

    Button studyButton;
    Button workButton;
    Button sleepButton;
    Button meetingsButton;
    Button sportsButton;
    Button leisureButton;
    Button allButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*allButton = findViewById(R.id.allButton);
        allButton.setOnClickListener(this);*/

        binding();
        setListeners();
        setData();
    }

    private void binding() {
        selectedDateButton = findViewById(R.id.btnSelectDate);
        selectedDate = findViewById(R.id.txtSelectDate);
        calendar = Calendar.getInstance();

        studyButton = findViewById(R.id.studyButton);
        workButton = findViewById(R.id.workButton);
        sleepButton = findViewById(R.id.sleepButton);
        meetingsButton = findViewById(R.id.meetingsButton);
        sportsButton = findViewById(R.id.sportsButton);
        leisureButton = findViewById(R.id.leisureButton);
        allButton = findViewById(R.id.allButton);

    }
    /*public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allButton:
                Intent intent = new Intent(this, AllActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }*/

    private void setData() {
        try {
            localDate = LocalDate.parse(getIntent().getExtras().get("selectedDate").toString());

            calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());
            calendar.set(Calendar.MONTH, localDate.getMonthValue()-1);
            calendar.set(Calendar.YEAR, localDate.getYear());
        } catch (Exception e) {
            localDate = Calendar.getInstance().getTime()
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } finally {
            selectedDate.setText(localDate.toString());
        }
    }

    private void setListeners() {

        DatePickerDialog.OnDateSetListener d = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            localDate = calendar.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            selectedDate.setText(localDate.toString());
        };

        selectedDateButton.setOnClickListener(view ->
                new DatePickerDialog(MainActivity.this, d,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH))
                        .show());

        studyButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.STUDY);
            startActivity(intent);
        });
        workButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.WORK);
            startActivity(intent);
        });
        sleepButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.SLEEP);
            startActivity(intent);
        });
        meetingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.MEETING);
            startActivity(intent);
        });
        sportsButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.SPORT);
            startActivity(intent);
        });
        leisureButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, BaseActivity.class);
            intent.putExtra("selectedDate", localDate);
            intent.putExtra("activityType", ActivityType.LEISURE);
            startActivity(intent);
        });

            allButton.setOnClickListener(view -> {
                Intent intent = new Intent(this, BaseActivity.class);
                intent.putExtra("selectedDate", localDate);
                intent.putExtra("activityType", ActivityType.ALL);
                startActivity(intent);
            });
        }
    }
