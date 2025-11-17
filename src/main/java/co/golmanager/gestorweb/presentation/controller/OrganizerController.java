package co.golmanager.gestorweb.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
@Tag(name = "Organizer", description = "Controller of Dashboard organizer")
public class OrganizerController {



}
