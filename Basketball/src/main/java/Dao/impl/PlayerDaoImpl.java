package Dao.impl;

import Dao.PlayerDao;
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
public class PlayerDaoImpl implements PlayerDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private static final class PlayerMapper implements RowMapper<Player> {
        @Override
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            player.setId(rs.getInt("id"));
            player.setName(rs.getString("name"));
            player.setAge(rs.getInt("age"));

            // 假设team_id是Team的id，你可以根据需要进行修改
            Team team = new Team();
            team.setId(rs.getInt("team_id"));
            player.setTeam(team);

            return player;
        }
    }
    @Override
    public void save(Player player) {
        String sql = "INSERT INTO player (id, name, age, team_id) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, player.getId(), player.getName(), player.getAge(), player.getTeam().getId());
    }

    @Override
    public Player findById(Integer id) {
        String sql = "SELECT * FROM player WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PlayerMapper());
    }

    @Override
    public void update(Player player) {
        String sql = "UPDATE player SET name = ?, age = ?, team_id = ? WHERE id = ?";
        jdbcTemplate.update(sql, player.getName(), player.getAge(), player.getTeam().getId(), player.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM player WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Player> findAll() {
        String sql = "SELECT * FROM player";
        return jdbcTemplate.query(sql, new PlayerMapper());
    }
}
