package plusplus.demo.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import plusplus.demo.entity.DemoConnection;
import plusplus.demo.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/prefix")
public class RestfulController {
    @PostMapping(value = "/user")
    public void insertUser(@RequestBody UserInserted userInserted) {
        String sql = "INSERT INTO USER (username,password,fullname,email,address,phone) VALUE ('" + userInserted.getUsername() + "','"
                + userInserted.getPassword() + "','" + userInserted.getFullname() + "','"
                + userInserted.getEmail() + "','" + userInserted.getAddress() + "','" + userInserted.getPhone() + "');";
        try {
            Statement statement = DemoConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate(sql);
            System.out.println("Insert succefullly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/user/list")
    public List<User> getUserList() {
        String sql = "SELECT * FROM USER ORDER BY fullname ASC;";
        List<User> users = new ArrayList<>();
        User user;
        try {
            Statement statement = DemoConnection.getInstance().getConnection().createStatement();
            ResultSet getUsers = statement.executeQuery(sql);
            while (getUsers.next()) {

                user = new User(getUsers.getInt(1), getUsers.getString(2), getUsers.getString(3), getUsers.getString(4)
                        , getUsers.getString(5), getUsers.getString(6), getUsers.getString(7));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return users;
        }
    }
    @PutMapping(value = "/user/{user_id}")
    public void updateUser(@PathVariable(name="user_id") int userID,@RequestBody UserInserted userInserted)
    {
        String findUser = "SELECT id FROM USER WHERE id = '" + userID + "';";
        try
        {
            Statement statement = DemoConnection.getInstance().getConnection().createStatement();
            ResultSet verifyUser = statement.executeQuery(findUser);
            if(verifyUser.next())
            {
                String update= "UPDATE USER SET id = " + userID;
                if(userInserted.getUsername()!=null)
                {
                    update+=",username = '" +userInserted.getUsername() + "'";
                }
                if(userInserted.getPassword()!=null)
                {
                    update+=",password = '" +userInserted.getPassword() + "'";
                }
                if(userInserted.getFullname()!=null)
                {
                    update+=",fullname = '" +userInserted.getFullname() + "'";
                }
                if(userInserted.getEmail()!=null)
                {
                    update+=",email = '" +userInserted.getEmail() + "'";
                }
                if(userInserted.getAddress()!=null)
                {
                    update+=",address = '" +userInserted.getAddress() + "'";
                }
                update+=" WHERE id = " + userID + ";";
                statement = DemoConnection.getInstance().getConnection().createStatement();
                statement.executeUpdate(update);
                System.out.println("UPDATE SUCCESSFULLY");
            }
            else
            {
                System.out.println("THIS ID IS NOT EXIST");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Update fail");
        }
    }
    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestParam(name = "user_id") int userID)
    {
        String delete = "DELETE FROM USER WHERE id = " + userID + ";";
   //     System.out.println(delete);
        try{
            Statement statement = DemoConnection.getInstance().getConnection().createStatement();
            statement.execute(delete);
            System.out.println("Delete successfully");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Delete fail");
        }
    }
}

@Getter
@Setter
class UserInserted {
    private String username, password, fullname, email, address, phone;
}