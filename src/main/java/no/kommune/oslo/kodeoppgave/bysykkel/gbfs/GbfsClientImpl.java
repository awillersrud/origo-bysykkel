package no.kommune.oslo.kodeoppgave.bysykkel.gbfs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.GbfsStationInformation;
import no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto.gbfsStationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

    private String baseUrl;

    private String stationInformationPath;

    private String stationStatusPath;

    private String clientIdentifier;

    public GbfsClientImpl(RestTemplate restTemplate,
                          ObjectMapper objectMapper,
                          @Value("${gbfs.base.url}") String baseUrl,
                          @Value("${gbfs.station.information.path}") String stationInformationPath,
                          @Value("${gbfs.station.status.path}") String stationStatusPath,
                          @Value("${gbfs.client.identifier}") String clientIdentifier) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.baseUrl = baseUrl;
        this.stationInformationPath = stationInformationPath;
        this.stationStatusPath = stationStatusPath;
        this.clientIdentifier = clientIdentifier;
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
