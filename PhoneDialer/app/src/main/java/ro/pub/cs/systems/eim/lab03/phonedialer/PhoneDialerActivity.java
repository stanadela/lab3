package ro.pub.cs.systems.eim.lab03.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneDialerActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

        editText = (EditText) findViewById(R.id.edit_text);
    }

    public void onOneClick(View view) {
        Button b = (Button)view;
        String buttonText = b.getText().toString();
        CharSequence cs;
        cs = buttonText;
        editText.append(cs);
    }

    public void onDeleteClick(View view) {
        String str = editText.getText().toString();
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        CharSequence cs;
        cs = str;
        editText.setText(cs);
    }

    public void onCallClick(View view) {
        if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    PhoneDialerActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    0);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + editText.getText().toString()));
            startActivity(intent);
        }
    }

    public void onEndCallClick(View view) {
        finish();
    }
}
