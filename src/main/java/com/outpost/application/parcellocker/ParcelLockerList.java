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

    public static int lengthParcelLocker(List<ParcelLocker> parcelLockers){
        return parcelLockers.size();
    }

    public static Boolean checkNameParcelLocker(String name, List<ParcelLocker> parcelLockerList){
        for (ParcelLocker test: parcelLockerList) {
            if (test.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static Boolean checkIdParcelLocker(String name, List<ParcelLocker> parcelLockerList){
        for (ParcelLocker test: parcelLockerList) {
            if (test.getID().equals(name)){
                return true;
            }
        }
        return false;
    }

//    public ParcelLocker getById(String id){
//        return parcelLockers.stream()
//                .filter(parcelLocker -> parcelLocker.getID().equals(id))
//                .findFirst()
//                .orElseThrow();
//    }




}
