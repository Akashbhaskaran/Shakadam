package com.example.akashbhaskaran.car;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Context context;


        CardView cv;
        TextView personName;
        TextView personAge;

        TextView efficiency,personVariant;

        ImageView CarPhoto;




        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cv.setOnClickListener(this);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personVariant = (TextView)itemView.findViewById(R.id.personvar);

            personAge = (TextView)itemView.findViewById(R.id.person_joke);
            efficiency = (TextView)itemView.findViewById(R.id.tv_eff);
            CarPhoto = (ImageView)itemView.findViewById(R.id.iv1);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    Toast.makeText(v.getContext(), "Awaken the Creator Within!!", Toast.LENGTH_SHORT).show();

                }

            });*/
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
           // Toast.makeText(v.getContext(),Integer.toString(position),Toast.LENGTH_SHORT).show();

            Intent i = new Intent(v.getContext(),Car_Specific.class);
            i.putExtra("name",personVariant.getText().toString());
            v.getContext().startActivity(i);
        }
    }

    List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).brand+" "+persons.get(i).title);
        personViewHolder.personVariant.setText(persons.get(i).variant);

        personViewHolder.personAge.setText(persons.get(i).joke+"Nm");
        personViewHolder.efficiency.setText(persons.get(i).eff);
        UrlImageViewHelper.setUrlDrawable(personViewHolder.CarPhoto,persons.get(i).url);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
