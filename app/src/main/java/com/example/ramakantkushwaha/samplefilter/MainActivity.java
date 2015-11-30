package com.example.ramakantkushwaha.samplefilter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Declare Variables
    ArrayList mSelectedItems = new ArrayList();
    Button temp_button;
    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] rank;
    String[] country;
    String[] population;
    LayoutInflater layoutInflater;
    Context mcontext;
    View mview;
    RelativeLayout insertView;
    ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcontext=this;
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setLogo(R.mipmap.ic_launcher);
        }
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(this.LAYOUT_INFLATER_SERVICE);
        mview = layoutInflater.inflate(R.layout.activity_main,null);
        //insertView = (RelativeLayout)mview.findViewById(R.id.dynamic_layout);

        // Generate sample data
        rank = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        country = new String[]{"China", "India", "United States",
                "Indonesia", "Brazil", "Pakistan", "Nigeria", "Bangladesh",
                "Russia", "Japan"};

        population = new String[]{"1,354,040,000", "1,210,193,422",
                "315,761,000", "237,641,326", "193,946,886", "182,912,000",
                "170,901,000", "152,518,015", "143,369,806", "127,360,000"};

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < rank.length; i++) {
            WorldPopulation wp = new WorldPopulation(rank[i], country[i],
                    population[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                // String text = mSelectedItems.get(0).toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.filter:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        applyFilter();
                        return true;
                    }
                });
                break;
            case R.id.action_settings:
                return true;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void applyFilter() {

        //mSelectedItems = new ArrayList();
        mSelectedItems.clear();
        // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle(R.string.pick_filter)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(country, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(country[which]);
                                } else if (mSelectedItems.contains(country[which])) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(country[which]);
                                }
                            }
                        })
                        // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            String text = mSelectedItems.get(i).toString().toLowerCase(Locale.getDefault());
                            switch (text) {
                                case "india":{
                                    text = "india";
                                    //temp_button = (Button) findViewById(R.id.India);
                                    //temp_button.setVisibility(View.VISIBLE);
                                    //layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(getApplication().LAYOUT_INFLATER_SERVICE);
                                    Button indiaButton = new Button(mcontext);
                                    indiaButton.setText("INDIA");
                                    indiaButton.setVisibility(View.VISIBLE);
                                    ImageView cross = new ImageView(mcontext);
                                    cross.setImageBitmap(BitmapFactory.decodeResource(mcontext.getResources(), R.mipmap.ic_cross));
                                    cross.setVisibility(View.VISIBLE);
                                    LinearLayout linearLayout = new LinearLayout(mcontext);
                                    linearLayout.addView(indiaButton,100,50);
                                    linearLayout.addView(cross,50,50);
                                    LinearLayout ll = (LinearLayout)findViewById(R.id.dynamic_layout);
                                    //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    //ll.setOrientation(LinearLayout.HORIZONTAL);
                                    //ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                    ll.addView(linearLayout);
                                    adapter.filter(text);
                                    break;}
                                case "japan":{
                                   // text = "japan";
                                   // temp_button = (Button) findViewById(R.id.japan);
                                    //temp_button.setVisibility(View.VISIBLE);
                                    //layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(getApplication().LAYOUT_INFLATER_SERVICE);
                                    Button japan = new Button(mcontext);
                                    japan.setText("JAPAN");
                                    japan.setVisibility(View.VISIBLE);
                                    ImageView cross = new ImageView(mcontext);
                                    cross.setImageBitmap(BitmapFactory.decodeResource(mcontext.getResources(), R.mipmap.ic_cross));
                                    cross.setVisibility(View.VISIBLE);
                                    LinearLayout linearLayout = new LinearLayout(mcontext);
                                    linearLayout.addView(japan, 100, 50);
                                    linearLayout.addView(cross, 50, 50);
                                    LinearLayout ll = (LinearLayout)findViewById(R.id.dynamic_layout);
                                    //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    //ll.setOrientation(LinearLayout.HORIZONTAL);
                                    ll.addView(linearLayout);
                                    adapter.filter(text);
                                    break;}
                            }
                        }
                        //if (text.equals("0")) {
                        //}
                        //adapter.filter(text);
                        //Toast.makeText(MainActivity.this, "Filter applied: " +mSelectedItems.size()+temp_button.getVisibility(), Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(MainActivity.this, "Filter not applied", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
        //builder.create();
    }


}
