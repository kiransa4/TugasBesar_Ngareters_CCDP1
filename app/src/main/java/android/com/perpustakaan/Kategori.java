package android.com.perpustakaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue queue;

    private VolleySingleton(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getQueue() {
        return queue;
    }
}

public class Kategori extends AppCompatActivity {

    Button tambah, cari;
    EditText edtCari;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HashMap<String, String>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);

        tambah = findViewById(R.id.tambah);
        cari = findViewById(R.id.cari);
        edtCari = findViewById(R.id.edtCari);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membuatPermintaan();
            }
        });
    }

    private void membuatPermintaan() {
        String url = "https://example.com/api/kategori";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("id", jsonObject.getString("id"));
                        map.put("nama", jsonObject.getString("nama"));
                        data.add(map);
                    }
                    adapter = new AdapterKategori(data);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Kategori.this, "Gagal membuat permintaan", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(this).getQueue().add(request);
    }
}

    

    private void cariKategori() {
        final String cr = pencarian.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.cari_kategori,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")) {
                            try {
                                JSONObject jsonObject;
                                jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("Hasil");
                                tampil.clear();
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject c = result.getJSONObject(i);
                                    String id = c.getString(Koneksi.id_kategori_buku);
                                    String nama = c.getString(Koneksi.nama_kategori);
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put(Koneksi.id_kategori_buku, id);
                                    map.put(Koneksi.nama_kategori, nama);
                                    tampil.add(map);
                                }
                                adapter=new AdapterKategori(Kategori.this,tampil);
                                rv_kategori.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //berfungsi memberi tahu jika ada kesalahan pada server atau koneksi aplikasi.
                        Toast.makeText(Kategori.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Koneksi.nama_kategori,cr);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void tampil() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Koneksi.tampil_kategori,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("1")) {
                            try {
                                JSONObject jsonObject;
                                jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("Hasil");
                                tampil.clear();
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject c = result.getJSONObject(i);
                                    String id = c.getString(Koneksi.id_kategori_buku);
                                    String nama = c.getString(Koneksi.nama_kategori);
                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put(Koneksi.id_kategori_buku, id);
                                    map.put(Koneksi.nama_kategori, nama);
                                    tampil.add(map);
                                }
                                adapter=new AdapterKategori(Kategori.this,tampil);
                                rv_kategori.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //berfungsi memberi tahu jika ada kesalahan pada server atau koneksi aplikasi.
                        Toast.makeText(Kategori.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
