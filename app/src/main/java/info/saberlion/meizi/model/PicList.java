package info.saberlion.meizi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class PicList{
    @SerializedName("code")
    public int code;
    @SerializedName("msg")
    public String msg;
    @SerializedName("meizi")
    public List<Filename> filename;
}
