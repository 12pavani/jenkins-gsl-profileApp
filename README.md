
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


<h2>8. Prerequisites and Requirements</h2>

<p>Before running this project, make sure the required tools are installed and configured on your system. Each tool has a specific responsibility in the CI/CD workflow.</p>

<h3>8.1 Required Tools</h3>

<table>
  <thead>
    <tr>
      <th>Tool</th>
      <th>Purpose</th>
      <th>Why It Is Required</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Git</td>
      <td>Version control</td>
      <td>Used to clone the application repository and manage source code.</td>
    </tr>
    <tr>
      <td>GitHub</td>
      <td>Source code hosting</td>
      <td>Stores the application and Jenkins Shared Library repositories.</td>
    </tr>
    <tr>
      <td>Jenkins LTS</td>
      <td>CI/CD automation</td>
      <td>Executes the pipeline stages automatically.</td>
    </tr>
    <tr>
      <td>Docker Desktop</td>
      <td>Containerization</td>
      <td>Builds Docker images and runs application containers.</td>
    </tr>
    <tr>
      <td>Docker Compose</td>
      <td>Multi-container management</td>
      <td>Starts and manages the frontend and backend services together.</td>
    </tr>
    <tr>
      <td>ngrok</td>
      <td>Public tunneling</td>
      <td>Allows GitHub to reach the locally running Jenkins instance for webhook notifications.</td>
    </tr>
    <tr>
      <td>Docker Hub</td>
      <td>Container image registry</td>
      <td>Stores the frontend and backend Docker images pushed by Jenkins.</td>
    </tr>
  </tbody>
</table>

<h3>8.2 Accounts and Access Required</h3>

<p>You should have access to the following accounts and resources:</p>

<ul>
  <li><strong>GitHub account:</strong> Required to access the application and shared library repositories.</li>
  <li><strong>Docker Hub account:</strong> Required to authenticate with Docker Hub and push container images.</li>
  <li><strong>Jenkins administrator access:</strong> Required to configure plugins, credentials, and shared libraries.</li>
  <li><strong>Local machine access:</strong> Required to install and run Jenkins, Docker Desktop, and ngrok.</li>
</ul>

<h3>8.3 Basic System Requirements</h3>

<ul>
  <li>A macOS, Linux, or Windows machine.</li>
  <li>Internet connectivity to download dependencies and communicate with GitHub and Docker Hub.</li>
  <li>Sufficient disk space for Docker images, containers, Jenkins workspaces, and build logs.</li>
  <li>Permissions to execute Docker commands from the terminal and Jenkins.</li>
</ul>

<p><strong>Important:</strong> Jenkins and Docker must be able to communicate with each other. Installing Docker Desktop alone is not sufficient if the Jenkins process cannot access the Docker CLI or Docker daemon.</p>

<hr/>

<h2>9. Setting Up the Local Development Environment</h2>

<p>In this project, Jenkins and Docker Desktop run on the local macOS machine. Jenkins acts as the automation server, while Docker handles image creation and container execution.</p>

<h3>9.1 Verify Git Installation</h3>

<p>Git is used by Jenkins to retrieve the application source code from GitHub.</p>

<p>Open the macOS Terminal and execute:</p>

<pre><code>git --version</code></pre>

<p>Expected output will look similar to:</p>

<pre><code>git version 2.x.x</code></pre>

<p>The exact version may differ depending on your installation.</p>

<p>If Git is not installed, macOS can install the Xcode Command Line Tools using:</p>

<pre><code>xcode-select --install</code></pre>

<h3>9.2 Verify Docker Desktop</h3>

<p>Docker Desktop provides the Docker engine and command-line tools required to build and run containers.</p>

<ol>
  <li>Install Docker Desktop for macOS.</li>
  <li>Open Docker Desktop.</li>
  <li>Wait until Docker finishes starting.</li>
  <li>Open Terminal and verify the Docker CLI.</li>
</ol>

<p>Execute:</p>

