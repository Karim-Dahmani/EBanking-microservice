# 🚀 EBanking Microservice - Spring Boot

## 📌 Description
Ce projet est un **système bancaire basé sur une architecture microservices**, permettant la **gestion des comptes bancaires, transactions et clients**. Il utilise **Spring Boot, Spring Cloud et Keycloak** pour garantir **scalabilité, sécurité et haute disponibilité**.

## 🏗️ Architecture Microservices
- **Customer Service** → Gestion des clients.
- **Bank Service** → Gestion des comptes et transactions.
- **Discovery Service (Eureka)** → Détection dynamique des services.
- **Gateway Service** → API Gateway pour le routage sécurisé.

## 🛠 Technologies Utilisées
- **Java 17** + **Spring Boot**
- **Spring Cloud (Eureka, Config, Gateway)**
- **Spring Security + Keycloak (OAuth2, JWT)**
- **PostgreSQL** (Base de données)
- **Kafka** (Communication interservices)
- **Docker & Kubernetes** (Déploiement)

## 📖 Endpoints API (Exemples)
### 🔹 1. Récupérer tous les clients
**GET** `/customers`
📌 **Réponse :** Liste des clients.

### 🔹 2. Créer un compte bancaire
**POST** `/accounts`
📌 **Réponse :** Détails du compte créé.

### 🔹 3. Effectuer une transaction
**POST** `/transactions`
📌 **Réponse :** Détails de la transaction.

## ✅ Installation et Exécution
1. **Cloner le projet** :
   ```sh
   git clone https://github.com/Karim-Dahmani/EBanking-microservice.git
   cd EBanking-microservice
   ```
2. **Construire et exécuter les services** :
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```
3. **Tester via Postman ou API Gateway (`http://localhost:8080`)**.

## 📌 Améliorations possibles
- 🔹 **Sécuriser les clés API avec Vault.**
- 🔹 **Ajouter un système de notifications (RabbitMQ/Kafka).**
- 🔹 **Optimiser avec un cache Redis.**

🚀 **Participez et améliorez l'API !**
