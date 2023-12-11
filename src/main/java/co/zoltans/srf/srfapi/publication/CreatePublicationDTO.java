package co.zoltans.srf.srfapi.publication;

import java.util.UUID;

public record CreatePublicationDTO (
        String title,
        String summary,
        String content,
        UUID topicId
) {

    public String title() {
        return title;
    }

    public String summary() {
        return summary;
    }

    public String content() {
        return content;
    }

    public UUID topicId() {
        return topicId;
    }

}