<pre><code>docker --version</code></pre>

<p>To check whether Docker is running correctly, execute:</p>

<pre><code>docker info</code></pre>

<p><strong>What does this command do?</strong></p>

<ul>
  <li><code>docker --version</code> displays the installed Docker CLI version.</li>
  <li><code>docker info</code> communicates with the Docker daemon and displays information about the Docker environment.</li>
</ul>

<p>If Docker is working correctly, <code>docker info</code> should display details such as the number of containers, images, storage driver, and server version.</p>

<p><strong>Important:</strong> If you receive an error saying that Docker cannot connect to the daemon, first open Docker Desktop and wait for it to finish starting. Then execute <code>docker info</code> again.</p>

<h3>9.3 Verify Docker Compose</h3>

<p>Docker Compose is used to manage multiple containers through a single YAML configuration file.</p>

<p>This project uses Docker Compose to run the frontend and backend services together.</p>

<p>Verify Docker Compose using:</p>

<pre><code>docker compose version</code></pre>

<p>Expected output will look similar to:</p>

<pre><code>Docker Compose version v2.x.x</code></pre>

<p>The command uses <code>docker compose</code> with a space because the project uses the modern Docker Compose V2 plugin.</p>

<h3>9.4 Verify Docker Hub Access</h3>

<p>Docker Hub is the container image registry used by this project.</p>

<p>The pipeline pushes the following images:</p>

<pre><code>vislavathpavani5123/profile-frontend:&lt;BUILD_NUMBER&gt;

vislavathpavani5123/profile-backend:&lt;BUILD_NUMBER&gt;</code></pre>

<p>The <code>&lt;BUILD_NUMBER&gt;</code> value is dynamically replaced with the Jenkins build number.</p>

<p>For example, if the Jenkins build number is 5, the image tags will be:</p>

<pre><code>vislavathpavani5123/profile-frontend:5

vislavathpavani5123/profile-backend:5</code></pre>

<p>You do not need to manually log in to Docker Hub for every pipeline execution. Jenkins will authenticate using the credentials configured inside Jenkins.</p>

<hr/>

<h2>10. Installing and Starting Jenkins on macOS</h2>

<p>Jenkins is the central component of this project. It coordinates the entire CI/CD process, including source code checkout, Docker image creation, image publishing, deployment, health checks, and email notifications.</p>

<h3>10.1 Install Jenkins LTS</h3>

<p>Jenkins LTS (Long-Term Support) is the stable release line recommended for regular CI/CD environments.</p>

<p>On macOS, Jenkins can be installed using Homebrew.</p>

<p>First, verify whether Homebrew is installed:</p>

<pre><code>brew --version</code></pre>

<p>If Homebrew is available, install Jenkins LTS using:</p>

<pre><code>brew install jenkins-lts</code></pre>

<p>After installation, verify the installed formula:</p>

<pre><code>brew list --formula | grep -i jenkins</code></pre>

<p>This should display:</p>

<pre><code>jenkins-lts</code></pre>

<h3>10.2 Start Jenkins</h3>

<p>There are two common ways to start Jenkins when installed through Homebrew.</p>

<p><strong>Method 1: Start Jenkins as a background service</strong></p>

<pre><code>brew services start jenkins-lts</code></pre>

<p>This starts Jenkins as a background service. Jenkins continues running without requiring the Terminal window to remain in the foreground.</p>

<p>To check its status:</p>

<pre><code>brew services list</code></pre>

<p><strong>Method 2: Start Jenkins directly from Terminal</strong></p>

<p>In this project setup, Jenkins was also started directly from the Terminal so that its logs could be viewed in the same Terminal window.</p>

<p>First, stop the background service if it is already running:</p>

<pre><code>brew services stop jenkins-lts</code></pre>

<p>Then execute:</p>

<pre><code>export PATH=$PATH:/usr/local/bin
jenkins-lts</code></pre>

<p><strong>Understanding these commands:</strong></p>

