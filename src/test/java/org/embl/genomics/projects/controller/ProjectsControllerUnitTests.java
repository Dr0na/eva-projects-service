package org.embl.genomics.projects.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import org.embl.genomics.projects.model.Project;
import org.embl.genomics.projects.model.Taxonomy;
import org.embl.genomics.projects.repository.ProjectsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(ProjectsController.class)
class ProjectsControllerUnitTests {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private ProjectsRepository projectsRepo;

  private static final String projectInfoUri = "/api/1.0/projects/{0}";
//  private static final String projectListFilterByTaxonomyUri = "/api/1.0/projects?taxonomyCommonName={0}";
  
  private final List<Project> projectList;
  
  public ProjectsControllerUnitTests() {
    
    projectList = new ArrayList<Project>();
    
    Taxonomy taxHuman = new Taxonomy(9606, "Human", "Homo sapiens");
    Taxonomy taxHorse = new Taxonomy(9796, "Horse", "Equus caballus");
    Taxonomy taxPig = new Taxonomy(9823, "Pig", "Sus scrofa");
    Taxonomy taxCow = new Taxonomy(9913, "Cow", "Bos taurus");
    Taxonomy taxSheep = new Taxonomy(9935, "Sheep", "Ovis");
    
    Project projectHuman = new Project("PRJEB6042","GEUVADIS: Genetic European Variation in Disease","GEUVADIS: Genetic European Variation in Disease (http://www.geuvadis.org), is a European Medical Sequencing Consortium aiming at sharing capacity across Europe in high-throughput sequencing technology to explore genetic variation in health and disease. It is funded by the European Commission 7th framework program under the Coordination and Support Action scheme. It started on the 1st October 2010, and ends on 31st December 2013. The project Coordinator is Xavier Estivill from Center for Genomic Regulation, Barcelona.","Germline", "Aggregate", "Center for Genomic Regulation - CRG (Barcelona); Institute of Human Genetics - Helmholtz Zentrum (Munich)","Center for Genomic Regulation - CRG (Barcelona); Institute of Human Genetics - Helmholtz Zentrum (Munich)", taxHuman);
    Project projectHorse = new Project("PRJEB22866", "Public Science and Technology Research Funds Projects of Agriculture", "To improve the technical standard and explore horse functional genome.", "Germline", "Control Set", null, "Functional genome of Chinese native horse.", taxHorse);
    Project projectPig = new Project("PRJEB22189", "Boar sperm DNA fragmentation", "Variant detection in RNA-seq data from boars with high/low sperm DFI", "Germline", "Control Set", null, "Norsvin SA", taxPig);
    Project projectCow = new Project("PRJEB21794", "OMIA LCV: Bulldog dwarfism in Dexter cattle", "This project describes two likely causal variants (LCVs) for bulldog dwarfism in Dexter cattle", "Germline", "Control Set", null, "OMIA", taxCow);
    Project projectSheep = new Project("PRJEB7378", "NextGen project variations for Ovis (sheep)", "Variation site discovery and genotype calls in the genus Ovis (sheep) by the NEXTGEN project (Next generation methods to preserve farm animal diversity by optimizing present and future breeding options).", "Germline", "Control Set", null, "EBI", taxSheep);

    projectList.add(projectHuman);
    projectList.add(projectHorse);
    projectList.add(projectPig);
    projectList.add(projectCow);
    projectList.add(projectSheep);
  }

  @Test
  public void testProjectInfo() throws Exception {

    int index = ThreadLocalRandom.current().nextInt(0, projectList.size());

    Project testProject = projectList.get(index);

    Optional<Project> data = Optional.of(testProject);
    Mockito.when(projectsRepo.findById(testProject.getProjectId())).thenReturn(data);

    String uri = MessageFormat.format(projectInfoUri, testProject.getProjectId());
    MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
    String actual = result.getResponse().getContentAsString();
    String expected = mapper.writeValueAsString(testProject);
    
    assertThat(actual).isEqualToIgnoringWhitespace(expected);
  }
  
  @Test
  public void testProjectInfoNotFound() throws Exception {

    int index = ThreadLocalRandom.current().nextInt(0, projectList.size());

    Project testProject = projectList.get(index);

    Optional<Project> data = Optional.of(testProject);
    Mockito.when(projectsRepo.findById(testProject.getProjectId())).thenReturn(data);

    String uri = MessageFormat.format(projectInfoUri, "DUMMY");
    mockMvc.perform(get(uri)).andExpect(status().isNotFound());
  }
  
}
