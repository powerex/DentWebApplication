package ua.azbest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private long id;
    private String title;
    private String lecturer;
    private int credits;

}