<ul>
  <li><code>export PATH=$PATH:/usr/local/bin</code> adds <code>/usr/local/bin</code> to the current shell's PATH.</li>
  <li><code>PATH</code> is an environment variable containing directories where the operating system searches for executable commands.</li>
  <li><code>jenkins-lts</code> starts Jenkins directly from the Terminal.</li>
</ul>

<p>Keep this Terminal window open while Jenkins is running in the foreground. Closing the Terminal or stopping the process can stop Jenkins.</p>

<p><strong>Note:</strong> Do not start Jenkins through both methods simultaneously. Doing so can cause a port conflict if both processes try to use the same port.</p>

<h3>10.3 Access the Jenkins Dashboard</h3>

<p>By default, Jenkins uses port <code>8080</code>.</p>

<p>Once Jenkins has started, open the following URL in your browser:</p>

<pre><code>http://localhost:8080</code></pre>

<p>You should see the Jenkins interface.</p>

<h3>10.4 Verify Jenkins Is Running</h3>

<p>You can verify that Jenkins is responding by executing:</p>

<pre><code>curl -I http://localhost:8080</code></pre>

<p>A successful HTTP response indicates that the Jenkins web server is responding.</p>

<p>You can also open <code>http://localhost:8080</code> directly in your browser.</p>

<h3>10.5 Jenkins Initial Setup</h3>

<p>When Jenkins is opened for the first time, it may display the Unlock Jenkins page.</p>

<p>Follow these steps:</p>

<ol>
  <li>Open <code>http://localhost:8080</code>.</li>
  <li>Locate the initial administrator password instructions displayed on the page.</li>
  <li>Retrieve the password using the file path provided by your Jenkins installation.</li>
  <li>Enter the password in the Unlock Jenkins screen.</li>
  <li>Install the suggested plugins or select the plugins required for your environment.</li>
  <li>Create the first administrator user.</li>
  <li>Complete the Jenkins setup wizard.</li>
</ol>

<p><strong>Security note:</strong> Never publish the initial administrator password or any Jenkins credentials in a public GitHub repository.</p>

<hr/>

<h2>11. Understanding the Jenkins and Docker Connection</h2>

<p>One important requirement in this project is that Jenkins must be able to execute Docker commands.</p>

<p>For example, the pipeline executes commands such as:</p>

<pre><code>docker info

docker build -t profile-frontend:${BUILD_NUMBER} frontend

docker images

docker compose up -d</code></pre>

<p>These commands are executed by the Jenkins pipeline through shell steps.</p>

<h3>11.1 How the Connection Works</h3>

<pre><code>+---------------------------------------+
|             macOS Machine             |
|                                       |
|   +-------------------------------+   |
|   |           Jenkins             |   |
|   |                               |   |
|   |   Pipeline executes shell     |   |
|   |   commands using Docker CLI   |   |
|   +---------------+---------------+   |
|                   |                   |
|                   | Docker commands   |
|                   v                   |
|   +-------------------------------+   |
|   |         Docker CLI             |   |
|   +---------------+---------------+   |
|                   |                   |
|                   | Docker API        |
|                   v                   |
|   +-------------------------------+   |
|   |       Docker Desktop          |   |
|   |                               |   |
|   |   Docker daemon               |   |
|   |   Frontend container          |   |
|   |   Backend container           |   |
|   +-------------------------------+   |
|                                       |
+---------------------------------------+</code></pre>

<h3>11.2 Why Docker Desktop Must Be Running</h3>

<p>The Docker CLI is only the command-line interface. It sends requests to the Docker daemon, which performs the actual container operations.</p>

<p>For example, when Jenkins executes:</p>

<pre><code>docker build -t profile-frontend:1 frontend</code></pre>

<p>The following happens:</p>

<ol>
  <li>Jenkins executes the shell command.</li>
  <li>The shell locates the Docker CLI using the PATH environment variable.</li>
  <li>The Docker CLI sends a build request to the Docker daemon.</li>
  <li>The Docker daemon reads the Dockerfile and build context.</li>
  <li>Docker creates the image and assigns the requested tag.</li>
