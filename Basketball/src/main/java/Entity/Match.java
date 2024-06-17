package Entity;

import lombok.Data;

import java.util.List;
@Data
public class Match {
    private Integer id;
    private String name;
    private Integer num;
    private List<Team> teams;
}
