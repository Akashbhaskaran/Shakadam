package com.example.akashbhaskaran.car;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Compare_new extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText p1,p2;
    String car1_model,car2_model,car1_var,car2_var;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SimpleCursorAdapter myAdapter;
    ArrayList list;
    public String JSON_STRING;
    ImageView car1,car2;
    TextView info1,info2;
    SearchView searchView = null;
    private String[] strArrData = {"No Suggestions"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        car1= (ImageView)findViewById(R.id.iv_car1);
        car2= (ImageView)findViewById(R.id.iv_car2);
        info1 = (TextView)findViewById(R.id.textView4); info2 = (TextView)findViewById(R.id.textView5);


        list = new ArrayList();
        list = Constants.list;

        if(list.size()<3)
            Toast.makeText(Compare_new.this,"Add more cars to compare",Toast.LENGTH_LONG).show();
        else
        {
            car1_model = String.valueOf(list.get(0));
            car1_var = String.valueOf(list.get(1));
            car2_model = String.valueOf(list.get(2));
            car2_var = String.valueOf(list.get(3));
          //  Toast.makeText(Compare_new.this,car1_model+car1_var+car2_model+car2_var,Toast.LENGTH_LONG).show();
            getJSON();
            getJSON1();
        }

        final String[] from = new String[] {"Model"};
        final int[] to = new int[] {android.R.id.text1};

        // setup SimpleCursorAdapter
        myAdapter = new SimpleCursorAdapter(Compare_new.this, android.R.layout.simple_spinner_dropdown_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        // Fetch data from mysql table using AsyncTask
        new AsyncFetch().execute();

        p1 = (EditText)findViewById(R.id.p1);
        p2 = (EditText)findViewById(R.id.p2);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void showPatients(){
        JSONObject jsonObject = null;

        String url;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");


            JSONObject jo = result.getJSONObject(0);

            String name = jo.getString("model");
            String torque = jo.getString("torque");
            String eff = jo.getString("eff")+"Kmpl";
           String variant = jo.getString("variant");
            String engine=jo.getString("Engine");
            String gearbox=jo.getString("GearBox");
            String power=jo.getString("Power(bhp)");
            String airbags=jo.getString("Airbags");
            String abs=jo.getString("ABS");
            String price = jo.getString("price");
            String alloys=jo.getString("Alloys");
            String length=jo.getString("Length(cm)");
            String width=jo.getString("Width(cm)");
            String kreb=jo.getString("Kreb");
            String boot=jo.getString("Boot(litres)");
            String fueltank=jo.getString("FuelTank");
            String brand = jo.getString("brand");


            url = "http://www.shakadam.esy.es/car/pics/"+jo.getString("image");
            // info.setText("Model:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff);
            info1.setText("Brand:"+brand+"\nModel:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff+"\n"+"Engine :"+engine+"\n"+
                    "Gear Box :"+gearbox+"\nPrice :"+price+"\n"+"Power :"+power+"\n"+"Airbags :"+airbags+"\n"+"ABS :"+abs+"\n"+"Alloys :"+alloys+"\n"+"Length :"+length+"\n"+
                    "Width :"+width+"\n"+"Kreb Weight :"+kreb+"\n"+"Boot Space :"+boot+"\n"+"FuelTank Capacity :"+fueltank+"\n");
            UrlImageViewHelper.setUrlDrawable(car1,url);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void showPatients1(){
        JSONObject jsonObject = null;

        String url;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");


            JSONObject jo = result.getJSONObject(0);
            String brand = jo.getString("brand");
            String name = jo.getString("model");
            String torque = jo.getString("torque");
            String eff = jo.getString("eff")+"Kmpl";
            String variant = jo.getString("variant");
            String engine=jo.getString("Engine");
            String gearbox=jo.getString("GearBox");
            String power=jo.getString("Power(bhp)");
            String airbags=jo.getString("Airbags");
            String abs=jo.getString("ABS");
            String alloys=jo.getString("Alloys");
            String length=jo.getString("Length(cm)");
            String width=jo.getString("Width(cm)");
            String kreb=jo.getString("Kreb");
            String boot=jo.getString("Boot(litres)");
            String fueltank=jo.getString("FuelTank");
            String price = jo.getString("price");


            url = "http://www.shakadam.esy.es/car/pics/"+jo.getString("image");
            // info.setText("Model:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff);
            info2.setText("Brand:"+brand+"\nModel:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff+"\n"+"Engine :"+engine+"\n"+
                    "Gear Box :"+gearbox+"\n"+"\nPrice :"+price+"Power :"+power+"\n"+"Airbags :"+airbags+"\n"+"ABS :"+abs+"\n"+"Alloys :"+alloys+"\n"+"Length :"+length+"\n"+
                    "Width :"+width+"\n"+"Kreb Weight :"+kreb+"\n"+"Boot Space :"+boot+"\n"+"FuelTank Capacity :"+fueltank+"\n");
            UrlImageViewHelper.setUrlDrawable(car2,url);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Compare_new.this,"Fetching Data","Wait...",false,false);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPatients();

            }

            @Override
            protected String doInBackground(Void... params) {

                String data="";
                int tmp;

                try {
                    URL url = new URL("http://www.shakadam.esy.es/car/car_compare1.php");
                    String urlParams = "name="+car1_model+"&var="+car1_var;

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    while((tmp=is.read())!=-1){
                        data+= (char)tmp;
                    }

                    is.close();
                    httpURLConnection.disconnect();

                    return data;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: "+e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception: "+e.getMessage();
                }

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    private void getJSON1(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Compare_new.this,"Fetching Data","Wait...",false,false);
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPatients1();

            }

            @Override
            protected String doInBackground(Void... params) {

                String data="";
                int tmp;

                try {
                    URL url = new URL("http://www.shakadam.esy.es/car/car_compare2.php");
                    String urlParams = "name="+car2_model+"&car1_var="+car2_var;

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    while((tmp=is.read())!=-1){
                        data+= (char)tmp;
                    }

                    is.close();
                    httpURLConnection.disconnect();

                    return data;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: "+e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception: "+e.getMessage();
                }

            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) Compare_new.this.getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(Compare_new.this.getComponentName()));
            searchView.setIconified(true);
            searchView.setQueryHint("Search By model");
            searchView.setSuggestionsAdapter(myAdapter);
            // Getting selected (clicked) item suggestion
            searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionClick(int position) {

                    // Add clicked text to search box
                    CursorAdapter ca = searchView.getSuggestionsAdapter();
                    Cursor cursor = ca.getCursor();
                    cursor.moveToPosition(position);
                    searchView.setQuery(cursor.getString(cursor.getColumnIndex("Model")),false);
                    return true;
                }

                @Override
                public boolean onSuggestionSelect(int position) {
                    return true;
                }
            });
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),Search_result.class);

                    i.putExtra("model",s);

                    startActivity(i);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {

                    // Filter data
                    final MatrixCursor mc = new MatrixCursor(new String[]{ BaseColumns._ID, "Model" });
                    for (int i=0; i<strArrData.length; i++) {
                        if (strArrData[i].toLowerCase().startsWith(s.toLowerCase()))
                            mc.addRow(new Object[] {i, strArrData[i]});
                    }
                    myAdapter.changeCursor(mc);
                    return false;
                }
            });
        }

        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent i = new Intent(Compare_new.this,MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(Compare_new.this,Search_Brand.class);
            startActivity(i);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(Compare_new.this,Compare.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onNewIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }

            // User entered text and pressed search button. Perform task ex: fetching data from database and display



        }
    }

    // Create class AsyncFetch
    public class AsyncFetch extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(Compare_new.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides or your JSON file address
                url = new URL("http://www.shakadam.esy.es/car/fetchall.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we receive data
                conn.setDoOutput(true);
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {
                    return ("Connection error");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread
            ArrayList<String> dataList = new ArrayList<String>();
            pdLoading.dismiss();


            if (result.equals("no rows")) {

                // Do some action if no data from database

            } else {

                try {

                    JSONArray jArray = new JSONArray(result);

                    // Extract data from json and store into ArrayList
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("Model"));
                    }

                    strArrData = dataList.toArray(new String[dataList.size()]);

                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately
                    Toast.makeText(Compare_new.this, e.toString(), Toast.LENGTH_LONG).show();
                    Toast.makeText(Compare_new.this, result.toString(), Toast.LENGTH_LONG).show();
                }

            }

        }
    }
    }
