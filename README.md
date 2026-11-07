
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


<!-- JENKINS INTEGRATION AND CI/CD IMPLEMENTATION -->

<hr/>

<h2>7. Jenkins Shared Library Configuration</h2>

<p>
  The Jenkins Shared Library is the central component responsible for executing the reusable CI/CD pipeline logic.
  Instead of writing the entire build and deployment workflow directly inside a Jenkins job, the implementation is maintained in a separate GitHub repository.
</p>

<h3>7.1 Shared Library Repository</h3>

<p>
  <a href="https://github.com/12pavani/jenkins-gsl-profileApp">
    Jenkins Shared Library – GitHub Repository
  </a>
</p>

<p>The pipeline implementation uses the following Groovy package:</p>

<pre><code>com.pavani.pipeline</code></pre>

<p>The main pipeline method is:</p>

<pre><code>BuildDeployPipeline()</code></pre>

<p>
  This method contains the reusable pipeline logic, including workspace preparation, Git checkout, Docker image creation, image publishing, deployment, health checks, log archiving, cleanup, and email notifications.
</p>

<h3>7.2 How Jenkins Shared Libraries Work</h3>

<p>
  Jenkins Shared Libraries allow developers to store reusable pipeline code in a separate source control repository.
  Jenkins retrieves this code and makes it available to pipeline jobs.
</p>

<p>The integration works conceptually as follows:</p>

<pre><code>Shared Library GitHub Repository
              |
              v
       Jenkins Configuration
              |
              v
       Global Pipeline Library
              |
              v
        Jenkins Pipeline Job
              |
              v
       Shared Library Method
              |
              v
       BuildDeployPipeline()
              |
              v
       CI/CD Workflow Execution</code></pre>

<h3>7.3 Configure the Global Pipeline Library</h3>

<p>
  Open the Jenkins dashboard and navigate to the Global Pipeline Libraries configuration.
</p>

<pre><code>Jenkins Dashboard
       |
       v
Manage Jenkins
       |
       v
System
       |
       v
Global Pipeline Libraries
       |
       v
Add</code></pre>

<p>Configure the library using the following information:</p>

<table>
  <thead>
    <tr>
      <th>Configuration</th>
      <th>Value / Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Name</td>
      <td>A library identifier chosen in Jenkins, for example <code>profile-app-shared-library</code>.</td>
    </tr>
    <tr>
      <td>Default Version</td>
      <td><code>main</code>, if that is the branch containing the library code.</td>
    </tr>
    <tr>
      <td>Retrieval Method</td>
      <td>Modern SCM</td>
    </tr>
    <tr>
      <td>Source Code Management</td>
      <td>Git</td>
    </tr>
    <tr>
      <td>Project Repository</td>
      <td><code>https://github.com/12pavani/jenkins-gsl-profileApp.git</code></td>
    </tr>
    <tr>
      <td>Credentials</td>
      <td>Select an appropriate GitHub credential if repository authentication is required.</td>
    </tr>
  </tbody>
</table>

<p>
  Save the configuration after verifying the repository URL, library name, and default branch.
</p>

<p><strong>Important:</strong> The library name is an identifier configured in Jenkins. It does not have to match the GitHub repository name. The class and method invocation must also match the actual Groovy source code in the Shared Library repository.</p>

<h3>7.4 Shared Library Code Organization</h3>

<p>
  Jenkins Shared Libraries commonly use two important directories:
</p>

<ul>
  <li><code>vars/</code> – Commonly contains globally accessible pipeline steps and variables.</li>
  <li><code>src/</code> – Commonly contains Groovy classes organized using package directories.</li>
</ul>

<p>
  For a Groovy package named <code>com.pavani.pipeline</code>, a conventional class location would be:
</p>

<pre><code>src/
  com/
    pavani/
      pipeline/
        PipelineClass.groovy</code></pre>

<p>
  This is the standard convention, not a verified representation of every file in the current repository. The actual class filename and directory structure must match the repository implementation.
</p>

<h3>7.5 How the Jenkins Job Uses the Library</h3>

<p>
  Once the library is configured globally, a Jenkins Pipeline job can load it and invoke the reusable implementation.
</p>

<p>
  A typical Shared Library loading declaration looks like:
</p>

<pre><code>@Library('profile-app-shared-library') _</code></pre>

<p>
  The name inside the declaration must match the library name configured in Jenkins.
</p>

<p>
  The exact method invocation depends on how <code>BuildDeployPipeline()</code> is declared in the Groovy source, including its class name and whether it is exposed as a class method or a global pipeline step.
</p>

<p>
  Therefore, do not copy an assumed class name into the Jenkinsfile. Verify the actual class and method declaration in the Shared Library repository before configuring the invocation.
</p>

<h3>7.6 Why Keep the Shared Library Separate?</h3>

<ul>
  <li>Pipeline implementation can be maintained independently of application code.</li>
  <li>The same pipeline logic can be reused by multiple Jenkins jobs.</li>
  <li>Changes to the pipeline can be managed centrally.</li>
  <li>Jenkinsfiles can remain smaller and easier to maintain.</li>
  <li>It encourages reusable and modular CI/CD design.</li>
</ul>

---

<h2>8. Jenkins Pipeline Configuration and Execution</h2>

<p>
  Once the Shared Library is configured, the next step is to create a Jenkins Pipeline job that invokes the reusable pipeline implementation.
</p>

<h3>8.1 Create a Jenkins Pipeline Job</h3>

<p>From the Jenkins dashboard:</p>

<pre><code>Jenkins Dashboard
       |
       v
New Item
       |
       v
Enter Job Name
       |
       v
Select Pipeline
       |
       v
Create</code></pre>

<p>
  Configure the job according to the Shared Library integration and the Jenkinsfile or pipeline definition used by the project.
</p>

<p>
  The job is responsible for initiating the pipeline. The Shared Library contains the reusable implementation.
</p>

<h3>8.2 Pipeline Execution Environment</h3>

<p>
  The pipeline uses Jenkins Pipeline steps to control execution and run shell commands on the configured Jenkins node.
</p>

<p>
  The implementation uses the following pipeline constructs:
</p>

<table>
  <thead>
    <tr>
      <th>Pipeline Construct</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>node</code></td>
      <td>Allocates a Jenkins execution node and workspace.</td>
    </tr>
    <tr>
      <td><code>timeout</code></td>
      <td>Limits the maximum pipeline execution time.</td>
    </tr>
    <tr>
      <td><code>timestamps</code></td>
      <td>Adds timestamps to console log output.</td>
    </tr>
    <tr>
      <td><code>stage</code></td>
      <td>Organizes pipeline operations into logical stages.</td>
    </tr>
    <tr>
      <td><code>sh</code></td>
      <td>Executes shell commands.</td>
    </tr>
    <tr>
      <td><code>withCredentials</code></td>
      <td>Provides access to stored credentials inside a controlled block.</td>
    </tr>
    <tr>
      <td><code>retry</code></td>
      <td>Repeats an operation when an attempt fails.</td>
    </tr>
    <tr>
      <td><code>archiveArtifacts</code></td>
      <td>Stores selected build output files as Jenkins artifacts.</td>
    </tr>
  </tbody>
</table>

<h3>8.3 Pipeline Timeout</h3>

<p>The pipeline sets a maximum execution time:</p>

<pre><code>timeout(time: 20, unit: 'MINUTES')</code></pre>

<p>
  This limits the execution to 20 minutes. If the pipeline exceeds this duration, Jenkins interrupts the execution with a timeout failure.
</p>

<p>
  A timeout prevents a pipeline from running indefinitely because of a stalled command or an operation that never completes.
</p>

<h3>8.4 Console Timestamps</h3>

<pre><code>timestamps {
    // Pipeline execution
}</code></pre>

<p>
  The <code>timestamps</code> wrapper adds date and time information to console log lines.
</p>

<p>
  This helps identify when individual commands started producing output and makes troubleshooting easier.
