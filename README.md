# 🚪 Roamly-GPS API Gateway

The **API Gateway** serves as the single entry point for all client requests within the **Roamly-GPS** microservice system.  
It routes and manages traffic between the **UI** and the backend microservices while handling load balancing and service discovery through **Spring Cloud Gateway** and **Eureka**.

---

## 🧭 Branch Overview

- **`main`**  
  Contains the **production-ready code** used for **cloud-native deployment in Kubernetes**.  
  This version integrates with other microservices in the organization’s architecture and was deployed as part of the full distributed system.

- **`local-setup`**  
  Contains the configuration required to **run the API Gateway locally** for development or testing purposes.  
  In this setup, the gateway communicates with the **Eureka Service Registry Server** microservice (available in the organization repository) to discover backend services.

---

## ⚙️ Tech Stack

- **Java 17**  
- **Spring Boot 3**  
- **Spring Cloud Gateway**  
- **Spring Cloud Netflix Eureka Client**  
- **Spring WebFlux (Reactive)**

---

## 🔗 Service Integration

In both configurations (Kubernetes and local):
- The gateway acts as the **reverse proxy** between the UI and backend services.
- In the **local setup**, it fetches the registry of available services from the [**Eureka Service Registry**](https://github.com/Roamly-GPS-application/eureka-service-registry-server) microservice.

---

## ⚠️ Important Note

The gateway code in the **`main`** branch is not intended to be executed locally,  
as it relies on a Kubernetes environment and other cloud-hosted components.   

For local development or testing, switch to the **`local-setup`** branch:
```bash
 git checkout local-setup
 ```
---

## 🚀 Getting Started

### 1. Clone the repo

```
$ git clone https://github.com/Roamly-GPS-application/api-gateway.git
$ cd api-gateway
```

### 2. Set up environment variables

**Step 1.** Configure the default URL for connecting the Gateway to the Eureka Service Registry in **`application.properties`** by setting the default zone for your Eureka server:

```
eureka.client.service-url.defaultZone=http://${EUREKA_USERNAME}:${EUREKA_PASSWORD}@localhost:8761/eureka
```

**Step 2.** Create an **`eureka.properties`** file
Inside `src/main/resources`, create a file named eureka.properties and add your Eureka credentials, for example:

```properties
   EUREKA_USERNAME=admin
   EUREKA_PASSWORD=admin123
```

### 3. Clone the repo for **Eureka Service Registry Server** microservice

Since the API Gateway relies on the Eureka Service Registry for service discovery, clone and run that microservice locally:

```
$ git clone https://github.com/Roamly-GPS-application/location-and-poi-service.git
```
---

## 🛠️ Related Repositories

- [**Eureka Service Registry**](https://github.com/Roamly-GPS-application/eureka-service-registry-server) – handles service discovery and registration.  

- [**UI-Service**](https://github.com/Roamly-GPS-application/ui-service) – frontend application that performs requests to the gateway.  

- [**Location-and-poi-service**](https://github.com/Roamly-GPS-application/location-and-poi-service) - provides geographical coordinate data for desired points of interest (POIs) by fetching it from the external **Geoapify API**.

- [**Travel-group-service**](https://github.com/Roamly-GPS-application/travel-groups-service) – handles travel group creation and management.
