
<h1 align="center">Jenkins Shared Library – Profile App CI/CD Automation</h1>

<p align="center">
  <strong>End-to-End CI/CD Pipeline using Jenkins, Groovy, Docker, Docker Hub and Docker Compose</strong>
</p>

<hr>

<h2>1. Project Overview</h2>

<p>
This project demonstrates how to automate the build, packaging, publishing, and deployment of a full-stack web application using Jenkins, Docker, and a Jenkins Shared Library.
</p>

<p>
The project uses a <strong>Jenkins Shared Library (Groovy)</strong> to define reusable pipeline logic. Instead of writing the entire CI/CD pipeline directly inside a Jenkinsfile, the pipeline logic is maintained separately and can be called from a Jenkins pipeline.
</p>

<p>
The application being deployed is the Profile App, maintained in a separate GitHub repository.
</p>

<h3>Repositories</h3>

<table>
  <thead>
    <tr>
      <th>Repository</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        <a href="https://github.com/12pavani/jenkins-gsl-profileApp">
          jenkins-gsl-profileApp
        </a>
      </td>
      <td>Contains the Jenkins Shared Library and pipeline implementation.</td>
    </tr>
    <tr>
      <td>
        <a href="https://github.com/12pavani/Profile_App">
          Profile_App
        </a>
      </td>
      <td>Contains the frontend, backend, and Docker Compose application configuration.</td>
    </tr>
  </tbody>
</table>

<h2>2. What Does This Project Do?</h2>

<p>
The project automates the process of taking application source code from GitHub and deploying it using Docker.
</p>

<p>
Whenever a Jenkins build is triggered, the pipeline performs the following operations:
</p>

<ol>
  <li>Cleans the Jenkins workspace.</li>
  <li>Checks whether Docker is accessible.</li>
  <li>Checks out the application source code from GitHub.</li>
  <li>Collects Git commit and build information.</li>
  <li>Builds the frontend Docker image.</li>
  <li>Builds the backend Docker image.</li>
  <li>Verifies the generated Docker images.</li>
  <li>Tags the images with the Docker Hub username and Jenkins build number.</li>
  <li>Authenticates with Docker Hub.</li>
  <li>Pushes both images to Docker Hub.</li>
  <li>Uses Docker Compose to deploy the application.</li>
  <li>Performs health checks on the frontend and backend.</li>
  <li>Collects and archives Docker container logs.</li>
  <li>Cleans up unused Docker images.</li>
  <li>Sends an email notification about the build result.</li>
</ol>

<p>
The pipeline is implemented in Groovy and organized into individual Jenkins stages.
</p>

<h2>3. Why Was This Project Built?</h2>

<p>
In a manual deployment process, developers or DevOps engineers may need to:
</p>

<ul>
  <li>Pull the latest application code.</li>
  <li>Build frontend and backend images separately.</li>
  <li>Tag the images.</li>
  <li>Log in to a container registry.</li>
  <li>Push images to the registry.</li>
  <li>Start or update application containers.</li>
  <li>Verify whether the application is accessible.</li>
  <li>Investigate errors using container logs.</li>
</ul>

<p>
Performing these activities manually for every application change can be time-consuming and error-prone.
</p>

<p>
This project automates these operations through Jenkins.
</p>

<p>
The objective is to demonstrate a repeatable CI/CD workflow in which Jenkins coordinates the application build, Docker image creation, registry publishing, deployment, and basic verification.
</p>

<h2>4. Technologies Used</h2>

<table>
  <thead>
    <tr>
      <th>Technology</th>
      <th>Purpose in This Project</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Git</td>
      <td>Version control.</td>
    </tr>
    <tr>
      <td>GitHub</td>
      <td>Hosts the application source code and shared library.</td>
    </tr>
    <tr>
      <td>Jenkins</td>
      <td>Automates the CI/CD workflow.</td>
    </tr>
    <tr>
      <td>Groovy</td>
      <td>Implements the shared library pipeline logic.</td>
    </tr>
    <tr>
      <td>Jenkins Shared Library</td>
      <td>Separates reusable pipeline logic from the Jenkinsfile.</td>
    </tr>
    <tr>
      <td>Docker</td>
      <td>Packages the frontend and backend into container images.</td>
    </tr>
    <tr>
      <td>Docker Hub</td>
      <td>Stores and distributes Docker images.</td>
    </tr>
    <tr>
      <td>Docker Compose</td>
      <td>Starts and manages the application services.</td>
    </tr>
    <tr>
      <td>Shell Scripting</td>
      <td>Executes Docker, Git, and curl commands.</td>
    </tr>
    <tr>
      <td>curl</td>
      <td>Checks application endpoint availability.</td>
    </tr>
    <tr>
      <td>Jenkins Email Extension</td>
      <td>Sends build notifications.</td>
    </tr>
  </tbody>
