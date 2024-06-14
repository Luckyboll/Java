package Service;

import Entity.Player;

import java.util.List;

public interface PlayerService {
    public void save(Player player);
    public void delete(Integer id);
    public void update(Player player);
    public Player findById(Integer id);
    public List<Player> findAll();
    public boolean exists(Integer id);
    public boolean canDelete(Integer id);
}
