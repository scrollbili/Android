package ranaivosoa_mac.miage.meetme;

import android.icu.text.SimpleDateFormat;

//import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Ranja on 07/11/2018.
 */

public class RendezVous {

    String MapsURL = "http://www.google.com/maps/place/";

    private String idRdv;
    private ArrayList<Contact> destinataires;
    private Calendar dateRdv;
    private String lieu; //En LatLng après
    private String clickablePlace;
    private String message;

    //Constructors
    public RendezVous(){}

    public RendezVous(String id,ArrayList<Contact> _destinataires,Calendar _cDate,String _lieu){
        destinataires = _destinataires;
        dateRdv = _cDate;
        lieu =  _lieu;
        clickablePlace = MapsURL + _lieu.toString();
        idRdv = id;
        setMessage(createMessage());

    }

    public RendezVous(ArrayList<Contact> _destinataires,Calendar _cDate,String _lieu){
        destinataires = _destinataires;
        dateRdv = _cDate;
        lieu =  _lieu;
        clickablePlace = MapsURL + _lieu.toString();
        idRdv = _cDate.toString() + _lieu.toString();
        setMessage(createMessage());
    }

    //Getters
    public Calendar getDateRdv() {
        return dateRdv;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDestString() {
        Iterator it = this.destinataires.iterator();
        String g = "";
        while(it.hasNext()) {
            g = g + it.next().toString();
        }
        return g;
    }

    public ArrayList<Contact> getDestinataires() {
        return destinataires;
    }

    public String getId() {
        return  this.idRdv;
    }

    public String getMessage() {
        return message;
    }

    public String getClickablePlace() {
        return clickablePlace;
    }
    //Setter
    public void setIdRdv (String id){idRdv = id;}

    public void setDateRdv(Calendar cDate){ dateRdv = cDate;}

    public void setDestinataires(ArrayList<Contact> destinataires) {
        this.destinataires = destinataires;
    }

    public void setClickablePlace(String clickablePlace) {
        this.clickablePlace = clickablePlace;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    //Other methods
    public String getDateAsString(){
        int mYear = dateRdv.get(Calendar.YEAR);
        int mMonth = dateRdv.get(Calendar.MONTH);
        int mDay = dateRdv.get(Calendar.DAY_OF_MONTH);
        int mHour = dateRdv.get(Calendar.HOUR_OF_DAY);
        int mMinute = dateRdv.get(Calendar.MINUTE);
        String sepDate = "-";
        String sepHour = ":";
        String str = mDay+sepDate+mMonth+sepDate+mYear+" "+mHour+sepHour+mMinute;
        return str;
    }

    public void setDatefromString(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-aaaa HH:mm ", Locale.FRANCE);
        //dateRdv.setTime(sdf.parse(date));
    }

    public String createMessage(){
        String str = "Rdv le " + getDateAsString() + " à " + getLieu();
        return str;
    }


}

