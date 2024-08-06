package android.com.perpustakaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView kategori, buku, pinjam, pinjam_buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        kategori = (CardView)findViewById(R.id.btnKategori);
        buku = (CardView)findViewById(R.id.btnBuku);
        pinjam = (CardView)findViewById(R.id.btnPeminjaman);
        pinjam_buku = (CardView)findViewById(R.id.btn_pinjam_buku);

        // Buat interface untuk observer
public interface CardViewObserver {
    void onCardViewClicked(CardView cardView);
}

// Buat kelas yang mengimplementasikan observer
public class CardViewClickListener implements CardViewObserver {
    @Override
    public void onCardViewClicked(CardView cardView) {
        // Lakukan aksi ketika card view di-klik
        if (cardView.getId() == R.id.btnKategori) {
            startActivity(new Intent(MainActivity.this, Kategori.class));
        } else if (cardView.getId() == R.id.btnBuku) {
            startActivity(new Intent(MainActivity.this, Buku.class));
        }
    }
}

// Daftarkan observer pada card view
kategori.setOnClickListener(new CardViewClickListener());
buku.setOnClickListener(new CardViewClickListener());

        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Peminjaman.class));
            }
        });

        pinjam_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pinjam.class));
            }
        });
    }
}
