package co.zoltans.srf.srfapi.topic;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

    public void createTopic(final CreateTopicDTO createTopic) {
        final Topic topic = Topic.builder().name(createTopic.name()).build();
        topicRepository.saveAndFlush(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public boolean deleteTopic(UUID uuid) {
        Optional<Topic> topic = topicRepository.findById(uuid);

        if (topic.isPresent()) {
            topicRepository.delete(topic.get());
            return true;
        }

        return false;
    }

    public Optional<Topic> getTopic(UUID uuid) {
        return topicRepository.findById(uuid);
    }

    public Topic updateTopic(UUID uuid, Topic newTopicData) {
        Optional<Topic> optionalTopic = topicRepository.findById(uuid);

        if (!optionalTopic.isPresent()) {
            throw new EntityNotFoundException("Topic with id " + uuid + " does not exist.");
        }

        Topic existingTopic = optionalTopic.get();
        existingTopic.setName(newTopicData.getName());

        return topicRepository.save(existingTopic);
    }

    public Optional<Topic> getTopicById(UUID uuid) {
        return topicRepository.findById(uuid);
    }
}