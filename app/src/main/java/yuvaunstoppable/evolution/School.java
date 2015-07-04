package yuvaunstoppable.evolution;

/**
 * Created by Yash on 30-Jun-15.
 */
public class School {
    String scl_name;
    int scl_id;
    public School(){}

    public School(String scl_name, int scl_id){
        this.scl_name=scl_name;
        this.scl_id=scl_id;
    }

    public int getScl_id() {
        return scl_id;
    }

    public String getScl_name() {
        return scl_name;
    }

    public void setScl_id(int scl_id) {
        this.scl_id = scl_id;
    }

    public void setScl_name(String scl_name) {
        this.scl_name = scl_name;
    }
}
