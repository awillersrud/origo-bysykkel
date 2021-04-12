package no.kommune.oslo.kodeoppgave.bysykkel.service;

import no.kommune.oslo.kodeoppgave.bysykkel.domain.Station;
import no.kommune.oslo.kodeoppgave.bysykkel.gdfs.GdfsClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BysykkelServiceImpl implements BysykkelService {

    private GdfsClient gdfsClient;

    public BysykkelServiceImpl(GdfsClient gdfsClient) {
        this.gdfsClient = gdfsClient;
    }

    @Override
    public List<Station> getStations() {
        return gdfsClient.getStationInformationList()
                .stream()
                .map(s -> new Station(s.getName(), 0, 0))
                .collect(Collectors.toList());
    }
}
