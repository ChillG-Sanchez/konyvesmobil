package com.example.konyvesmobil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final List<Book> books;

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        this.context = context;
        this.books = books;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = new LinearLayout(context);
            ((LinearLayout) convertView).setOrientation(LinearLayout.VERTICAL);
            convertView.setPadding(16, 16, 16, 16);

            TextView tvTitle = new TextView(context);
            tvTitle.setId(View.generateViewId());
            tvTitle.setTextSize(18);
            tvTitle.setPadding(0, 0, 0, 8);
            ((LinearLayout) convertView).addView(tvTitle);

            TextView tvAuthor = new TextView(context);
            tvAuthor.setId(View.generateViewId());
            tvAuthor.setTextSize(16);
            tvAuthor.setPadding(0, 0, 0, 8);
            ((LinearLayout) convertView).addView(tvAuthor);

            Button btnDelete = new Button(context);
            btnDelete.setId(View.generateViewId());
            btnDelete.setText("Törlés");
            ((LinearLayout) convertView).addView(btnDelete);

            btnDelete.setOnClickListener(v -> new AlertDialog.Builder(context)
                    .setTitle("Törlés")
                    .setMessage("Biztosan törölni szeretnéd ezt a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show());

            convertView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("book", books.get(position));
                context.startActivity(intent);
            });

            convertView.setTag(new View[]{tvTitle, tvAuthor});
        }

        View[] views = (View[]) convertView.getTag();
        TextView tvTitle = (TextView) views[0];
        TextView tvAuthor = (TextView) views[1];

        Book book = books.get(position);
        tvTitle.setText(book.getTitle());
        tvAuthor.setText("Szerző: " + book.getAuthor());

        return convertView;
    }
}
