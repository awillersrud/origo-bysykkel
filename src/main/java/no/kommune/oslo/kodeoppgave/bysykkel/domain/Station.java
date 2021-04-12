package no.kommune.oslo.kodeoppgave.bysykkel.domain;

public class Station {

    public Station(String name, Integer availableBikes, Integer availableDocks) {
        this.name = name;
        this.availableBikes = availableBikes;
        this.availableDocks = availableDocks;
    }

    public Station() {
    }

    private String name;

    private Integer availableBikes;

    private Integer availableDocks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(Integer availableBikes) {
        this.availableBikes = availableBikes;
    }

    public Integer getAvailableDocks() {
        return availableDocks;
    }

    public void setAvailableDocks(Integer availableDocks) {
        this.availableDocks = availableDocks;
    }
}
