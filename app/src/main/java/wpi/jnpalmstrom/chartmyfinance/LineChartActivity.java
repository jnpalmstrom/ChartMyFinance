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
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class LineChartActivity extends AppCompatActivity {

    // Initialize the Line Chart View
    private LineChartView lineChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);

        drawLineChart();
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
            startActivity(new Intent(LineChartActivity.this, MainActivity.class));
        }
        else if (item.getItemId() == R.id.line_chart_item) {
            // Go to Gallery Activity
            startActivity(new Intent(LineChartActivity.this, LineChartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
    // Code for Menu Ends Here

    // This function creates the Line Chart using static data points
    public void drawLineChart() {

        lineChartView = (LineChartView) findViewById(R.id.line_chart);

        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 0));
        values.add(new PointValue(1, 2));
        values.add(new PointValue(2, 4));
        values.add(new PointValue(3, 3));
        values.add(new PointValue(4, 4));

        Line line = new Line(values)
                .setColor(Color.BLUE)
                .setHasPoints(true)
                .setHasLabels(true);

        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        
        AxisValue tempAxisValue; // Temporary Placeholder for Axis-Values in for loop

        // Create X-Axis data points from 0 to 10
        List<AxisValue> axisValuesForX = new ArrayList<>();
        for (int i = 0; i <= 10; i++){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(""+i);
            axisValuesForX.add(tempAxisValue);
        }

        // Create Y-Axis data points from 0 to 10
        List<AxisValue> axisValuesForY = new ArrayList<>();
        for (int i = 0; i <= 10; i++){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(""+i);
            axisValuesForY.add(tempAxisValue);
        }

        // Create X & Y Axis and initialize with data points generated above
        Axis xAxis = new Axis(axisValuesForX);
        Axis yAxis = new Axis(axisValuesForY);

        // Set the Orientation and the Names of the X & Y Axises
        data.setAxisXBottom(xAxis);
        data.setAxisYLeft(yAxis);
        xAxis.setName("Axis X");
        yAxis.setName("Axis Y");

        lineChartView.setLineChartData(data);
    }

}
