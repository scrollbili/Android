package ranaivosoa_mac.miage.meetme;


import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Ranja on 11/12/18.
 */

public class RdvSQLiteHelper extends SQLiteOpenHelper {

    //Database
    private static final String DATABASE_NAME = "rendez_vous.db";
    private static final int DATABASE_VERSION = 1;

    //Tables
    public static final String TABLE_SENT_RDV = "sentRdvTable";
    public static final String TABLE_INBOX_RDV = "inboxRdvTable";
    public static final String TABLE_DESTINATAIRES = "inboxRdvTable";

    //Colums
    public static final String COLUMN_RDV_ID = "idRdv";
    public static final String COLUMN_PLACE = "lieuRdv";
    public static final String COLUMN_DATE = "dateRdv";
    public static final String COLUMN_CONTACT = "telephone";
    public static final String COLUMN_EMETTEUR = "organisateur";
    public static final String COLUMN_MESSAGE = "message";

    //Commande sql pour la création des tables
    private static final String TABLE_SENT_CREATE = "create table "
            + TABLE_SENT_RDV + "IF NOT EXISTS ("
            + COLUMN_RDV_ID + " text not null"
            + COLUMN_DATE + "text not null"
            + COLUMN_PLACE + "text not null"
            + COLUMN_MESSAGE + "text not null"
            +");";

    private static final String TABLE_INBOX_CREATE = "create table "
            + TABLE_INBOX_RDV + "IF NOT EXISTS ("
            + COLUMN_RDV_ID + " text not null"
            + COLUMN_EMETTEUR + "text not null"
            + COLUMN_DATE + "text not null"
            + COLUMN_PLACE + "text not null"
            + COLUMN_MESSAGE + "text not null"
            +");";

    private static final String TABLE_DESTINATAIRES_CREATE = "create table "
            + TABLE_DESTINATAIRES + "IF NOT EXISTS ("
            + COLUMN_RDV_ID + " integer not null"
            + COLUMN_CONTACT
            +");";

    public RdvSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_SENT_CREATE);
        database.execSQL(TABLE_INBOX_CREATE);
        database.execSQL(TABLE_DESTINATAIRES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RdvSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SENT_RDV);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INBOX_RDV);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DESTINATAIRES);
        onCreate(db);
    }
}
