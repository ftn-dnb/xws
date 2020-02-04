package rs.ac.uns.ftn.xwsservice.dto.response;

import rs.ac.uns.ftn.xwsservice.model.CTKomentar;
import rs.ac.uns.ftn.xwsservice.model.Recenzija;

import java.util.List;

public class ReviewDTO {

    private String id;
    private String preporuka;
    private String htmlFilePath;
    private String pdfFilePath;
    private UserDTO user;
    private List<CTKomentar> komentari;

    public ReviewDTO() {

    }

    public ReviewDTO(Recenzija recenzija) {
        this.id = recenzija.getId();
        this.preporuka = recenzija.getPreporuka();
        this.komentari = recenzija.getKomentar();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.htmlFilePath = "http://localhost:8080/api/reviews/public/html/" + id;
        this.pdfFilePath = "http://localhost:8080/api/reviews/public/pdf/" + id;
    }

    public String getPreporuka() {
        return preporuka;
    }

    public void setPreporuka(String preporuka) {
        this.preporuka = preporuka;
    }

    public List<CTKomentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<CTKomentar> komentari) {
        this.komentari = komentari;
    }

    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    public String getPdfFilePath() {
        return pdfFilePath;
    }

    public void setPdfFilePath(String pdfFilePath) {
        this.pdfFilePath = pdfFilePath;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
