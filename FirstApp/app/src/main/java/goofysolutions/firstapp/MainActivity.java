package goofysolutions.firstapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView total;
    EditText billTotal;
    NumberPicker numPick;
    int percentage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = (EditText) findViewById(R.id.billTotal);
        total = (TextView) findViewById(R.id.total);

        // create button
        Button button = (Button) findViewById(R.id.button);
        // create button listener
        ButtonListener buttonListener = new ButtonListener();
        button.setOnClickListener(buttonListener);


        // create number picker
        numPick = (NumberPicker) findViewById(R.id.numberPicker);
        // create number picker listener
        WheelListener wheelListener = new WheelListener();
        numPick.setOnValueChangedListener(wheelListener);
        // set percentage to base value
        percentage = 12;
        // set values and selector
        numPick.setMinValue(12);
        numPick.setMaxValue(25);
        numPick.setWrapSelectorWheel(false);








        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    public class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            // ------------------------ calculate tip -----------------------------
            Double percent = ((double)percentage/(double)100);
            Double tip = percent * Double.parseDouble(billTotal.getText().toString());
            DecimalFormat decimal = new DecimalFormat("$###,###.##");
            decimal.applyPattern("$###,###.##");
            String adjustedTip = decimal.format(tip);

            total.setText(adjustedTip);
            // ------------------------ calculate tip -----------------------------
        }
    }


    public class WheelListener implements NumberPicker.OnValueChangeListener {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            percentage = picker.getValue();
        }
    }










































    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
