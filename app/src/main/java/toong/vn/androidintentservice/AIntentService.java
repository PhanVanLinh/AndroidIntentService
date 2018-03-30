package toong.vn.androidintentservice;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by PhanVanLinh on 30/03/2018.
 * phanvanlinh.94vn@gmail.com
 */

public class AIntentService extends IntentService {
    final static String INTENT_SERVICE_ACTION = "INTENT_SERVICE_ACTION";

    public AIntentService() {
        super("AIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String data = intent.getStringExtra("data");
        if (data.equals("1")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendCallbackToClient("1 finished");
        }
        if (data.equals("2")) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendCallbackToClient("2 finished");
        }
    }

    private void sendCallbackToClient(String msg) {
        Intent intent = new Intent();
        intent.setAction(INTENT_SERVICE_ACTION);
        intent.putExtra("data_response", msg);
        sendBroadcast(intent);
    }
}
