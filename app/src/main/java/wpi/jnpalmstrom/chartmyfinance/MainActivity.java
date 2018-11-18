package wpi.jnpalmstrom.chartmyfinance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Code for Menu Starts Here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.home_item) {
            // Go to Take Picture Activity
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
        else if (item.getItemId() == R.id.line_chart_item) {
            // Go to Gallery Activity
            startActivity(new Intent(MainActivity.this, LineChartActivity.class));
        }
        else if (item.getItemId() == R.id.pie_chart_item) {
            // Go to Gallery Activity
            startActivity(new Intent(MainActivity.this, PieChartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu ends here

    public void onSubmitForm(View v) {
        final EditText travelField = (EditText) findViewById(R.id.travelEntry);
        String travelCost = travelField.getText().toString();

        final EditText foodField = (EditText) findViewById(R.id.foodEntry);
        String foodCost = foodField.getText().toString();

        final EditText shoppingField = (EditText) findViewById(R.id.shoppingEntry);
        String shoppingCost = shoppingField.getText().toString();

        final EditText healthField = (EditText) findViewById(R.id.healthEntry);
        String healthCost = healthField.getText().toString();

        final EditText otherField = (EditText) findViewById(R.id.otherEntry);
        String otherCost = otherField.getText().toString();

        Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
        intent.putExtra("travelCost", travelCost);
        intent.putExtra("foodCost", foodCost);
        intent.putExtra("shoppingCost", shoppingCost);
        intent.putExtra("healthCost", healthCost);
        intent.putExtra("otherCost", otherCost);
        startActivity(intent);

    }


}
