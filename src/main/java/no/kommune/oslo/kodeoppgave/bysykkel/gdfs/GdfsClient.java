package no.kommune.oslo.kodeoppgave.bysykkel.gdfs;

import no.kommune.oslo.kodeoppgave.bysykkel.gdfs.dto.GdfsStationInformation;

import java.util.List;

public interface GdfsClient {

    List<GdfsStationInformation> getStationInformationList();

}