</p>

<h3>8.5 Jenkins Node and Workspace</h3>

<pre><code>node {
    // Pipeline operations
}</code></pre>

<p>
  The <code>node</code> block allocates a Jenkins node and its workspace for executing the pipeline.
</p>

<p>
  In this project, Jenkins executes the shell commands using the configured local execution environment.
  Since Docker Desktop is running on the Mac, the Jenkins process must be able to access the Docker CLI and Docker daemon.
</p>

<h3>8.6 Build Display Name</h3>

<p>The pipeline sets a readable build display name:</p>

<pre><code>currentBuild.displayName = "Build-${BUILD_NUMBER}"</code></pre>

<p>
  For example, build number 5 appears with the display name:
</p>

<pre><code>Build-5</code></pre>

<p>
  This makes individual executions easier to identify in the Jenkins dashboard.
</p>

<h3>8.7 Workspace Cleanup</h3>

<pre><code>deleteDir()</code></pre>

<p>
  This removes the contents of the current Jenkins workspace before the application checkout.
</p>

<p>
  Workspace cleanup helps avoid interference from files left behind by previous builds.
</p>

<p>
  For example, an old Docker Compose file, stale application files, or previously generated logs should not accidentally be used by a new execution.
</p>

<p><strong>Note:</strong> <code>deleteDir()</code> deletes workspace contents. It does not delete Docker images or containers from the Docker daemon.</p>

<h3>8.8 Verify Docker Availability</h3>

<pre><code>docker info</code></pre>

<p>
  Before building images, the pipeline checks Docker availability by executing <code>docker info</code>.
</p>

<p>
  This command communicates with the Docker daemon and displays information about the Docker environment.
</p>

<p>
  If Jenkins cannot communicate with Docker, the pipeline should fail before proceeding to image creation.
</p>

---

<h2>9. GitHub Integration and Webhook Configuration</h2>

<p>
  GitHub stores the application source code, while Jenkins performs the automated build and deployment operations.
</p>

<p>
  A GitHub webhook can notify Jenkins when a relevant repository event occurs, allowing the Jenkins job to start without manually selecting Build Now.
</p>

<h3>9.1 What Is a GitHub Webhook?</h3>

<p>
  A webhook is an HTTP request sent by one application to another when a configured event occurs.
</p>

<p>
  In this project, GitHub can send a webhook notification to Jenkins when a developer pushes changes to the application repository.
</p>

<p>Conceptual flow:</p>

<pre><code>Developer Pushes Code
         |
         v
     GitHub Event
         |
         v
   GitHub Webhook
         |
         v
   Public ngrok URL
         |
         v
   Jenkins Webhook Endpoint
         |
         v
    Jenkins Job Trigger
         |
         v
     CI/CD Pipeline</code></pre>

<h3>9.2 Why Is ngrok Required?</h3>

<p>
  Jenkins is running locally on the Mac at:
</p>

<pre><code>http://localhost:8080</code></pre>

<p>
  GitHub's webhook service cannot directly reach a private localhost address on your machine.
</p>

<p>
  ngrok provides a publicly reachable HTTPS forwarding URL and forwards requests to the local Jenkins server.
</p>

<p>Start the tunnel:</p>

<pre><code>ngrok http 8080</code></pre>

<p>
  Keep the ngrok Terminal session running while webhook events need to reach Jenkins.
</p>

<h3>9.3 Configure the Jenkins Webhook Endpoint</h3>

<p>
  When the Jenkins GitHub integration plugin and the corresponding job trigger are configured, the commonly used webhook endpoint is:
</p>

<pre><code>https://YOUR-NGROK-DOMAIN/github-webhook/</code></pre>

<p>
  Replace <code>YOUR-NGROK-DOMAIN</code> with the active HTTPS forwarding address displayed by ngrok.
</p>

<p>
  The endpoint must be reachable and the Jenkins GitHub webhook integration must be enabled.
</p>

<h3>9.4 Configure the Webhook in GitHub</h3>

<p>Open the application repository:</p>

<pre><code>https://github.com/12pavani/Profile_App</code></pre>

<p>Navigate to:</p>

<pre><code>Repository
   |
   v
Settings
   |
   v
Webhooks
   |
   v
Add webhook</code></pre>

<p>Configure the webhook:</p>

<table>
  <thead>
    <tr>
      <th>Field</th>
      <th>Configuration</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Payload URL</td>
      <td>Active ngrok URL followed by <code>/github-webhook/</code>.</td>
    </tr>
    <tr>
      <td>Content type</td>
      <td><code>application/json</code></td>
    </tr>
    <tr>
      <td>Secret</td>
      <td>Use a configured webhook secret if your integration is set up to validate it.</td>
    </tr>
    <tr>
      <td>Events</td>
      <td>Select the relevant events, such as push events.</td>
    </tr>
    <tr>
      <td>Active</td>
      <td>Enabled</td>
    </tr>
  </tbody>
</table>

<p>
  Save the webhook configuration.
</p>

<h3>9.5 Enable the Jenkins Job Trigger</h3>

<p>
  The Jenkins job must also be configured to react to GitHub webhook notifications.
</p>

<p>
  Depending on the installed GitHub integration plugin and job type, enable the relevant GitHub hook trigger, commonly labelled:
</p>

<pre><code>GitHub hook trigger for GITScm polling</code></pre>

<p>
  Save the Jenkins job configuration.
</p>

<p><strong>Important:</strong> A successful webhook HTTP delivery does not by itself guarantee that a build will start. The Jenkins plugin, job trigger, repository configuration, and polling or event integration must all be configured correctly.</p>

<h3>9.6 Test Webhook Delivery</h3>

<p>
  After configuring the webhook, push a change to the application repository or use the webhook's test delivery functionality where available.
</p>

<p>
  Check the delivery status in GitHub under the repository's Webhooks page.
</p>

<p>Also verify:</p>

<ul>
  <li>The ngrok tunnel is still active.</li>
  <li>The payload URL contains the correct active ngrok address.</li>
  <li>The Jenkins webhook endpoint is accessible.</li>
  <li>The Jenkins job has the required trigger enabled.</li>
  <li>The expected repository and branch are configured.</li>
  <li>The Jenkins job actually starts after the event.</li>
</ul>

<h3>9.7 Common Webhook Issues</h3>

<table>
  <thead>
    <tr>
      <th>Problem</th>
      <th>Possible cause</th>
      <th>Check</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Connection refused</td>
      <td>Jenkins or ngrok is not running.</td>
      <td>Check both Terminal sessions.</td>
    </tr>
    <tr>
      <td>404 response</td>
      <td>Incorrect webhook endpoint.</td>
      <td>Verify <code>/github-webhook/</code>.</td>
    </tr>
    <tr>
      <td>Webhook delivered but no build starts</td>
      <td>Jenkins job trigger or integration is not configured correctly.</td>
      <td>Check the job configuration and Jenkins logs.</td>
    </tr>
    <tr>
      <td>Webhook stops working after restart</td>
      <td>ngrok generated a different public URL.</td>
      <td>Update the GitHub webhook URL.</td>
    </tr>
  </tbody>
</table>

---

<h2>10. Jenkins Credentials and Secure Authentication</h2>

<p>
  The pipeline interacts with GitHub and Docker Hub. Authentication details are managed through Jenkins Credentials instead of storing sensitive information directly in Groovy code.
</p>

<h3>10.1 Credentials Used in the Project</h3>

<table>
  <thead>
    <tr>
      <th>Credential ID</th>
      <th>Purpose</th>
      <th>Credential Type</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>github-cred</code></td>
      <td>GitHub repository checkout authentication, when required.</td>
      <td>Suitable Git authentication credential.</td>
    </tr>
    <tr>
      <td><code>dockerhub-cred</code></td>
      <td>Docker Hub login for image publishing.</td>
      <td>Username with password / Personal Access Token.</td>
    </tr>
  </tbody>
