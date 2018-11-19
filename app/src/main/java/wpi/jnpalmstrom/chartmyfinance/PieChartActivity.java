package wpi.jnpalmstrom.chartmyfinance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartActivity extends AppCompatActivity {

    private PieChartView pieChartView;

    private float totalCost;
    private float travelCost;
    private float shoppingCost;
    private float foodCost;
    private float healthCost;
    private float otherCost;

    private float travelCostPercentage;
    private float shoppingCostPercentage;
    private float foodCostPercentage;
    private float healthCostPercentage;
    private float otherCostPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        Intent intent = getIntent();
        String travelCost = intent.getStringExtra("travelCost");
        String shoppingCost = intent.getStringExtra("shoppingCost");
        String foodCost = intent.getStringExtra("foodCost");
        String healthCost = intent.getStringExtra("healthCost");
        String otherCost = intent.getStringExtra("otherCost");

        this.travelCost = Long.parseLong(travelCost);
        this.shoppingCost = Long.parseLong(shoppingCost);
        this.foodCost = Long.parseLong(foodCost);
        this.healthCost = Long.parseLong(healthCost);
        this.otherCost = Long.parseLong(otherCost);

        this.totalCost = this.travelCost + this.shoppingCost + this.foodCost + this.healthCost + this.otherCost;

        this.travelCostPercentage = (this.travelCost / this.totalCost) * 100;
        this.shoppingCostPercentage = (this.shoppingCost / this.totalCost) * 100;
        this.foodCostPercentage = (this.foodCost / this.totalCost) * 100;
        this.healthCostPercentage = (this.healthCost / this.totalCost) * 100;
        this.otherCostPercentage = (this.otherCost / this.totalCost) * 100;

        drawPieChart();
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
            startActivity(new Intent(PieChartActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu Ends Here

    public void drawPieChart() {

        pieChartView = findViewById(R.id.pie_chart);

        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(shoppingCostPercentage, Color.BLUE).setLabel("Shopping: $" + shoppingCost));
        pieData.add(new SliceValue(foodCostPercentage, Color.GRAY).setLabel("Food: $" + foodCost));
        pieData.add(new SliceValue(travelCostPercentage, Color.RED).setLabel("Travel: $" + travelCost));
        pieData.add(new SliceValue(healthCostPercentage, Color.GREEN).setLabel("Health: $" + healthCost));
        pieData.add(new SliceValue(otherCostPercentage, Color.MAGENTA).setLabel("Other: $" + otherCost));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Daily Spending").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
    }

}
