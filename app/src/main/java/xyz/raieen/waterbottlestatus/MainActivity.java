package xyz.raieen.waterbottlestatus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView statusMessage;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusMessage = findViewById(R.id.text_status_message);
        layout = findViewById(R.id.layout_content);

        // Initalize UI
        updateUI(getStatus());

        // Click to change status
        statusMessage.setOnLongClickListener(v -> {
            boolean newStatus = !getStatus();
            setStatus(newStatus); // Flip status
            updateUI(newStatus); // Update UI
            return true;
        });
    }

    /**
     * Updates the water bottle status.
     */
    public void updateUI(boolean status) {
        if (status) {
            // Message
            statusMessage.setText(getRandomString(R.array.has_water));

            // Colour of background.
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorHasWater));
        } else {
            // Message
            statusMessage.setText(getRandomString(R.array.no_water));

            // Colour of background
            layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorNoWater));
        }
    }

    public String getRandomString(int id) {
        String[] array = getResources().getStringArray(id);
        return array[new Random().nextInt(array.length)];
    }

    /**
     * Returns the saved status.
     *
     * @return True if there is a water bottle.
     * False if there is no water bottle.
     */
    public boolean getStatus() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);

        return sharedPreferences.getBoolean(getString(R.string.key_status), false);
    }

    /**
     * Sets the water bottle status in SharedPreferences and asynchronously writes new status to disk.
     */
    public void setStatus(boolean status) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(getString(R.string.key_status), status).apply();
    }
}
