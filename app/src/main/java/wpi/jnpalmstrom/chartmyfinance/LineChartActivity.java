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

    public void drawLineChart() {

        lineChartView = (LineChartView) findViewById(R.id.line_chart);

        List<PointValue> values = new ArrayList<>();
        values.add(new PointValue(0, 2));
        values.add(new PointValue(1, 4));
        values.add(new PointValue(2, 3));
        values.add(new PointValue(3, 4));

        Line line = new Line(values)
                .setColor(Color.BLUE)
                .setHasPoints(true)
                .setHasLabels(true);

        List<Line> lines = new ArrayList<>();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        List<AxisValue> axisValuesForX = new ArrayList<>();
        List<AxisValue> axisValuesForY = new ArrayList<>();
        AxisValue tempAxisValue;

        for (int i = 0; i <= 10; i++){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(""+i);
            axisValuesForX.add(tempAxisValue);
        }

        for (int i = 0; i <= 10; i++){
            tempAxisValue = new AxisValue(i);
            tempAxisValue.setLabel(""+i);
            axisValuesForY.add(tempAxisValue);
        }

        Axis xAxis = new Axis(axisValuesForX);
        Axis yAxis = new Axis(axisValuesForY);

        data.setAxisXBottom(xAxis);
        data.setAxisYLeft(yAxis);

        xAxis.setName("Axis X");
        yAxis.setName("Axis Y");

        lineChartView.setLineChartData(data);
    }

}
