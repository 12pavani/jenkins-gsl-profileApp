
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

