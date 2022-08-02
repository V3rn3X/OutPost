package com.outpost.application.parcellocker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParcelLockerList {

    private List<ParcelLocker> parcelLockers;

    public ParcelLockerList(){
        this.parcelLockers = new ArrayList<>();
    }

    public List<ParcelLocker> getParcelLockers(){
        return parcelLockers;
    }

    public void setParcelLockers(List<ParcelLocker> parcelLockers){
        this.parcelLockers = parcelLockers;
    }

    public void deleteParcelLocker(String index){
        parcelLockers.removeIf(t -> t.getName().equals(index));
    }

    public String updateParcelLocker(String index){
        int x = parcelLockers.indexOf(index);
        String iD = parcelLockers.get(x).getID();
        return iD;
    }
}
