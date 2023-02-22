# 🎯 Deploy Spring Boot Application step-by-step guide

---
## 1️⃣ Create Spring Boot Application
### ✅ Demo: [Application](https://github.com/Aqilzeka/employee-ms)

---
## 2️⃣ Clean and install your application
### ✅ Run: `mvn clean install -D maven.test.skip=true`

---
## 3️⃣ Add Dockerfile in your root folder
### ✅ Demo: [Dockerfile](Dockerfile)

---
## 4️⃣ Add spring boot application in your local docker
### ✅ Run: `docker build -t your_docker_name/employees:0.0.1.RELEASE .`

---
## 5️⃣ Push spring boot application in your docker hub
### ✅ Run: `docker push docker.io/your_docker_name/employees:0.0.1.RELEASE`

---
## 6️⃣ Create free account in google cloud
### ✅ Video: [Create Google Cloud Account](https://www.youtube.com/watch?v=KcHx5dXaDtk&ab_channel=CloudSprint)

---
## 7️⃣ Create Google Kubernetes Engine (GKE) Cluster
### ✅ Video: [How to create a Google Kubernetes Engine (GKE) Cluster | Google Cloud Platform](https://www.youtube.com/watch?v=3EF4fXZcTDg&ab_channel=DailyCodeBuffer)

---
## 8️⃣ Take a break and have a coffee

---
## 9️⃣ Connect kubernetes cluster and deploy your application
### ✅ Run in Cloud Shell Editor: `kubectl create deployment employees --image=your_docker_name/employees:0.0.1.RELEASE`

---
## 1️⃣0️⃣ Expose this deployment outside the world
### ✅ Run: `kubectl expose deployment employees --type=LoadBalancer --port=8080`

---
# 🎉🎊🎇 Congratulations, your application open the world right now !!! 