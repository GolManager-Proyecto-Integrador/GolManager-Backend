package co.golmanager.gestorweb.controller;

import co.golmanager.gestorweb.entity.Team;
import co.golmanager.gestorweb.service.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name= "Teams", description = "Endpoints for managing teams.")
@RequiredArgsConstructor
public class TeamController {

    @Autowired
    private final TeamService teamService;

//    @GetMapping
//    public List<Team> getTeams() {}
//
//    @PostMapping
//    public ResponseEntity<> createTeam {}
//
//
}
