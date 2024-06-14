package Dao;

import Entity.Match;

import java.util.List;

public interface MatchDao {
    public void save(Match match);
    public void delete(Integer id);
    public void update(Match match);
    public Match findById(Integer id);
    public List<Match> findAll();
}
