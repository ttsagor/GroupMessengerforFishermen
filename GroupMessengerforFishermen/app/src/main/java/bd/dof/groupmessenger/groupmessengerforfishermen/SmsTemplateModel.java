package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
public class SmsTemplateModel {
    private int smsTemplateID;
    private String smsTemplateText;

    public void setSmsTemplateID(int smsTemplateID) {
        this.smsTemplateID = smsTemplateID;
    }

    public void setSmsTemplateText(String smsTemplateText) {
        this.smsTemplateText = smsTemplateText;
    }

    public int getSmsTemplateID() {
        return smsTemplateID;
    }

    public String getSmsTemplateText() {
        return smsTemplateText;
    }
}
