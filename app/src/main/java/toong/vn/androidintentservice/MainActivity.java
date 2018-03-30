package toong.vn.androidintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private IntentServiceReceiver intentServiceReceiver;
    private Button btnStartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartService = findViewById(R.id.btnStartService);
        registerReceiver();
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntentService();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(intentServiceReceiver);
    }

    public void startIntentService() {
        Intent cbIntent = new Intent(this, AIntentService.class);
        cbIntent.putExtra("data", "1");
        startService(cbIntent);
    }

    private void registerReceiver() {
        intentServiceReceiver = new IntentServiceReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AIntentService.INTENT_SERVICE_ACTION);

        registerReceiver(intentServiceReceiver, intentFilter);
    }

    private class IntentServiceReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String dataResponse = intent.getStringExtra("data_response");
            Toast.makeText(getApplicationContext(), "" + dataResponse, Toast.LENGTH_SHORT).show();
        }
    }
}
