package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by Sagor on 5/23/2017.
 */

public class DiseaseModel {

    private int diseaseID;
    private String diseaseName;
    private String diseaseReason;
    private String diseaseSym;
    private String diseaseVac;
    private String diseasePic;

    public DiseaseModel()
    {

    }
    public DiseaseModel(int diseaseID,String diseaseName,  String diseaseReason, String diseaseSym, String diseaseVac,String diseasePic) {
        this.diseaseName = diseaseName;
        this.diseaseID = diseaseID;
        this.diseaseReason = diseaseReason;
        this.diseaseSym = diseaseSym;
        this.diseaseVac = diseaseVac;
        this.diseasePic = diseasePic;
    }

    public void setDiseasePic(String diseasePic) {
        this.diseasePic = diseasePic;
    }

    public String getDiseasePic() {
        return diseasePic;
    }

    public void setDiseaseID(int diseaseID) {
        this.diseaseID = diseaseID;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public void setDiseaseReason(String diseaseReason) {
        this.diseaseReason = diseaseReason;
    }

    public void setDiseaseSym(String diseaseSym) {
        this.diseaseSym = diseaseSym;
    }

    public void setDiseaseVac(String diseaseVac) {
        this.diseaseVac = diseaseVac;
    }

    public int getDiseaseID() {
        return diseaseID;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDiseaseReason() {
        return diseaseReason;
    }

    public String getDiseaseSym() {
        return diseaseSym;
    }

    public String getDiseaseVac() {
        return diseaseVac;
    }
}
