package Service.impl;

import Dao.impl.MatchDaoImpl;
import Entity.Match;
import Entity.Player;
import Entity.Team;
import Service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDaoImpl matchDao;
    @Override
    public void save(Match match) {
        if(exists(match.getId())){
            System.out.println("联赛已经存在不可添加！");
        }else{
            System.out.println("添加联赛：" + match);
            matchDao.save(match);
        }

    }

    @Override
    public Match findById(Integer id) {
        Match match = matchDao.findById(id);
        System.out.println("添加联赛信息:" + match);
        return match;
    }

    @Override
    public void update(Match match) {
        System.out.println("更新联赛信息：" + match);
        matchDao.update(match);
    }

    @Override
    public void delete(Integer id) {
        if(canDelete(id)){
            matchDao.delete(id);
        }else{
            System.out.println("删除失败！");
        }
    }

    @Override
    public List<Match> findAll() {
        List<Match> matches = matchDao.findAll();
        for(Match match : matches){
            System.out.println(match);
        }
        return matches;
    }

    @Override
    public boolean exists(Integer id) {
        Set<Integer> set = new HashSet<>();
        List<Match> matches = matchDao.findAll();
        for(Match match : matches){
            set.add(match.getId());
        }
        return set.contains(id);
//        Match match = matchDao.findById(id);
//        System.out.println("检查联赛是否存在？" + match);
//        return matchDao.findById(id) != null;
    }

    @Override
    public boolean canDelete(Integer id) {
        Match match = matchDao.findById(id);
        System.out.println("检查联赛信息是否可以被删除？" + match);
        return match != null && match.getNum() == 0;
    }

    public void setMatchDao(MatchDaoImpl matchDao) {
        this.matchDao = matchDao;
    }
}
