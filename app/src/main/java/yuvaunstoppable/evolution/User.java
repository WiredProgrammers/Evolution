package yuvaunstoppable.evolution;

/**
 * Created by Yash on 30-Jun-15.
 */
public class User {
    String email, type, name;
    public User(){

    }
    public User(String email, String type, String name){
        this.email = email;
        this.type = type;
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setType(String type){
        this.type= type;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getType(){
        return type;
    }
}
