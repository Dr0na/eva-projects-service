package org.embl.genomics.projects.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.embl.genomics.projects.model.Project;
import org.embl.genomics.projects.repository.ProjectSpec;
import org.embl.genomics.projects.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = {"/api/1.0"})
@Tag(name = "Project Details", description = "Details of genomics sequencing projects")
@AllArgsConstructor
public class ProjectsController {

  @Autowired
  ProjectsRepository repository;

  // TODO: It seems Springdoc could not handle OpenAPI document generation for the object type
  // inputs. Need to figure it out and fix

  @Operation(description = "Lists all genome sequencing projects.")
  @GetMapping(value = "/projects", produces = APPLICATION_JSON_VALUE)
  public Page<Project> getAllProjects(ProjectSpec projectSpec, Pageable pageable) {
     return repository.findAll(projectSpec, pageable);
  }

  @Operation(description = "List details of a project.")
  @GetMapping(value = "/projects/{projectId}", produces = APPLICATION_JSON_VALUE)
  public Optional<Project> getSingleProject(
      @Parameter(name = "projectId", description = "Project Id", example = "PRJEB629",
          in = ParameterIn.PATH) @PathVariable("projectId") @NotNull @NotEmpty String projectId) {
     Optional<Project> optional = repository.findById(projectId);
     if (optional.isPresent()) {
       return optional;
     }
     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found!");
  }

}
