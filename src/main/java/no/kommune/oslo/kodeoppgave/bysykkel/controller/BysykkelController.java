package no.kommune.oslo.kodeoppgave.bysykkel.controller;

import no.kommune.oslo.kodeoppgave.bysykkel.domain.Station;
import no.kommune.oslo.kodeoppgave.bysykkel.service.BysykkelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class BysykkelController {

    private BysykkelService bysykkelService;

    public BysykkelController(BysykkelService bysykkelService) {
        this.bysykkelService = bysykkelService;
    }

    @GetMapping("/stasjoner")
    public void getStations(Model model) {
        List<Station> stations = bysykkelService.getStations();
        stations.sort(Comparator.comparing(Station::getName));
        model.addAttribute("stations", stations);
    }
}
