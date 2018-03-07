package com.androidprojects;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.regex.Pattern;

import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class MainActivity extends Activity {
    private String senderEmail = new String();
    private Button send;
    private  EditText emailInput;

    public static final int RequestPermissionCode = 1;
    ListView listView ;
    ArrayList<String> SampleArrayList ;
    ArrayAdapter<String> arrayAdapter;
    Pattern pattern;
    Account[] account ;
    String[] StringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnableRuntimePermission();
        /*
        listView = (ListView) findViewById(R.id.listview1);

        SampleArrayList = new ArrayList<String>();
        pattern = Patterns.EMAIL_ADDRESS;
        GetAccountsName();
        //Converting account string array list into string array.
        StringArray = SampleArrayList.toArray(new String[SampleArrayList.size()]);
        //Setting up string array into array adapter.
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, StringArray);
        listView.setAdapter(arrayAdapter);
        */



        send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                boolean isOnline = isOnline();
                View vMsg = findViewById(R.id.textMsg);

                if(isOnline)
                {
                    vMsg.setVisibility(View.INVISIBLE);
                    RelativeLayout.LayoutParams sendLayoutParams =
                            (RelativeLayout.LayoutParams)send.getLayoutParams();
                    sendLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
                    send.setLayoutParams(sendLayoutParams);

                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                EditText skypeInput;
                                skypeInput = findViewById(R.id.editSkype);

                                MultiAutoCompleteTextView projDetail = findViewById(R.id.multiAutoCompleteProjDetail);

                                GMailSender sender = new GMailSender(
                                        "and.proj72@gmail.com",
                                        "fastnu72");
                                //                          sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");
                                sender.sendMail("Android Project Email", projDetail.getText().toString()
                                                + "\n SkypeId: " + skypeInput.getText().toString()
                                                + "\n Sender Email Original: " + senderEmail
                                                + "\n Sender Email Changed: " + emailInput.getText().toString(),
                                        "and.proj72@gmail.com",
                                        "kashif.ir@gmail.com");

                                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                startActivity(intent);
                                finish();
                                ProcessingActivity.inProgressActivity.finish();
                            } catch (Exception e) {
                                Log.e("Error AA Gya", e.getMessage());
                                //     Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }).start();

                    Intent intent = new Intent(MainActivity.this, ProcessingActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    RelativeLayout.LayoutParams sendLayoutParams =
                            (RelativeLayout.LayoutParams)send.getLayoutParams();
                    sendLayoutParams.removeRule(RelativeLayout.ALIGN_PARENT_TOP);
                    send.setLayoutParams(sendLayoutParams);
                    vMsg.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void GetAccountsName(){
        try {
            account = AccountManager.get(MainActivity.this).getAccounts();
        }
        catch (SecurityException e) {
        }

        for (Account TempAccount : account) {
            if (pattern.matcher(TempAccount.name).matches()) {
                SampleArrayList.add(TempAccount.name) ;
            }
        }
    }

    public void EnableRuntimePermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        GET_ACCOUNTS,
                        READ_PHONE_STATE,
                        INTERNET,
                        ACCESS_NETWORK_STATE
                }, RequestPermissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length > 0) {
                    boolean GetAccountPermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    senderEmail = new UserEmailFetcher().getEmail(MainActivity.this.getApplicationContext());
                    emailInput = findViewById(R.id.editEmail);
                    emailInput.setText(senderEmail);
                }
                break;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}