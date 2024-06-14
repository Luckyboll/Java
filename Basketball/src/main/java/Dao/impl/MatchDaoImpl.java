package Dao.impl;

import Dao.MatchDao;
import Entity.Match;
import Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class MatchDaoImpl implements MatchDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 定义内部类MatchMapper，用于将查询结果映射到Match对象
    private static final class MatchMapper implements RowMapper<Match> {
        private JdbcTemplate jdbcTemplate;

        public MatchMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
            Match match = new Match();
            match.setId(rs.getInt("id"));
            match.setName(rs.getString("name"));
            match.setNum(rs.getInt("num"));

            // 获取并设置teams列表
            String teamSql = "SELECT * FROM Team WHERE match_id = ?";
            List<Team> teams = jdbcTemplate.query(teamSql, new Object[]{match.getId()}, new RowMapper<Team>() {
                @Override
                public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Team team = new Team();
                    team.setId(rs.getInt("id"));
                    team.setName(rs.getString("name"));
                    team.setNum(rs.getInt("num"));
                    return team;
                }
            });

            match.setTeams(teams);

            return match;
        }
    }

    @Override
    public void save(Match match) {
        String sql = "INSERT INTO `match` (id, name, num) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, match.getId(), match.getName(), match.getNum());
    }

    @Override
    public Match findById(Integer id) {
        String sql = "SELECT * FROM `match` WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new MatchMapper(jdbcTemplate));
    }

    @Override
    public void update(Match match) {
        String sql = "UPDATE `match` SET name = ?, num = ? WHERE id = ?";
        jdbcTemplate.update(sql, match.getName(), match.getNum(), match.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM `match` WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Match> findAll() {
        String sql = "SELECT * FROM `match`";
        return jdbcTemplate.query(sql, new MatchMapper(jdbcTemplate));
    }
}
