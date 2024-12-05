package com.example.konyvesmobil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etTitle = findViewById(R.id.etTitle);
        EditText etAuthor = findViewById(R.id.etAuthor);
        EditText etPages = findViewById(R.id.etPages);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView bookListView = findViewById(R.id.bookListView);

        bookList = new ArrayList<>();
        bookAdapter = new BookAdapter(this, bookList);
        bookListView.setAdapter(bookAdapter);

        btnAdd.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String author = etAuthor.getText().toString();
            String pagesStr = etPages.getText().toString();

            if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int pages;
            try {
                pages = Integer.parseInt(pagesStr);
                if (pages < 50) {
                    Toast.makeText(MainActivity.this, "Az oldalszám nem lehet kevesebb 50-nél!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Az oldalszámnak számnak kell lennie!", Toast.LENGTH_SHORT).show();
                return;
            }

            Book newBook = new Book(title, author, pages);
            bookList.add(newBook);
            bookAdapter.notifyDataSetChanged();

            etTitle.setText("");
            etAuthor.setText("");
            etPages.setText("");
        });

        bookListView.setOnItemClickListener((parent, view, position, id) -> {
            Book selectedBook = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("book", selectedBook);
            startActivity(intent);
        });
    }
}
