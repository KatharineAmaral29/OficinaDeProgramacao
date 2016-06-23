package a371839.katharine.startedservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast.makeText(context,"Bablablablabla",Toast.LENGTH_LONG).show();
        Intent newIntent = new Intent(context,StartedService.class);
        context.startService(newIntent);
    }
}
