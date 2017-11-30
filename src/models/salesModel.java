package models;

/**
 * Created by gedeagas on 23/11/17.
 */
public class salesModel {
    private int idBarang;
    private String namaBarang;
    private Long hargaBarang;
    private int eanBarang;

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Long getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(Long hargaBarang) {
        this.hargaBarang = hargaBarang;
    }

    public int getEanBarang() {
        return eanBarang;
    }

    public void setEanBarang(int eanBarang) {
        this.eanBarang = eanBarang;
    }

    public salesModel(int idBarang, String namaBarang, Long hargaBarang, int eanBarang) {

        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.eanBarang = eanBarang;
    }
}
