package com.example.grawkosci;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.ArrayList;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button rzuc;
    private Button resetuj;

    private TextView kostka1;
    private TextView kostka2;
    private TextView kostka3;
    private TextView kostka4;
    private TextView kostka5;

    private TextView wynik_los;
    private TextView wynik_gry;
    private TextView l_rzut;
    private int liczba_rzut=0;
    private int sum=0;
    private int sum_gry=0;
    private ArrayList<Integer> wyn_los;
    private ArrayList<Integer> wyn_gry;

    private Random random1;
    private Random random2;
    private Random random3;
    private Random random4;
    private Random random5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rzuc = findViewById(R.id.rzuc);
        resetuj=findViewById(R.id.resetuj);

        kostka1=findViewById(R.id.kostka1);
        kostka2=findViewById(R.id.kostka2);
        kostka3=findViewById(R.id.kostka3);
        kostka4=findViewById(R.id.kostka4);
        kostka5=findViewById(R.id.kostka5);

        wynik_los=findViewById(R.id.wynik);
        wynik_gry=findViewById(R.id.wynik_gry);
        l_rzut=findViewById(R.id.liczba_rzutow);
        wyn_los=new ArrayList<>();
        wyn_gry= new ArrayList<>();

        random1=new Random();
        random2=new Random();
        random3=new Random();
        random4=new Random();
        random5=new Random();

        rzuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                liczba_rzut++;
                l_rzut.setText("Liczba rzutów: "+liczba_rzut);

                int random_k1=random1.nextInt(6-1+1)+1;
                int random_k2=random2.nextInt(6-1+1)+1;
                int random_k3=random3.nextInt(6-1+1)+1;
                int random_k4=random4.nextInt(6-1+1)+1;
                int random_k5=random5.nextInt(6-1+1)+1;

                wyn_los.add(random_k1);
                wyn_los.add(random_k2);
                wyn_los.add(random_k3);
                wyn_los.add(random_k4);
                wyn_los.add(random_k5);

                Log.d("wynik los",String.valueOf(wyn_los));

                int ilosc;

                for(int i=0;i<wyn_los.size();i++){
                    ilosc=0;
                    for (int j = 0; j < wyn_los.size(); j++) {
                        if (wyn_los.get(i)==wyn_los.get(j) && i != j) {
                            ilosc++;
                        }
                    }

                    if (ilosc > 0) {
                        sum += wyn_los.get(i);
                    }

                }


                wynik_los.setText("Wynik tego losowania: "+String.valueOf(sum));

                sum_gry+=sum;
                Log.d("suma gry:",String.valueOf(sum_gry));

                wynik_gry.setText("Wynik gry: "+sum_gry);
                sum=0;


                kostka1.setText(String.valueOf(random_k1));
                kostka2.setText(String.valueOf(random_k2));
                kostka3.setText(String.valueOf(random_k3));
                kostka4.setText(String.valueOf(random_k4));
                kostka5.setText(String.valueOf(random_k5));


                wyn_los=new ArrayList<>();
            }
        });
        resetuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kostka1.setText("?");
                kostka2.setText("?");
                kostka3.setText("?");
                kostka4.setText("?");
                kostka5.setText("?");

                liczba_rzut=0;
                l_rzut.setText("Liczba rzutów: "+liczba_rzut);
                sum=0;
                wynik_los.setText("Wynik tego losowania: "+sum);
                sum_gry=0;
                wynik_gry.setText("Wynik gry: "+sum_gry);
            }
        });
    }
}