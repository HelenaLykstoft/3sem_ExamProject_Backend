package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
@NamedQuery(name = "users.deleteAllRows", query = "DELETE from User")

public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Column(name = "user_name", length = 25)
  private String userName;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "user_pass")
  private String userPass;



  @JoinTable(name = "user_roles", joinColumns = {
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
    @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
  @ManyToMany
  private List<Role> roleList = new ArrayList<>();

  @Column(name = "phone")
  private int phone;

  @Column(name = "job")
  private String job;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "users_rentals",
          joinColumns = @JoinColumn(name = "user_user_name"),
          inverseJoinColumns = @JoinColumn(name = "rentals_id"))
  private List<Rental> rentals = new ArrayList<>();


  // Method that returns roles as strings.
  public List<String> getRolesAsStrings() {
    if (roleList.isEmpty()) {
      return null;
    }
    List<String> rolesAsStrings = new ArrayList<>();
    roleList.forEach((role) -> {
        rolesAsStrings.add(role.getRoleName());
      });
    return rolesAsStrings;
  }

    // Null args constructor
  public User() {}

    // Password is hashed
  public boolean verifyPassword(String pw){
    return BCrypt.checkpw(pw, userPass);
    }

    // Constructor
  public User(String userName, String userPass, int phone, String job) {
    this.userName = userName;
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    this.phone = phone;
    this.job = job;
  }

    // Getters & Setters
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPass() {
    return this.userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());;
  }

  public int getPhone() {
    return phone;
  }

  public void setPhone(int phone) {
    this.phone = phone;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }

  public List<Rental> getRentals() {
    return rentals;
  }

  public void setRentals(List<Rental> rentals) {
    this.rentals = rentals;
  }


    // A setRole method rewritten to add a role to roleList.
  public void addRole(Role userRole) {
    roleList.add(userRole);
  }


}
