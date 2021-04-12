package no.kommune.oslo.kodeoppgave.bysykkel.mapper;

import no.kommune.oslo.kodeoppgave.bysykkel.domain.Station;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.GbfsStationInformation;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.gbfsStationStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GbfsStationMapper {

    public List<Station> mapFromGbfsStations(List<GbfsStationInformation> gbfsStations,
                                             List<gbfsStationStatus> gbfsStationStatuses) {
        Map<Long, gbfsStationStatus> statusById = groupByStationId(gbfsStationStatuses);
        return gbfsStations
                .stream()
                .map(gbfsStation -> mapFromGbfsStation(gbfsStation, statusById))
                .collect(Collectors.toList());
    }

    private Station mapFromGbfsStation(GbfsStationInformation gbfsStation, Map<Long, gbfsStationStatus> statusById) {
        Station station = new Station();
        station.setName(gbfsStation.getName());
        gbfsStationStatus status = statusById.get(gbfsStation.getStation_id());
        if (status != null) {
            station.setAvailableBikes(status.getNum_bikes_available());
            station.setAvailableDocks(status.getNum_docks_available());
        }
        return station;
    }

    private Map<Long, gbfsStationStatus> groupByStationId(List<gbfsStationStatus> gbfsStationStatus) {
        Map<Long, gbfsStationStatus> statusById = new HashMap<>();
        gbfsStationStatus.forEach(s -> statusById.put(s.getStation_id(), s));
        return statusById;
    }
}
