
<!-- PROJECT HEADER -->

<div align="center">

  <img src="https://capsule-render.vercel.app/api?type=waving&color=0:0072ff,100:00c6ff&height=190&section=header&text=Profile%20App%20CI%2FCD&fontSize=42&fontColor=ffffff&animation=fadeIn&fontAlignY=38" width="100%" alt="Profile App CI/CD banner"/>

  <h2>Automated CI/CD Pipeline Using Jenkins Shared Library</h2>

  <p>
    <img src="https://readme-typing-svg.demolab.com?font=Fira+Code&size=16&duration=2500&pause=800&color=00AEEF&center=true&vCenter=true&width=650&lines=Automated+Build+%7C+Test+%7C+Deploy;Jenkins+Shared+Libraries+%7C+Groovy;Docker+%7C+Docker+Compose+%7C+Docker+Hub;End-to-End+DevOps+Automation" alt="Animated project description"/>
  </p>

  <p>
    <a href="https://github.com/12pavani/Profile_App">
      <img src="https://img.shields.io/badge/Application-Repository-blue?logo=github" alt="Application repository"/>
    </a>
    <a href="https://github.com/12pavani/jenkins-gsl-profileApp">
      <img src="https://img.shields.io/badge/Jenkins_Shared_Library-Repository-orange?logo=jenkins" alt="Shared Library repository"/>
    </a>
    <img src="https://img.shields.io/badge/CI%2FCD-Automation-success" alt="CI/CD automation"/>
    <img src="https://img.shields.io/badge/Containerization-Docker-2496ED?logo=docker&logoColor=white" alt="Docker"/>
    <img src="https://img.shields.io/badge/Pipeline-Groovy-4298B8?logo=apachegroovy&logoColor=white" alt="Groovy"/>
  </p>

</div>

---

<h2>1. Project Overview</h2>

<p>
  This project demonstrates the implementation of an automated Continuous Integration and Continuous Deployment (CI/CD) pipeline for a containerized Profile Application using Jenkins and Jenkins Shared Libraries.
</p>

<p>
  The primary objective is to automate the application delivery process, starting from source code retrieval in GitHub to Docker image creation, publishing images to Docker Hub, and deploying the application using Docker Compose.
</p>

<p>
  Instead of manually building frontend and backend components, creating Docker images, tagging them, and deploying containers, the Jenkins pipeline performs these operations through a reusable Groovy-based Shared Library.
</p>

<p>
  The project also incorporates build-specific Docker image tagging, Jenkins credential management, application health checks, container log archiving, cleanup operations, and email notifications.
</p>

<h3>What does this project automate?</h3>

<table>
  <thead>
    <tr>
      <th>Stage</th>
      <th>Operation</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Source Checkout</td>
      <td>GitHub checkout</td>
      <td>Retrieve application source code.</td>
    </tr>
    <tr>
      <td>Build</td>
      <td>Docker image creation</td>
      <td>Build separate frontend and backend images.</td>
    </tr>
    <tr>
      <td>Verification</td>
      <td>Docker image inspection</td>
      <td>Check the locally built application images.</td>
    </tr>
    <tr>
      <td>Versioning</td>
      <td>Docker image tagging</td>
      <td>Associate images with Jenkins build numbers.</td>
    </tr>
    <tr>
      <td>Authentication</td>
      <td>Jenkins Credentials</td>
      <td>Authenticate securely with Docker Hub.</td>
    </tr>
    <tr>
      <td>Publishing</td>
      <td>Docker push</td>
      <td>Upload application images to Docker Hub.</td>
    </tr>
    <tr>
      <td>Deployment</td>
      <td>Docker Compose</td>
      <td>Start frontend and backend containers.</td>
    </tr>
    <tr>
      <td>Validation</td>
      <td>Health checks</td>
      <td>Verify application endpoint availability.</td>
    </tr>
    <tr>
      <td>Monitoring</td>
      <td>Log archiving and email</td>
      <td>Retain execution logs and communicate build results.</td>
    </tr>
  </tbody>
