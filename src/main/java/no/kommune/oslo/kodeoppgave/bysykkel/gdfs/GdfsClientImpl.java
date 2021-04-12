package no.kommune.oslo.kodeoppgave.bysykkel.gdfs;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.kommune.oslo.kodeoppgave.bysykkel.gdfs.dto.GdfsStationInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class GdfsClientImpl implements GdfsClient {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    public GdfsClientImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<GdfsStationInformation> getStationInformationList() {

        ResponseEntity<JsonNode> response = restTemplate.exchange(RequestEntity
                .get("https://gbfs.urbansharing.com/oslobysykkel.no/station_information.json")
                .header("Client-Identifier", "AsbjørnWillersrud-OrigoKodeoppgave")
                .build(), JsonNode.class);

        JsonNode stationsJsonArray = response.getBody().get("data").get("stations");

        List<GdfsStationInformation> stations = objectMapper.convertValue(stationsJsonArray, new TypeReference<>() {});

        logger.debug("Found {} gdfs station information entries", stations.size());

        return stations;
    }

}
