package Dao;

import Entity.Player;

import java.util.List;

public interface PlayerDao {
    public void save(Player player);
    public void delete(Integer id);
    public void update(Player player);
    public Player findById(Integer id);
    public List<Player> findAll();

}
