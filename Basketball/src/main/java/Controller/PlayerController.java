package Controller;

import Entity.Player;
import Entity.Result;
import Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @GetMapping
    public Result<List<Player>> list() {
        List<Player> list = playerService.findAll();
        return Result.success(list);
    }
}
