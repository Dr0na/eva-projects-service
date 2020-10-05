package org.embl.genomics.projects.model;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "taxonomies")
@AllArgsConstructor
@NoArgsConstructor
public class Taxonomy {
  
  @Id
  private int taxonomyId;
  
  @NotNull
  private String taxonomyCommonName;
  
  @NotNull
  private String taxonomyScientificName;
}
