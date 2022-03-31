package Model;

import java.time.LocalDateTime;

public class User {
    Long id;
    String fullName;
    Long age;
    String inn;

    public User ()
    {

    }

    public User ( String fullName, Long age, String inn )
    {
        this.fullName = fullName;
        this.age = age;
        this.inn = inn;
    }

    public User ( Long id, String fullName, Long age, String inn )
    {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.inn = inn;
    }

    @Override
    public String toString ()
    {
        return "User{" +
               "id=" + id +
               ", fullName='" + fullName + '\'' +
               ", age=" + age +
               ", inn='" + inn + '\'' +
               '}';
    }

    public Long getId ()
    {
        return id;
    }

    public void setId ( Long id )
    {
        this.id = id;
    }

    public String getFullName ()
    {
        return fullName;
    }

    public void setFullName ( String fullName )
    {
        this.fullName = fullName;
    }

    public Long getAge ()
    {
        return age;
    }

    public void setAge ( Long age )
    {
        this.age = age;
    }

    public String getInn ()
    {
        return inn;
    }

    public void setInn ( String inn )
    {
        this.inn = inn;
    }
}
