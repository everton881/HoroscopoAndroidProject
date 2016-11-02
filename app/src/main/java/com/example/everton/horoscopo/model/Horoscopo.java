package com.example.everton.horoscopo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Everton on 11/09/2016.
 */
public class Horoscopo implements Serializable{


    @SerializedName("horoscope")
    public String messagem;
    @SerializedName("sunsign")
    public String sunsign;
    @SerializedName("date")
    public String data;
  /*  @SerializedName("intensity")
    public String intensity;
    @SerializedName("meta.keywords")
    public String keywords;
    @SerializedName("meta.mood")
    public String mood;*/
    @SerializedName("icone")
    public int icone;



    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }
    public String getSigno() {
        return sunsign;
    }

    public void setSigno(String sunsign) {
        this.sunsign = sunsign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

  /*  public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }*/
    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

}