</ol>

<p>If the Docker daemon is unavailable, the command fails even if the Docker CLI is installed.</p>

<h3>11.3 Important Difference: Docker CLI and Docker Daemon</h3>

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Responsibility</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Docker CLI</td>
      <td>Accepts commands such as <code>docker build</code>, <code>docker ps</code>, and <code>docker compose up</code>.</td>
    </tr>
    <tr>
      <td>Docker daemon</td>
      <td>Performs operations such as building images, creating containers, and managing networks.</td>
    </tr>
    <tr>
      <td>Docker Desktop</td>
      <td>Provides and manages the local Docker environment on macOS.</td>
    </tr>
  </tbody>
</table>

<p><strong>Key takeaway:</strong> Jenkins does not automatically receive Docker access simply because Docker Desktop is installed on the machine. The Jenkins process must be able to locate the Docker CLI and communicate with the Docker daemon.</p>


<h2>12. Understanding the GitHub Repositories</h2>

<p>This project is maintained using two separate GitHub repositories. Separating application code from pipeline automation makes the project easier to maintain and allows the same pipeline logic to be reused for other applications.</p>

<h3>12.1 Repository 1: Jenkins Shared Library</h3>

<p><strong>Repository name:</strong> jenkins-gsl-profileApp</p>

<p><strong>GitHub URL:</strong> <a href="https://github.com/12pavani/jenkins-gsl-profileApp">https://github.com/12pavani/jenkins-gsl-profileApp</a></p>

<p>This repository contains the reusable Jenkins pipeline implementation written in Groovy.</p>

<p>Instead of writing the entire CI/CD pipeline inside every application's Jenkinsfile, we maintain the main pipeline logic in this repository.</p>

<p>The repository currently has the following top-level structure:</p>

<pre><code>jenkins-gsl-profileApp/
|
+-- src/
|   |
|   +-- com/
|       |
|       +-- pavani/
|           |
|           +-- pipeline/
|
+-- vars/</code></pre>

<p><strong>Understanding the folders:</strong></p>

<h4>src/</h4>

<p>The <code>src</code> directory contains Groovy source code organized using Java-style package naming conventions.</p>

<p>In this project, the pipeline code uses the package:</p>

<pre><code>package com.pavani.pipeline</code></pre>

<p>The <code>src/com/pavani/pipeline</code> directory is therefore associated with the <code>com.pavani.pipeline</code> package.</p>

<p>The shared library method <code>BuildDeployPipeline()</code> contains the implementation of the application's build and deployment workflow.</p>

<h4>vars/</h4>

<p>The <code>vars</code> directory is used by Jenkins Shared Libraries to expose reusable pipeline steps and global variables.</p>

<p>For example, a Groovy file inside this directory can define a custom pipeline step that a Jenkinsfile can invoke.</p>

<p>The exact filenames and method wrappers in this project's <code>vars</code> directory should be checked against the repository before documenting their individual responsibilities.</p>

<h3>12.2 Repository 2: Profile Application</h3>

<p><strong>Repository name:</strong> Profile_App</p>

<p><strong>GitHub URL:</strong> <a href="https://github.com/12pavani/Profile_App">https://github.com/12pavani/Profile_App</a></p>

<p>This repository contains the actual application that the Jenkins pipeline builds and deploys.</p>

<p>The repository currently has the following top-level structure:</p>

<pre><code>Profile_App/
|
+-- backend/
|
+-- frontend/
|
+-- node_modules/
|
+-- .DS_Store
|
+-- docker-compose.yaml
|
+-- package.json
|
+-- package-lock.json</code></pre>

<p><strong>Understanding the folders and files:</strong></p>

<h4>frontend/</h4>

<p>This directory contains the frontend application.</p>

<p>The frontend is packaged into a Docker image using the following command in the shared library:</p>

