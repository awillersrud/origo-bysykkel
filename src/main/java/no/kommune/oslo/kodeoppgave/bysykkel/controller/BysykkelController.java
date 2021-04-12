package no.kommune.oslo.kodeoppgave.bysykkel.controller;

import no.kommune.oslo.kodeoppgave.bysykkel.domain.Station;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class BysykkelController {

    @GetMapping("/stasjoner")
    public void getStations(Model model) {
        List<Station> stations = Arrays.asList(
                new Station("Myraløkka Øst", 5, 10),
                new Station("Bentsebrugata", 13, 11),
                new Station("Bjølsen mølle", 15, 14));
        model.addAttribute("stations", stations);
    }
}
