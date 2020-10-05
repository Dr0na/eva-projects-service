# eva-projects-service
This repository hosts a Spring Boot based ReSTful service implementation for listing genome sequencing and variation study projects at EMBL. The service allows fetching information by project id alongwith searching projects on taxonomy common name.    


# Features
1. Support for listing project by Id
2. Support for listing of all projects
3. Support for case-Insensitive filtering on taxonomy common name
4. Support for sorting of results on multiple output attributes with the default order
5. Support for pagination of output 
6. Support for defining output page size 

# API Endpoints

The OpenAPI documentation can be accessed at this [link](http://localhost:8080/swagger-ui.html).

Method | URI | Query params | Response Codes
------------ | ------------- | ------------- | -------------
GET | /api/1.0/projects | <ul><li><strong>taxonomyCommonName (string) </strong> - case-insensitive taxonomy common name</li><li><strong>page (integer) </strong> - Zero based page number</li><li> <strong>size (integer)</strong> - items to list in the page</li><li><strong>sort (string)</strong> - comma separated list of attributes to sort the output on</li></ul> | <ul><li><strong>200</strong> - when matching resource(s) is found.</li><li><strong>404</strong> - when no matching resource(s) is found.</li></ul>
GET | /api/1.0/projects/{projectId} | None | <ul><li><strong>200</strong> - when matching resource(s) is found.</li><li><strong>404</strong> - when no matching resource(s) is found.</li></ul>

# Usage examples
1. [List project with id 'PRJEB629'](http://localhost:8080/api/1.0/projects/PRJEB629)
2. [List all projects](http://localhost:8080/api/1.0/projects)
3. [Filter and list projects matching a taxonomy](http://localhost:8080/api/1.0/projects?taxonomyCommonName=Human)
4. [Filter and list single page of projects](http://localhost:8080/api/1.0/projects?taxonomyCommonName=Human&page=0)
5. [Filter and list single page of projects with custom page size](http://localhost:8080/api/1.0/projects?taxonomyCommonName=Human&page=0&size=10)
6. [Filter and list single page of projects with custom page size sorted on a single attribute](http://localhost:8080/api/1.0/projects?taxonomyCommonName=barley&page=0&size=10&sort=projectId)
7. [Filter and list single page of projects with custom page size sorted on a multiple attributes](http://localhost:8080/api/1.0/projects?taxonomyCommonName=barley&page=0&size=10&sort=projectId,centerName)

# Dependencies
1. [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - For JPA implementation 
2. [Spring Web](https://github.com/spring-projects/spring-framework/tree/master/spring-web) - For building RESTful interface using Spring MVC
3. [springdoc-openapi](https://springdoc.org/) - For OpenApi document generation
2. [Lombok](https://projectlombok.org/) - For auto-generation of code
2. [specification-arg-resolver](https://github.com/tkaczmarzyk/specification-arg-resolver) - An alternative API for filtering data with Spring MVC and Spring Data JPA
