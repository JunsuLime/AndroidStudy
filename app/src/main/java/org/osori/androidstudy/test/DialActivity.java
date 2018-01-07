package org.osori.androidstudy.test;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;

import org.osori.androidstudy.R;

import retrofit2.Retrofit;

/**
 * Created by junsu on 2017-07-30.
 */

public class DialActivity extends AppCompatActivity {

    public void test () {
        Retrofit.Builder builder = null;
        ObservableScrollViewCallbacks a = null;

    }

    private static final String TAG = DialActivity.class.getSimpleName();
    private String number = "";
    public final int CALL_PERMISSION_REQUEST = 2017;
    private EditText editText;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private ImageButton star;
    private ImageButton sharp;
    private ImageButton erase;
    public ImageButton call;
    private ImageButton tap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        editText = (EditText) findViewById(R.id.editText);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        star = (ImageButton) findViewById(R.id.star);
        sharp = (ImageButton) findViewById(R.id.sharp);
        erase = (ImageButton) findViewById(R.id.erase);
        call = (ImageButton) findViewById(R.id.call);
        tap = (ImageButton) findViewById(R.id.tap);

        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        manager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);

        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "0";
                editText.setText(number);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "1";
                editText.setText(number);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "2";
                editText.setText(number);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "3";
                editText.setText(number);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "4";
                editText.setText(number);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "5";
                editText.setText(number);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "6";
                editText.setText(number);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "7";
                editText.setText(number);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "8";
                editText.setText(number);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "9";
                editText.setText(number);
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "*";
                editText.setText(number);
            }
        });
        sharp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                number += "#";
                editText.setText(number);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] permission = {Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions(DialActivity.this, permission, CALL_PERMISSION_REQUEST);
            }
        });
        erase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        tap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CALL_PERMISSION_REQUEST:
                if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {

                    Intent it = new Intent(Intent.ACTION_CALL);
                    it.setData(Uri.parse("tel:" + number));

                    if (ActivityCompat.checkSelfPermission(this , Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(it);
                }
        }
    }

    private PhoneStateListener phoneStateListener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.d(TAG, "state: " + state + "  incomingNumber: " + incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
            }
        }
    };
}