</table>

<h3>10.2 Configure GitHub Credentials</h3>

<p>
  If the application repository requires authentication, create a Jenkins credential for Git access.
</p>

<p>
  The pipeline references the credential using:
</p>

<pre><code>credentialsId: 'github-cred'</code></pre>

<p>
  The checkout configuration uses the credential ID rather than embedding authentication details in the repository URL.
</p>

<p>
  If the repository is public, Git checkout may not require authentication. The credential can still be retained if it is intentionally part of the Jenkins configuration.
</p>

<h3>10.3 Configure Docker Hub Credentials</h3>

<p>
  The pipeline uses the Jenkins credential ID:
</p>

<pre><code>dockerhub-cred</code></pre>

<p>
  Configure it as a username/password credential. The username is the Docker Hub account name and the password field contains a Docker Hub Personal Access Token with the permissions needed to push images.
</p>

<p>
  The Docker Hub username used by this project is:
</p>

<pre><code>vislavathpavani5123</code></pre>

<h3>10.4 Why Use a Personal Access Token?</h3>

<p>
  A Personal Access Token (PAT) provides an alternative authentication mechanism to an account password.
</p>

<p>
  A token can be assigned specific permissions and can be rotated or revoked independently of the account password.
</p>

<p>
  For this pipeline, the token must have permission to push to the required Docker Hub repositories.
</p>

<h3>10.5 Credential Security</h3>

<ul>
  <li>Never commit tokens or passwords to GitHub.</li>
  <li>Never hardcode secrets in Jenkinsfiles or Groovy classes.</li>
  <li>Use Jenkins Credentials and credential binding.</li>
  <li>Use the minimum permissions required.</li>
  <li>Rotate expired or compromised tokens.</li>
  <li>Restrict access to Jenkins credential configuration.</li>
</ul>

---

<h2>11. CI/CD Pipeline Implementation</h2>

<p>
  The core pipeline is implemented through the Groovy method <code>BuildDeployPipeline()</code> in the Shared Library.
</p>

<p>
  It coordinates the complete application delivery process by executing operations in a controlled sequence.
</p>

<h3>11.1 Pipeline Execution Overview</h3>

<pre><code>BuildDeployPipeline()
        |
        v
Set Timeout and Timestamps
        |
        v
Allocate Jenkins Node
        |
        v
Initialize Pipeline Variables
        |
        v
Set Build Display Name
        |
        v
Clean Workspace
        |
        v
Check Docker Availability
        |
        v
Checkout Application Repository
        |
        v
Collect Git and Build Information
        |
        v
Build Frontend and Backend Images
        |
        v
Verify and Tag Images
        |
        v
Authenticate with Docker Hub
        |
        v
Push Images
        |
        v
Deploy with Docker Compose
        |
        v
Health Checks
        |
        v
Archive Container Logs
        |
        v
Clean Up Docker Images
        |
        v
Email Notification</code></pre>

<p>
  The deployment, validation, cleanup, and notification operations are explained in detail in the following documentation sections.
</p>

<h3>11.2 Pipeline Initialization</h3>

<p>
  At the beginning of the pipeline, the implementation defines the execution timeout, enables console timestamps, and allocates a Jenkins node.
</p>

<p>
  It also initializes variables used by subsequent build and deployment operations.
</p>

<p>The main image names are:</p>

<pre><code>profile-frontend
profile-backend</code></pre>

<p>The Docker Hub namespace is:</p>

<pre><code>vislavathpavani5123</code></pre>

<p>The application endpoints used for validation are:</p>

<pre><code>http://localhost:3000
http://localhost:8000/profile</code></pre>

<p>
  These URLs are used by the pipeline's health-check operations after deployment.
</p>

<h3>11.3 Container Names Used by the Pipeline</h3>

<p>
  The pipeline also defines the expected Docker Compose container names for retrieving application logs.
</p>

<pre><code>profileapp_dev_feature_builddeploy-profile-frontend-1

profileapp_dev_feature_builddeploy-profile-backend-1</code></pre>

<p>
  These names are tied to the Compose project and service naming convention used in the current environment.
</p>

<p>
  If the Compose project name or service names change, these hardcoded values may need to be updated. A more flexible implementation can discover container IDs using Docker Compose rather than relying on fixed names.
</p>

<h3>11.4 Source Code Checkout</h3>

<p>
  The pipeline retrieves the application source code from the Profile_App repository.
</p>

<pre><code>checkout([
    $class: 'GitSCM',
    branches: [[name: '*/main']],
    userRemoteConfigs: [[
        url: 'https://github.com/12pavani/Profile_App.git',
        credentialsId: 'github-cred'
    ]]
])</code></pre>

<p>
  This configuration checks out the <code>main</code> branch into the Jenkins workspace.
</p>

<p>
  After checkout, the workspace contains the frontend and backend directories required for Docker image creation, along with the Docker Compose configuration.
</p>

<h3>11.5 Git and Build Metadata</h3>

<p>
  The pipeline collects Git information to help identify the application version being processed.
</p>

<pre><code>git branch --show-current

git rev-parse HEAD

git log</code></pre>

<p>
  These commands provide the current branch name when available, the checked-out commit hash, and the commit history.
</p>

<p>
  Jenkins build metadata is also available through environment variables:
</p>

<pre><code>JOB_NAME
BUILD_NUMBER
BUILD_URL
NODE_NAME
WORKSPACE</code></pre>

<p>
  This information helps trace a deployment back to the Jenkins job, build execution, workspace, and source revision.
</p>

<h3>11.6 Build the Frontend Image</h3>

<pre><code>docker build -t profile-frontend:${BUILD_NUMBER} frontend</code></pre>

<p>
  Docker uses the frontend directory as the build context and builds an image named <code>profile-frontend</code>.
</p>

<p>
  The Jenkins build number is used as the image tag.
</p>

<p>For example, build number 5 creates:</p>

<pre><code>profile-frontend:5</code></pre>

<h3>11.7 Build the Backend Image</h3>

<pre><code>docker build -t profile-backend:${BUILD_NUMBER} backend</code></pre>

<p>
  This command independently builds the backend image using the backend directory as its build context.
</p>

<p>For build number 5:</p>

<pre><code>profile-backend:5</code></pre>

<p>
  Building frontend and backend independently keeps their Docker images separate while allowing both to be associated with the same Jenkins build.
</p>

<h3>11.8 Verify Docker Images</h3>

<pre><code>docker images | grep "profile"</code></pre>

<p>
  This command lists local Docker images and filters the output for entries containing the word <code>profile</code>.
</p>

<p>
  It provides a quick view of the application images before they are tagged for Docker Hub.
</p>

<h3>11.9 Tag Images for Docker Hub</h3>

<p>
  After building the local images, the pipeline assigns Docker Hub repository names to them.
</p>

<pre><code>docker tag profile-frontend:${BUILD_NUMBER} \
  vislavathpavani5123/profile-frontend:${BUILD_NUMBER}

docker tag profile-backend:${BUILD_NUMBER} \
  vislavathpavani5123/profile-backend:${BUILD_NUMBER}</code></pre>

<p>
  For build number 5, the resulting image references are:
</p>

<pre><code>vislavathpavani5123/profile-frontend:5

vislavathpavani5123/profile-backend:5</code></pre>

<p>
  The <code>docker tag</code> command creates an additional reference to the existing local image. It does not rebuild the image.
</p>

<h3>11.10 Docker Image Versioning Strategy</h3>

<p>
  The Jenkins build number provides a simple versioning mechanism for the images.
</p>

<table>
  <thead>
    <tr>
      <th>Jenkins Build</th>
      <th>Frontend Image Tag</th>
      <th>Backend Image Tag</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Build 1</td>
      <td><code>1</code></td>
      <td><code>1</code></td>
    </tr>
    <tr>
      <td>Build 2</td>
      <td><code>2</code></td>
      <td><code>2</code></td>
    </tr>
    <tr>
      <td>Build 3</td>
      <td><code>3</code></td>
      <td><code>3</code></td>
    </tr>
  </tbody>