</table>

<h3>Key Objectives</h3>

<ul>
  <li>Implement a reusable Jenkins Shared Library instead of keeping all pipeline logic inside individual Jenkinsfiles.</li>
  <li>Automate the complete Docker image build and publishing workflow.</li>
  <li>Use Jenkins credentials to avoid hardcoding sensitive authentication details.</li>
  <li>Implement build-specific Docker image tags for traceability.</li>
  <li>Automate multi-container application deployment using Docker Compose.</li>
  <li>Validate deployment using HTTP health checks and retry logic.</li>
  <li>Improve pipeline visibility through archived logs and email notifications.</li>
</ul>

---

<h2>2. Project Architecture</h2>

<p>
  The architecture consists of GitHub repositories, Jenkins, a reusable Jenkins Shared Library, Docker, Docker Hub, and Docker Compose.
</p>

<p>
  The application source code and pipeline implementation are maintained separately. Jenkins uses the Shared Library to execute the CI/CD workflow against the application repository.
</p>

<h3>2.1 High-Level Architecture</h3>

<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=rect&color=0:232526,100:414345&height=45&section=header&text=CI%2FCD%20Architecture&fontColor=ffffff&fontSize=20" width="100%" alt="Architecture heading"/>
</div>

<pre>
                    DEVELOPER
                        |
                        v
                 APPLICATION CODE
                        |
                        v
                 GITHUB REPOSITORY
                  Profile_App
                        |
                        v
                JENKINS PIPELINE
                        |
                        v
              JENKINS SHARED LIBRARY
                 Groovy Pipeline
                        |
                        v
                SOURCE CODE CHECKOUT
                        |
             +----------+----------+
             |                     |
             v                     v
        FRONTEND                BACKEND
             |                     |
             v                     v
       DOCKER BUILD            DOCKER BUILD
             |                     |
             v                     v
     FRONTEND IMAGE           BACKEND IMAGE
             |                     |
             +----------+----------+
                        |
                        v
                 DOCKER IMAGE TAGGING
                  BUILD_NUMBER
                        |
                        v
                 DOCKER HUB LOGIN
                        |
                        v
                  DOCKER HUB PUSH
                        |
             +----------+----------+
             |                     |
             v                     v
      FRONTEND REPOSITORY   BACKEND REPOSITORY
             |                     |
             +----------+----------+
                        |
                        v
                DOCKER COMPOSE
                        |
             +----------+----------+
             |                     |
             v                     v
       FRONTEND CONTAINER   BACKEND CONTAINER
          PORT 3000            PORT 8000
             |                     |
             +----------+----------+
                        |
                        v
                 HEALTH CHECKS
                        |
                        v
             LOG ARCHIVING & CLEANUP
                        |
                        v
                EMAIL NOTIFICATION
</pre>

<h3>2.2 Component Responsibilities</h3>

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Responsibility</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GitHub</td>
      <td>Hosts application source code and Jenkins Shared Library code.</td>
    </tr>
    <tr>
      <td>Jenkins</td>
      <td>Orchestrates and executes the CI/CD pipeline.</td>
    </tr>
    <tr>
      <td>Jenkins Shared Library</td>
      <td>Contains reusable Groovy pipeline logic.</td>
    </tr>
    <tr>
      <td>Groovy</td>
      <td>Defines pipeline execution, stages, conditions, and error handling.</td>
    </tr>
    <tr>
      <td>Docker</td>
      <td>Builds and runs container images.</td>
    </tr>
    <tr>
      <td>Docker Hub</td>
      <td>Stores the published frontend and backend images.</td>
    </tr>
    <tr>
      <td>Docker Compose</td>
      <td>Manages the frontend and backend application containers together.</td>
    </tr>
    <tr>
      <td>ngrok</td>
      <td>Exposes the local Jenkins server through a public endpoint for webhook integration.</td>
    </tr>
  </tbody>
