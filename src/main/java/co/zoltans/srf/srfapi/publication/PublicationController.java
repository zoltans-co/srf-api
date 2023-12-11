package co.zoltans.srf.srfapi.publication;

import co.zoltans.srf.srfapi.topic.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/publication")
@Tag(name = "Publication", description = "The publication API")
public class PublicationController {

    private final TopicService topicService;
    private final PublicationService publicationService;

    @Operation(summary = "Create a new publication for topic", description = "Create a new publication for topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publication created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request: publication creation failed"),
            @ApiResponse(responseCode = "404", description = "Publication creation failed, topic not found")
    })
    @PostMapping
    public ResponseEntity<?> createPublication(@RequestBody CreatePublicationDTO createPublicationDTO) {
        if (createPublicationDTO.topicId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request: publication creation failed");
        }
        if (topicService.getTopicById(createPublicationDTO.topicId()).isPresent()) {
            Publication publication = publicationService.createPublication(createPublicationDTO);
            return ResponseEntity.ok(publication);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication creation failed, topic not found");
        }
    }

    @Operation(summary = "Get all publications", description = "Get all publications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "No publications found")
    })
    @GetMapping
    public ResponseEntity<?> getAllPublications() {
        List<Publication> publications = publicationService.getAllPublications();
        if (publications.isEmpty()) {
            return new ResponseEntity<>("No publications found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(publications, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get publication by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the publication"),
            @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getPublicationWithId(@PathVariable UUID id) {
        Optional<Publication> publication = publicationService.getPublicationWithId(id);
        if (publication.isPresent()) {
            return new ResponseEntity<>(publication.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Publication not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete publication by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the publication"),
            @ApiResponse(responseCode = "400", description = "Invalid publication ID supplied"),
            @ApiResponse(responseCode = "404", description = "Publication not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublicationWithId(@PathVariable UUID id) {

        if (id == null) {
            return new ResponseEntity<>("Invalid publication ID supplied", HttpStatus.BAD_REQUEST);
        }

        Optional<Publication> publication = publicationService.deletePublicationWithId(id);
        if (publication.isPresent()) {
            return new ResponseEntity<>("Publication deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Publication not found", HttpStatus.NOT_FOUND);
        }
    }

}