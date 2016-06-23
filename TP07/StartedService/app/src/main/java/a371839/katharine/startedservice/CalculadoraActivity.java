package a371839.katharine.startedservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener{

    BoundService mService;
    private boolean mIsBound;
    private TextView display;
    private int last_number = 0;
    private int current_operation = 1;

    private Button button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        display = (TextView) findViewById(R.id.display);

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


        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.doBindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.doUnbindService();
    }

    ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(CalculadoraActivity.this, "Service is disconnected", Toast.LENGTH_SHORT).show();
            mIsBound = false;
            mService = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(CalculadoraActivity.this, "Service is connected", Toast.LENGTH_SHORT).show();
            mIsBound = true;
            BoundService.LocalBinder mLocalBinder = (BoundService.LocalBinder)service;
            mService = mLocalBinder.getService();
        }
    };

    private void doBindService() {
        // Establish a connection with the service.  We use an explicit
        // class name because we want a specific service implementation that
        // we know will be running in our own process (and thus won't be
        // supporting component replacement by other applications).
        bindService(new Intent(CalculadoraActivity.this,
                BoundService.class), mConnection, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    private void doUnbindService() {
        if (mIsBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mIsBound = false;
        }
    }

    @Override
    public void onClick(View v) {
        String number = ((Button) v).getText().toString();
        Toast.makeText(CalculadoraActivity.this,number,Toast.LENGTH_SHORT).show();
        display.setText(number);
    }

    public void dotClick(View v){

    }

    public void plusClick(View v){
        last_number = Integer.parseInt(display.getText().toString());
        current_operation = BoundService.OPERATION_SUM;
    }

    public void minusClick(View v){
        last_number = Integer.parseInt(display.getText().toString());
        current_operation = BoundService.OPERATION_SUB;
    }

    public void multiClick(View v){
        last_number = Integer.parseInt(display.getText().toString());
        current_operation = BoundService.OPERATION_MULTI;
    }

    public void divClick(View v){
        last_number = Integer.parseInt(display.getText().toString());
        current_operation = BoundService.OPERATION_DIV;
    }

    public void resultClick(View v){
        int number = Integer.parseInt(display.getText().toString());
        mService.addValue(last_number);

        int result =  mService.resolve(number,current_operation);
        display.setText(""+result);

    }
}