</table>

<p>
  This makes it easier to identify which frontend and backend image versions belong to a particular Jenkins build.
</p>

---

<h2>12. Docker Hub Authentication and Image Publishing</h2>

<p>
  Once the images have been built and tagged, Jenkins authenticates with Docker Hub and pushes both images to their respective repositories.
</p>

<h3>12.1 Authenticate Using Jenkins Credentials</h3>

<p>
  The pipeline uses the <code>withCredentials</code> step to retrieve the stored Docker Hub username and token.
</p>

<pre><code>withCredentials([
    usernamePassword(
        credentialsId: 'dockerhub-cred',
        usernameVariable: 'USERNAME',
        passwordVariable: 'PASSWORD'
    )
]) {
    // Docker Hub authentication and publishing
}</code></pre>

<p>
  The Jenkins credential is mapped to two temporary environment variables inside the credentials block:
</p>

<table>
  <thead>
    <tr>
      <th>Variable</th>
      <th>Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>USERNAME</code></td>
      <td>Docker Hub username.</td>
    </tr>
    <tr>
      <td><code>PASSWORD</code></td>
      <td>Docker Hub Personal Access Token.</td>
    </tr>
  </tbody>
</table>

<h3>12.2 Docker Login</h3>

<pre><code>echo "$PASSWORD" | docker login \
  -u "$USERNAME" \
  --password-stdin</code></pre>

<p>
  The token is passed to Docker through standard input using <code>--password-stdin</code>.
</p>

<p>
  This avoids placing the token directly in the Docker login command as a command-line password argument.
</p>

<h3>12.3 Push the Frontend Image</h3>

<pre><code>docker push vislavathpavani5123/profile-frontend:${BUILD_NUMBER}</code></pre>

<p>
  This uploads the tagged frontend image to the Docker Hub frontend repository.
</p>

<h3>12.4 Push the Backend Image</h3>

<pre><code>docker push vislavathpavani5123/profile-backend:${BUILD_NUMBER}</code></pre>

<p>
  This uploads the tagged backend image to the Docker Hub backend repository.
</p>

<h3>12.5 Docker Hub Repositories</h3>

<ul>
  <li>
    <a href="https://hub.docker.com/r/vislavathpavani5123/profile-frontend">
      Profile Frontend – Docker Hub
    </a>
  </li>
  <li>
    <a href="https://hub.docker.com/r/vislavathpavani5123/profile-backend">
      Profile Backend – Docker Hub
    </a>
  </li>
</ul>

<p>
  After a successful push, the corresponding build-number tags should be visible in the Docker Hub repositories, subject to repository permissions and visibility settings.
</p>

<h3>12.6 Verify Published Images</h3>

<p>
  The images can be pulled from another Docker environment to verify that they are available in the registry.
</p>

<pre><code>docker pull vislavathpavani5123/profile-frontend:5

docker pull vislavathpavani5123/profile-backend:5</code></pre>

<p>
  Replace <code>5</code> with the required build number.
</p>

<h3>12.7 Authentication Failure Encountered</h3>

<p>
  During project execution, Docker Hub authentication encountered the following error:
</p>

<pre><code>unauthorized: personal access token is expired</code></pre>

<p>
  The issue was caused by an expired Docker Hub Personal Access Token stored in the Jenkins credential.
</p>

<p>
  The image build and tagging operations had already completed; the failure occurred during registry authentication.
</p>

<p><strong>Resolution:</strong></p>

<ol>
  <li>Generate a new Docker Hub Personal Access Token with the required permissions.</li>
  <li>Open Jenkins Credentials.</li>
  <li>Locate <code>dockerhub-cred</code>.</li>
  <li>Update its password field with the new token.</li>
  <li>Save the credential and rerun the pipeline.</li>
</ol>

<p>
  There is no need to expose or commit the token in the project documentation.
</p>

<h3>12.8 Publishing Flow Summary</h3>

<pre><code>Frontend and Backend Images
            |
            v
     Docker Hub Image Tags
            |
            v
    Jenkins Credential Binding
            |
            v
       Docker Login
            |
            v
      Authentication
            |
            v
        Docker Push
            |
       +----+----+
       |         |
       v         v
   Frontend   Backend
   Repository Repository
       |         |
       v         v
   Versioned  Versioned
     Image      Image</code></pre>


<!-- DEPLOYMENT, VALIDATION, TROUBLESHOOTING AND PROJECT CONCLUSION -->

<hr/>

<h2>13. Application Deployment Using Docker Compose</h2>

<p>
  After successfully building and publishing the frontend and backend Docker images, the pipeline deploys the application using Docker Compose.
</p>

<p>
  Docker Compose manages the application services and starts the frontend and backend containers together using the project's Compose configuration.
</p>

<h3>13.1 Deployment Workflow</h3>

<pre><code>Docker Hub
    |
    v
Published Frontend and Backend Images
    |
    v
Jenkins Deployment Stage
    |
    v
Set BUILD_NUMBER
    |
    v
Stop Existing Compose Services
    |
    v
Start Application Services
    |
    v
Frontend + Backend Containers</code></pre>

<h3>13.2 Pass the Jenkins Build Number</h3>

<pre><code>export BUILD_NUMBER=${BUILD_NUMBER}</code></pre>

<p>
  The Jenkins build number is exported as an environment variable so that the Docker Compose configuration can use the corresponding image version.
</p>

<p>
  For example, when Jenkins executes build number 5, the deployment can use the image tags associated with build 5.
</p>

<h3>13.3 Stop Existing Containers</h3>

<pre><code>docker compose down || true</code></pre>

<p>
  This command stops and removes the existing Compose-managed containers and networks before starting the new deployment.
</p>

<p>
  The <code>|| true</code> expression prevents a failure in the shutdown command from stopping the shell execution at that point.
</p>

<p>
  This is useful when there may be no existing Compose deployment to stop. However, it can also hide a genuine shutdown problem, so production pipelines should handle and report cleanup failures more carefully.
</p>

<h3>13.4 Start the Application</h3>

<pre><code>docker compose -f docker-compose.yaml up -d</code></pre>

<p>
  This command starts the services defined in <code>docker-compose.yaml</code>.
</p>

<ul>
  <li><code>-f</code> specifies the Compose configuration file.</li>
  <li><code>up</code> creates and starts the configured services.</li>
  <li><code>-d</code> runs the containers in detached mode.</li>
</ul>

<p>
  The frontend and backend services run as separate containers and communicate according to the Docker Compose network configuration.
</p>

<h3>13.5 Verify Running Containers</h3>

<pre><code>docker ps</code></pre>

<p>
  This command lists running Docker containers and provides information such as container ID, image, status, ports, and container name.
</p>

<p>
  The pipeline also filters containers using the application-specific name prefix:
</p>

<pre><code>docker ps --filter name=profileapp_dev_feature_builddeploy</code></pre>

<p>
  This helps identify the expected application containers.
</p>

<p><strong>Important:</strong> A container appearing in <code>docker ps</code> confirms that it is running. It does not independently prove that the application is responding correctly. That is why the pipeline performs HTTP health checks separately.</p>

<h3>13.6 Deployment Consideration: Downtime</h3>

<p>
  The current deployment sequence runs <code>docker compose down</code> before <code>docker compose up -d</code>.
</p>

<p>
  This creates a period during which the existing containers are stopped before the replacement containers become available. Therefore, the current approach is a stop-and-recreate deployment, not a zero-downtime deployment.
</p>

<p>
  For production environments, this can be improved using rolling deployments, blue-green deployment, or another deployment strategy that keeps the previous application version available until the replacement is ready.
</p>

---

<h2>14. Application Health Checks and Validation</h2>

