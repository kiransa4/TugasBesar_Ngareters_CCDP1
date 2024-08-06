package android.com.perpustakaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Buku extends AppCompatActivity {

    Button tambah_buku,cari_buku;
    EditText ed_cari_buku;
    RecyclerView rv_buku;
    ArrayList<HashMap<String, String>> tampil = new ArrayList<HashMap<String, String>>();
    //mana class adapter dibuat varibel
    AdapterBuku adapter;
  //  ImageView imgBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);

        tambah_buku = (Button)findViewById(R.id.btn_tambah_buku);
        tambah_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Buku.this, FormBuku.class));
            }
        });

        rv_buku = (RecyclerView)findViewById(R.id.rv_buku);
        rv_buku.setHasFixedSize(true);
        //menngatur rotasi pada tampilan data.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        // tipe oriention ada vertical dan horizontal
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv_buku.setLayoutManager(llm);

        ed_cari_buku = (EditText)findViewById(R.id.ed_cari_buku);
        cari_buku = (Button)findViewById(R.id.btn_cari_buku);
        cari_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cariBuku();
            }
        });


        tampil();
    }

    private void cariBuku() {
        final String cr_bk = ed_cari_buku.getText().toString();
        public abstract class ResponseHandler {
            public void handleResponse(String response) {
                if (response.length() > 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        handleSuccessResponse(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    handleEmptyResponse();
                }
            }
        
            public abstract void handleSuccessResponse(JSONObject jsonObject);
            public abstract void handleEmptyResponse();
            public abstract void handleErrorResponse(VolleyError error);
        }
        
        public class SuccessResponseHandler extends ResponseHandler {
            @Override
            public void handleSuccessResponse(JSONObject jsonObject) {
                // extract data dari JSON
            }
        
            @Override
            public void handleEmptyResponse() {
                Toast.makeText(getApplicationContext(), "Tidak ada", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public void handleErrorResponse(VolleyError error) {
                // tidak digunakan
            }
        }
        
        public class ErrorResponseHandler extends ResponseHandler {
            @Override
            public void handleSuccessResponse(JSONObject jsonObject) {
                // tidak digunakan
            }
        
            @Override
            public void handleEmptyResponse() {
                // tidak digunakan
            }
        
            @Override
            public void handleErrorResponse(VolleyError error) {
                Toast.makeText(Buku.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        }
        
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, 
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ResponseHandler handler = new SuccessResponseHandler();
                    handler.handleResponse(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ResponseHandler handler = new ErrorResponseHandler();
                    handler.handleErrorResponse(error);
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

    private void tampil() {
        public abstract class ResponseHandler {
            public void handleResponse(String response) {
                if (response.length() > 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        handleSuccessResponse(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    handleEmptyResponse();
                }
            }
        
            public abstract void handleSuccessResponse(JSONObject jsonObject);
            public abstract void handleEmptyResponse();
            public abstract void handleErrorResponse(VolleyError error);
        }
        
        public class SuccessResponseHandler extends ResponseHandler {
            @Override
            public void handleSuccessResponse(JSONObject jsonObject) {
                // extract data dari JSON
            }
        
            @Override
            public void handleEmptyResponse() {
                Toast.makeText(getApplicationContext(), "Tidak ada", Toast.LENGTH_SHORT).show();
            }
        
            @Override
            public void handleErrorResponse(VolleyError error) {
                // tidak digunakan
            }
        }
        
        public class ErrorResponseHandler extends ResponseHandler {
            @Override
            public void handleSuccessResponse(JSONObject jsonObject) {
                // tidak digunakan
            }
        
            @Override
            public void handleEmptyResponse() {
                // tidak digunakan
            }
        
            @Override
            public void handleErrorResponse(VolleyError error) {
                Toast.makeText(Buku.this, "Tidak terhubung ke server", Toast.LENGTH_SHORT).show();
            }
        }
        
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, 
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ResponseHandler handler = new SuccessResponseHandler();
                    handler.handleResponse(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ResponseHandler handler = new ErrorResponseHandler();
                    handler.handleErrorResponse(error);
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
