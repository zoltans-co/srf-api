package co.zoltans.srf.srfapi.topic;

public record CreateTopicDTO(
        String name
) {
    public String name() {
        return name;
    }
}