<p>
  A successful Docker image build or container startup does not necessarily mean that the application is functioning correctly.
</p>

<p>
  The pipeline therefore performs HTTP-based checks against the frontend and backend after deployment.
</p>

<h3>14.1 Health Check Strategy</h3>

<p>
  The pipeline uses Jenkins' <code>retry</code> mechanism to allow the application some time to initialize before declaring the deployment unsuccessful.
</p>

<pre><code>retry(5) {
    sleep 5

    // Container visibility check

    // Frontend HTTP check

    // Backend HTTP check
}</code></pre>

<p>
  The health-check block can execute up to five attempts. Each attempt includes a five-second sleep before the validation commands.
</p>

<p>
  This provides approximately 25 seconds of waiting time across five attempts, excluding the time spent executing the individual commands and requests.
</p>

<h3>14.2 Check Frontend Availability</h3>

<pre><code>curl -f http://localhost:3000</code></pre>

<p>
  This sends an HTTP request to the frontend endpoint exposed on port 3000.
</p>

<p>
  The <code>-f</code> option makes curl return a failure status for HTTP responses with status codes of 400 or higher.
</p>

<p>
  This allows Jenkins to detect unsuccessful HTTP responses instead of treating every HTTP response as a successful check.
</p>

<h3>14.3 Check Backend Availability</h3>

<pre><code>curl -f http://localhost:8000/profile</code></pre>

<p>
  This sends an HTTP request to the backend profile endpoint exposed on port 8000.
</p>

<p>
  The pipeline checks both application components independently:
</p>

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Endpoint</th>
      <th>Validation</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Frontend</td>
      <td><code>http://localhost:3000</code></td>
      <td>HTTP response check</td>
    </tr>
    <tr>
      <td>Backend</td>
      <td><code>http://localhost:8000/profile</code></td>
      <td>HTTP response check</td>
    </tr>
  </tbody>
</table>

<h3>14.4 Why Retry Health Checks?</h3>

<p>
  Containers may take a few seconds to initialize after Docker Compose starts them.
</p>

<p>
  Without a retry mechanism, Jenkins could mark the deployment as failed simply because an HTTP endpoint was checked before the application finished starting.
</p>

<p>
  Retry provides a limited opportunity for the services to become ready.
</p>

<h3>14.5 Health Check Limitations</h3>

<p>
  The current checks validate HTTP availability and successful response status. They do not validate the complete business functionality of the application.
</p>

<p>
  Additional production-grade validation could include:
</p>

<ul>
  <li>Dedicated application health endpoints.</li>
  <li>Response body validation.</li>
  <li>Database connectivity checks, if applicable.</li>
  <li>Container health status using Docker health checks.</li>
  <li>Readiness and liveness probes in Kubernetes deployments.</li>
  <li>Automated integration and smoke tests.</li>
</ul>

---

<h2>15. Container Log Collection and Artifact Archiving</h2>

<p>
  Logs are important for understanding application behavior and diagnosing deployment failures.
</p>

<p>
  After deployment and health validation, the pipeline collects logs from the frontend and backend containers and archives them in Jenkins.
</p>

<h3>15.1 Collect Frontend Logs</h3>

<p>
  The pipeline uses the configured frontend container name to retrieve its logs.
</p>

<pre><code>docker logs profileapp_dev_feature_builddeploy-profile-frontend-1 \
  &gt; frontend.log 2&gt;&amp;1</code></pre>

<p>
  The command redirects the container's standard output and standard error into <code>frontend.log</code>.
</p>

<h3>15.2 Collect Backend Logs</h3>

<pre><code>docker logs profileapp_dev_feature_builddeploy-profile-backend-1 \
  &gt; backend.log 2&gt;&amp;1</code></pre>

<p>
  This collects the backend container logs into <code>backend.log</code>.
</p>

<h3>15.3 Archive Logs in Jenkins</h3>

<pre><code>archiveArtifacts artifacts: '*.log', fingerprint: true</code></pre>

<p>
  Jenkins archives the matching log files from the workspace as build artifacts.
</p>

<p>The configuration has two important parts:</p>

<ul>
  <li><code>artifacts: '*.log'</code> – Selects log files in the workspace that match the pattern.</li>
  <li><code>fingerprint: true</code> – Enables artifact fingerprinting for tracking artifacts across Jenkins builds.</li>
</ul>

<h3>15.4 Where to Find Archived Logs</h3>

<pre><code>Jenkins Dashboard
       |
       v
Profile App Pipeline Job
       |
       v
Select Build Number
       |
       v
Build Artifacts
       |
       +---- frontend.log
       |
       +---- backend.log</code></pre>

<p>
  The archived files can be downloaded from the corresponding Jenkins build page for troubleshooting and inspection.
</p>

<h3>15.5 Why Archive Logs?</h3>

<ul>
  <li>Preserves application logs for later investigation.</li>
  <li>Helps diagnose frontend and backend startup problems.</li>
  <li>Provides useful information when a deployment fails.</li>
  <li>Maintains build-level troubleshooting evidence.</li>
</ul>

<p>
  The current implementation uses fixed container names. If Compose naming changes, the log collection commands must be updated or replaced with a more dynamic container discovery approach.
</p>

---

<h2>16. Docker Resource Cleanup</h2>

<p>
  Docker images can consume significant disk space, particularly when Jenkins creates new image versions for every build.
</p>

<p>
  To reduce unused image accumulation, the pipeline executes a Docker cleanup command.
</p>

<h3>16.1 Remove Unused Docker Images</h3>

<pre><code>docker image prune -a -f</code></pre>

<p>Command breakdown:</p>

<table>
  <thead>
    <tr>
      <th>Option</th>
      <th>Meaning</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>docker image prune</code></td>
      <td>Removes unused Docker images.</td>
    </tr>
    <tr>
      <td><code>-a</code></td>
      <td>Includes all images not referenced by existing containers, rather than only dangling images.</td>
    </tr>
    <tr>
      <td><code>-f</code></td>
      <td>Skips the interactive confirmation prompt.</td>
    </tr>
  </tbody>
</table>

<h3>16.2 Why Cleanup Matters</h3>

<ul>
  <li>Controls unnecessary Docker image accumulation.</li>
  <li>Helps manage disk usage on the Jenkins execution host.</li>
  <li>Reduces unused local image storage.</li>
</ul>

<h3>16.3 Important Cleanup Risk</h3>

<p>
  The command <code>docker image prune -a -f</code> is not restricted to this project. It can remove every image on the Docker host that is not currently referenced by a container.
</p>

<p>
  This may affect unrelated projects sharing the same Docker daemon and can remove images that would otherwise be useful for future builds.
</p>

<p>
  For a shared or production Docker host, prefer targeted cleanup, image retention policies, or scheduled cleanup with explicit ownership rules.
</p>

---

<h2>17. Pipeline Error Handling and Email Notifications</h2>

<p>
  A reliable CI/CD pipeline should not only execute build and deployment operations but also handle failures and communicate the final execution status.
</p>

<p>
  The pipeline uses <code>try</code>, <code>catch</code>, and <code>finally</code> to manage execution errors and send an email notification.
</p>

<h3>17.1 Try Block</h3>

<p>
  The main pipeline operations execute inside the <code>try</code> block.
</p>

<p>
  These operations include source checkout, Docker image creation, image publishing, deployment, health checks, and post-deployment tasks.
</p>

<h3>17.2 Catch Block</h3>

<p>
  If an operation throws an exception, the pipeline enters the <code>catch</code> block.
</p>

<p>
  The implementation marks the current build as failed and rethrows the exception.
</p>

<pre><code>catch (Exception e) {
    currentBuild.result = 'FAILURE'
    throw e
}</code></pre>

<p>
  Rethrowing the exception is important because it preserves the failure signal instead of allowing Jenkins to treat the pipeline as successful.
</p>

<h3>17.3 Finally Block</h3>

