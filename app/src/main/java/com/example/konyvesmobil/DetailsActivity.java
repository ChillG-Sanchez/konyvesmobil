package com.example.konyvesmobil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvAuthor = findViewById(R.id.tvAuthor);
        TextView tvPages = findViewById(R.id.tvPages);
        TextView tvYear = findViewById(R.id.tvYear);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        if (book != null) {
            tvTitle.setText("Cím: " + book.getTitle());
            tvAuthor.setText("Szerző: " + book.getAuthor());
            tvPages.setText("Oldalszám: " + book.getPages());

            Random random = new Random();
            int randomYear = random.nextInt(2024 - 1500) + 1500;
            tvYear.setText("Kiadás éve: " + randomYear);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
