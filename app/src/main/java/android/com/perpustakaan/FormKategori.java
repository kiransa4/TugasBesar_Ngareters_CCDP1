package android.com.perpustakaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.com.perpustakaan.Koneksi.koneksi;

public class FormKategori extends AppCompatActivity {

    EditText nama_kategori;
    Button simpan, edit,hapus;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kategori);

        nama_kategori = findViewById(R.id.nama_kategori);
        simpan = findViewById(R.id.simpan);
        edit = findViewById(R.id.edit);
        hapus = findViewById(R.id.hapus);

        volleySingleton = VolleySingleton.getInstance(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input and send HTTP request to create category
                sendRequest("kategori.php", "Berhasil menambah kategori", "Gagal menambah kategori");
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate input and send HTTP request to update category
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send HTTP request to delete category
            }
        });
    }

    private void sendRequest(String url, final String successMessage, final String errorMessage) {
        StringRequest request = new StringRequest(Request.Method.GET, koneksi + url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(FormKategori.this, successMessage, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(FormKategori.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        volleySingleton.getQueue().add(request);
    }
}

class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue queue;

    private VolleySingleton(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
    }

    private void hapus() {
//        final String ide_ = edid.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.hapus_kategori,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")) {
                            //Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormKategori.this, Kategori.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Koneksi.id_kategori_buku,String.valueOf(id));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void update() {
        final String nm_kategori =  nama_kategori.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.edit_kategori,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")) {
                            //Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormKategori.this, Kategori.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Koneksi.id_kategori_buku, String.valueOf(id));
                params.put(Koneksi.nama_kategori, nm_kategori);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void simpanKategori() {
        final String nm_kategori =  nama_kategori.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.simpankategori,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //untuk menerima respon ketika berhasil / tidak berhasil pada php.
                        if(response.contains("1")) {
//                            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormKategori.this, Kategori.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //berfungsi memberi tahu jika ada kesalahan pada server atau koneksi aplikasi.
                        Toast.makeText(getApplicationContext(), "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //untuk mengirim request pada php
                params.put(Koneksi.nama_kategori, nm_kategori);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
