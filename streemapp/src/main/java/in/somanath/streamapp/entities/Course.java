package in.somanath.streamapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//import java.util.ArrayList;
//import java.util.List;

@Entity
@Table(name = "yt_course")
public class Course {

    @Id
    private String id;

    private String title;

//    @OneToMany(mappedBy = "course")
//    private List<Video> list= new ArrayList<>();
}
