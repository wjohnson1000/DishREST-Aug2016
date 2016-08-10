package data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student {
    public Student() {};
    
    public String name;
    public String grade;

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", grade=" + grade + '}';
    }
}
