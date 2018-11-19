package wpi.jnpalmstrom.chartmyfinance;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class BarChartActivity extends AppCompatActivity {

    // Initialize the Bar Chart View
    private ColumnChartView barChartView;
    private ColumnChartData data;

    private float travelCost;
    private float shoppingCost;
    private float foodCost;
    private float healthCost;
    private float otherCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        Intent intent2 = getIntent();
        String travelCost = intent2.getStringExtra("travelCost");
        String shoppingCost = intent2.getStringExtra("shoppingCost");
        String foodCost = intent2.getStringExtra("foodCost");
        String healthCost = intent2.getStringExtra("healthCost");
        String otherCost = intent2.getStringExtra("otherCost");

        this.travelCost = Long.parseLong(travelCost);
        this.shoppingCost = Long.parseLong(shoppingCost);
        this.foodCost = Long.parseLong(foodCost);
        this.healthCost = Long.parseLong(healthCost);
        this.otherCost = Long.parseLong(otherCost);

        drawBarChart();
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
            startActivity(new Intent(BarChartActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu Ends Here

    // This function creates the Line Chart using static data points
    public void drawBarChart() {

        barChartView = (ColumnChartView) findViewById(R.id.bar_chart);

        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values = new ArrayList<>();

        values.add(new SubcolumnValue(travelCost, Color.BLUE).setLabel(""));
        values.add(new SubcolumnValue(foodCost, Color.RED).setLabel("Food"));
        values.add(new SubcolumnValue(healthCost, Color.GREEN).setLabel("Health"));
        values.add(new SubcolumnValue(shoppingCost, Color.GRAY).setLabel("Travel"));
        values.add(new SubcolumnValue(otherCost, Color.MAGENTA).setLabel("Other"));

        Column column = new Column(values);
        columns.add(column);

        data = new ColumnChartData(columns);
        data.setBaseValue(0);
        data.setValueLabelTextSize(16);

        Axis axisX = new Axis();
        axisX.setName("Travel, Food, Health, Shopping, Other");
        data.setAxisXBottom(axisX);

        Axis axisY = new Axis().setHasLines(true);
        axisY.setName("Total Money Spent ($)");
        data.setAxisYLeft(axisY);

        barChartView.setColumnChartData(data);
    }
}
