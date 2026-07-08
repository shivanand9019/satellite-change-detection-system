# Satellite Change Detection Platform

## Project Overview

Satellite Change Detection Platform is a microservices-based backend application developed to monitor crop health using simulated satellite NDVI (Normalized Difference Vegetation Index) data.

The system follows an event-driven architecture where field analysis requests are published through Kafka, processed by backend services, classified using a Python FastAPI service, and finally stored as alerts in PostgreSQL.

The main objective of this project is to identify crop growth, crop stress, and no significant changes between two satellite observation dates.

---

## Features

- Event-driven communication using Apache Kafka
- NDVI change detection using simulated satellite data
- Crop growth and crop stress classification
- FastAPI integration for classification service
- Alert generation based on vegetation changes
- PostgreSQL database integration
- Dockerized infrastructure
- Microservices architecture

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Data JPA
- Apache Kafka
- PostgreSQL

### Classification Service
- Python
- FastAPI
- Uvicorn

### Infrastructure
- Docker
- Docker Compose

---

## System Architecture

```text
Ingestion Service (Spring Boot)
            |
            | Kafka Event
            v
Change Detection Service (Spring Boot)
            |
            | REST API Call
            v
Classification Service (FastAPI)
            |
            v
Alert Generation
            |
            v
PostgreSQL Database
```

---

## Project Workflow

### Step 1: Ingestion Service

The Ingestion Service receives a field analysis request containing:

- Field ID
- Date 1
- Date 2

Example:

```json
{
  "fieldId": 1,
  "date1": "2026-06-01",
  "date2": "2026-06-10"
}
```

The request is published as a Kafka event.

---

### Step 2: Change Detection Service

The Change Detection Service consumes Kafka events and generates simulated NDVI values for both dates.

Example:

```text
Date 1 NDVI -> [0.45, 0.62, 0.33]
Date 2 NDVI -> [0.70, 0.40, 0.35]
```

NDVI difference is calculated using:

```text
Delta NDVI = NDVI(Date2) - NDVI(Date1)
```

---

### Step 3: Classification Service

The calculated delta values are sent to a FastAPI service.

Classification Rules:

```text
Delta > 0.15      -> Crop Growth
Delta < -0.15     -> Crop Stress
Otherwise         -> No Change
```

Example:

```json
Input:
[0.25, -0.22, 0.05]

Output:
[
  "crop_growth",
  "crop_stress",
  "no_change"
]
```

---

### Step 4: Alert Generation

Based on the classification results, alerts are generated.

Rules:

```text
Stress Percentage > 20%
    -> CRITICAL Alert

Growth Percentage > 30%
    -> POSITIVE Alert

Otherwise
    -> NORMAL Alert
```

Example Alert:

```text
Severity : CRITICAL
Message  : Crop Stress Detected
```

---

## Database Schema

### Fields Table

```sql
CREATE TABLE fields (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Alerts Table

```sql
CREATE TABLE alerts (
    id BIGSERIAL PRIMARY KEY,
    field_id BIGINT,
    severity VARCHAR(20),
    message TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Sample Output

```text
Classification Result:
[crop_growth, crop_stress, no_change]

Growth Pixels: 156 (39.00%)
Stress Pixels: 102 (25.50%)
No Change Pixels: 142 (35.50%)

========== ALERT GENERATED ==========
Severity : CRITICAL
Message  : Crop Stress Detected
=====================================
```

---

## How to Run the Project

### 1. Start Docker Services

```bash
docker compose up -d
```

This starts:

- PostgreSQL
- Kafka
- Zookeeper

---

### 2. Start Classification Service

```bash
cd classification-service

uvicorn main:app --reload --port 8000
```



---

### 3. Start Spring Boot Services

Run the following services:

```text
ingestion-service
change-detection-service
```

---

### 4. Trigger Analysis

Send a request to the ingestion endpoint.

The request will flow through:

```text
Ingestion Service
      ↓
Kafka
      ↓
Change Detection Service
      ↓
FastAPI Classification Service
      ↓
Alert Generation
      ↓
PostgreSQL
```

---

## Learning Outcomes

Through this project, I learned:

- Microservices architecture
- Event-driven communication using Kafka
- REST API integration between Spring Boot and FastAPI
- Database persistence using PostgreSQL
- Docker containerization
- NDVI-based vegetation change analysis
- Alert generation and monitoring workflows

---

## Future Improvements

- Integration with real satellite imagery
- React Native mobile application
- Interactive dashboards and analytics
- Real-time notifications
- Machine Learning based crop health prediction
- Multi-field monitoring support

---

## Author

Shivanand Madar

B.E. Information Science and Engineering

Spring Boot | Java | PostgreSQL | Kafka | FastAPI
