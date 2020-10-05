package org.embl.genomics.projects.repository;

import org.embl.genomics.projects.model.Project;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectsRepository
    extends PagingAndSortingRepository<Project, String>, JpaSpecificationExecutor<Project> {

}
