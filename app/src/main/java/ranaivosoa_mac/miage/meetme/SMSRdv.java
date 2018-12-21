package ranaivosoa_mac.miage.meetme;

/**
 * Created by mac001 on 14/12/18.
 */

public class SMSRdv {
    private String idSMS;
    private String emetteur;
    private String destinataire;
    private String message;

    public SMSRdv(String idSMS, String emetteur, String destinataire, String message) {
        this.idSMS = idSMS;
        this.emetteur = emetteur;
        this.destinataire = destinataire;
        this.message = message;
    }

    public SMSRdv() {
    }

    public String getIdSMS() {
        return idSMS;
    }

    public void setIdSMS(String idSMS) {
        this.idSMS = idSMS;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        String ret = "De : " + emetteur + "\n"
                + "à : " +  destinataire + "\n"
                + "message : \n"
                + message;
        return ret;
    }
}
