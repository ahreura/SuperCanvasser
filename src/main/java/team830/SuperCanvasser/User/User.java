package team830.SuperCanvasser.User;

import lombok.Data;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    @Indexed(unique=true)
    private String email;
    private String pwd;
    private String firstName;
    private String lastName;
    private List<Role> role;

    public User(){}

    public User(String email, String pwd){
        this.email = email;
        this.pwd = pwd;
    }

    public User(String id, String email, String pwd, String firstName, String lastName, List<Role> role) {
        this._id = id;
        this.email = email;
        this.pwd = pwd;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public static boolean validatePwd(String requestedPwd, String pwd){ return requestedPwd.equals(pwd); }

    public boolean hasRole(Role tempRole){
        for (Role r: role) {
            if(r.equals(tempRole)) return true;
        }
        return false;
    }

}
