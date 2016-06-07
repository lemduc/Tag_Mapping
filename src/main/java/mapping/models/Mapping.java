package mapping.models;

/**
 * Created by EVC_User on 6/7/2016.
 */
public class Mapping {


    private String tag;
    private String keywork;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getKeywork() {
        return keywork;
    }

    public void setKeywork(String keywork) {
        this.keywork = keywork;
    }

    public Mapping(String tag, String keywork){
        this.tag = tag;
        this.keywork = keywork;
    }

}
