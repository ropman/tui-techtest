#Github API Consumer application
```java code
curl --request GET \
--url "https://api.github.com/octocat"

               MMM.           .MMM
               MMMMMMMMMMMMMMMMMMM
               MMMMMMMMMMMMMMMMMMM      ____________________________
              MMMMMMMMMMMMMMMMMMMMM    |                            |
             MMMMMMMMMMMMMMMMMMMMMMM   | Keep it logically awesome. |
            MMMMMMMMMMMMMMMMMMMMMMMM   |_   ________________________|
            MMMM::- -:::::::- -::MMMM    |/
             MM~:~ 00~:::::~ 00~:~MM
        .. MMMMM::.00:::+:::.00::MMMMM ..
              .MM::::: ._. :::::MM.
                 MMMM;:::::;MMMM
          -MM        MMMMMMM
          ^  M+     MMMMMMMMM
              MMMMMMM MM MM MM
                   MM MM MM MM
                   MM MM MM MM
                .~~MM~MM~MM~MM~~.
             ~~~~MM:~MM~~~MM~:MM~~~~
            ~~~~~~==~==~~~==~==~~~~~~
             ~~~~~~==~==~==~==~~~~~~
                 :~==~==~==~==~~
```
# Purpose of the application

Request github public developer api for information about user related information. Based on the given username, it's collect public repositories ( which are not forked ) and their branches with some information.

### information which returned by the application:

- repository name
- repository owner login
- branches under the repository:
  - name of the branch
  - latest commit sha under the given branch


## How to run and use the application

run the application with maven:
```command line
./mvnw spring-boot:run
```

**Note - system requirements**:
  - internet connection to update maven dependencies and reach github
  - installed java 17   
    - to check java version run in command line:
        ``` 
          java --version
      
          example response:
          openjdk 17.0.6 2023-01-17 LTS
          OpenJDK Runtime Environment Corretto-17.0.6.10.1 (build 17.0.6+10-LTS)
          OpenJDK 64-Bit Server VM Corretto-17.0.6.10.1 (build 17.0.6+10-LTS, mixed mode, sharing)
       ```    
  - maven ( tested with the latest 3.8.7 )
    - to check maven version run in command line: 
      ```   
        mvn --version
      
        example respons:
        Maven home: /Users/bakl/.sdkman/candidates/maven/current
        Java version: 17.0.6, vendor: Amazon.com Inc., runtime: /Users/bakl/.sdkman/candidates/java/17.0.6-amzn
        Default locale: en_GB, platform encoding: UTF-8
        OS name: "mac os x", version: "12.5.1", arch: "x86_64", family: "mac"
      ```

after the maven downloaded the required dependencies on the command line there should be seen log lines like:
```
...
2023-02-03T12:22:32.653+01:00  INFO 4971 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-02-03T12:22:32.654+01:00  INFO 4971 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1315 ms
2023-02-03T12:22:33.665+01:00  INFO 4971 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-02-03T12:22:33.672+01:00  INFO 4971 --- [           main] c.c.tuitechtest.TuiTechtestApplication   : Started TuiTechtestApplication in 3.225 seconds (process running for 4.359)
```
This is the indicator that the application started properly.

To use the application HTTP request should send to the localhost:8080 ( 8080 is the default springboot application port )

## Examples

#### Example with `existing username` and `proper Accept header`:
```
curl --location --request GET 'http://localhost:8080/api/repositories/ropman' \
--header 'Accept: application/json'
```
and the response from the application [ `status code 200` - `OK` ]
```json response
[
    {
        "repositoryName": "test",
        "ownerLogin": "ropman",
        "branchInfo": [
            {
                "name": "main",
                "lastCommitSha": "d32d0f383d6aacdce47941de8836ed3987481ac1"
            },
            {
                "name": "test",
                "lastCommitSha": "0f80ffe544f9dac14cd25c8541c9b0a7318ff1ac"
            }
        ]
    }
]
```
---
#### example with `non-existing username` and `proper Accept header`:
```
curl --location --request GET 'http://localhost:8080/api/repositories/ropman_not_existing_username' \
--header 'Accept: application/json'
```
and the response from the application [ `status code 404` - `NOT_FOUND` ]
```json response
{
    "status": 404,
    "Message": "Repositories are not available for ropman_not_existing_username"
}
```
---
#### example with `existing username` but `not proper Accept header`:
```
curl --location --request GET 'http://localhost:8080/api/repositories/ropman' \
--header 'Accept: application/xml'
```
and the response from the application [ `status code 406` - `NOT_ACCEPTABLE` ]
```json response
{
    "status": 406,
    "Message": "No acceptable representation"
}
```
---

##FAQ - Troubleshooting:
- internet connection is not available for download / refresh maven dependencies
- internet connection is not available for reach github
- possible problems 8080 port already used

### Possible future improvements:
- making the API non blocking
- adding caching ( ttl and memory size retaliation ) to mitigate the call limit on the client ( github ) 
- full test coverage
- adding authentication option to have extended call limits on the client ( github )