<pre><code>docker build -t profile-frontend:${BUILD_NUMBER} frontend</code></pre>

<p>Here:</p>

<ul>
  <li><code>docker build</code> instructs Docker to build an image.</li>
  <li><code>-t</code> assigns a name and tag to the image.</li>
  <li><code>profile-frontend</code> is the image name.</li>
  <li><code>${BUILD_NUMBER}</code> is the Jenkins build number.</li>
  <li><code>frontend</code> is the build context directory containing the frontend's Docker build files and application source.</li>
</ul>

<h4>backend/</h4>

<p>This directory contains the backend application.</p>

<p>The backend is packaged into a separate Docker image:</p>

<pre><code>docker build -t profile-backend:${BUILD_NUMBER} backend</code></pre>

<p>The frontend and backend are built separately because they are independent application components and run as separate containers.</p>

<h4>docker-compose.yaml</h4>

<p>This file defines how the frontend and backend containers are configured and run together.</p>

<p>Docker Compose reads this YAML file to understand the application services and their runtime configuration.</p>

<p>In this project, Jenkins uses:</p>

<pre><code>docker compose -f docker-compose.yaml up -d</code></pre>

<p>Breaking down the command:</p>

<ul>
  <li><code>docker compose</code> invokes Docker Compose.</li>
  <li><code>-f docker-compose.yaml</code> specifies the Compose configuration file.</li>
  <li><code>up</code> creates and starts the configured services.</li>
  <li><code>-d</code> runs the containers in detached mode, meaning they continue running in the background.</li>
</ul>

<p>The pipeline also executes <code>docker compose down</code> before starting the services again.</p>

<h4>package.json</h4>

<p>This is a Node.js project configuration file. It commonly contains application metadata, scripts, and dependency declarations.</p>

<p>For example, Node.js applications may define commands for starting the application, running tests, or installing dependencies in this file.</p>

<h4>package-lock.json</h4>

<p>This file records the resolved dependency versions for an npm project. It helps maintain consistent dependency installation across environments.</p>

<h4>node_modules/</h4>

<p>This directory contains installed Node.js packages.</p>

<p>It is generally better not to commit <code>node_modules</code> to GitHub. Dependencies should normally be installed from the package manifests using npm, and the directory should be excluded through <code>.gitignore</code>.</p>

<p>For a production-ready repository, review whether this directory should be removed from version control.</p>

<h4>.DS_Store</h4>

<p>This is a macOS-generated metadata file used by Finder to store folder display settings.</p>

<p>It is not required to build or deploy the application. It is generally excluded from Git repositories using <code>.gitignore</code>.</p>

<hr/>

<h2>13. How Both Repositories Work Together</h2>

<p>Now that we understand the two repositories, let's understand how Jenkins uses them during a build.</p>

<h3>13.1 The Relationship Between the Repositories</h3>

<pre><code>       GITHUB
          |
          |
     +----+---------------------+
     |                          |
     v                          v
+----------------------+  +----------------------+
| Jenkins Shared       |  | Profile Application  |
| Library Repository   |  | Repository            |
|                      |  |                      |
| Groovy pipeline code |  | frontend/            |
| src/                 |  | backend/             |
| vars/                |  | docker-compose.yaml  |
+----------+-----------+  +----------+-----------+
           |                         |
           |                         |
           v                         |
+------------------------------------+
|               JENKINS             |
|                                    |
| Loads Shared Library              |
| Executes BuildDeployPipeline()    |
|                                    |
| Checks out Profile_App source code|
|                                    |
| Builds frontend and backend       |
| Docker images                     |
|                                    |
| Pushes images to Docker Hub        |
|                                    |
| Deploys using Docker Compose       |
+------------------+-----------------+
                   |
                   v
             DOCKER DESKTOP
                   |
          +--------+--------+
          |                 |
          v                 v
   +-------------+   +-------------+
   |  Frontend   |   |   Backend   |
   |  Container  |   |  Container  |
   +-------------+   +-------------+</code></pre>

