# Hellocharts-android Tutorial

### 1. Overview of Tutorial: 

In this tutorial, we'll be introducing how to implement the hellocharts-android library into a
Android project. Hellocharts simplifies integrating different types of charts such as line charts, 
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

    compile 'com.github.lecho:hellocharts-library:1.5.8@aar'

Once you have added this line, you will want to sync your gradle file (This requires Internet access).

### 3. Creating a simple menu
Next, you will want to add a menu to your application for easier navigation. To add a menu to your 
application navigate to your res folder and right-click on it. Select a new android resource directory
this will bring up a menu, and you want to change the resource type of the directory to menu. You can 
keep the rest of the defaults and click OK. Navigate to your new menu directory and right-click to 
add a new menu resource file. You will want to name this file main_menu. Within your menu file, this 
is where you will add "Items" to navigate throughout your application. For our application, we are 
only adding 1 item. To implement this use the following lines of code:

    <item android:id="@+id/home_item"
        android:title="@string/home"
        app:showAsAction="never">
    </item>
    
The showAsAction set to never makes it so the menu item is only seen as the 3 dots unless clicked on.

Then, you will need to implement the "backend" code to make your menu function properly. Start by 
going to your MainActivity. You will need to Override two functions in your activity. The code to 
complete this is:

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


### 5. Passing data with explicit intents
Then, you will be looking to set up passing an explicit intent from your MainActivity to your BarChart
or PieChart activity. To do this you will first want to set up a function that will be called on a 
button click. In your new function, you will first want to find each of the EditText fields you 
created for your form. You will then want to convert each of the textviews into strings to be passed
through your event. You will need to define and explicit intent and define what activity you are 
passing the intent to. Now, you will want to add to the intent with putExtra("yourTag", stringVar) 
with the stringVar being your string you extracted from the EditText above. Now, you will need to
pass with intent with the function startActivity(yourIntent). The code to pass the intent with a 
function is as follows:

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
    
Then, you will need to parse the data inside of your BarChartActivity. Start by setting up your 
global private variables. The global private variables for this project are:

    private float travelCost;
    private float shoppingCost;
    private float foodCost;
    private float healthCost;
    private float otherCost;
    
Now, you will want to parse out your intent. To do this you will need to define and Intent then call 
getIntent(). Then, set up a String variable to handle each of the pieces of data you passed through 
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
This will add the default column chart with a little bit of padding.

Now, you will need to start creating the real column chart in your BarChartActivity. Start by adding
two global variables one of type ColumnChartView and one of type ColumnChartData.
    
    private ColumnChartView barChartView;
    private ColumnChartData data;
         
Now, you will want to find the barChartView using findViewById for your barChart in your XML file as
well as initialize two lists one for Columns and one for SubcolumnValues.

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

Then, you will want to set up the X and Y axises. You will want to start by initializing an Axis 
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