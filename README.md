Protein Visualizer Pro

Protein Visualizer Pro is a web-based bioinformatics project that allows users to visualize 3D protein structures, switch between multiple molecular display styles, and analyse protein-protein interactions.

Built using **HTML, CSS, JavaScript, 3Dmol.js, Java Spring Boot, Docker, GitHub, and Render**.

---

 Live Demo

🔗 https://protein-project-yaqa.onrender.com

---

Features

### 🔬 Protein Structure Visualization
- Load any protein using its **PDB ID**
- Fetches real protein structures from the **RCSB Protein Data Bank**
- Interactive 3D rotation / zoom / movement

### 🎨 Multiple Display Modes
- Cartoon View  
- Ball-and-Stick View  
- Surface View

### 🔗 Protein Interaction Analysis
- Enter two protein IDs
- Simulated interaction strength analysis using backend API

### 🌐 Full Stack Deployment
- Frontend + Backend connected live
- Hosted using **Render**
- Version controlled with **GitHub**

---

## 🛠️ Technologies Used

### Frontend
- HTML5
- CSS3
- JavaScript

### Visualization
- 3Dmol.js

### Backend
- Java
- Spring Boot
- Maven

### Deployment
- Docker
- Render

### Version Control
- Git
- GitHub

---

## 📂 Project Structure

```bash
protein-project/
│── frontend/
│   └── index.html
│
│── backend/
│   ├── src/main/java/app/
│   │   ├── Application.java
│   │   └── ProteinController.java
│   ├── pom.xml
│   └── Dockerfile