</table>

<h3>2.3 Application Components</h3>

<p>
  The Profile App is divided into two independently containerized components.
</p>

<h4>Frontend</h4>

<ul>
  <li>Provides the application's user interface.</li>
  <li>Runs in its own Docker container.</li>
  <li>Exposed through localhost port <code>3000</code>.</li>
  <li>Uses the Docker image repository <code>profile-frontend</code>.</li>
</ul>

<h4>Backend</h4>

<ul>
  <li>Handles backend application logic and profile-related API requests.</li>
  <li>Runs in a separate Docker container.</li>
  <li>Exposed through localhost port <code>8000</code>.</li>
  <li>Uses the Docker image repository <code>profile-backend</code>.</li>
</ul>

<p>
  Separating frontend and backend into individual containers allows each component to be built, packaged, and managed independently.
</p>

<h3>2.4 Why Use a Jenkins Shared Library?</h3>

<p>
  A Jenkins Shared Library allows pipeline logic to be maintained in a separate Git repository and reused by Jenkins jobs.
</p>

<p>
  In this project, the pipeline implementation is organized around the Groovy method:
</p>

<pre><code>BuildDeployPipeline()</code></pre>

<p>
  The method belongs to the package:
</p>

<pre><code>com.pavani.pipeline</code></pre>

<p>
  Rather than duplicating all build and deployment operations inside a Jenkinsfile, the job can invoke the reusable pipeline implementation through the configured Shared Library.
</p>

<p><strong>Benefits:</strong></p>

<ul>
  <li>Centralized pipeline implementation.</li>
  <li>Code reuse across Jenkins jobs.</li>
  <li>Easier maintenance and updates.</li>
  <li>Separation of application code and CI/CD logic.</li>
  <li>Improved consistency across pipeline executions.</li>
</ul>

---

<h2>3. Repositories and Project Source Code</h2>

<p>
  The project uses two separate GitHub repositories to maintain a clear separation between the application and its CI/CD implementation.
</p>

<h3>3.1 Application Repository</h3>

<p>
  <a href="https://github.com/12pavani/Profile_App">
    <strong>Profile_App – GitHub Repository</strong>
  </a>
</p>

<p>
  This repository contains the application source code used by Jenkins during the build and deployment process.
</p>

<p>Important application components:</p>

<ul>
  <li><code>frontend/</code> – Frontend application and its Docker build context.</li>
  <li><code>backend/</code> – Backend application and its Docker build context.</li>
  <li><code>docker-compose.yaml</code> – Docker Compose configuration used to deploy the application containers.</li>
</ul>

<h3>3.2 Jenkins Shared Library Repository</h3>

<p>
  <a href="https://github.com/12pavani/jenkins-gsl-profileApp">
    <strong>jenkins-gsl-profileApp – GitHub Repository</strong>
  </a>
</p>

<p>
  This repository contains the reusable Jenkins pipeline implementation written in Groovy.
</p>

<p>
  Its main pipeline method is <code>BuildDeployPipeline()</code>, which implements the build, image publishing, deployment, validation, cleanup, and notification workflow.
</p>

<p>
  The exact Jenkins Shared Library folder layout and Jenkinsfile invocation should be checked against the repository's current contents and Jenkins configuration.
</p>

<h3>3.3 Why Maintain Two Repositories?</h3>

<table>
  <thead>
    <tr>
      <th>Application Repository</th>
      <th>Shared Library Repository</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Contains frontend and backend source code.</td>
      <td>Contains reusable pipeline implementation.</td>
    </tr>
    <tr>
      <td>Contains Docker build contexts.</td>
      <td>Contains Groovy-based CI/CD logic.</td>
    </tr>
    <tr>
      <td>Contains Docker Compose configuration.</td>
      <td>Can be maintained independently of application changes.</td>
    </tr>
    <tr>
      <td>Used as the application checkout source.</td>
      <td>Configured as a Jenkins Shared Library.</td>
    </tr>
  </tbody>
