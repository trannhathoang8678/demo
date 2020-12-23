package plusplus.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User{
    private String MSSV;

    public Student(String name, String email, String MSSV) {
        super(name, email);
        this.MSSV = MSSV;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }
}