<p>
  The <code>finally</code> block executes after the try/catch processing, regardless of whether the main pipeline succeeds or fails, subject to Jenkins interruption and execution conditions.
</p>

<p>
  In this project, the finally block is used to send the build notification through the Email Extension plugin.
</p>

<h3>17.4 Email Notification</h3>

<p>
  The pipeline uses the <code>emailext</code> step to send an email containing the Jenkins build result.
</p>

<p>The notification includes information such as:</p>

<ul>
  <li>Jenkins job name.</li>
  <li>Build number.</li>
  <li>Current build result.</li>
  <li>Jenkins build URL.</li>
</ul>

<p>The subject follows this pattern:</p>

<pre><code>Profile App Build - ${currentBuild.currentResult}</code></pre>

<p>The configured recipient is:</p>

<pre><code>vislavathpavani5123@gmail.com</code></pre>

<p>
  The notification helps identify whether a particular pipeline execution completed successfully or failed.
</p>

<h3>17.5 Email Extension Plugin Requirement</h3>

<p>
  The <code>emailext</code> pipeline step requires the Jenkins Email Extension plugin and working SMTP configuration.
</p>

<p>
  Installing the plugin alone is not sufficient. Configure the SMTP server, sender information, authentication where required, and test email delivery from Jenkins.
</p>

<h3>17.6 Error Handling Flow</h3>

<pre><code>      Pipeline Starts
            |
            v
        Try Block
            |
            v
   Execute CI/CD Operations
            |
       +----+----+
       |         |
       v         v
    Success    Failure
       |         |
       |         v
       |      Catch Block
       |         |
       |         v
       |    Mark Failure
       |         |
       |         v
       |    Rethrow Error
       |         |
       +----+----+
            |
            v
       Finally Block
            |
            v
     Email Notification
            |
            v
      Pipeline Result</code></pre>

---

<h2>18. End-to-End CI/CD Execution Summary</h2>

<p>
  The following sequence summarizes the complete workflow implemented in the project.
</p>

<pre><code>             DEVELOPER
                 |
                 v
       Push Code to GitHub
                 |
                 v
         GitHub Webhook
                 |
                 v
              JENKINS
                 |
                 v
       Load Shared Library
                 |
                 v
      BuildDeployPipeline()
                 |
                 v
       Initialize Workspace
                 |
                 v
        Checkout Source Code
                 |
                 v
       Collect Build Metadata
                 |
                 v
       Build Frontend Image
                 |
                 v
       Build Backend Image
                 |
                 v
        Verify Docker Images
                 |
                 v
           Tag Images
                 |
                 v
       Authenticate Docker Hub
                 |
                 v
          Push Both Images
                 |
                 v
       Deploy with Compose
                 |
                 v
       Validate Application
                 |
                 v
         Collect Logs
                 |
                 v
       Archive Jenkins Logs
                 |
                 v
        Docker Cleanup
                 |
                 v
       Send Email Notification</code></pre>

<h3>18.1 Pipeline Stage Responsibility</h3>

<table>
  <thead>
    <tr>
      <th>Execution Area</th>
      <th>Responsibility</th>
      <th>Expected Outcome</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Initialization</td>
      <td>Prepare Jenkins execution context.</td>
      <td>Clean workspace and available Docker environment.</td>
    </tr>
    <tr>
      <td>Source Checkout</td>
      <td>Retrieve application code.</td>
      <td>Frontend and backend source available.</td>
    </tr>
    <tr>
      <td>Build</td>
      <td>Create Docker images.</td>
      <td>Two locally built application images.</td>
    </tr>
    <tr>
      <td>Image Publishing</td>
      <td>Authenticate and push images.</td>
      <td>Versioned images available in Docker Hub.</td>
    </tr>
    <tr>
      <td>Deployment</td>
      <td>Start application services.</td>
      <td>Frontend and backend containers started.</td>
    </tr>
    <tr>
      <td>Validation</td>
      <td>Check application endpoints.</td>
      <td>HTTP checks return successful responses.</td>
    </tr>
    <tr>
      <td>Post-Deployment</td>
      <td>Collect logs and clean resources.</td>
      <td>Build artifacts and reduced unused image storage.</td>
    </tr>
    <tr>
      <td>Notification</td>
      <td>Communicate execution status.</td>
      <td>Email notification with build information.</td>
    </tr>
  </tbody>
</table>

---

<h2>19. Troubleshooting and Issues Resolved</h2>

<p>
  During implementation, several environment and integration issues were encountered. Resolving these issues helped validate the interactions between Jenkins, Docker Desktop, GitHub, ngrok, and Docker Hub.
</p>

<h3>19.1 Docker Command Not Found in Jenkins</h3>

<p><strong>Observed problem:</strong></p>

<pre><code>docker: command not found</code></pre>

<p>
  Docker Desktop was running, and Docker commands worked in the regular macOS terminal. However, Jenkins could not initially locate the Docker executable.
</p>

<p><strong>Root cause:</strong></p>

<p>
  Jenkins was running with a different environment PATH from the interactive terminal. The Docker executable was not available in the Jenkins process environment.
</p>

<p><strong>Resolution:</strong></p>

<p>
  Jenkins was started in the foreground with the required PATH configuration.
</p>

<pre><code>brew services stop jenkins-lts

export PATH=$PATH:/usr/local/bin

jenkins-lts</code></pre>

<p>
  The Docker executable was available at:
</p>

<pre><code>/usr/local/bin/docker</code></pre>

<p>
  After starting Jenkins with the correct PATH, the Docker availability check succeeded.
</p>

<p><strong>Key learning:</strong> A tool being installed and working in an interactive terminal does not guarantee that a Jenkins process can access it. Jenkins must have the correct executable PATH and Docker daemon access.</p>

<h3>19.2 Understanding the Role of ngrok</h3>

<p><strong>Observed confusion:</strong></p>

<p>
  Jenkins was running locally, and ngrok was also being used. It was important to distinguish which problem ngrok actually solves.
</p>

<p><strong>Explanation:</strong></p>

<ul>
  <li>Jenkins runs the pipeline.</li>
  <li>Docker Desktop provides the Docker daemon.</li>
  <li>The Docker CLI communicates with the Docker daemon.</li>
  <li>ngrok exposes the local Jenkins HTTP endpoint for incoming webhook requests.</li>
</ul>

<p>
  ngrok does not install Docker, start Docker containers, or provide Docker connectivity to Jenkins.
</p>

<h3>19.3 Docker Compose Could Not Find the Required Images</h3>

<p><strong>Observed problem:</strong></p>

<p>
  Docker Compose attempted to pull images when the expected local images had not been built with the names and tags referenced by the Compose configuration.
</p>

<p><strong>Root cause:</strong></p>

<p>
  The Compose configuration referenced image names that were not available locally. The application configuration also did not provide Compose build directives for automatically building those images.
</p>

<p><strong>Resolution:</strong></p>

<p>
  Build the images explicitly before starting the Compose services, ensuring that their names and tags match the Compose configuration.
</p>

<pre><code>cd ~/Projects/Profile_App

export BUILD_NUMBER=local

docker build -t profile-backend:local ./backend

docker build -t profile-frontend:local ./frontend

docker compose up -d</code></pre>

<p>
  The commands above illustrate the local manual build workflow. The image names and tags must remain consistent with the Compose configuration being used.
</p>

<h3>19.4 Docker Hub Authentication Failure</h3>

<p><strong>Observed error:</strong></p>

<pre><code>unauthorized: personal access token is expired</code></pre>

<p><strong>Root cause:</strong></p>

<p>
  The Docker Hub Personal Access Token stored in Jenkins had expired.
</p>

<p><strong>Resolution:</strong></p>

<ol>
  <li>Generate a new Docker Hub token.</li>
  <li>Update the existing <code>dockerhub-cred</code> Jenkins credential.</li>
  <li>Verify that the token has image-publishing permissions.</li>
  <li>Rerun the pipeline.</li>
