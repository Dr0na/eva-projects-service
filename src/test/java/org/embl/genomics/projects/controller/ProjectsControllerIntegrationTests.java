package org.embl.genomics.projects.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.MessageFormat;
import org.embl.genomics.projects.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectsControllerIntegrationTests {

  private static final String projectInfoUri = "/api/1.0/projects/{0}";
  
  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  private ObjectMapper mapper;
  
  @Test
  public void testProjectInfo() throws Exception {
    String projectId = "PRJEB9858";
    String uri = MessageFormat.format(projectInfoUri, projectId);
    MvcResult result = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();
    Project returnedProject = mapper.readValue(result.getResponse().getContentAsString(), Project.class);
    assertThat(returnedProject.getProjectId()).isEqualToIgnoringWhitespace(projectId);
  }
  
  @Test
  public void testProjectInfoNotFound() throws Exception {
    String uri = MessageFormat.format(projectInfoUri, "NON-EXISTING-PROJECT");
    mockMvc.perform(get(uri)).andExpect(status().isNotFound());
  }
}
