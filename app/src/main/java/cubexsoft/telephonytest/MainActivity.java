package cubexsoft.telephonytest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;

    EditText et3,et4,et5;

    Uri u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);

        et3=(EditText)findViewById(R.id.to);
        et4=(EditText)findViewById(R.id.sub);
        et5=(EditText)findViewById(R.id.msg);

    }

    public void sendSMS(View v){

        StringTokenizer tokenizer=
                new StringTokenizer(et1.getText().toString(),",");
        while(tokenizer.hasMoreElements()) {
            SmsManager sManager = SmsManager.getDefault();
            sManager.sendTextMessage(tokenizer.nextToken(),
                    null, et2.getText().toString(), null, null);
        }
    }

    public void call(View v){

        Intent i=new Intent();
        i.setAction(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+et1.getText().toString()));
        startActivity(i);

    }

    public void attach(View v){

        Intent i=new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("*/*");
        startActivityForResult(i,123);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         u=data.getData();

    }

    public void sendMail(View v){

        Intent i=new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL,new String[]{et3.getText().toString()});
        i.putExtra(Intent.EXTRA_SUBJECT,et4.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT,et5.getText().toString());
        i.putExtra(Intent.EXTRA_STREAM,u);
        i.setType("message/rfc822");    // enable MIME
        startActivity(i.createChooser(i,"Select any Email Client"));

    }

    public void javaMail(View v){

        LongOperation lop=new LongOperation(et3.getText().toString(),
                  et4.getText().toString(),et5.getText().toString());
        lop.execute();

    }



}
