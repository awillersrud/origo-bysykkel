package no.kommune.oslo.kodeoppgave.bysykkel.gbfs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.GbfsStationInformation;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.gbfsStationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GbfsClientImpl implements GbfsClient {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    private String baseUrl = "https://gbfs.urbansharing.com/oslobysykkel.no/";

    private String stationInformationPath = "station_information.json";

    private String stationStatusPath = "station_status.json";

    private String clientIdentifier = "AsbjørnWillersrud-OrigoKodeoppgave";

    public GbfsClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<GbfsStationInformation> getStationInformationList() {

        JsonNode stationsJsonArray = executeGbfsRequest(baseUrl + stationInformationPath)
                .getBody().get("data")
                .get("stations");

        List<GbfsStationInformation> stations = objectMapper.convertValue(stationsJsonArray, new TypeReference<>() {});

        logger.debug("Found {} gbfs station information entries", stations.size());

        return stations;
    }

    @Override
    public List<gbfsStationStatus> getStationStatusList() {
        JsonNode stationsJsonArray = executeGbfsRequest(baseUrl + stationStatusPath)
                .getBody().get("data")
                .get("stations");

        List<gbfsStationStatus> stationStatusList = objectMapper.convertValue(stationsJsonArray, new TypeReference<>() {});

        logger.debug("Found {} gbfs station status entries", stationStatusList.size());

        return stationStatusList;
    }

    private ResponseEntity<JsonNode> executeGbfsRequest(String url) {
        return restTemplate.exchange(RequestEntity
                .get(url)
                .header("Client-Identifier", clientIdentifier)
                .build(), JsonNode.class);
    }
}
