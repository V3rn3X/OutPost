package com.outpost.application.box;

import com.outpost.application.parcellocker.ParcelLocker;

public class Box {

    private String id;
    private BoxSize size;
    private String weight;
    private String recipient;
    private String sender;
    private ParcelLocker recipientParcel;
    private ParcelLocker senderParcel;
    private BoxStatus boxStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BoxSize getSize() {
        return size;
    }

    public void setSize(BoxSize size) {

        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ParcelLocker getRecipientParcel() {
        return recipientParcel;
    }

    public void setRecipientParcel(ParcelLocker recipientParcel) {
        this.recipientParcel = recipientParcel;
    }

    public ParcelLocker getSenderParcel() {
        return senderParcel;
    }

    public void setSenderParcel(ParcelLocker senderParcel) {
        this.senderParcel = senderParcel;
    }

    public BoxStatus getBoxStatus() {
        return boxStatus;
    }

    public void setBoxStatus(BoxStatus boxStatus) {
        this.boxStatus = boxStatus;
    }
}
