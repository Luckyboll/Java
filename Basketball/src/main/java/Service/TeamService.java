package Service;

import Entity.Team;

import java.util.List;

public interface TeamService {
    public void save(Team team);
    public void delete(Integer id);
    public void update(Team team);
    public Team findById(Integer id);
    public List<Team> findAll();
    public boolean canDelete(Integer id);
    public boolean exists(Integer id);
}
