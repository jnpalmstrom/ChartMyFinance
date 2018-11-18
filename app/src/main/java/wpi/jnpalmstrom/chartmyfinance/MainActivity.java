package wpi.jnpalmstrom.chartmyfinance;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu ends here

    public void onSubmitPieForm(View v) {
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

        // Code to pass form with an intent for PieChart
        Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
        intent.putExtra("travelCost", travelCost);
        intent.putExtra("foodCost", foodCost);
        intent.putExtra("shoppingCost", shoppingCost);
        intent.putExtra("healthCost", healthCost);
        intent.putExtra("otherCost", otherCost);

        startActivity(intent);
    }

    public void onSubmitBarForm(View v) {
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

        // Code to pass form with an intent for PieChart
        Intent intent2 = new Intent(MainActivity.this, BarChartActivity.class);
        intent2.putExtra("travelCost", travelCost);
        intent2.putExtra("foodCost", foodCost);
        intent2.putExtra("shoppingCost", shoppingCost);
        intent2.putExtra("healthCost", healthCost);
        intent2.putExtra("otherCost", otherCost);

        startActivity(intent2);
    }

}
