package a371839.katharine.startedservice;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BoundService extends Service {

    private int result = 0;
    public static final int OPERATION_SUM = 1;
    public static final int OPERATION_SUB = 2;
    public static final int OPERATION_MULTI = 3;
    public static final int OPERATION_DIV = 4;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        BoundService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BoundService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int resolve(int value, int operation) {
        if (operation == OPERATION_SUM){
            return result +=value;
        }
        else if (operation == OPERATION_SUB){
            return result -=value;
        }
        else if (operation == OPERATION_DIV){
            return result /=value;
        }
        else
            return result *=value;
    }

    public void addValue(int value) {
        result = value;
    }


}
