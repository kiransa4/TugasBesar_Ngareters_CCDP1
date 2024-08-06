package android.com.perpustakaan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterKategori extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static AdapterKategori instance;

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, String>> data;

    private AdapterKategori(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public static AdapterKategori getInstance(Context context, ArrayList<HashMap<String, String>> data) {
        if (instance == null) {
            instance = new AdapterKategori(context, data);
        }
        return instance;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_kategori, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        HashMap<String, String> item = data.get(position);
        viewHolder.tvNama.setText(item.get("nama_kategori"));
        viewHolder.tvDeskripsi.setText(item.get("deskripsi_kategori"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public TextView tvDeskripsi;
        public LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama_kategori);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi_kategori);
            llItem = itemView.findViewById(R.id.ll_item_kategori);
        }
    }
}
