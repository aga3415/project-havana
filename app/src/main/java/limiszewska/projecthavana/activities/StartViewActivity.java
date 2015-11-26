package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ProgressBar;
import android.widget.TextView;





public class StartViewActivity extends ActionBarActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);


        context = getApplicationContext();
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.startProgress);
        final TextView errorTextView = (TextView) findViewById(R.id.startErrorText);

        Intent mainIntent = new Intent(context, MainActivity.class );
        super.onResume();
        startActivity(mainIntent);



        //errorTextView.setText((CharSequence) new GetConnection().execute());




        /*final Handler handler = new Handler() {
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                String message = msg.obj.toString();
                if (!message.equals("ok")){
                    progressBar.setVisibility(View.GONE);
                    errorTextView.setText(message);
                    errorTextView.setVisibility(View.VISIBLE);


                }else{

                    progressBar.setVisibility(View.GONE);
                    //errorTextView.setText("Wszystko ok");
                    errorTextView.setText((CharSequence) new GetConnection().execute());
                    errorTextView.setVisibility(View.VISIBLE);
                    Intent mainIntent = new Intent(context, MainActivity.class );
                    //startActivity(mainIntent);

            }

            }

        };



        new Thread (new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();


                if (!CheckAccess.isOnline(context)){
                    //krok 1, sprawdzenie dostepu do internetu
                    msg.obj = getString(R.string.no_internet);
                }else if (!CheckAccess.isServerOnline()){
                    //krok 2, sprawdzenie czy serwer odpowiada
                    msg.obj = getString(R.string.server_not_response);
                }else{
                    msg.obj = "ok";
                    //sprawdzenie czy sa dane do logowania i proba zalogowania
                }
                handler.sendMessage(msg);
            }
        }).start();

*/



    }



}
