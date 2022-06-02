<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/Mahzarasua/resume-api-modules">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Resume API Multi Modules</h3>

  <p align="center">
    CRUD operations for Resume API
    <br />
    <a href="https://github.com/Mahzarasua/resume-api-modules">View Demo</a>
    ·
    <a href="https://github.com/Mahzarasua/resume-api-modules/issues">Report Bug</a>
    ·
    <a href="https://github.com/Mahzarasua/resume-api-modules/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

REST API Modules is a multi modules project. The goal is to manage Resumes
so a Front End application can create, read, update or delete the information.

The main goal was to implement the concepts of microservices and have them interact
with each other.

Adding security for JWT and encryption and decryption for config server, 
have all the services registering with the discovery service and gather metrics
using zipkin service. Utilize messaging services like RabbitMQ, ActiveMQ and Kafka.

The service interacts with Docker, a compose file has been included to get the images 
required for the services to work out of the box.

A bit of manual work is required to create the tables in the database and insert the initial
data needed by the services. Instructions were included on how to perform this task.

This is an initial draft, yet there are many ideas waiting to be implemented.


### Built With

* [Docker](https://docs.docker.com/get-docker/)
  * [Zipkin](https://zipkin.io/)
  * [PostgreSQL](https://www.postgresql.org/)
  * [pgAdmin](https://www.pgadmin.org/)
  * [RabbitMQ](https://www.rabbitmq.com/)
  * [Apache Kafka](https://kafka.apache.org/)
  * [Apache ZooKeeper](https://zookeeper.apache.org/)
  * [Apache ActiveMQ](https://activemq.apache.org/)
* [Springboot](https://spring.io/)
* [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
* [Intellij](https://www.jetbrains.com/idea/download/)
* [SB Banner Generator](https://devops.datenkollektiv.de/banner.txt/index.html)



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Mahzarasua/resume-api-modules.git
   ```
2. Install NPM packages
   ```sh
   npm install
   ```
3. Make sure Docker is installed and run the following command to get the images installed and started
  ```sh
  docker compose up -d --remove-orphans  
  ```
4. Check database_script directory and review notes.md file to see instructions related to database
5. Configuration files used by Config Server were created in a separate [repo](https://github.com/Mahzarasua/ConfigServerRepo/tree/main/config-data)
   1. Make sure the url matches to the one used in Config Server application.yaml file
6. Authorization api was created to authenticate users and generate the jwt token.
7. Resume, Education, Skills and WorkExperience services need authorization-api.jar as a dependency since it is used to authenticate and
expose an operation to authenticate as part of each service independently. In order to avoid duplicating code
authentication-api contains the model classes for the database, it also has the configuration classes that
are used by the other services.


<!-- USAGE EXAMPLES -->
## Usage

Once the tables have been created and the initial data has been inserted, this is the order 
recommended in which the services must be started.

1. Eureka-server
2. Config-server
3. Authentication
4. Resume
5. WorkExperience

The following services can be started after Authentication is running.

1. Education
2. Skills



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/Mahzarasua/resume-api-modules/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Your Name - [@mahzarasua](https://twitter.com/mahzarasua) - mahzarasua@outlook.com

Project Link: [https://github.com/Mahzarasua/resume-api-modules](https://github.com/Mahzarasua/resume-api-modules)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

* []()
* []()
* []()

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Mahzarasua/resume-api-modules.svg?style=for-the-badge
[contributors-url]: https://github.com/Mahzarasua/resume-api-modules/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Mahzarasua/resume-api-modules.svg?style=for-the-badge
[forks-url]: https://github.com/Mahzarasua/resume-api-modules/network/members
[stars-shield]: https://img.shields.io/github/stars/Mahzarasua/resume-api-modules.svg?style=for-the-badge
[stars-url]: https://github.com/Mahzarasua/resume-api-modules/stargazers
[issues-shield]: https://img.shields.io/github/issues/Mahzarasua/resume-api-modules.svg?style=for-the-badge
[issues-url]: https://github.com/Mahzarasua/resume-api-modules/issues
[license-shield]: https://img.shields.io/github/license/Mahzarasua/resume-api-modules.svg?style=for-the-badge
[license-url]: https://github.com/Mahzarasua/resume-api-modules/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/Mahzarasua
