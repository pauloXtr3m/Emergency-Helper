package app.com.bugdroidbuilder.paulo.emergencyhelper.controler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.com.bugdroidbuilder.paulo.emergencyhelper.model.Hospital;

/**
 * Created by pedro on 17/06/16.
 */
public class FireControler {

    private FirebaseDatabase fireDb = FirebaseDatabase.getInstance();

    public Hospital getHospital(String id) {

        HospitalListener listener = new HospitalListener();
        DatabaseReference captain = fireDb.getReference().child("Hospitais").child(id);

        Log.d("ICEDB", "Ref : " + captain.toString());

        captain.addValueEventListener(listener);
        return (listener.getInstance());
    }

    public void callHospital(Hospital hospital, Context context) {

        try {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + hospital.getTelefone()));
            context.startActivity(callIntent);
        }catch (SecurityException e){
            Log.d("ICEDB", "Erro de chamada");
        }
    }

    public MarkerOptions getHospitalMark(Hospital hospital){

        LatLng position = new LatLng(hospital.getLatitude(), hospital.getLongitude());
        MarkerOptions mark = new MarkerOptions()
                .position(position)
                .title(hospital.getNome())
                .snippet(hospital.getEndereco() + "\nTel: " + hospital.getTelefone())
                .icon(BitmapDescriptorFactory.fromFile("iconGrade_" + hospital.getNota()));

        return(mark);
    }

}