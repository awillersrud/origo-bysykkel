package no.kommune.oslo.kodeoppgave.bysykkel.controller;

import no.kommune.oslo.kodeoppgave.bysykkel.service.BysykkelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BysykkelController {

    private BysykkelService bysykkelService;

    public BysykkelController(BysykkelService bysykkelService) {
        this.bysykkelService = bysykkelService;
    }

    @GetMapping("/stasjoner")
    public void getStations(Model model) {
        model.addAttribute("stations", bysykkelService.getStations());
    }
}
