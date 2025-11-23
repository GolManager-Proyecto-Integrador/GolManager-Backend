package co.golmanager.gestorweb.presentation.controller;

import co.golmanager.gestorweb.presentation.dto.generalDto.GeneralDeleteResponse;
import co.golmanager.gestorweb.presentation.dto.matchEvents.*;
import co.golmanager.gestorweb.service.interfaces.MatchEventService;
import co.golmanager.gestorweb.service.interfaces.PermissionEvaluatorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(("/api/tournaments/{tournamentId}/matches/events"))
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Matches Events", description = "Endpoints for managing events of matches")
@RequiredArgsConstructor
public class MatchEventsController {

    private final PermissionEvaluatorService permissionEvaluatorService;
    private final MatchEventService matchEventService;


    @GetMapping("/{matchId}")
    public ResponseEntity<GetAllEventsForMatchResponse> obtainMatchEvents(@PathVariable("tournamentId") Long tournamentId, @PathVariable Long matchId, Authentication authentication) {
        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getAllEventsForMatch(tournamentId, matchId));
    }

    @GetMapping("/goal/{idGoal}")
    public ResponseEntity<GetGoalDetailsResponse> obtainDetailsOfGoals(@PathVariable Long tournamentId, @PathVariable Long idGoal, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getGoalDetails(
                matchEventService.getGoal(tournamentId, idGoal
                )));
    }

    @PostMapping("/goal")
    public ResponseEntity<GetGoalDetailsResponse> recordGoal(@PathVariable Long tournamentId, @Valid @RequestBody PostGoalRequest postGoalRequest, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getGoalDetails(
                matchEventService.recordGoal(
                        tournamentId,
                        postGoalRequest.getMatchId(),
                        postGoalRequest.getPlayerId(),
                        postGoalRequest.getGoalMinute()
                )));
    }

    @PutMapping("/goal/{idGoal}")
    public ResponseEntity<GetGoalDetailsResponse> editGoalDetail(@PathVariable Long tournamentId, @PathVariable Long idGoal, @Valid @RequestBody PostGoalRequest postGoalRequest, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getGoalDetails(
                matchEventService.editGoal(
                        tournamentId,
                        postGoalRequest.getMatchId(),
                        postGoalRequest.getPlayerId(),
                        postGoalRequest.getGoalMinute(),
                        idGoal
                )));
    }

    @DeleteMapping("/goal/{idGoal}")
    public ResponseEntity<GeneralDeleteResponse> deleteGoal(@PathVariable Long tournamentId, @PathVariable Long idGoal, Authentication authentication) {
        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.deleteGoal(tournamentId, idGoal));
    }

    @GetMapping("/card/{idCard}")
    public ResponseEntity<GetCardDetailsResponse> obtainDetailsOfCards(@PathVariable Long tournamentId, @PathVariable Long idCard, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getCardDetails(matchEventService.getCard(tournamentId, idCard)));
    }

    @PostMapping("/card")
    public ResponseEntity<GetCardDetailsResponse> recordCard(@PathVariable Long tournamentId, @Valid @RequestBody PostCardRequest postCardRequest, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getCardDetails(
                matchEventService.recordCard(
                        tournamentId,
                        postCardRequest.getMatchId(),
                        postCardRequest.getPlayerId(),
                        postCardRequest.getCardMinute(),
                        postCardRequest.getCardColor()
                )));
    }

    @PutMapping("/card/{idCard}")
    public ResponseEntity<GetCardDetailsResponse> editCardDetails(@PathVariable Long tournamentId, @PathVariable Long idCard,@Valid @RequestBody PostCardRequest postCardRequest, Authentication authentication) {

        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.getCardDetails(
                matchEventService.editCard(
                        tournamentId,
                        postCardRequest.getMatchId(),
                        postCardRequest.getPlayerId(),
                        postCardRequest.getCardMinute(),
                        postCardRequest.getCardColor(),
                        idCard
                )));
    }

    @DeleteMapping("/card/{idCard}")
    public ResponseEntity<GeneralDeleteResponse> deleteCard(@PathVariable Long tournamentId, @PathVariable Long idCard, Authentication authentication) {
        permissionEvaluatorService.canAccessTournament(tournamentId, authentication);
        return ResponseEntity.ok(matchEventService.deleteCard(tournamentId, idCard));
    }


}
