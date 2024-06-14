package Service;

import Entity.Match;

import java.util.List;

public interface MatchService {
    public void save(Match match);
    public void delete(Integer id);
    public void update(Match match);
    public Match findById(Integer id);
    public List<Match> findAll();
    public boolean exists(Integer id);
    public boolean canDelete(Integer id);
}
