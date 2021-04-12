package no.kommune.oslo.kodeoppgave.bysykkel.gbfs;

import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.GbfsStationInformation;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.gbfsStationStatus;

import java.util.List;

public interface GbfsClient {

    List<GbfsStationInformation> getStationInformationList();

    List<gbfsStationStatus> getStationStatusList();

}
