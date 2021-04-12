package no.kommune.oslo.kodeoppgave.bysykkel.service;

import no.kommune.oslo.kodeoppgave.bysykkel.domain.Station;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.GbfsClient;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.GbfsStationInformation;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.gbfsStationStatus;
import no.kommune.oslo.kodeoppgave.bysykkel.mapper.GbfsStationMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BysykkelServiceImpl implements BysykkelService {

    private GbfsClient gbfsClient;

    private GbfsStationMapper gbfsStationMapper;

    public BysykkelServiceImpl(GbfsClient gbfsClient,
                               GbfsStationMapper gbfsStationMapper) {
        this.gbfsClient = gbfsClient;
        this.gbfsStationMapper = gbfsStationMapper;
    }

    @Override
    public List<Station> getStations() {
        List<GbfsStationInformation> stationInformation = gbfsClient.getStationInformationList();
        List<gbfsStationStatus> stationStatus = gbfsClient.getStationStatusList();

        return gbfsStationMapper.mapFromGbfsStations(stationInformation, stationStatus);
    }
}
