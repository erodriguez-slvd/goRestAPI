<!-- PROJECT LOGO -->
<div align="center">
  <h1 align="center">Go Rest API</h1>
  <p align="center">API Testing Project</p>
</div>

<!-- TABLE OF CONTENTS -->
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
   <li><a href="#implementation">Implementation details</a></li>
   <li><a href="#useful documentation">Useful Documentation</a></li>
  </ol>

<!-- ABOUT THE PROJECT -->
## About The Project

The project focuses on testing the Go Rest API, using core testing tools such as Java, and RestAssured Framework. The goal of the project is to improve testing skills and gain experience in api testing.

### Built With

* Programming Language: Java
* Build Tool: Maven
* Frameworks: Rest-assured

### Prerequisites

- [ ] Install Java +11
- [ ] Install and configure [Apache Maven 3.6.0+](http://maven.apache.org/)
- [ ] Add the testng, rest-assured, httpclient, json-schema-validator dependencies to the pom.xml

### Installation

- [ ] Clone the repo.
- [ ] Run mvn clean install in terminal.
- [ ] Modify the api.properties located in src>main>java>resources with your personal bearer token.

<!-- IMPLEMENTATION DETAILS -->
## Implementation details

[//]: # (### JsonSchema Validation)

[//]: # (- When you need to validate response structure regardless of the actual values, you may use validation by JSON schema.)

[//]: # (1. In this case, you need an actual response from the service.)

[//]: # (2. Now we need to generate a schema, you can use an <a href="https://www.liquid-technologies.com/online-json-to-schema-converter">online JSON schema Generator</a>. You need to provide the original JSON from the response, then choose some schema options &#40;allow the additional properties in objects, mark the current object properties as required, hard-code some expected values, etc.&#41; and then generate the schema.)

[//]: # (3. Copy-paste the generated schema into test resources as a .schema file, and you're ready to use it in the test providing the file path.)

[//]: # ()
[//]: # (### Authorization)

[//]: # (- Some endpoints may require authentication. To submit or view an order, you need to register your API client and obtain an access token. The endpoints that require authentication expect a bearer token sent in the Authorization header.)

[//]: # (- Once you get your Bearer Token, you can send it in Header Request.)

[//]: # (```)

[//]: # (@Header&#40;key = "Authorization", value = token&#41;)

[//]: # (```)

<!-- USEFUL DOCUMENTATION -->
## Useful Documentation

* [GoRestAPI Documentation](https://gorest.co.in/)
* [RestAssured](https://rest-assured.io/)
* [TestNG](https://testng.org/doc/documentation-main.html)
* [JsonSchema Generator](https://www.liquid-technologies.com/online-json-to-schema-converter)