package plusplus.demo.controller;


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
    public List<User> insertUser(@RequestBody UserInserted userInserted) {
        List<User> users = new ArrayList<>();
        User user;
        String sql = "INSERT INTO user (username,password,fullname,email,phone) VALUE ('" + userInserted.getUsername() + "',"
                + userInserted.getFullname() + ",'" + userInserted.getPassword() + "'," + userInserted.getFullname() + "',"
                + userInserted.getEmail() + "'," + userInserted.getAddress() + "'," + userInserted.getPhone() + "';";
        System.out.println(sql);
        try {
            Statement statement = DemoConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(sql);
            return users;
        }


        sql = "SELECT * FROM USER ORDER BY fullname ASC;";
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

    class UserInserted {
        private String username, password, fullname, email, address, phone;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "UserInserted{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", fullname='" + fullname + '\'' +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
}
