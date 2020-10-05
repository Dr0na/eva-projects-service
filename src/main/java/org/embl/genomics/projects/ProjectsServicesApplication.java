package org.embl.genomics.projects;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
            title = "EVA Genomics Projects Service",
            version = "1.0",
            description = "EVA Genomics Projects Service API Specification",
            license = @License(
                    name = "Creative Commons Attribution-NoDerivs Licence",
                    url = "https://www.ebi.ac.uk/ipd/imgt/hla/licence.html"),
            contact = @Contact(
                    url = "https://www.ebi.ac.uk/about/variation-team",
                    name = "European Variation Archive",
                    email = "eva-helpdesk@ebi.ac.uk")
    )
)
public class ProjectsServicesApplication implements WebMvcConfigurer {
  
  public static void main(String[] args) {
    SpringApplication.run(ProjectsServicesApplication.class, args);
  }
  
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
      argumentResolvers.add(new SpecificationArgumentResolver());
      argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
  }
}