</table>

---

<h2>4. Technology Stack and Tools</h2>

<p>
  The following technologies are used to implement and execute the CI/CD workflow.
</p>

<div align="center">
  <img src="https://skillicons.dev/icons?i=jenkins,groovy,git,github,docker,bash,linux,ngrok" alt="Technology stack icons" />
</div>

<table>
  <thead>
    <tr>
      <th>Technology</th>
      <th>How It Is Used</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Jenkins</strong></td>
      <td>Automates the complete CI/CD workflow.</td>
    </tr>
    <tr>
      <td><strong>Groovy</strong></td>
      <td>Implements reusable pipeline logic and Jenkins Pipeline steps.</td>
    </tr>
    <tr>
      <td><strong>Git</strong></td>
      <td>Manages source code and tracks application revisions.</td>
    </tr>
    <tr>
      <td><strong>GitHub</strong></td>
      <td>Hosts application and Shared Library repositories.</td>
    </tr>
    <tr>
      <td><strong>Docker</strong></td>
      <td>Builds frontend and backend container images.</td>
    </tr>
    <tr>
      <td><strong>Docker Compose</strong></td>
      <td>Starts and manages the multi-container application.</td>
    </tr>
    <tr>
      <td><strong>Docker Hub</strong></td>
      <td>Stores versioned application images.</td>
    </tr>
    <tr>
      <td><strong>Bash / Shell</strong></td>
      <td>Executes Docker, Git, curl, and system commands from Jenkins.</td>
    </tr>
    <tr>
      <td><strong>ngrok</strong></td>
      <td>Provides a public endpoint to reach the local Jenkins server for webhook delivery.</td>
    </tr>
  </tbody>
</table>

<h3>4.1 Jenkins Plugins and Integrations</h3>

<p>
  The pipeline relies on Jenkins Pipeline functionality, Git integration, credentials management, and email notification support.
</p>

<ul>
  <li><strong>Pipeline:</strong> Executes pipeline scripts and Shared Library code.</li>
  <li><strong>Git:</strong> Provides Git repository checkout functionality.</li>
  <li><strong>Credentials:</strong> Stores repository and Docker Hub authentication details.</li>
  <li><strong>Credentials Binding:</strong> Exposes stored credentials securely within pipeline steps.</li>
  <li><strong>Email Extension:</strong> Provides the <code>emailext</code> notification step.</li>
  <li><strong>GitHub integration:</strong> Supports webhook-triggered builds when the relevant plugin and job trigger are configured.</li>
</ul>

<p>
  Docker commands in this implementation are executed through the shell using the Docker CLI; a dedicated Docker Pipeline plugin is not required for those commands.
</p>

---

<h2>5. Prerequisites</h2>

<p>
  Before setting up the project, make sure the following tools and access permissions are available.
</p>

<table>
  <thead>
    <tr>
      <th>Requirement</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>macOS</td>
      <td>Local development and Jenkins execution environment used in this project.</td>
    </tr>
    <tr>
      <td>Homebrew</td>
      <td>Package manager used to install Jenkins.</td>
    </tr>
    <tr>
      <td>Java</td>
      <td>Runtime required by Jenkins.</td>
    </tr>
    <tr>
      <td>Jenkins LTS</td>
      <td>Runs the automation pipeline.</td>
    </tr>
    <tr>
      <td>Docker Desktop</td>
      <td>Provides the Docker daemon and CLI.</td>
    </tr>
    <tr>
      <td>Git</td>
      <td>Clones and manages the project repositories.</td>
    </tr>
    <tr>
      <td>ngrok</td>
      <td>Exposes local Jenkins for GitHub webhook integration.</td>
    </tr>
    <tr>
      <td>GitHub account</td>
      <td>Provides access to both project repositories.</td>
    </tr>
    <tr>
      <td>Docker Hub account</td>
      <td>Stores the frontend and backend Docker images.</td>
    </tr>
  </tbody>