</table>

<h2>5. High-Level Architecture</h2>

<p>
The following diagram illustrates the overall CI/CD workflow:
</p>

<pre>
                 Developer
                     |
                     | Pushes application changes
                     v
                  GitHub
                     |
                     | Jenkins build trigger
                     v
                  Jenkins
                     |
                     | Loads Shared Library
                     v
            BuildDeployPipeline()
                     |
            +--------+--------+
            |                 |
            v                 v
      Build Frontend     Build Backend
            |                 |
            v                 v
      Docker Image       Docker Image
            |                 |
            +--------+--------+
                     |
                     v
                 Tag Images
                     |
                     v
                 Docker Login
                     |
                     v
                 Docker Hub
                     |
                 Push Images
                     |
                     v
              Docker Compose
                     |
            +--------+--------+
            |                 |
            v                 v
         Frontend           Backend
         Port 3000          Port 8000
            |                 |
            v                 v
       Health Check      Health Check
            |                 |
            +--------+--------+
                     |
                     v
                Archive Logs
                     |
                     v
             Email Notification
</pre>

<p>
<strong>Note:</strong> The diagram represents the intended pipeline workflow. The exact Jenkins trigger configuration, shared-library loading configuration, and Docker Compose service definitions must be documented separately.
</p>

<h2>6. Understanding the Two Repositories</h2>

<p>
This project uses two separate GitHub repositories because the application code and CI/CD automation logic serve different purposes.
</p>

<h3>Repository 1: Jenkins Shared Library</h3>

<p>
<a href="https://github.com/12pavani/jenkins-gsl-profileApp">
  12pavani/jenkins-gsl-profileApp
</a>
</p>

<p>
This repository contains the reusable Jenkins pipeline implementation.
</p>

<p>Its responsibility is to define:</p>

<ul>
  <li>Pipeline stages.</li>
  <li>Docker build commands.</li>
  <li>Image tagging and publishing.</li>
  <li>Deployment commands.</li>
  <li>Health checks.</li>
  <li>Log archiving.</li>
  <li>Email notifications.</li>
</ul>

<h3>Repository 2: Application Source Code</h3>

<p>
<a href="https://github.com/12pavani/Profile_App">
  12pavani/Profile_App
</a>
</p>

<p>
This repository contains the actual application that Jenkins builds and deploys.
</p>

<p>
The supplied pipeline checks out this repository:
</p>

<pre><code>url: 'https://github.com/12pavani/Profile_App.git'</code></pre>

<p>
It then builds the application images using:
</p>

<pre><code>docker build -t profile-frontend:${BUILD_NUMBER} frontend

docker build -t profile-backend:${BUILD_NUMBER} backend</code></pre>

<p>
This separation makes it possible to maintain application code independently from CI/CD pipeline logic.
</p>

<h2>7. Expected Outcome</h2>

<p>After a successful pipeline execution:</p>

<ul>
  <li>The frontend Docker image is built.</li>
  <li>The backend Docker image is built.</li>
  <li>Both images are tagged with the Jenkins build number.</li>
  <li>Both images are pushed to Docker Hub.</li>
  <li>Docker Compose starts the application services.</li>
  <li>The pipeline checks the frontend and backend endpoints.</li>
  <li>Docker logs are archived in Jenkins.</li>
  <li>An email notification is sent with the build result.</li>
</ul>

<h3>Application Endpoints</h3>

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>URL</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Frontend</td>
      <td><a href="http://localhost:3000">http://localhost:3000</a></td>
    </tr>
    <tr>
      <td>Backend</td>
      <td><a href="http://localhost:8000/profile">http://localhost:8000/profile</a></td>
    </tr>
  </tbody>
</table>
