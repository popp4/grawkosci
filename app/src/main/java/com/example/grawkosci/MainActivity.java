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
    // Deklaracja przycisków i TextView
    private Button rzuc; // Przycisk do rzucania kostkami
    private Button resetuj; // Przycisk do resetowania gry

    private TextView kostka1, kostka2, kostka3, kostka4, kostka5; // TextView dla wyników kostek
    private TextView wynik_los, wynik_gry, l_rzut; // TextView dla wyników losowania, gry i liczby rzutów

    private int liczba_rzut = 0; // Licznik liczby rzutów
    private int sum = 0; // Zmienna przechowująca wynik aktualnego losowania
    private int sum_gry = 0; // Zmienna przechowująca łączny wynik gry

    private ArrayList<Integer> wyn_los; // Lista do przechowywania wyników losowania
    private ArrayList<Integer> wyn_gry; // Lista do przechowywania wyników gry

    private Random random1, random2, random3, random4, random5; // Obiekty Random do generowania liczb losowych

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Włączenie trybu edge-to-edge dla pełnoekranowego wyświetlania
        setContentView(R.layout.activity_main); // Ustawienie widoku

        // Inicjalizacja przycisków i TextView
        rzuc = findViewById(R.id.rzuc);
        resetuj = findViewById(R.id.resetuj);
        kostka1 = findViewById(R.id.kostka1);
        kostka2 = findViewById(R.id.kostka2);
        kostka3 = findViewById(R.id.kostka3);
        kostka4 = findViewById(R.id.kostka4);
        kostka5 = findViewById(R.id.kostka5);
        wynik_los = findViewById(R.id.wynik);
        wynik_gry = findViewById(R.id.wynik_gry);
        l_rzut = findViewById(R.id.liczba_rzutow);

        // Inicjalizacja list do przechowywania wyników
        wyn_los = new ArrayList<>();
        wyn_gry = new ArrayList<>();

        // Inicjalizacja generatorów liczb losowych
        random1 = new Random();
        random2 = new Random();
        random3 = new Random();
        random4 = new Random();
        random5 = new Random();

        // Ustawienie akcji na przycisk rzucania kostkami
        rzuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zwiększanie liczby rzutów
                liczba_rzut++;
                l_rzut.setText("Liczba rzutów: " + liczba_rzut);

                // Losowanie wyników dla 5 kostek
                int random_k1 = random1.nextInt(6 - 1 + 1) + 1;
                int random_k2 = random2.nextInt(6 - 1 + 1) + 1;
                int random_k3 = random3.nextInt(6 - 1 + 1) + 1;
                int random_k4 = random4.nextInt(6 - 1 + 1) + 1;
                int random_k5 = random5.nextInt(6 - 1 + 1) + 1;

                // Dodawanie wyników do listy wyn_los
                wyn_los.add(random_k1);
                wyn_los.add(random_k2);
                wyn_los.add(random_k3);
                wyn_los.add(random_k4);
                wyn_los.add(random_k5);

                // Obliczanie sumy wyników powtarzających się liczb
                int ilosc;
                for (int i = 0; i < wyn_los.size(); i++) {
                    ilosc = 0;
                    for (int j = 0; j < wyn_los.size(); j++) {
                        if (wyn_los.get(i) == wyn_los.get(j) && i != j) {
                            ilosc++;
                        }
                    }

                    // Dodawanie wartości do sumy, jeśli liczba się powtarza
                    if (ilosc > 0) {
                        sum += wyn_los.get(i);
                    }
                }

                // Wyświetlenie wyników tego losowania
                wynik_los.setText("Wynik tego losowania: " + String.valueOf(sum));

                // Dodawanie wyniku losowania do sumy gry
                sum_gry += sum;
                wynik_gry.setText("Wynik gry: " + sum_gry);
                
                // Resetowanie sumy dla następnego losowania
                sum = 0;

                // Wyświetlanie wyników na ekranie
                kostka1.setText(String.valueOf(random_k1));
                kostka2.setText(String.valueOf(random_k2));
                kostka3.setText(String.valueOf(random_k3));
                kostka4.setText(String.valueOf(random_k4));
                kostka5.setText(String.valueOf(random_k5));

                // Czyszczenie listy wyników losowania
                wyn_los = new ArrayList<>();
            }
        });

        // Ustawienie akcji na przycisk resetowania
        resetuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Resetowanie wyników na ekranie
                kostka1.setText("?");
                kostka2.setText("?");
                kostka3.setText("?");
                kostka4.setText("?");
                kostka5.setText("?");

                // Resetowanie liczników i wyników gry
                liczba_rzut = 0;
                l_rzut.setText("Liczba rzutów: " + liczba_rzut);
                sum = 0;
                wynik_los.setText("Wynik tego losowania: " + sum);
                sum_gry = 0;
                wynik_gry.setText("Wynik gry: " + sum_gry);
            }
        });
    }
}

}
