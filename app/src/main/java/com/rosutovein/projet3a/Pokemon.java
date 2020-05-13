package com.rosutovein.projet3a;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Pokemon implements Parcelable {

    private String id;
    private String name;
    private List<String> type;
    private String height;
    private String weight;
    private String abilityname;
    private String secondabilityname;
    private String abilitydescription;
    private String secondabilitydescription;
    private String hiddenabilityname;
    private String hiddenabilitydescription;
    private List<String> weakness;
    private List<String> resistance;
    private int statpv;
    private int statatt;
    private int statdef;
    private int statattspe;
    private int statdefspe;
    private int statvit;
    private String artwork;
    private String sprite;

    public Pokemon(){

    }

    public Pokemon(String id, String name, List<String> type, String height, String weight, String abilityname, String secondabilityname, String abilitydescription,
                   String secondabilitydescription, String hiddenabilityname, String hiddenabilitydescription, List<String> weakness, List<String> resistance, int statpv,
                   int statatt, int statdef, int statattspe, int statdefspe, int statvit, String artwork, String sprite){
        this.id = id;
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.abilityname = abilityname;
        this.secondabilityname = secondabilityname;
        this.abilitydescription = abilitydescription;
        this.secondabilitydescription = secondabilitydescription;
        this.hiddenabilityname = hiddenabilityname;
        this.hiddenabilitydescription = hiddenabilitydescription;
        this.weakness = weakness;
        this.resistance = resistance;
        this.statpv = statpv;
        this.statatt = statatt;
        this.statdef = statdef;
        this.statattspe = statattspe;
        this.statdefspe = statdefspe;
        this.statvit = statvit;
        this.artwork = artwork;
        this.sprite = sprite;
    }

    protected Pokemon(Parcel in) {
        id = in.readString();
        name = in.readString();
        this.type = new ArrayList<String>();
        in.readList(this.type, String.class.getClassLoader());
        height = in.readString();
        weight = in.readString();
        abilityname = in.readString();
        secondabilityname = in.readString();
        abilitydescription = in.readString();
        secondabilitydescription = in.readString();
        hiddenabilityname = in.readString();
        hiddenabilitydescription = in.readString();
        this.weakness = new ArrayList<String>();
        in.readList(this.weakness, String.class.getClassLoader());
        this.resistance = new ArrayList<String>();
        in.readList(this.resistance, String.class.getClassLoader());
        statpv = in.readInt();
        statatt = in.readInt();
        statdef = in.readInt();
        statattspe = in.readInt();
        statdefspe = in.readInt();
        statvit = in.readInt();
        artwork = in.readString();
        sprite = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getType() {
        return type;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getAbilityname() {
        return abilityname;
    }

    public String getSecondabilityname() {
        return secondabilityname;
    }

    public String getAbilitydescription() {
        return abilitydescription;
    }

    public String getSecondabilitydescription() {
        return secondabilitydescription;
    }

    public String getHiddenabilityname() {
        return hiddenabilityname;
    }

    public String getHiddenabilitydescription() {
        return hiddenabilitydescription;
    }

    public List<String> getWeakness() {
        return weakness;
    }

    public List<String> getResistance() {
        return resistance;
    }

    public int getStatpv() {
        return statpv;
    }

    public int getStatatt() {
        return statatt;
    }

    public int getStatdef() {
        return statdef;
    }

    public int getStatattspe() {
        return statattspe;
    }

    public int getStatdefspe() {
        return statdefspe;
    }

    public int getStatvit() {
        return statvit;
    }

    public String getArtwork() {
        return artwork;
    }

    public String getSprite() {
        return sprite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAbilityname(String abilityname) {
        this.abilityname = abilityname;
    }

    public void setSecondabilityname(String secondabilityname) {
        this.secondabilityname = secondabilityname;
    }

    public void setAbilitydescription(String abilitydescription) {
        this.abilitydescription = abilitydescription;
    }

    public void setSecondabilitydescription(String secondabilitydescription) {
        this.secondabilitydescription = secondabilitydescription;
    }

    public void setHiddenabilityname(String hiddenabilityname) {
        this.hiddenabilityname = hiddenabilityname;
    }

    public void setHiddenabilitydescription(String hiddenabilitydescription) {
        this.hiddenabilitydescription = hiddenabilitydescription;
    }

    public void setWeakness(List<String> weakness) {
        this.weakness = weakness;
    }

    public void setResistance(List<String> resistance) {
        this.resistance = resistance;
    }

    public void setStatpv(int statpv) {
        this.statpv = statpv;
    }

    public void setStatatt(int statatt) {
        this.statatt = statatt;
    }

    public void setStatdef(int statdef) {
        this.statdef = statdef;
    }

    public void setStatattspe(int statattspe) {
        this.statattspe = statattspe;
    }

    public void setStatdefspe(int statdefspe) {
        this.statdefspe = statdefspe;
    }

    public void setStatvit(int statvit) {
        this.statvit = statvit;
    }

    public void setArtwork(String artwork) {
        this.artwork = artwork;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeList(type);
        parcel.writeString(height);
        parcel.writeString(weight);
        parcel.writeString(abilityname);
        parcel.writeString(secondabilityname);
        parcel.writeString(abilitydescription);
        parcel.writeString(secondabilitydescription);
        parcel.writeString(hiddenabilityname);
        parcel.writeString(hiddenabilitydescription);
        parcel.writeList(weakness);
        parcel.writeList(resistance);
        parcel.writeInt(statpv);
        parcel.writeInt(statatt);
        parcel.writeInt(statdef);
        parcel.writeInt(statattspe);
        parcel.writeInt(statdefspe);
        parcel.writeInt(statvit);
        parcel.writeString(artwork);
        parcel.writeString(sprite);
    }
}
