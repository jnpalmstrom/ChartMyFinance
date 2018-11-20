# Hellocharts-android Tutorial

### 1. Overview of Tutorial: 

In this tutorial, we'll be introducing how to implement the hellocharts-android library into an
Android project. Hellocharts simplifies the integration of charts such as line charts, 
pie charts, and bar charts into an android application. 

In this tutorial we'll be doing the following:

* Adding hellocharts to your application dependencies
* Creating a simple menu
* Setting up a simple text entry form
* Passing data with explicit intents
* Creating a hellocharts column chart 
* Creating a hellocharts pie chart

### 2. Adding hellocharts to your application dependencies

To begin implementing hellocharts into your android project, you will need to add the necessary 
dependencies into your build.gradle. You will only need to add these dependencies to your Module:app
file and not your project's build.gradle file. You simply just need to add the following line into
your list of dependencies.

    implementation 'com.github.lecho:hellocharts-library:1.5.8@aar'

Once you have added this line, you will want to sync your gradle file (This requires Internet access).

### 3. Creating a simple menu

Next, you will want to add a menu to your application for easier navigation. To add a menu to your 
application, navigate to your res folder and right-click on it. Select a new android resource directory
this will bring up a menu, and you'll want to change the resource type of the directory to menu. You can 
keep the rest of the defaults and click OK. Navigate to your new menu directory and right-click to 
add a new menu resource file named main_menu. This 
is where you will add "Items" to navigate throughout your application. For our application, we are 
only adding one item. To implement this, use the following lines of code:

    <item android:id="@+id/home_item"
        android:title="@string/home"
        app:showAsAction="never">
    </item>
    
The showAsAction set to never makes it so the menu item is only seen as the 3 dots unless clicked on.

You will then need to implement the "backend" code to make your menu function properly. Start by 
going to your MainActivity. You will need to Override two functions in your activity. The code to 
complete this is as follows:

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

You will want to include this for all activities within your project in order to navigate through 
the application.

### 4. Setting up a simple text entry form

Before creating charts that will show us data, we will first need to gather the data. For the purpose of
this tutorial, the form for submitting data is simple to easily show what the different charts are
capable of.

Navigate to the activity_main.xml. First, change the layout of the activity to be a LinearLayout since
this allows the form to flow nicer. After this, add a TextView to the top of the activity with the string
"Record Your Daily Spending" so the user knows what the form is for. You will then need to create five
different EditText widgets. The easiest way to do this is by manually adding them to the .xml file. They
all follow the following layout:

	<EditText
        android:id="@+id/<EDIT_NAME>"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:hint="<EDIT_HINT>"
        android:inputType="text"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
		
As stated before, you'll need five of these. Replace "EDIT_NAME" and "EDIT_HINT" with the following to
accomplish the proper goal of the form:

Name:				Hint:

travelEntry			Travel Costs
foodEntry			Food Costs
healthEntry			Health Costs
shoppingEntry		Shopping Costs
otherEntry			Other Costs

We can now use this form to gather data from the user. However, we still need to do stuff with this data.
Add two buttons below the newly created form with the following information:

Id:						onClick:				text:

submitPieButton			onSubmitPieForm			See Percent Spending!
submitBarButton			onSubmitBarForm			See Total Spending!

The form is now complete and we can utilize this information.

### 5. Passing data with explicit intents

You will be looking to pass an explicit intent from your MainActivity to your BarChart
or PieChart activity. To do this you will first want to set up a function that will be called on a 
button click as defined above. In the new function, you will first want to find each of the EditText fields you 
created for the form. After this, convert each of the textviews into strings to be passed
through your event. You will need to define an explicit intent and define what activity you are 
passing the intent to. Now, you will want to add to the intent with putExtra("yourTag", stringVar) 
with the stringVar being your string you extracted from the EditText above. Then you will want to
pass the intent with the function startActivity(yourIntent). The code to pass the intent with a 
function is as follows. Note that this setup applies to the PieChart's onSubmitPieForm function as
well so implement that in the same way as you would this:

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
    
Once passed through the intent, you will need to parse the data inside of your BarChartActivity. Start by setting up your 
global private variables. The global private variables for this project are:

    private float travelCost;
    private float shoppingCost;
    private float foodCost;
    private float healthCost;
    private float otherCost;
    
Now parse out your intent. To do this, you will need to define an Intent and call 
getIntent(). After this, set up a String variable to handle each of the pieces of data you passed through 
the intent. Call the function getStringExtra("YOUR_TAG") on the Intent to parse out the appropriate 
string. Finally, we need to convert this string value to a long to use in a chart. You can 
accomplish this with Long.parseLong("YOUR_VAR"). The code to complete this is as follows and should
live inside your onCreate method:

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

### 6. Creating a hellocharts column chart 

