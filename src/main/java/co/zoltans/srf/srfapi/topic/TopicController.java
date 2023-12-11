package co.zoltans.srf.srfapi.topic;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/topic")
@Tag(name = "Topic", description = "The topic API")
public class TopicController {

    private final TopicService topicService;

    @Operation(summary = "Create a new topic", description = "Create a new topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic created successfully"),
            @ApiResponse(responseCode = "400", description = "Topic creation failed")
    })
    @PostMapping
    public void createTopic(@RequestBody CreateTopicDTO topic) {
        topicService.createTopic(topic);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic returned succesfully."),
            @ApiResponse(responseCode = "404", description = "No topic found.")
    })
    @Operation(summary = "Get topic with UUID", description = "Get topic with UUID")
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopic(@PathVariable UUID id) {
        return topicService.getTopic(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic list returned succesfully."),
            @ApiResponse(responseCode = "404", description = "No topic found.")
    })
    @Operation(summary = "Get all topics", description = "Get all topics")
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic updated succesfully."),
            @ApiResponse(responseCode = "404", description = "No topic found.")
    })
    @Operation(summary = "Update topic with UUID", description = "Update topic with UUID")
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable UUID id, @RequestBody Topic updatedTopic) {
        try {
            Topic savedTopic = topicService.updateTopic(id, updatedTopic);
            return ResponseEntity.ok(savedTopic);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Topic deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Topic was not found")
    })
    @Operation(summary = "Delete topic by UUID", description = "Delete topic by UUID")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable UUID id) {
        boolean isRemoved = topicService.deleteTopic(id);

        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}