</ol>

<h3>19.5 ngrok URL Changes</h3>

<p><strong>Possible problem:</strong></p>

<p>
  A previously configured GitHub webhook may stop reaching Jenkins after ngrok is restarted.
</p>

<p><strong>Root cause:</strong></p>

<p>
  A temporary ngrok URL can change when the tunnel is restarted.
</p>

<p><strong>Resolution:</strong></p>

<p>
  Update the GitHub webhook payload URL with the currently active ngrok HTTPS address.
</p>

<h3>19.6 Quick Troubleshooting Reference</h3>

<table>
  <thead>
    <tr>
      <th>Symptom</th>
      <th>First Check</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Docker command unavailable</td>
      <td>Jenkins PATH and Docker executable.</td>
    </tr>
    <tr>
      <td>Docker daemon connection failure</td>
      <td>Docker Desktop status and Docker context.</td>
    </tr>
    <tr>
      <td>Git checkout failure</td>
      <td>Repository URL, branch, and <code>github-cred</code>.</td>
    </tr>
    <tr>
      <td>Docker build failure</td>
      <td>Dockerfile, build context, and application dependencies.</td>
    </tr>
    <tr>
      <td>Docker push unauthorized</td>
      <td>Docker Hub token validity and permissions.</td>
    </tr>
    <tr>
      <td>Compose image not found</td>
      <td>Image repository names and build-number tags.</td>
    </tr>
    <tr>
      <td>Application health check failure</td>
      <td>Container status, application logs, port mappings, and endpoints.</td>
    </tr>
    <tr>
      <td>Logs not archived</td>
      <td>Container names, log commands, and workspace files.</td>
    </tr>
    <tr>
      <td>Email not delivered</td>
      <td>Email Extension plugin and SMTP configuration.</td>
    </tr>
  </tbody>
</table>

---

<h2>20. Security and Reliability Considerations</h2>

<p>
  The project demonstrates several useful DevOps practices. The following considerations are important when evolving the implementation toward a production-ready delivery pipeline.
</p>

<h3>20.1 Secrets Management</h3>

<ul>
  <li>Keep Docker Hub tokens and GitHub credentials inside Jenkins Credentials.</li>
  <li>Avoid hardcoding secrets in source code.</li>
  <li>Use scoped and revocable Personal Access Tokens.</li>
  <li>Restrict access to Jenkins credentials.</li>
  <li>Rotate credentials before expiration.</li>
</ul>

<h3>20.2 Image Versioning</h3>

<p>
  The current implementation uses the Jenkins build number as the Docker image tag.
</p>

<p>
  A future enhancement could include the Git commit SHA as an additional tag to make image-to-source traceability stronger.
</p>

<pre><code>profile-frontend:${BUILD_NUMBER}
profile-backend:${BUILD_NUMBER}</code></pre>

<p>
  Additional traceability tags could follow a pattern such as:
</p>

<pre><code>profile-frontend:${GIT_COMMIT}
profile-backend:${GIT_COMMIT}</code></pre>

<p>
  These are suggested improvements, not additional tags currently confirmed in the implementation.
</p>

<h3>20.3 Docker Image Cleanup</h3>

<p>
  Replace broad host-wide cleanup with a targeted image retention strategy, especially when Jenkins shares a Docker daemon with other workloads.
</p>

<h3>20.4 Deployment Reliability</h3>

<p>
  The current stop-and-recreate Compose deployment can introduce downtime.
</p>

<p>
  A production implementation should consider:
</p>

<ul>
  <li>Blue-green deployments.</li>
  <li>Rolling updates.</li>
  <li>Automated rollback to the previous working image.</li>
  <li>Application readiness checks before switching traffic.</li>
  <li>Deployment approval gates where required.</li>
</ul>

<h3>20.5 Configuration Management</h3>

<p>
  Avoid hardcoded container names and environment-specific endpoints wherever possible.
</p>

<p>
  Use configuration variables, Compose service discovery, and environment-specific configuration to make the pipeline easier to maintain across development, staging, and production environments.
</p>

<h3>20.6 Observability</h3>

<p>
  Jenkins build logs and archived container logs provide basic troubleshooting information.
</p>

<p>
  Additional observability can be introduced through centralized logging, metrics collection, dashboards, and alerting.
</p>

---

<h2>21. Future Enhancements</h2>

<p>
  The current project provides an end-to-end foundation for automated application delivery. The following enhancements can extend it toward a more scalable and production-oriented DevOps workflow.
</p>

<table>
  <thead>
    <tr>
      <th>Area</th>
      <th>Proposed Enhancement</th>
      <th>Expected Benefit</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Code Quality</td>
      <td>Integrate automated linting and static analysis.</td>
      <td>Detect code quality issues earlier.</td>
    </tr>
    <tr>
      <td>Testing</td>
      <td>Add unit, integration, and smoke tests.</td>
      <td>Improve release confidence.</td>
    </tr>
    <tr>
      <td>Security</td>
      <td>Integrate SAST, SCA, and container image scanning.</td>
      <td>Identify vulnerabilities before deployment.</td>
    </tr>
    <tr>
      <td>Image Management</td>
      <td>Introduce image retention and vulnerability policies.</td>
      <td>Improve registry and storage management.</td>
    </tr>
    <tr>
      <td>Deployment</td>
      <td>Implement blue-green or rolling deployments.</td>
      <td>Reduce deployment downtime.</td>
    </tr>
    <tr>
      <td>Rollback</td>
      <td>Automate rollback to a previous image version.</td>
      <td>Improve recovery from failed releases.</td>
    </tr>
    <tr>
      <td>Orchestration</td>
      <td>Deploy the application to Kubernetes.</td>
      <td>Enable container orchestration and scaling.</td>
    </tr>
    <tr>
      <td>Monitoring</td>
      <td>Integrate Prometheus and Grafana.</td>
      <td>Improve infrastructure and application visibility.</td>
    </tr>
    <tr>
      <td>Logging</td>
      <td>Introduce centralized log management.</td>
      <td>Improve investigation across services.</td>
    </tr>
    <tr>
      <td>Notifications</td>
      <td>Extend notifications with richer failure details.</td>
      <td>Improve incident awareness.</td>
    </tr>
  </tbody>
</table>

---

<h2>22. Technical Concepts Demonstrated</h2>

<p>
  This project brings together multiple DevOps concepts in a single application delivery workflow.
</p>

<table>
  <thead>
    <tr>
      <th>Technology / Concept</th>
      <th>How It Is Used</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Jenkins</td>
      <td>Orchestrates the CI/CD workflow.</td>
    </tr>
    <tr>
      <td>Jenkins Shared Library</td>
      <td>Stores reusable Groovy pipeline logic.</td>
    </tr>
    <tr>
      <td>Groovy</td>
      <td>Implements pipeline automation.</td>
    </tr>
    <tr>
      <td>GitHub</td>
      <td>Hosts application and pipeline source code.</td>
    </tr>
    <tr>
      <td>GitHub Webhooks</td>
      <td>Notify Jenkins about repository events.</td>
    </tr>
    <tr>
      <td>ngrok</td>
      <td>Exposes the local Jenkins endpoint for webhook access.</td>
    </tr>
    <tr>
      <td>Docker</td>
      <td>Builds and runs container images.</td>
    </tr>
    <tr>
      <td>Docker Compose</td>
      <td>Manages the frontend and backend services.</td>
    </tr>
    <tr>
      <td>Docker Hub</td>
      <td>Stores and distributes versioned container images.</td>
    </tr>
    <tr>
      <td>Jenkins Credentials</td>
      <td>Manages authentication details securely.</td>
    </tr>
    <tr>
      <td>Shell Scripting</td>
      <td>Automates Docker, Git, and HTTP operations.</td>
    </tr>
    <tr>
      <td>HTTP Health Checks</td>
      <td>Validates application endpoint availability.</td>
    </tr>
    <tr>
      <td>Artifact Archiving</td>
      <td>Preserves frontend and backend logs in Jenkins.</td>
    </tr>
  </tbody>
