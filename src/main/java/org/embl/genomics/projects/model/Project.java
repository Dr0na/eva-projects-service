package org.embl.genomics.projects.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class Project {
  
  @Id
  private String projectId;
  
  @NotNull
  private String title;
  
  @NotNull
  private String description;
  
  @NotNull
  private String sourceType;
  
  @NotNull
  private String studyType;
  
  @Nullable
  private String evaCenterName; 
  
  @NotNull
  private String centerName;
  
  @ManyToOne
  @JoinColumn(name = "taxonomy_id")
  private Taxonomy taxonomy;
}
