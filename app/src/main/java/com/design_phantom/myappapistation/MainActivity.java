package com.design_phantom.myappapistation;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultMsg;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultMsg = findViewById(R.id.tv_result);

        bt = findViewById(R.id.bt_api);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskClass task = new AsyncTaskClass();
                task.execute("START!");
                view.setEnabled(false);
                resultMsg.setText("BackGround処理開始!");
            }
        });
        
    }

    class AsyncTaskClass extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground (String... params) {

            // バックグラウンドで行う処理

            Log.i("INFO", params[0]);

            Thread thread = new Thread();

            try {
                for(int i = 0; i < 5; i++){
                    thread.sleep(1000);
                    this.publishProgress(String.valueOf(i+1) + "/ 5");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return "END";
        }

        @Override
        protected void onProgressUpdate(String... values) {

            Log.i("INFO",values[0]);
            resultMsg.setText(values[0]);

        }

        @Override
        protected void onPostExecute(String str) {
            // UIスレッドに反映する処理
            Log.i("INFO",str);
            resultMsg.setText("処理終了！" + "わたされた値：" + str);
            bt.setEnabled(true);

        }
    }
}
