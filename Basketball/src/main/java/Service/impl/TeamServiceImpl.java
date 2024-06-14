package Service.impl;

import Dao.MatchDao;
import Dao.TeamDao;
import Dao.impl.TeamDaoImpl;
import Entity.Match;
import Entity.Player;
import Entity.Team;
import Service.MatchService;
import Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private MatchDao matchDao;

    @Override
    public void save(Team team) {
        if(exists(team.getId())){
            System.out.println("该球队已经存在，不可再插入");
            return;
        }
        teamDao.save(team);
        System.out.println("插入球队数据：" + team);
        Match match = matchDao.findById(team.getMatch().getId());
        if (match != null) {
            match.setNum(match.getNum() + 1);
            matchDao.update(match);
        }
    }

    @Override
    public Team findById(Integer id) {
        Team team = teamDao.findById(id);
        System.out.println("根据id查找到相应的球队：" + team);
        return team;
    }

    @Override
    public void update(Team team) {
        System.out.println("球员信息更新：" + team);
        teamDao.update(team);
    }

    @Override
    public void delete(Integer id) {
        Team team = teamDao.findById(id);
        if (canDelete(id)) {
            teamDao.delete(id);
            System.out.println("删除球队信息：" + team);
            Match match = matchDao.findById(team.getMatch().getId());
            if (match != null) {
                match.setNum(match.getNum() - 1);
                matchDao.update(match);
            }
        }else{
            System.out.println("该球队包含球员人数大于0，不可删除！");
        }
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = teamDao.findAll();
        for (Team team : teams){
            System.out.println(team);
        }
        return teams;
    }

    @Override
    public boolean exists(Integer id) {
        Team team = teamDao.findById(id);
        System.out.println("检查该球队是否存在：" + team);
        return teamDao.findById(id) != null;
    }

    @Override
    public boolean canDelete(Integer id) {
        Team team = teamDao.findById(id);
        System.out.println("判断该球队是否可以删除");
        return team != null && team.getNum() == 0;
    }

    public void setTeamDao(TeamDaoImpl teamDao) {
        this.teamDao = teamDao;
    }
}
