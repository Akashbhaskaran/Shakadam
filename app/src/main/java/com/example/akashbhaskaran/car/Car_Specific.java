package com.example.akashbhaskaran.car;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Car_Specific extends AppCompatActivity implements View.OnClickListener {

    public String JSON_STRING;
    String name,variant,model;
    TextView info;
    ImageView car;
    Button video,compare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car__specific);

        video = (Button)findViewById(R.id.btn_video);
        video.setOnClickListener(this);
        compare = (Button)findViewById(R.id.cmp);
        compare.setOnClickListener(this);
        info = (TextView)findViewById(R.id.tv_info);
        car = (ImageView)findViewById(R.id.iv_car);
        name = getIntent().getExtras().getString("name");
        getJSON();
    }
    private void showPatients(){
        JSONObject jsonObject = null;

        String url;
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray("result");


                JSONObject jo = result.getJSONObject(0);

                String name = jo.getString("model");
            model = jo.getString("model");
                String torque = jo.getString("torque");
                String eff = jo.getString("eff")+"Kmpl";
             variant = jo.getString("variant");
            String engine=jo.getString("Engine");
            String gearbox=jo.getString("GearBox");
            String price = jo.getString("price");
            String power=jo.getString("Power(bhp)");
            String airbags=jo.getString("Airbags");
            String abs=jo.getString("ABS");
            String alloys=jo.getString("Alloys");
            String length=jo.getString("Length(cm)");
            String width=jo.getString("Width(cm)");
            String kreb=jo.getString("Kreb");
            String boot=jo.getString("Boot(litres)");
            String fueltank=jo.getString("FuelTank");
            String brand = jo.getString("brand");


            if(model.equals("SUNNY")||model.equals("A4")||
                    model.equals("AMAZE")||model.equals("BRIO")
                    ||model.equals("CIAZ")||model.equals("CITY")||
                    model.equals("DUSTER")||model.equals("ECOSPORT")||
                    model.equals("ELITEI20")||model.equals("ERTIGA")||model.equals("FIGO")
                    ||model.equals("KUV100")||model.equals("KWID")||model.equals("MOBILIO")
                    ||model.equals("POLO")||model.equals("RAPID")||model.equals("SCALA")
                    ||model.equals("VENTO")||model.equals("VERNA")
                    ||model.equals("VITARABREZZA")||model.equals("XYLO"))
                video.setVisibility(View.VISIBLE);
            url = "http://www.shakadam.esy.es/car//pics/"+jo.getString("image");
           // info.setText("Model:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff);
            info.setText("Brand:"+brand+"\nModel:"+name+"\n"+"Variant:"+variant+"\nTorque :"+torque+"\n Efficiency : "+eff+"\n"+"Engine :"+engine+"\n"+
                    "Gear Box :"+gearbox+"\nPrice :"+price+"\n"+"Power :"+power+"\n"+"Airbags :"+airbags+"\n"+"ABS :"+abs+"\n"+"Alloys :"+alloys+"\n"+"Length :"+length+"\n"+
                    "Width :"+width+"\n"+"Kreb Weight :"+kreb+"\n"+"Boot Space :"+boot+"\n"+"FuelTank Capacity :"+fueltank+"\n");
            UrlImageViewHelper.setUrlDrawable(car,url);



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
                loading = ProgressDialog.show(Car_Specific.this,"Fetching Data","Wait...",false,false);
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
                    URL url = new URL("http://www.shakadam.esy.es/car/car_info.php");
                    String urlParams = "name="+name;

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
    public void onClick(View v) {
        if(v.getId()==R.id.btn_video)
        { Intent i;
            i = new Intent(Car_Specific.this,Video_view.class);

            if(model.equals("SUNNY"))
            {

                i.putExtra("video","SUNNY.html");
                startActivity(i);
            }
            else if(model.equals("A4"))
            {

                i.putExtra("video","A4.html");
                startActivity(i);
            }
            else if(model.equals("AMAZE"))
            {

                i.putExtra("video","AMAZE.html");
                startActivity(i);
            }
            else if(model.equals("BRIO"))
            {

                i.putExtra("video","BRIO.html");
                startActivity(i);
            }
            else if(model.equals("CIAZ"))
            {

                i.putExtra("video","CIAZ.html");
                startActivity(i);
            }
            else if(model.equals("CITY"))
            {

                i.putExtra("video","CITY.html");
                startActivity(i);
            }
            else if(model.equals("DUSTER"))
            {

                i.putExtra("video","DUSTER.html");
                startActivity(i);
            }
            else if(model.equals("ECOSPORT"))
            {

                i.putExtra("video","ECOSPORT.html");
                startActivity(i);
            }
            else if(model.equals("ELITEI20"))
            {

                i.putExtra("video","ELITEI20.html");
                startActivity(i);
            }
            else if(model.equals("ERTIGA"))
            {

                i.putExtra("video","ERTIGA.html");
                startActivity(i);
            }
            else if(model.equals("FIGO"))
            {

                i.putExtra("video","FIGO.html");
                startActivity(i);
            }
            else if(model.equals("KUV100"))
            {

                i.putExtra("video","KUV100.html");
                startActivity(i);
            }
            else if(model.equals("KWID"))
            {

                i.putExtra("video","KWID.html");
                startActivity(i);
            }
            else if(model.equals("MOBILIO"))
            {

                i.putExtra("video","MOBILIO.html");
                startActivity(i);
            }
            else if(model.equals("POLO"))
            {

                i.putExtra("video","POLO.html");
                startActivity(i);
            }
            else if(model.equals("RAPID"))
            {

                i.putExtra("video","RAPID.html");
                startActivity(i);
            }
            else if(model.equals("SCALA"))
            {

                i.putExtra("video","SCALA.html");
                startActivity(i);
            }
            else if(model.equals("VENTO"))
            {

                i.putExtra("video","VENTO.html");
                startActivity(i);
            }
            else if(model.equals("VERNA"))
            {

                i.putExtra("video","VERNA.html");
                startActivity(i);
            }
            else if(model.equals("VITARABREZZA"))
            {

                i.putExtra("video","VITARABREZZA.html");
                startActivity(i);
            }
            else if(model.equals("XYLO"))
            {

                i.putExtra("video","XYLO.html");
                startActivity(i);
            }


        }


        else {
                if (Constants.list.size() <= 2) {

                    Constants.list.add(model);
                    Constants.list.add(variant);
                    Toast.makeText(Car_Specific.this, "Added", Toast.LENGTH_LONG).show();
                    if(Constants.list.size()==4)
                    {
                        // Toast.makeText(Car_Specific.this,"List has been reset , only 2 cars can be added",Toast.LENGTH_LONG).show();
                        //Constants.list.clear();
                        Intent i = new Intent(Car_Specific.this,Compare_new.class);
                        startActivity(i);
                    }
                } else if(Constants.list.size()>4) {
                    Toast.makeText(Car_Specific.this, "List has been reset , only 2 cars can be added", Toast.LENGTH_LONG).show();
                    Constants.list.clear();

                }
            }

    }

}
