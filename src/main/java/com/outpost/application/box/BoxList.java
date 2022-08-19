package com.outpost.application.box;

import com.outpost.application.parcellocker.ParcelLocker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BoxList {

    private List<Box> box;

    public BoxList(){
        this.box = new ArrayList<>();
    }

    public List<Box> getBox(){
        return box;
    }

    public void setBox(List<Box> box){
        this.box = box;
    }

    public void deleteBox(String index){
        box.removeIf(t -> t.getId().equals(index));
    }

    public static Boolean checkIdBox(String name, List<Box> boxList){
        for (Box test: boxList) {
            if (test.getId().equals(name)){
                return true;
            }
        }
        return false;
    }


}
