package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtWebAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.edtWebAddress = findViewById(R.id.editText3);
    }

    /*
    * INTENT IMPLICITO
    * */
    public void accessWebSite(View view) {
        String url = edtWebAddress.getText().toString();
        /*
        * TRANSFORMAR A URL DIGITADA EM URI
        * */
        Uri uri = Uri.parse(url);

        Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);

        //SABER SE EXISTE ALGUEM DENTRO DO S.O CAPAZ DE RESOLVER ESSE INTENT
        this.checkAppsForIntent(webIntent);
    }

    public void setAlarm(View view) {
        Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, "Alarme devventure");
        alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, 9);
        alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        
        this.checkAppsForIntent(alarmIntent);
    }

    /*
    * FUNCTION PARA VERIFICAR OS APLICATIVOS QUE PODEM RODAR ESSE INTENT
    * */
    private void checkAppsForIntent(Intent intent) {
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "There's not app aviable ", Toast.LENGTH_SHORT).show();
        }
    }
}
