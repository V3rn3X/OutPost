package com.outpost.application.box;

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
        int a = box.indexOf(index);
        box.remove(a);
    }

}