</table>

<h3>5.1 Verify the Installed Tools</h3>

<p>Open the macOS Terminal and execute:</p>

<pre><code># Check Java
java -version

# Check Git
git --version

# Check Docker CLI
docker --version

# Check Docker Compose
docker compose version

# Check Homebrew
brew --version

# Check ngrok
ngrok version</code></pre>

<p>
  If Docker Desktop is installed, open it and wait until the Docker engine is running.
</p>

<p>Verify Docker daemon connectivity:</p>

<pre><code>docker info</code></pre>

<p>
  This command displays information about the Docker environment, including the Docker server, storage driver, containers, images, and available resources.
</p>

<p>
  If Docker is not running or Jenkins cannot access the Docker daemon, subsequent image build and deployment operations will fail.
</p>

---

<h2>6. Local Development Environment Setup</h2>

<p>
  This project was developed and tested using a macOS environment with Homebrew Jenkins LTS and Docker Desktop.
</p>

<h3>6.1 Install Jenkins LTS Using Homebrew</h3>

<p>
  If Homebrew is already installed, use the following command:
</p>

<pre><code>brew install jenkins-lts</code></pre>

<p>
  Jenkins LTS is the Long-Term Support release. It provides a stable release line for running Jenkins automation.
</p>

<p>Verify the installation:</p>

<pre><code>brew list --formula | grep -i jenkins</code></pre>

<h3>6.2 Start Jenkins</h3>

<p>
  Jenkins can be started as a Homebrew service:
</p>

<pre><code>brew services start jenkins-lts</code></pre>

<p>
  Check its status:
</p>

<pre><code>brew services list</code></pre>

<p>
  Alternatively, this project also used foreground execution to keep Jenkins logs visible in the Terminal.
</p>

<p>First, stop the Homebrew service to avoid running two Jenkins instances on the same port:</p>

<pre><code>brew services stop jenkins-lts</code></pre>

<p>Then open a Terminal window and execute:</p>

<pre><code>export PATH=$PATH:/usr/local/bin

jenkins-lts</code></pre>

<p>
  Jenkins now runs in the foreground in that Terminal session. Keep this Terminal window open while using Jenkins.
</p>

<p>
  This PATH command reflects the local Homebrew executable location used in this project. On other Mac installations, Homebrew may use a different path, such as <code>/opt/homebrew/bin</code>.
</p>

<h3>6.3 Access Jenkins Dashboard</h3>

<p>
  Open a browser and navigate to:
</p>

<pre><code>http://localhost:8080</code></pre>

<p>
  Jenkins uses port <code>8080</code> by default in this setup.
</p>

<p>
  Complete the initial Jenkins setup if this is a fresh installation:
</p>

<ol>
  <li>Unlock Jenkins using the initial administrator password.</li>
  <li>Install the recommended plugins or select the required plugins.</li>
  <li>Create the administrator account.</li>
  <li>Confirm the Jenkins URL.</li>
  <li>Open the Jenkins dashboard.</li>
</ol>

<h3>6.4 Install and Start Docker Desktop</h3>

<p>
  Docker Desktop provides the Docker engine and command-line interface required by this pipeline.
</p>

<p>
  Once Docker Desktop is installed, launch the application and wait until the engine is ready.
</p>

<p>Verify Docker connectivity:</p>

<pre><code>docker --version

docker info

docker context ls</code></pre>

<p>
  The project environment used the Docker Desktop context named <code>desktop-linux</code>.
</p>

<p>
  Jenkins must be able to execute Docker CLI commands and communicate with the Docker daemon. Having Docker Desktop open in the macOS graphical interface alone does not guarantee that a Jenkins process has the required PATH and Docker access.
