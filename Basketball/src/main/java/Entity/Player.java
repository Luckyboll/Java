package Entity;

import lombok.Data;

@Data
public class Player {
    private Integer id;
    private String name;
    private Integer age;
    private Team team;
}
