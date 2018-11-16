package wpi.jnpalmstrom.chartmyfinance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


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
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu ends here


}