<h3>13.2 What Happens During Pipeline Execution?</h3>

<ol>
  <li><strong>Shared Library loading:</strong> Jenkins loads the configured Shared Library repository so that its reusable Groovy pipeline code is available to the Jenkins job.</li>
  <li><strong>Pipeline invocation:</strong> The Jenkinsfile or another pipeline entry point invokes the shared pipeline method.</li>
  <li><strong>Application checkout:</strong> The shared pipeline checks out the Profile_App repository using the Git checkout configuration.</li>
  <li><strong>Source code availability:</strong> Jenkins now has the frontend and backend source code in its workspace.</li>
  <li><strong>Docker image building:</strong> Jenkins builds separate Docker images for the frontend and backend.</li>
  <li><strong>Image publishing:</strong> Jenkins authenticates with Docker Hub and pushes the tagged images.</li>
  <li><strong>Application deployment:</strong> Docker Compose starts the frontend and backend containers.</li>
  <li><strong>Health verification:</strong> Jenkins checks whether the application endpoints respond successfully.</li>
</ol>

<h3>13.3 Important Concept: Shared Library Checkout vs Application Checkout</h3>

<p>These are two different operations and should not be confused.</p>

<table>
  <thead>
    <tr>
      <th>Operation</th>
      <th>Repository</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Shared Library loading</td>
      <td>jenkins-gsl-profileApp</td>
      <td>Makes reusable Groovy pipeline logic available to Jenkins.</td>
    </tr>
    <tr>
      <td>Application checkout</td>
      <td>Profile_App</td>
      <td>Downloads the actual application source code into the Jenkins workspace.</td>
    </tr>
  </tbody>
</table>

<p>The shared library is loaded through Jenkins Shared Library configuration or a library annotation. The application source is checked out by the <code>checkout</code> step inside the shared pipeline.</p>

<p>In this project, the application checkout uses:</p>

<pre><code>checkout([
    $class: 'GitSCM',
    branches: [[name: '*/main']],
    userRemoteConfigs: [[
        url: 'https://github.com/12pavani/Profile_App.git',
        credentialsId: 'github-cred'
    ]]
])</code></pre>

<p>This tells Jenkins to retrieve the application source from the <code>main</code> branch.</p>

<p><strong>Remember:</strong> Loading the Shared Library does not automatically check out the application repository into the job workspace. These are separate operations.</p>

<hr/>

<h2>14. Why Use a Jenkins Shared Library?</h2>

<p>Before configuring the pipeline, it is important to understand why the project uses a Shared Library instead of keeping all pipeline logic inside a Jenkinsfile.</p>

<h3>14.1 Without a Shared Library</h3>

<p>Imagine that an organization maintains five applications. Each application has its own Jenkinsfile containing the same build, Docker login, image push, deployment, and notification logic.</p>

<p>If the Docker login process needs to change, the team may need to update five different Jenkinsfiles.</p>

<h3>14.2 With a Shared Library</h3>

<p>Instead, common pipeline logic can be maintained in one reusable repository.</p>

<p>Each application pipeline can load the Shared Library and invoke the required functionality.</p>

<h3>14.3 Main Advantages</h3>

<ul>
  <li><strong>Reusability:</strong> The same pipeline logic can be used across multiple application repositories.</li>
  <li><strong>Maintainability:</strong> Common changes can be made centrally.</li>
  <li><strong>Consistency:</strong> Applications can follow a common build and deployment process.</li>
  <li><strong>Reduced duplication:</strong> Developers do not need to copy large pipeline scripts into every repository.</li>
  <li><strong>Separation of concerns:</strong> Application source code and CI/CD automation logic are maintained independently.</li>
</ul>

<p><strong>Interview point:</strong> A Jenkins Shared Library is a reusable collection of Groovy code that allows teams to centralize and share pipeline logic across multiple Jenkins jobs.</p>

