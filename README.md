Spring boot JPA example

#### Description
이 프로젝트는 첫 Spring boot application이며, 제가 생각하는 가장 간단하지만 필수적인 요소를 곁들인 형태의 프로젝트 입니다.

#### Environment
 - MacOS, IntelliJ, NCP-mysqlcloud, PostMan
 - Spring boot, Gradle, JPA, MySQL

#### Files
```bash
Main code
src
 ├── main
    ├── generated
    ├── java
       └── com
           └── example
               └── demo
                   ├── DemoApplication.java            [Main thread]
                   ├── controller                      [Contollers]
                   │   └── PersonController.java
                   ├── dto                             [DTOs create, search, etc...]
                   │   └── PersonCreationRequest.java  
                   ├── model                           [Schema, Model, Entity blah blah...]
                   │   └── Person.java
                   ├── repository                      [ORM]         
                   │   └── PersonRepository.java 
                   └── service                         [Buiness logic]
                       └── PersonService.java 
```

```bash
Profiles
resources                                              [Profiles seperated with dev, local, production]  
 ├── application-dev.yml
 ├── application-local.yml
 ├── application-prod.yml
 └── application.yml
```

```bash
Test
test
 └── java
    └── com
        └── example
            └── demo
                ├── PersonControllerTest.java.         [Contoller test -- JUnit5]
                ├── PersonRepositoryTest.java          [Repository test -- JUnit5]
                └── PersonServiceTest.java             [Service test -- JUnit5]
```

#### Support
##### Code review by [@wooju.shin](https://github.com/engineer-myoa).