</table>

---

<h2>23. Interview Preparation – Project Questions and Answers</h2>

<p>
  The following questions help explain the architecture, implementation decisions, troubleshooting experience, and limitations of the project during a technical interview.
</p>

<h3>Q1. Can you explain your project?</h3>

<p>
  I developed an automated CI/CD workflow for a frontend and backend application using Jenkins, Groovy, Docker, Docker Compose, GitHub, and Docker Hub.
</p>

<p>
  I implemented reusable pipeline logic through a Jenkins Shared Library. The pipeline checks out application code, builds separate frontend and backend Docker images, tags them using the Jenkins build number, authenticates with Docker Hub, and publishes the images.
</p>

<p>
  It then deploys the application using Docker Compose, validates the frontend and backend endpoints, collects container logs, archives them in Jenkins, performs Docker image cleanup, and sends an email notification with the build result.
</p>

<h3>Q2. Why did you use a Jenkins Shared Library?</h3>

<p>
  I used a Shared Library to separate reusable pipeline implementation from the Jenkins job configuration.
  It makes the pipeline easier to maintain, modularize, and reuse across multiple jobs.
</p>

<h3>Q3. What is the difference between a Jenkinsfile and a Shared Library?</h3>

<p>
  A Jenkinsfile defines the pipeline entry point and job-specific execution configuration. A Shared Library stores reusable pipeline steps and Groovy implementation that can be called by Jenkins pipeline jobs.
</p>

<h3>Q4. Why did you use Docker for this application?</h3>

<p>
  Docker packages application components and their runtime dependencies into container images. Keeping frontend and backend in separate images makes them independently buildable and deployable.
</p>

<h3>Q5. Why did you use Docker Compose?</h3>

<p>
  Docker Compose allows multiple related application services to be defined and managed together through a YAML configuration file. It simplifies starting the frontend and backend services and managing their network configuration.
</p>

<h3>Q6. How are Docker image versions managed?</h3>

<p>
  The pipeline uses the Jenkins build number as the image tag. This associates the frontend and backend images with the same Jenkins execution and makes it easier to identify a particular build.
</p>

<h3>Q7. How are Docker Hub credentials managed?</h3>

<p>
  I configured a username/password credential in Jenkins using the ID <code>dockerhub-cred</code>. The pipeline retrieves the username and Personal Access Token using the <code>withCredentials</code> step and authenticates with Docker using <code>--password-stdin</code>.
</p>

<h3>Q8. What was the Docker issue you encountered with Jenkins?</h3>

<p>
  Docker commands were working in my regular macOS terminal but Jenkins initially could not find the Docker executable. I identified that the Jenkins process had a different PATH environment. Starting Jenkins with the required PATH configuration resolved the issue.
</p>

<h3>Q9. Why did you use ngrok?</h3>

<p>
  Jenkins was running locally, so GitHub could not directly access its localhost endpoint. I used ngrok to expose the local Jenkins endpoint through a public HTTPS forwarding URL for webhook communication.
</p>

<h3>Q10. How do you validate the deployment?</h3>

<p>
  The pipeline checks the frontend endpoint at <code>http://localhost:3000</code> and the backend endpoint at <code>http://localhost:8000/profile</code> using curl. It also uses retry logic to allow the services time to initialize.
</p>

<h3>Q11. What happens if the health check fails?</h3>

<p>
  The retry block attempts the health-check operation up to five times. If the operation continues to fail, the exception propagates to the pipeline error-handling logic, which marks the build as failed and rethrows the error.
</p>

<h3>Q12. How do you collect application logs?</h3>

<p>
  I use <code>docker logs</code> to collect the frontend and backend container logs into separate files. Jenkins then archives the matching log files using <code>archiveArtifacts</code> with fingerprinting enabled.
</p>

<h3>Q13. How are pipeline failures communicated?</h3>

<p>
  The pipeline uses a try/catch/finally structure. The catch block marks the build as failed and rethrows the exception. The finally block sends an email notification containing the job name, build number, build status, and Jenkins build URL.
</p>

<h3>Q14. Is this a zero-downtime deployment?</h3>

<p>
  No. The current Docker Compose implementation stops the existing services using <code>docker compose down</code> before starting the new deployment. This can cause temporary downtime. A blue-green or rolling deployment strategy would be a better approach for minimizing downtime.
</p>

<h3>Q15. What would you improve in this pipeline?</h3>

<p>
  I would add automated testing, static code analysis, container image vulnerability scanning, better image retention, targeted cleanup, deployment rollback, and centralized monitoring. I would also improve the deployment strategy to reduce downtime and replace hardcoded container names with dynamic discovery.
</p>

<h3>Q16. What did you learn from this project?</h3>

<p>
  The project helped me understand how Jenkins Shared Libraries, Groovy, GitHub webhooks, Docker, Docker Compose, and Docker Hub work together in an automated delivery workflow. It also gave me practical experience troubleshooting environment PATH issues, Docker connectivity, image tagging, registry authentication, and application availability checks.
</p>

---

<h2>24. Project Outcomes</h2>

<p>
  The project demonstrates an automated delivery workflow for a multi-component application, with reusable pipeline implementation and container-based deployment.
</p>

<ul>
  <li>Implemented reusable Jenkins pipeline logic using a Groovy Shared Library.</li>
  <li>Integrated GitHub source checkout into Jenkins.</li>
  <li>Configured a webhook-based approach for triggering Jenkins from GitHub events.</li>
  <li>Automated independent frontend and backend Docker image builds.</li>
  <li>Implemented build-number-based Docker image tagging.</li>
  <li>Integrated Jenkins Credentials for GitHub and Docker Hub authentication.</li>
  <li>Automated Docker Hub image publishing.</li>
  <li>Deployed application services using Docker Compose.</li>
  <li>Implemented frontend and backend HTTP availability checks.</li>
  <li>Collected and archived container logs in Jenkins.</li>
  <li>Added Docker cleanup and email notification logic.</li>
  <li>Resolved practical environment and registry authentication issues.</li>
</ul>

<h3>24.1 What This Project Demonstrates</h3>

<p>
  Beyond individual tool usage, this project demonstrates the ability to connect multiple DevOps components into a working application delivery process.
</p>

<p>
  It covers source control integration, pipeline automation, reusable code, containerization, registry publishing, deployment, validation, troubleshooting, and operational notifications.
</p>

---

<h2>25. Conclusion</h2>

<p>
  This project demonstrates the implementation of a Jenkins-based CI/CD workflow using a reusable Groovy Shared Library and Docker-based application deployment.
</p>

<p>
  It brings together GitHub, Jenkins, Docker, Docker Compose, Docker Hub, ngrok, and Jenkins Credentials to automate the major steps involved in building, publishing, deploying, and validating a frontend and backend application.
</p>

<p>
  The implementation also provides a foundation for further improvements in automated testing, security scanning, deployment strategies, monitoring, and production-grade reliability.
</p>

<p>
  The overall objective is to make application delivery more consistent, repeatable, maintainable, and easier to troubleshoot.
</p>

<hr/>

<p align="center">
  <strong>Built with Jenkins • Groovy • Docker • Docker Compose • GitHub</strong>
</p>

<p align="center">
  <a href="https://github.com/12pavani/jenkins-gsl-profileApp">Jenkins Shared Library</a>
  &nbsp; | &nbsp;
  <a href="https://github.com/12pavani/Profile_App">Application Repository</a>
</p>

<p align="center">
  <a href="https://github.com/12pavani">
    <img src="https://img.shields.io/badge/GitHub-12pavani-181717?style=for-the-badge&amp;logo=github" alt="GitHub Profile"/>
  </a>
</p>

<p align="center">
  <em>Automate. Build. Ship. Validate. Improve.</em>
</p>
