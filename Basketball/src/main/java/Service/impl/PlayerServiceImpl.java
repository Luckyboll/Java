package Service.impl;

import Dao.PlayerDao;
import Dao.TeamDao;
import Entity.Player;
import Entity.Team;
import Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public void save(Player player) {
        if(exists(player.getId())){
            System.out.println("该球员已存在不可再添加！");
            return;
        }
        playerDao.save(player);
        System.out.println("插入的球员信息：" + player);
        Team team = teamDao.findById(player.getTeam().getId());
        if (team != null) {
            team.setNum(team.getNum() + 1);
            teamDao.update(team);
        }
    }

    @Override
    public Player findById(Integer id) {
        Player player = playerDao.findById(id);
        System.out.println(player);
        return player;
    }

    @Override
    public void update(Player player) {
        playerDao.update(player);
        System.out.println("球员信息更新为：" + player);
    }

    @Override
    public void delete(Integer id) {
        Player player = playerDao.findById(id);
        if (player != null) {
            playerDao.delete(id);
            System.out.println("删除球员信息" + player);
            Team team = teamDao.findById(player.getTeam().getId());
            if (team != null) {
                team.setNum(team.getNum() - 1);
                teamDao.update(team);
            }
        }
    }

    @Override
    public List<Player> findAll() {
        List<Player> list = playerDao.findAll();
        for(Player p : list){
            System.out.println(p);
        }
        return list;
    }

    @Override
    public boolean exists(Integer id) {
        Set<Integer> set = new HashSet<>();
        List<Player> players = playerDao.findAll();
        for(Player p : players){
            set.add(p.getId());
        }
        return set.contains(id);
    }

    @Override
    public boolean canDelete(Integer id) {
        Player player = playerDao.findById(id);
        System.out.println("检查该球员是否可以删除：" + player);
        return playerDao.findById(id) != null;
    }

    public void setPlayerDao(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }
}
