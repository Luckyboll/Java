package Dao.impl;

import Dao.TeamDao;
import Entity.Match;
import Entity.Player;
import Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 定义内部类TeamMapper，用于将查询结果映射到Team对象
    private static final class TeamMapper implements RowMapper<Team> {
        private JdbcTemplate jdbcTemplate;

        public TeamMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            team.setNum(rs.getInt("num"));

            // 获取并设置players列表
            String playerSql = "SELECT * FROM player WHERE team_id = ?";
            List<Player> players = jdbcTemplate.query(playerSql, new Object[]{team.getId()}, new RowMapper<Player>() {
                @Override
                public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Player player = new Player();
                    player.setId(rs.getInt("id"));
                    player.setName(rs.getString("name"));
                    player.setAge(rs.getInt("age"));
//                    player.setTeam(team);
                    return player;
                }
            });

            team.setPlayers(players);

            // 获取并设置match对象
            String matchSql = "SELECT * FROM `match` WHERE id = (SELECT match_id FROM team WHERE id = ?)";
            Match match = jdbcTemplate.queryForObject(matchSql, new Object[]{team.getId()}, new RowMapper<Match>() {
                @Override
                public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Match match = new Match();
                    match.setId(rs.getInt("id"));
                    match.setName(rs.getString("name"));
                    match.setNum(rs.getInt("num"));
                    return match;
                }
            });

            team.setMatch(match);

            return team;
        }
    }

    @Override
    public void save(Team team) {
        String sql = "INSERT INTO team (id, name, num, match_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, team.getId(), team.getName(), team.getNum(), team.getMatch().getId());
    }

    @Override
    public Team findById(Integer id) {
        String sql = "SELECT * FROM team WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TeamMapper(jdbcTemplate));
    }

    @Override
    public void update(Team team) {
        String sql = "UPDATE team SET name = ?, num = ? WHERE id = ?";
        jdbcTemplate.update(sql, team.getName(), team.getNum(), team.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM team WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Team> findAll() {
        String sql = "SELECT * FROM team";
        return jdbcTemplate.query(sql, new TeamMapper(jdbcTemplate));
    }
}
