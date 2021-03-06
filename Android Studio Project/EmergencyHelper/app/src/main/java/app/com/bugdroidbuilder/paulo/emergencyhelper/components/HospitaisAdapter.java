package app.com.bugdroidbuilder.paulo.emergencyhelper.components;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.com.bugdroidbuilder.paulo.emergencyhelper.R;
import app.com.bugdroidbuilder.paulo.emergencyhelper.model.Hospital;

/**
 * Created by paulo on 19/07/16.
 */
public class HospitaisAdapter extends RecyclerView.Adapter<HospitaisAdapter.MyViewHolder>{


    private List<Hospital> listaHospitais;

    public HospitaisAdapter(List<Hospital> _listaHospitais) {
        this.listaHospitais = _listaHospitais;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hospital, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Hospital hospital = listaHospitais.get(position);
        holder.descricao.setText(hospital.getNome());
    }

    @Override
    public int getItemCount() {
        return listaHospitais.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView descricao;

        public MyViewHolder(View view) {
            super(view);
            descricao = (TextView) view.findViewById(R.id.hospitais_descricao);

        }
    }
}

