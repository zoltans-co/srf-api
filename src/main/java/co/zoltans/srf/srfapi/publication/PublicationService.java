package co.zoltans.srf.srfapi.publication;

import co.zoltans.srf.srfapi.topic.Topic;
import co.zoltans.srf.srfapi.topic.TopicRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PublicationService {

    private final TopicRepository topicRepository;
    private final PublicationRepository publicationRepository;

    public Publication createPublication(CreatePublicationDTO createPublicationDTO) {
        Publication publication = new Publication();
        publication.setTitle(createPublicationDTO.title());
        publication.setSummary(createPublicationDTO.summary());
        publication.setContent(createPublicationDTO.content());

        Topic topic = topicRepository.findById(createPublicationDTO.topicId()).orElseThrow(()
                -> new RuntimeException("Topic Not Found!"));

        publication.setTopic(topic);
        return publicationRepository.save(publication);
    }

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Optional<Publication> getPublicationWithId(UUID id) {
        return publicationRepository.findById(id);
    }

    public Optional<Publication> deletePublicationWithId(UUID id) {
        Optional<Publication> publication = publicationRepository.findById(id);
        if (publication.isPresent()) {
            publicationRepository.deleteById(id);
        }
        return publication;
    }

}