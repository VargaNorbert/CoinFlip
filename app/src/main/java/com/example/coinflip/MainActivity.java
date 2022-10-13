package com.example.coinflip;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView kep;
    private Button fejGomb;
    private Button irasGomb;
    private TextView dobasokSzama;
    private TextView gyozelemSzama;
    private TextView veresegSzama;
    private Random r;
    private int a;
    private int tipp;
    private int dobasok=0;
    private int gyozelem=0;
    private int vereseg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        fejGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobas();
                tipp =0;

                dobasok++;

                if (tipp==a){
                    gyozelem++;
                }else{
                    vereseg++;
                }

                beallit();

                if (dobasok==5){
                    vege();
                }


            }
        });

        irasGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobas();
                tipp =1;

                dobasok++;


                if (tipp==a){
                    gyozelem++;
                }else{
                    vereseg++;
                }

                beallit();

                if (dobasok==5){
                    vege();
                }
            }
        });
    }

    private void init(){

        kep= findViewById(R.id.kep);
        fejGomb = findViewById(R.id.fejGomb);
        irasGomb = findViewById(R.id.irasGomb);
        dobasokSzama = findViewById(R.id.dobasokSzama);
        gyozelemSzama = findViewById(R.id.gyozelemSzama);
        veresegSzama = findViewById(R.id.veresegSzama);
        r = new Random();


    }

    private void dobas(){
       a = r.nextInt(2);

       if (a==0){
           kep.setImageResource(R.drawable.heads);
           Toast.makeText(this, "A dobás eredménye fej.", Toast.LENGTH_SHORT).show();
       }else{
           kep.setImageResource(R.drawable.tails);
           Toast.makeText(this, "A dobás eredménye írás.", Toast.LENGTH_SHORT).show();
       }

    }

    private void beallit(){
        dobasokSzama.setText("Dobások: "+ dobasok);
        veresegSzama.setText("Vereség: "+ vereseg);
        gyozelemSzama.setText("Győzelem: "+gyozelem);
    }

    private void vege(){

        AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Szeretnél még játszani?");
        alert.setCancelable(false);
        alert.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vereseg=0;
                dobasok=0;
                gyozelem=0;

                beallit();
            }
        });

        alert.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        if (vereseg<gyozelem){
            alert.setTitle("Gratulálok,nyertél!");
            alert.show();
        }else{
            alert.setTitle("Sajnos vesztettél!");
            alert.show();
        }
    }
}