</p>

<h3>6.5 Important: Jenkins and Docker Connectivity</h3>

<p>
  One important setup consideration is that Jenkins and Docker Desktop run as separate processes.
</p>

<p>
  When Jenkins executes a command such as:
</p>

<pre><code>docker build -t profile-frontend:1 frontend</code></pre>

<p>
  the Jenkins process must be able to locate the Docker CLI and communicate with the Docker daemon.
</p>

<p>Verify these conditions from the same environment in which Jenkins runs:</p>

<pre><code>which docker

docker --version

docker info</code></pre>

<p>
  In this project, the Docker CLI was available at:
</p>

<pre><code>/usr/local/bin/docker</code></pre>

<p>
  If Jenkins cannot recognize Docker even though Docker Desktop works from another Terminal, investigate the Jenkins process PATH and Docker daemon access before changing Docker configuration.
</p>

<h3>6.6 Install and Configure ngrok</h3>

<p>
  GitHub needs a publicly reachable endpoint to send webhook requests to a Jenkins instance running locally on a Mac.
</p>

<p>
  ngrok provides a public HTTPS endpoint that forwards incoming requests to a local port.
</p>

<p>
  In this project, ngrok exposes the Jenkins port <code>8080</code>.
</p>

<p>Open a second Terminal window while Jenkins continues running in the first Terminal.</p>

<pre><code>ngrok http 8080</code></pre>

<p>
  ngrok generates a public forwarding address, typically an HTTPS URL.
</p>

<p>Conceptually, the connection looks like this:</p>

<pre><code>GitHub Webhook
      |
      v
Public ngrok HTTPS URL
      |
      v
ngrok Tunnel
      |
      v
localhost:8080
      |
      v
Jenkins Server</code></pre>

<p>
  The public URL is temporary on typical free ngrok configurations and may change when the tunnel is restarted.
</p>

<p>
  The GitHub webhook must therefore use the currently active ngrok URL. The webhook endpoint and Jenkins job trigger configuration are covered in the CI/CD integration documentation.
</p>

<h3>6.7 Clone the Application Repository</h3>

<p>
  Create a local project directory and clone the application repository:
</p>

<pre><code># Create a projects directory if required
mkdir -p ~/Projects

# Navigate into it
cd ~/Projects

# Clone the application repository
git clone https://github.com/12pavani/Profile_App.git

# Navigate into the repository
cd Profile_App</code></pre>

<p>
  The application repository used in this project is:
</p>

<pre><code>https://github.com/12pavani/Profile_App.git</code></pre>

<p>Verify the repository contents:</p>

<pre><code>ls -la

ls -la frontend

ls -la backend</code></pre>

<p>
  The frontend and backend directories are the two Docker build contexts used later by Jenkins.
</p>

<h3>6.8 Clone the Jenkins Shared Library Repository</h3>

<p>
  Clone the Shared Library repository separately:
</p>

<pre><code>cd ~/Projects

git clone https://github.com/12pavani/jenkins-gsl-profileApp.git</code></pre>

<p>
  This repository contains the reusable Groovy pipeline implementation.
</p>

<p>
  The application repository and Shared Library repository serve different purposes and should not be confused during Jenkins configuration.
</p>

<h3>6.9 Local Environment Readiness Checklist</h3>

<ul>
  <li>Jenkins LTS is installed and accessible at <code>http://localhost:8080</code>.</li>
  <li>Docker Desktop is running.</li>
  <li><code>docker info</code> succeeds from the Jenkins execution environment.</li>
  <li>Git is available.</li>
  <li>Both GitHub repositories are accessible.</li>
  <li>ngrok can expose port <code>8080</code>.</li>
  <li>Docker Hub account is available for image publishing.</li>
</ul>

<p>
  Once these prerequisites are satisfied, the environment is ready for Jenkins Shared Library configuration and pipeline integration.
</p>

<hr/>
