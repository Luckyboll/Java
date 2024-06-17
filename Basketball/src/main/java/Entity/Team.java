package Entity;

import lombok.Data;

import java.util.List;
@Data
public class Team {
    private Integer id;
    private String name;
    private Integer num;
    private Match match;
    private List<Player> players;
}
