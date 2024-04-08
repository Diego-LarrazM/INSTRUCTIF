/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.util;

/**
 *
 * @author dlarrazmar
 */
public class EtabHitInstance {
    private Long etabId;
    private Double lat;
    private Double lng;
    private Long nbElevesInstructif;

    public EtabHitInstance() {
    }

    public EtabHitInstance(Long etabId, Double lat, Double lng, Long nbElevesInstructif) {
        this.etabId = etabId;
        this.lat = lat;
        this.lng = lng;
        this.nbElevesInstructif = nbElevesInstructif;
    }

    public Long getEtabId() {
        return etabId;
    }

    public void setEtabId(Long etabId) {
        this.etabId = etabId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Long getNbElevesInstructif() {
        return nbElevesInstructif;
    }

    public void setNbElevesInstructif(Long nbElevesInstructif) {
        this.nbElevesInstructif = nbElevesInstructif;
    }

    @Override
    public String toString() {
        return "EtabHitInstance{" + "etabId=" + etabId + ", lat=" + lat + ", lng=" + lng + ", nbElevesInstructif=" + nbElevesInstructif + '}';
    }
    
    
    
            
}