To begin creating your column chart (aka a bar chart), you will first need to implement it in your 
barChart activity's layout file. Go to your activity_bar_chart.xml file and add the following code:

    <lecho.lib.hellocharts.view.ColumnChartView
            android:id="@+id/bar_chart"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:padding="10dp"/>
			
This will add the default column chart with a small amount of padding.

You will still need to create the real column chart in your BarChartActivity. Start by adding
two global variables one of type ColumnChartView and one of type ColumnChartData.
    
    private ColumnChartView barChartView;
    private ColumnChartData data;
         
Now, you will want to find the barChartView using findViewById for your barChart in your XML file as
well as initialize two lists: one for Columns and one for SubcolumnValues.

        barChartView = (ColumnChartView) findViewById(R.id.bar_chart);
        List<Column> columns = new ArrayList<Column>();
        List<SubcolumnValue> values = new ArrayList<>();
        
Now, you will add your global values passed through the intent for the activity to the SubcolumnValue
list. 

    values.add(new SubcolumnValue(travelCost, Color.BLUE).setLabel(""));
    values.add(new SubcolumnValue(foodCost, Color.RED).setLabel("Food"));
    values.add(new SubcolumnValue(healthCost, Color.GREEN).setLabel("Health"));
    values.add(new SubcolumnValue(shoppingCost, Color.GRAY).setLabel("Travel"));
    values.add(new SubcolumnValue(otherCost, Color.MAGENTA).setLabel("Other"));

You will then initialize a column and insert the values into it with:
        
    Column column = new Column(values);
    columns.add(column);
    
Next, add this columnChartData to your global data variable created previously along with setting 
the base value of the data to 0 and the label size of the text to 16.

    data = new ColumnChartData(columns);
    data.setBaseValue(0);
    data.setValueLabelTextSize(16);

After this, you will want to set up the X and Y axises. You will want to start by initializing an Axis 
variable. You will also want to add a label to each axises as well as set the axis to your data 
variable.

    Axis axisX = new Axis();
    axisX.setName("Travel, Food, Health, Shopping, Other");
    data.setAxisXBottom(axisX);

    Axis axisY = new Axis().setHasLines(true);
    axisY.setName("Total Money Spent ($)");
    data.setAxisYLeft(axisY);

You will set the column chart data to your bar chart view with:

    barChartView.setColumnChartData(data);

The complete function for creating this bar chart looks like:

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

Finally, you will want to call this function in your onCreate method for this activity.

### 7. Creating a hellocharts pie chart

To begin creating your pie chart, you will first need to implement it in your 
pieChart activity's layout file. Go to your activity_pie_chart.xml file and add the following code:

    <lecho.lib.hellocharts.view.PieChartView
        android:id="@+id/pie_chart"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="10dp"/>
			
You will notice that this setup is incredibly similar to that of the Bar Chart.

Just as before, you will still need to create the real pie chart in your PieChartActivity. Add the
following global variable:
    
    private PieChartView pieChartView;
         
Next, you'll want to create a drawPieChart function. This will contain all necessary steps for using
the data gathered by the intent.

First, get the View for your Pie Chart as defined here:

	pieChartView = findViewById(R.id.pie_chart);
        
After this, you need to create slices for the pie chart using the collected data. Add the following code:

	List pieData = new ArrayList<>();
    pieData.add(new SliceValue(shoppingCostPercentage, Color.BLUE).setLabel("Shopping: $" + shoppingCost));
    pieData.add(new SliceValue(foodCostPercentage, Color.GRAY).setLabel("Food: $" + foodCost));
    pieData.add(new SliceValue(travelCostPercentage, Color.RED).setLabel("Travel: $" + travelCost));
    pieData.add(new SliceValue(healthCostPercentage, Color.GREEN).setLabel("Health: $" + healthCost));
    pieData.add(new SliceValue(otherCostPercentage, Color.MAGENTA).setLabel("Other: $" + otherCost));
	
In each line, you first define a SliceValue which contains the value of the slice from 0 to 100 and the
color that defines it. Then, we set the label to describe what the slice represents. Finally, we add
each slice value to the ArrayList to later use in our Pie Chart.


Now that the data has been consolidated we can add it to the Pie Chart with the following code:
        
	PieChartData pieChartData = new PieChartData(pieData);
    pieChartData.setHasLabels(true).setValueLabelTextSize(14);
    pieChartData.setHasCenterCircle(true).setCenterText1("Daily Spending").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
    pieChartView.setPieChartData(pieChartData);

The complete function for creating this pie chart looks like this:

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
	
Simply call the drawPieChart function from the onCreate function and the class is set up!

### 8. Summary

In this tutorial, we've covered how to utilize the plugin hellocharts and some of its charts to make our data mean more to the user.

Since there are numerous other charts that can be used, any number of useful charts could be added that all show data differently.
Feel free to explore these options to see what might be useful to you in the future!

