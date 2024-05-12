### Cloud Technologies
![docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)
![kubernetes](https://img.shields.io/badge/kubernetes-326ce5.svg?&style=for-the-badge&logo=kubernetes&logoColor=white)
![terraform](https://img.shields.io/badge/Terraform-7B42BC?style=for-the-badge&logo=terraform&logoColor=white)
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)

### Frontend Technologies
![html](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)


### Backend Technologies
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![springboot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)


## Why a ScraperApp?

I decided to build a webScraper for African E-commerce WebSites Because i was tired of looking for and article i want to buy for long and at a better price. There are somany other Price comparators but non for the African E-commerce Market. So i thought it important to creat one.  I will further develop it so it could be i use some and help you save some time.


## Instances

TODO



## Repositories:
[https://github.com/njike2000/WebScraper/tree/master](https://github.com/njike2000/WebScraper/tree/master)


## Deployment:

The deployment is seperated and documented in this repository [https://github.com/njike2000/WebScraper/tree/master](https://github.com/njike2000/WebScraper/tree/master)

## Observability

TODO


## Services

The backend consists of 3 services:

`Poi Service`: The Scraper Service responsible for Scraping the website.

`User Service`: The User Service handles user-related operations, including authentication, user profiles, and access control within the platform.




## Changelog/Releasenotes

TODO

## CI/CD


### Build-push-image Workflow
I used Github Actions to build the projects/dockerfiles and pusht them to the Github Container Registry.
Currently ther are 2 published containers:
- `docker-compose`for the projekt
- `docker-file`for the datebase


## Credits 

Thanks to the prometheus communuity for providing helpful helmcharts [kube-prometheus-stack](https://github.com/prometheus-community/helm-charts/tree/main/charts/kube-prometheus-stack ).

When it comes to terraform and gke we can recommand this guide which helped us a lot: [https://dev.to/admantium/google-kubernetes-engine-mostly-automated-installation-with-terraform-47dg](https://dev.to/admantium/google-kubernetes-engine-mostly-automated-installation-with-terraform-47dg) 

This guide is also very good:[cert-manager-gke-guide](https://cert-manager.io/docs/tutorials/getting-started-with-cert-manager-on-google-kubernetes-engine-using-lets-encrypt-for-ingress-ssl/#7-create-an-issuer-for-lets-encrypt-staging)
