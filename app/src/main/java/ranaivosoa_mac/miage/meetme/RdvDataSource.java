package ranaivosoa_mac.miage.meetme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;

import java.util.ArrayList;

/**
 * Created by Ranja on 11/12/18.
 */

public class RdvDataSource {
    // Champs de la base de données
    private SQLiteDatabase database;
    private RdvSQLiteHelper dbHelper;
    private String[] allColumns = {RdvSQLiteHelper.COLUMN_RDV_ID, RdvSQLiteHelper.COLUMN_PLACE,RdvSQLiteHelper.COLUMN_DATE,RdvSQLiteHelper.COLUMN_CONTACT,RdvSQLiteHelper.COLUMN_EMETTEUR,RdvSQLiteHelper.COLUMN_MESSAGE };

    public RdvDataSource(Context context){
        dbHelper = new RdvSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertSentRendezVous(String rdvId, String place, String date, String message) {
        ContentValues values = new ContentValues();
        values.put(RdvSQLiteHelper.COLUMN_RDV_ID, rdvId);
        values.put(RdvSQLiteHelper.COLUMN_PLACE, place);
        values.put(RdvSQLiteHelper.COLUMN_DATE, date);
        values.put(RdvSQLiteHelper.COLUMN_MESSAGE, message);
        return database.insert(RdvSQLiteHelper.TABLE_SENT_RDV,null,values);
    }

    public long insertInboxRendezVous(String rdvId, String place, String date, String emetteur, String message) {
        ContentValues values = new ContentValues();
        values.put(RdvSQLiteHelper.COLUMN_RDV_ID, rdvId);
        values.put(RdvSQLiteHelper.COLUMN_PLACE, place);
        values.put(RdvSQLiteHelper.COLUMN_DATE, date);
        values.put(RdvSQLiteHelper.COLUMN_EMETTEUR, emetteur);
        values.put(RdvSQLiteHelper.COLUMN_MESSAGE, message);
        return database.insert(RdvSQLiteHelper.TABLE_INBOX_RDV,null,values);
    }

    public long insertDestinatairesRdv(String rdvId, String contact) {
        ContentValues values = new ContentValues();
        values.put(RdvSQLiteHelper.COLUMN_RDV_ID, rdvId);
        values.put(RdvSQLiteHelper.COLUMN_CONTACT, contact);
        return database.insert(RdvSQLiteHelper.TABLE_DESTINATAIRES,null,values);
    }

    public List<SMSRdv> getAllSentRdv() {
        List<SMSRdv> listRdv = new ArrayList<SMSRdv>();

        Cursor cursor = database.query(RdvSQLiteHelper.TABLE_SENT_RDV,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SMSRdv smsRdv = cursorToSMS(cursor);
            listRdv.add(smsRdv);
            cursor.moveToNext();
        }
        //fermeture du curseur
        cursor.close();
        return listRdv;
    }

    private SMSRdv cursorToSMS(Cursor cursor) {
        SMSRdv smsRdv = new SMSRdv();

        smsRdv.setDestinataire("besoind'une jointure");
        smsRdv.setEmetteur("moi");
        smsRdv.setMessage(cursor.getString(3));
        smsRdv.setIdSMS(cursor.getString(0)+"destinataire");
        return smsRdv;
    }



    //public RendezVous getAllSentRendezVous(){}
    //public RendezVous getInboxRendezVous(){}

}

