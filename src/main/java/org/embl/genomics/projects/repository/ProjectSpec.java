package org.embl.genomics.projects.repository;

import org.embl.genomics.projects.model.Project;
import org.springframework.data.jpa.domain.Specification;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@Spec(path = "taxonomy.taxonomyCommonName", params = "taxonomyCommonName", spec = EqualIgnoreCase.class)
public interface ProjectSpec extends Specification<Project> {

}

