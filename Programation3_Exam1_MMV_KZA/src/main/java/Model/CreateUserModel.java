package Model;

public class CreateUserModel {
    
    private int identification;
    private String username, email, status;
    
    public CreateUserModel(){}
    
    public CreateUserModel(int id){
        this.identification=id;
    }

    public CreateUserModel(int identification, String username, String email, String status) {
        this.identification = identification;
        this.username = username;
        this.email = email;
        this.status = status;
    }
    
    public void setIdentification(int identification_){this.identification = identification_;}
    public int getIdentification(){return identification;}
    
    public void setUsername(String username_){this.username = username_;}
    public String getUsername(){return username;}
    
    public void setEmail(String email_){this.email = email_;}
    public String getEmail(){return email;}
    
    public void setStatus(String status_){this.status = status_;}
    public String getStatus(){return status;}
}
