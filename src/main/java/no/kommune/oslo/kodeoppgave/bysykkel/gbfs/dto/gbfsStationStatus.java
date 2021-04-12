package no.kommune.oslo.kodeoppgave.bysykkel.gbfs.dto;

public class gbfsStationStatus {

    private Long station_id;

    private Integer num_bikes_available;

    private Integer num_docks_available;

    public Long getStation_id() {
        return station_id;
    }

    public void setStation_id(Long station_id) {
        this.station_id = station_id;
    }

    public Integer getNum_bikes_available() {
        return num_bikes_available;
    }

    public void setNum_bikes_available(Integer num_bikes_available) {
        this.num_bikes_available = num_bikes_available;
    }

    public Integer getNum_docks_available() {
        return num_docks_available;
    }

    public void setNum_docks_available(Integer num_docks_available) {
        this.num_docks_available = num_docks_available;
    }
}
