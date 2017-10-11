package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Jatin on 06/10/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @Column(name="id")
    private String id;
    @Column
    private String name;
    @Column
    private String deptName;
    @Column
    private int totalCredits;
}
