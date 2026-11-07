
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


<h2>15. Configuring the Jenkins Shared Library</h2>

<p>Before Jenkins can execute the reusable pipeline code stored in GitHub, we must tell Jenkins where the Shared Library repository is located and how to retrieve it.</p>

<p>This configuration connects Jenkins with the <strong>jenkins-gsl-profileApp</strong> repository.</p>

<h3>15.1 Why Do We Need to Configure a Shared Library?</h3>

<p>Jenkins cannot automatically identify every GitHub repository as a Shared Library.</p>

<p>We must configure the repository in Jenkins so that it knows:</p>

<ul>
  <li>Which GitHub repository contains the reusable pipeline code.</li>
  <li>What name should be used to reference the library.</li>
  <li>Which branch or version should be loaded.</li>
  <li>Which credentials should be used to access the repository, if authentication is required.</li>
</ul>

<p>Once configured, Jenkins can load the library when a pipeline requests it.</p>

<h3>15.2 Open Jenkins System Configuration</h3>

<ol>
  <li>Open the Jenkins dashboard.</li>
  <li>Click <strong>Manage Jenkins</strong>.</li>
  <li>Open <strong>System</strong> or <strong>Configure System</strong>, depending on your Jenkins version.</li>
  <li>Scroll down to the <strong>Global Pipeline Libraries</strong> section.</li>
  <li>Click <strong>Add</strong> to register a new Shared Library.</li>
</ol>

<p>This section allows Jenkins administrators to define libraries that can be reused by different pipeline jobs.</p>

<h3>15.3 Configure the Shared Library Details</h3>

<p>Use the following values as the configuration guide for this project. Confirm the actual default branch and repository visibility before saving.</p>

<table>
  <thead>
    <tr>
      <th>Configuration</th>
      <th>Value</th>
      <th>Explanation</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Name</td>
      <td><code>jenkins-gsl-profileApp</code></td>
      <td>The identifier used to refer to the library from a Jenkins pipeline.</td>
    </tr>
    <tr>
      <td>Default version</td>
      <td><code>main</code></td>
      <td>The Git branch Jenkins should load by default, assuming the repository uses this branch.</td>
    </tr>
    <tr>
      <td>Load implicitly</td>
      <td>Unchecked</td>
      <td>Allows the Jenkinsfile to explicitly request the library instead of loading it automatically for every pipeline.</td>
    </tr>
    <tr>
      <td>Allow default version to be overridden</td>
      <td>According to project requirements</td>
      <td>Controls whether a pipeline can request another library version.</td>
    </tr>
    <tr>
      <td>Retrieval method</td>
      <td>Modern SCM</td>
      <td>Uses the configured source control management system to retrieve the library.</td>
    </tr>
    <tr>
      <td>SCM</td>
      <td>Git</td>
      <td>Identifies Git as the source control system.</td>
    </tr>
    <tr>
      <td>Project Repository</td>
      <td><code>https://github.com/12pavani/jenkins-gsl-profileApp.git</code></td>
      <td>The GitHub URL of the Shared Library repository.</td>
    </tr>
    <tr>
      <td>Credentials</td>
      <td>Only if required</td>
      <td>Authentication details for a private repository.</td>
    </tr>
  </tbody>
</table>

<h3>15.4 Understanding the Name and Default Version</h3>

<p><strong>Name:</strong></p>

<p>The name is the identifier Jenkins uses when a pipeline requests the Shared Library.</p>

<p>For example, if the configured name is:</p>

<pre><code>jenkins-gsl-profileApp</code></pre>

<p>A Jenkinsfile can request that library using:</p>

<pre><code>@Library('jenkins-gsl-profileApp') _</code></pre>

<p><strong>Default version:</strong></p>

<p>The default version tells Jenkins which branch, tag, or commit to retrieve when the pipeline does not specify another version.</p>

<p>For example:</p>

<pre><code>main</code></pre>

<p>This means Jenkins retrieves the library from the <code>main</code> branch by default.</p>

<h3>15.5 Understanding Modern SCM</h3>

<p>SCM stands for <strong>Source Code Management</strong>.</p>

<p>It refers to the system used to store and manage source code versions. Git is one of the most commonly used SCM tools.</p>

<p>Jenkins supports different ways of retrieving Shared Libraries. Modern SCM is the recommended configuration when the appropriate SCM plugin is available.</p>

<p>In this project:</p>

<pre><code>Jenkins
   |
   v
Modern SCM
   |
   v
Git
   |
   v
GitHub Repository
   |
   v
jenkins-gsl-profileApp</code></pre>

<p>Jenkins uses this configuration to retrieve the Groovy library code from GitHub.</p>

<h3>15.6 Configure Credentials If the Repository Is Private</h3>

<p>If the Shared Library repository is private, Jenkins needs authentication to retrieve it.</p>

<p>For a public repository, authentication may not be necessary for read access.</p>

<p>If credentials are required, create an appropriate GitHub credential in Jenkins and select it in the Shared Library SCM configuration.</p>

<p>Do not confuse these credentials with the Docker Hub credentials used later in the pipeline. They serve different purposes.</p>

<h3>15.7 Save the Configuration</h3>

<ol>
  <li>Review the library name.</li>
  <li>Verify the repository URL.</li>
  <li>Check the default branch.</li>
  <li>Verify the retrieval method and credentials.</li>
  <li>Click <strong>Save</strong>.</li>
</ol>

<p>Jenkins now has the Shared Library configuration available to pipeline jobs.</p>

<p><strong>Important:</strong> Registering the library does not automatically execute its methods. A pipeline must still load and invoke the appropriate library functionality.</p>

<hr/>

<h2>16. Loading and Invoking the Shared Library</h2>

<p>After registering the Shared Library, the next step is to make its reusable pipeline code available to a Jenkins job.</p>

<h3>16.1 What Does Loading a Shared Library Mean?</h3>

<p>Loading a Shared Library means retrieving its Groovy code and making the library's available classes and pipeline steps accessible to the Jenkins pipeline.</p>

<p>For example, the project contains a reusable method named:</p>

<pre><code>BuildDeployPipeline()</code></pre>

<p>This method contains the build and deployment workflow.</p>

<p>The Jenkins pipeline must load the library and invoke the appropriate entry point for that workflow to execute.</p>

<h3>16.2 The @Library Annotation</h3>

<p>Jenkins provides the <code>@Library</code> annotation to load a Shared Library in a Jenkinsfile.</p>

<p>For example, the following is an illustrative library-loading statement:</p>

<pre><code>@Library('jenkins-gsl-profileApp') _</code></pre>

<p>Here:</p>

<ul>
  <li><code>@Library</code> tells Jenkins to load a configured Shared Library.</li>
  <li><code>'jenkins-gsl-profileApp'</code> is the library name registered in Jenkins.</li>
  <li><code>_</code> is the wildcard import form used by Jenkins to make the library's global variables available.</li>
</ul>

<h3>16.3 Understanding the Package Declaration</h3>

<p>The shared pipeline code supplied for this project uses the following package declaration:</p>

<pre><code>package com.pavani.pipeline</code></pre>

<p>A package groups related Groovy classes and helps organize the code.</p>

<p>For example, if a class is defined inside this package, it can be referenced using its fully qualified class name:</p>

<pre><code>com.pavani.pipeline.ClassName</code></pre>

<p>The exact class name and the location of the <code>BuildDeployPipeline()</code> method must match the actual repository implementation.</p>

<h3>16.4 Understanding the Difference Between a Class Method and a Global Pipeline Step</h3>

<p>There are two common patterns for exposing reusable functionality in a Jenkins Shared Library.</p>

<p><strong>Pattern 1: Class-based implementation under src/</strong></p>

<p>Reusable Groovy classes are stored under the <code>src/</code> directory and can be imported into pipeline code.</p>

<p><strong>Pattern 2: Global pipeline step under vars/</strong></p>

<p>Groovy scripts stored under <code>vars/</code> can expose global pipeline steps that Jenkinsfiles invoke directly.</p>

<p>For example, a file named <code>buildDeployPipeline.groovy</code> could expose a global step named <code>buildDeployPipeline()</code>.</p>

<p>These patterns are not interchangeable automatically. The actual method and invocation must match the repository's implementation.</p>

<h3>16.5 How the Application Pipeline Gets Executed</h3>

<p>The expected high-level execution relationship is:</p>

<pre><code>Jenkins Job
    |
    v
Jenkinsfile
    |
    v
Load Shared Library
    |
    v
Invoke Pipeline Entry Point
    |
    v
BuildDeployPipeline()
    |
    v
Execute CI/CD Stages</code></pre>

<p>The Jenkinsfile is the entry point for a Pipeline job. The Shared Library provides the reusable implementation.</p>

<p><strong>Important project-specific verification:</strong> We have the implementation of <code>BuildDeployPipeline()</code>, but its exact class wrapper, global step wrapper, filename, and calling Jenkinsfile have not yet been established. Therefore, do not copy an assumed invocation example into the project without checking the actual files.</p>

<hr/>

<h2>17. Understanding the Jenkins Workspace</h2>

<p>The Jenkins workspace is the directory where Jenkins performs the operations required by a particular job.</p>

<p>For this project, the workspace is important because the pipeline checks out the application source code and executes Docker build commands from the workspace.</p>

<h3>17.1 What Is a Workspace?</h3>

<p>A workspace is a working directory allocated to a Jenkins job on the agent where the pipeline executes.</p>

<p>It can contain:</p>

<ul>
  <li>Checked-out application source code.</li>
  <li>Frontend and backend directories.</li>
  <li>Dockerfiles and Compose configuration.</li>
  <li>Temporary build files.</li>
  <li>Generated logs and other build artifacts.</li>
</ul>

<h3>17.2 How the Workspace Is Used in This Project</h3>

<p>The shared pipeline uses:</p>

<pre><code>deleteDir()</code></pre>

<p>This removes the current workspace contents before the application is checked out.</p>

<p>Later, the pipeline executes the Git checkout operation to retrieve the Profile_App repository.</p>

<p>Once checkout completes, the workspace contains the application source code needed for the Docker builds.</p>

<h3>17.3 Understanding the WORKSPACE Environment Variable</h3>

<p>Jenkins automatically provides an environment variable named <code>WORKSPACE</code>.</p>

<p>It contains the absolute path to the current job's workspace.</p>

<p>The project prints this value as part of its build information:</p>

<pre><code>echo "Workspace: ${WORKSPACE}"</code></pre>

<p>This is useful when troubleshooting file paths, Docker build contexts, and missing application files.</p>

<h3>17.4 Why Workspace Cleanup Matters</h3>

<p>Suppose a previous build generated temporary files or checked out an older version of the application.</p>

<p>If the next build reuses the workspace without appropriate cleanup, unwanted files may remain and affect the new build.</p>

<p>Using <code>deleteDir()</code> helps ensure that the next checkout starts with a clean workspace.</p>

<p><strong>Important:</strong> Workspace cleanup removes files from the Jenkins workspace. It does not automatically delete Docker images, Docker containers, or data stored elsewhere on the machine.</p>


<h2>18. Installing and Configuring Jenkins Plugins</h2>

<p>Jenkins plugins extend the functionality of Jenkins. They allow Jenkins to integrate with external tools and services such as GitHub, Docker, and email servers.</p>

<p>In this project, plugins are required for source code management, pipeline execution, Shared Library integration, and build notifications.</p>

<h3>18.1 What Is a Jenkins Plugin?</h3>

<p>A Jenkins plugin is an extension that adds additional functionality to the Jenkins platform.</p>

<p>For example:</p>

<ul>
  <li>The Git plugin allows Jenkins to retrieve source code from Git repositories.</li>
  <li>The Pipeline plugin provides support for defining and executing CI/CD pipelines.</li>
  <li>The Email Extension plugin provides advanced email notification capabilities.</li>
</ul>

<p>Without the required plugins, some pipeline steps or integrations may not be available.</p>

<h3>18.2 Plugins Required for This Project</h3>

<table>
  <thead>
    <tr>
      <th>Plugin</th>
      <th>Purpose</th>
      <th>Project Usage</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Pipeline</td>
      <td>Provides Jenkins Pipeline functionality.</td>
      <td>Executes the pipeline and its stages.</td>
    </tr>
    <tr>
      <td>Git</td>
      <td>Integrates Git with Jenkins.</td>
      <td>Checks out the Profile_App repository.</td>
    </tr>
    <tr>
      <td>Pipeline: Groovy</td>
      <td>Provides Groovy support for pipeline scripts.</td>
      <td>Supports Groovy-based pipeline implementation.</td>
    </tr>
    <tr>
      <td>GitHub</td>
      <td>Provides GitHub integration.</td>
      <td>Useful for GitHub webhook and job integrations, depending on the trigger configuration.</td>
    </tr>
    <tr>
      <td>Credentials</td>
      <td>Stores and manages sensitive information.</td>
      <td>Stores GitHub and Docker Hub credentials.</td>
    </tr>
    <tr>
      <td>Credentials Binding</td>
      <td>Exposes credentials securely to pipeline steps.</td>
      <td>Provides Docker Hub username and password variables inside the pipeline.</td>
    </tr>
    <tr>
      <td>GitHub Branch Source</td>
      <td>Provides GitHub organization and multibranch pipeline integrations.</td>
      <td>Required only if using the corresponding GitHub multibranch functionality.</td>
    </tr>
    <tr>
      <td>Email Extension</td>
      <td>Provides advanced email notifications.</td>
      <td>Supports the <code>emailext</code> notification step.</td>
    </tr>
  </tbody>
</table>

<p><strong>Note:</strong> Plugin requirements depend on the Jenkins job type and implementation. For example, a regular Pipeline job using a Git checkout does not necessarily require GitHub Branch Source. The pipeline uses the Docker CLI directly, so a Docker-specific Jenkins plugin is not automatically required.</p>

<h3>18.3 How to Install Plugins</h3>

<ol>
  <li>Open the Jenkins dashboard.</li>
  <li>Navigate to <strong>Manage Jenkins</strong>.</li>
  <li>Open <strong>Plugins</strong> or <strong>Manage Plugins</strong>, depending on your Jenkins version.</li>
  <li>Open the Available plugins section.</li>
  <li>Search for the required plugin.</li>
  <li>Select the plugin.</li>
  <li>Click Install.</li>
  <li>Wait until the installation finishes.</li>
</ol>

<p>Some plugins may require Jenkins to restart before their functionality becomes available.</p>

<h3>18.4 Understanding the Email Extension Plugin</h3>

<p>The project uses the following pipeline step:</p>

<pre><code>emailext(
    subject: "Profile App Build - ${currentBuild.currentResult}",
    body: "...",
    to: "vislavathpavani5123@gmail.com"
)</code></pre>

<p>The <code>emailext</code> step is provided by the Email Extension plugin.</p>

<p>It allows Jenkins to send customized emails containing information such as:</p>

<ul>
  <li>Job name.</li>
  <li>Build number.</li>
  <li>Build status.</li>
  <li>Build URL.</li>
</ul>

<p>Installing the plugin alone is not enough. Jenkins must also be configured with a working SMTP server to send emails.</p>

<h3>18.5 Verify Installed Plugins</h3>

<p>Navigate to:</p>

<pre><code>Manage Jenkins → Plugins → Installed plugins</code></pre>

<p>Check whether the required plugins are installed and enabled.</p>

<p><strong>Key takeaway:</strong> Plugins provide the integrations and capabilities required by Jenkins. Always verify plugin availability when a pipeline reports that a particular step or integration is missing.</p>

<hr/>

<h2>19. Configuring Jenkins Credentials</h2>

<p>Credentials are sensitive authentication details that Jenkins uses to communicate securely with external systems.</p>

<p>This project requires credentials for GitHub repository access and Docker Hub authentication.</p>

<h3>19.1 Why Do We Need Jenkins Credentials?</h3>

<p>Consider the following operations:</p>

<ul>
  <li>Jenkins needs access to the GitHub repository to download application source code.</li>
  <li>Jenkins needs to authenticate with Docker Hub before pushing container images.</li>
</ul>

<p>Instead of writing usernames, passwords, or access tokens directly inside the pipeline script, we store them in Jenkins Credentials.</p>

<p>This is a safer and more maintainable approach.</p>

<h3>19.2 Open Jenkins Credentials</h3>

<ol>
  <li>Open the Jenkins dashboard.</li>
  <li>Navigate to <strong>Manage Jenkins</strong>.</li>
  <li>Open <strong>Credentials</strong>.</li>
  <li>Select the appropriate credentials store and domain.</li>
  <li>Click <strong>Add Credentials</strong>.</li>
</ol>

<p>For a simple project, the Global credentials domain is commonly used.</p>

<h3>19.3 GitHub Credentials</h3>

<p>The shared pipeline uses the credential ID:</p>

<pre><code>github-cred</code></pre>

<p>This credential is referenced in the application checkout configuration:</p>

<pre><code>checkout([
    $class: 'GitSCM',
    branches: [[name: '*/main']],
    userRemoteConfigs: [[
        url: 'https://github.com/12pavani/Profile_App.git',
        credentialsId: 'github-cred'
    ]]
])</code></pre>

<p><strong>What does credentialsId mean?</strong></p>

<p>The <code>credentialsId</code> tells Jenkins which stored credential to use when accessing the repository.</p>

<p>It does not contain the actual password or token. It is only a reference to a credential stored in Jenkins.</p>

<h4>Creating the GitHub credential</h4>

<ol>
  <li>Click Add Credentials.</li>
  <li>Select the appropriate credential type, commonly Username with password for HTTPS Git authentication.</li>
  <li>Enter your GitHub username.</li>
  <li>Enter a suitable GitHub Personal Access Token if authentication is required.</li>
  <li>Set the ID to <code>github-cred</code>.</li>
  <li>Provide a description, such as <code>GitHub repository access</code>.</li>
  <li>Save the credential.</li>
</ol>

<p>For a public repository, anonymous read access may be sufficient. In that case, the checkout can often be configured without a credential. For a private repository, Jenkins needs an appropriate credential with repository read access.</p>

<h3>19.4 Docker Hub Credentials</h3>

<p>The pipeline also requires Docker Hub authentication to push the generated images.</p>

<p>The credential ID used in the shared pipeline is:</p>

<pre><code>dockerhub-cred</code></pre>

<p>This credential is referenced using Jenkins' <code>withCredentials</code> functionality.</p>

<p>The pipeline code follows this pattern:</p>

<pre><code>withCredentials([
    usernamePassword(
        credentialsId: 'dockerhub-cred',
        usernameVariable: 'USERNAME',
        passwordVariable: 'PASSWORD'
    )
]) {
    sh '''
        echo "$PASSWORD" |
        docker login -u "$USERNAME" --password-stdin
    '''
}</code></pre>

<h3>19.5 Understanding the withCredentials Block</h3>

<p>The <code>withCredentials</code> step temporarily makes the stored credential available to the enclosed pipeline steps.</p>

<p>In this example, two environment variables are created:</p>

<table>
  <thead>
    <tr>
      <th>Variable</th>
      <th>Contains</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>USERNAME</td>
      <td>Docker Hub username.</td>
    </tr>
    <tr>
      <td>PASSWORD</td>
      <td>Docker Hub Personal Access Token.</td>
    </tr>
  </tbody>
</table>

<p>These variables are available inside the <code>withCredentials</code> block.</p>

<h3>19.6 Why Use --password-stdin?</h3>

<p>Docker supports authentication through the <code>docker login</code> command.</p>

<p>A common but less secure approach is to provide the password directly as a command-line argument.</p>

<p>Instead, this project uses:</p>

<pre><code>echo "$PASSWORD" | docker login -u "$USERNAME" --password-stdin</code></pre>

<p>Let's understand the command:</p>

<ul>
  <li><code>echo "$PASSWORD"</code> writes the token to standard output.</li>
  <li><code>|</code> is a pipe operator that passes the output of one command to another command.</li>
  <li><code>docker login</code> starts Docker authentication.</li>
  <li><code>-u "$USERNAME"</code> specifies the Docker Hub username.</li>
  <li><code>--password-stdin</code> tells Docker to read the password or token from standard input.</li>
</ul>

<p>This avoids putting the token directly in the command-line arguments.</p>

<h3>19.7 Create the Docker Hub Credential</h3>

<ol>
  <li>Log in to your Docker Hub account.</li>
  <li>Open your account's security or access-token settings.</li>
  <li>Create a new Personal Access Token.</li>
  <li>Give it an appropriate name, such as <code>jenkins-profile-app</code>.</li>
  <li>Grant the permissions required to push images, including Read and Write access.</li>
  <li>Copy the token securely when it is displayed.</li>
  <li>Return to Jenkins and open Add Credentials.</li>
  <li>Select <strong>Username with password</strong>.</li>
  <li>Enter your Docker Hub username.</li>
  <li>Paste the Personal Access Token into the password field.</li>
  <li>Set the credential ID to <code>dockerhub-cred</code>.</li>
  <li>Save the credential.</li>
</ol>

<p><strong>Important:</strong> A Docker Hub Personal Access Token may expire. If it expires, Jenkins authentication will fail even if the username is correct. Generate a replacement token and update the existing Jenkins credential.</p>

<h3>19.8 Difference Between GitHub and Docker Hub Credentials</h3>

<table>
  <thead>
    <tr>
      <th>Credential ID</th>
      <th>Service</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>github-cred</code></td>
      <td>GitHub</td>
      <td>Allows Jenkins to access the application repository when authentication is required.</td>
    </tr>
    <tr>
      <td><code>dockerhub-cred</code></td>
      <td>Docker Hub</td>
      <td>Allows Jenkins to authenticate and push Docker images.</td>
    </tr>
  </tbody>
</table>

<h3>19.9 Security Best Practices</h3>

<ul>
  <li>Never hardcode passwords or access tokens in Jenkinsfiles or Groovy source files.</li>
  <li>Never upload Jenkins credentials to GitHub.</li>
  <li>Use tokens with only the permissions required by the pipeline.</li>
  <li>Rotate tokens periodically and replace expired tokens.</li>
  <li>Restrict access to Jenkins credentials to authorized users.</li>
  <li>Avoid printing secret variables using <code>echo</code> or other debugging commands.</li>
  <li>Use Jenkins credential IDs in pipeline scripts rather than storing secret values in source code.</li>
</ul>

<p><strong>Key takeaway:</strong> Jenkins Credentials provide a centralized way to manage sensitive information. The pipeline references credentials by ID and retrieves their values only when required.</p>


<h2>20. Configuring GitHub Webhooks Using ngrok</h2>

<p>In this project, Jenkins runs locally on the macOS machine. However, GitHub is an external service hosted on the internet.</p>

<p>When a developer pushes new code to GitHub, GitHub needs a way to notify the local Jenkins server that a change has occurred.</p>

<p>This is where <strong>GitHub Webhooks</strong> and <strong>ngrok</strong> are useful.</p>

<h3>20.1 What Is a Webhook?</h3>

<p>A webhook is a mechanism through which one application sends an HTTP request to another application when a particular event occurs.</p>

<p>For example, when a developer pushes code to a GitHub repository, GitHub can send an HTTP POST request to a configured webhook URL.</p>

<p>The receiving application can then process the event and perform an action.</p>

<p>In this project:</p>

<ul>
  <li>GitHub is the event sender.</li>
  <li>Jenkins is the event receiver.</li>
  <li>A code push is the event that can trigger the notification.</li>
  <li>Jenkins processes the webhook and starts the configured build, if the job is set up to respond to that event.</li>
</ul>

<h3>20.2 Why Do We Need ngrok?</h3>

<p>Jenkins is running locally at:</p>

<pre><code>http://localhost:8080</code></pre>

<p>The word <code>localhost</code> refers to the current machine. GitHub cannot directly access this local address from its servers.</p>

<p>To solve this problem, we use ngrok.</p>

<p>ngrok creates a secure tunnel between a public URL and a service running on the local machine.</p>

<p>For this project, ngrok forwards incoming public HTTP requests to the local Jenkins server.</p>

<h3>20.3 Understanding the Webhook Architecture</h3>

<pre><code>+----------------------+
|       Developer      |
|                      |
|   Pushes code to     |
|       GitHub         |
+----------+-----------+
           |
           | GitHub webhook
           | HTTP POST
           v
+----------------------+
|        ngrok         |
|                      |
|    Public HTTPS URL  |
+----------+-----------+
           |
           | Tunnel forwards
           | request to port 8080
           v
+----------------------+
|       Jenkins        |
|                      |
|   Local macOS        |
|   Port 8080          |
+----------+-----------+
           |
           v
+----------------------+
|   Jenkins Pipeline   |
|                      |
|   Executes CI/CD     |
+----------------------+</code></pre>

<h3>20.4 Install ngrok</h3>

<p>ngrok can be installed on macOS using Homebrew.</p>

<pre><code>brew install ngrok</code></pre>

<p>Verify the installation:</p>

<pre><code>ngrok version</code></pre>

<h3>20.5 Configure Your ngrok Account</h3>

<ol>
  <li>Create an account on the official ngrok website.</li>
  <li>Open your ngrok dashboard.</li>
  <li>Locate your authentication token.</li>
  <li>Copy the token securely.</li>
  <li>Configure ngrok using the command shown in your account dashboard.</li>
</ol>

<p>The command generally follows this format:</p>

<pre><code>ngrok config add-authtoken YOUR_AUTH_TOKEN</code></pre>

<p>Replace <code>YOUR_AUTH_TOKEN</code> with your actual token. Never commit this token to GitHub or share it publicly.</p>

<h3>20.6 Start Jenkins Before Starting ngrok</h3>

<p>Make sure Jenkins is running locally before creating the tunnel.</p>

<p>Open your browser and verify:</p>

<pre><code>http://localhost:8080</code></pre>

<p>Once Jenkins is accessible, open another Terminal window and execute:</p>

<pre><code>ngrok http 8080</code></pre>

<p><strong>What does this command mean?</strong></p>

<ul>
  <li><code>ngrok</code> starts the ngrok tunneling application.</li>
  <li><code>http</code> tells ngrok to create an HTTP tunnel.</li>
  <li><code>8080</code> identifies the local port where Jenkins is listening.</li>
</ul>

<p>ngrok displays a public forwarding URL. It may look similar to:</p>

<pre><code>Forwarding
https://example-random.ngrok-free.app
    -> http://localhost:8080</code></pre>

<p>The actual URL will be different for your session.</p>

<p>Keep the ngrok Terminal window open while you need the tunnel. Stopping ngrok or closing the process disconnects the public endpoint.</p>

<h3>20.7 Configure the GitHub Webhook</h3>

<p>Now that Jenkins has a public URL, configure GitHub to send webhook events to it.</p>

<ol>
  <li>Open the <strong>Profile_App</strong> repository on GitHub.</li>
  <li>Navigate to <strong>Settings</strong>.</li>
  <li>Select <strong>Webhooks</strong>.</li>
  <li>Click <strong>Add webhook</strong>.</li>
  <li>Enter the ngrok public URL followed by the Jenkins webhook endpoint.</li>
  <li>Select <strong>application/json</strong> as the content type.</li>
  <li>Select the events that should trigger notifications. For a basic setup, select push events.</li>
  <li>Save the webhook.</li>
</ol>

<p>The URL commonly used with the Jenkins GitHub plugin is:</p>

<pre><code>https://YOUR-NGROK-URL/github-webhook/</code></pre>

<p>For example:</p>

<pre><code>https://example-random.ngrok-free.app/github-webhook/</code></pre>

<p>Use your current ngrok forwarding URL, not the example URL above.</p>

<h3>20.8 Configure Jenkins to Receive GitHub Push Events</h3>

<p>Creating the webhook in GitHub is only one side of the configuration. The Jenkins job must also be configured to respond to the webhook.</p>

<p>For a Pipeline job using the GitHub plugin:</p>

<ol>
  <li>Open the Jenkins dashboard.</li>
  <li>Open the relevant Pipeline job.</li>
  <li>Click <strong>Configure</strong>.</li>
  <li>Locate the Build Triggers section.</li>
  <li>Select <strong>GitHub hook trigger for GITScm polling</strong>, if this is the trigger mechanism being used.</li>
  <li>Save the configuration.</li>
</ol>

<p>The exact trigger configuration depends on the Jenkins job type and the plugins installed.</p>

<h3>20.9 Test the GitHub Webhook</h3>

<p>After completing the configuration, test whether GitHub can communicate with Jenkins.</p>

<ol>
  <li>Open the Profile_App GitHub repository.</li>
  <li>Navigate to Settings → Webhooks.</li>
  <li>Open the configured webhook.</li>
  <li>Review the Recent Deliveries section.</li>
  <li>Check whether GitHub received a successful response from the endpoint.</li>
  <li>Push a small test change to the configured branch.</li>
  <li>Check whether Jenkins receives the event and starts the expected job.</li>
</ol>

<p><strong>Important distinction:</strong> A successful webhook delivery only confirms that the endpoint responded successfully. It does not, by itself, prove that the Jenkins job was configured correctly or that the build completed successfully.</p>

<h3>20.10 Common Webhook Problems</h3>

<table>
  <thead>
    <tr>
      <th>Problem</th>
      <th>Possible Reason</th>
      <th>What to Check</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Connection refused</td>
      <td>Jenkins is not running.</td>
      <td>Open localhost:8080 and check Jenkins.</td>
    </tr>
    <tr>
      <td>Webhook delivery fails</td>
      <td>ngrok is stopped or the URL is incorrect.</td>
      <td>Check the active ngrok forwarding URL.</td>
    </tr>
    <tr>
      <td>Webhook succeeds but no build starts</td>
      <td>The Jenkins job trigger is not configured or the event does not match.</td>
      <td>Check the job's Build Triggers configuration.</td>
    </tr>
    <tr>
      <td>Old webhook URL</td>
      <td>The ngrok URL changed after restarting the tunnel.</td>
      <td>Update the URL in GitHub.</td>
    </tr>
  </tbody>
</table>

<p><strong>Security note:</strong> Exposing Jenkins through a public tunnel should be done carefully. Use Jenkins authentication, keep Jenkins updated, protect webhook endpoints appropriately, and avoid exposing unnecessary services.</p>

<hr/>

<h2>21. Creating and Configuring the Jenkins Pipeline Job</h2>

<p>After configuring the Shared Library, plugins, credentials, and webhook, we need a Jenkins job that executes the application pipeline.</p>

<h3>21.1 What Is a Jenkins Job?</h3>

<p>A Jenkins job is a configured unit of work that Jenkins can execute.</p>

<p>For example, a job can:</p>

<ul>
  <li>Download source code.</li>
  <li>Compile or build an application.</li>
  <li>Run tests.</li>
  <li>Build Docker images.</li>
  <li>Deploy containers.</li>
  <li>Send notifications.</li>
</ul>

<p>In this project, the Jenkins job is responsible for triggering the reusable Shared Library pipeline.</p>

<h3>21.2 Create a New Pipeline Job</h3>

<ol>
  <li>Open the Jenkins dashboard.</li>
  <li>Click <strong>New Item</strong>.</li>
  <li>Enter a suitable job name, such as <code>Profile-App-CI-CD</code>.</li>
  <li>Select <strong>Pipeline</strong> as the job type.</li>
  <li>Click <strong>OK</strong>.</li>
</ol>

<p>Jenkins creates a new Pipeline job and opens its configuration page.</p>

<h3>21.3 Understanding the Pipeline Job Configuration</h3>

<p>A Pipeline job can obtain its pipeline definition in different ways.</p>

<p>For example:</p>

<ul>
  <li>Pipeline script: The script is entered directly into the Jenkins configuration.</li>
  <li>Pipeline script from SCM: Jenkins retrieves the Jenkinsfile from a source code repository.</li>
</ul>

<p>Because this project uses a Shared Library, the pipeline entry point must load and invoke that library.</p>

<h3>21.4 Pipeline Script from SCM</h3>

<p>If the Jenkinsfile is stored in a Git repository, select:</p>

<pre><code>Definition: Pipeline script from SCM</code></pre>

<p>Then configure the source control settings:</p>

<table>
  <thead>
    <tr>
      <th>Field</th>
      <th>Configuration</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>SCM</td>
      <td>Git</td>
    </tr>
    <tr>
      <td>Repository URL</td>
      <td>The repository containing the Jenkinsfile.</td>
    </tr>
    <tr>
      <td>Credentials</td>
      <td>Appropriate GitHub credential, if required.</td>
    </tr>
    <tr>
      <td>Branch Specifier</td>
      <td>The branch containing the Jenkinsfile.</td>
    </tr>
    <tr>
      <td>Script Path</td>
      <td>The actual path to the Jenkinsfile.</td>
    </tr>
  </tbody>
</table>

<p><strong>Important:</strong> The application checkout inside <code>BuildDeployPipeline()</code> is separate from the retrieval of the Jenkinsfile. Do not assume that the application repository is automatically the correct location for the Jenkinsfile.</p>

<h3>21.5 Configure the Build Trigger</h3>

<p>If you want GitHub pushes to trigger the pipeline automatically, configure the appropriate webhook trigger.</p>

<p>For a job using the GitHub plugin, this may be:</p>

<pre><code>GitHub hook trigger for GITScm polling</code></pre>

<p>Save the configuration after enabling the trigger.</p>

<h3>21.6 Configure the Pipeline Entry Point</h3>

<p>The Jenkins job must execute a Jenkinsfile or pipeline script that loads the configured Shared Library and invokes the pipeline entry point.</p>

<p>The project-specific implementation should be verified before adding the final Jenkinsfile code.</p>

<p>The following is only a conceptual illustration of the flow:</p>

<pre><code>Load configured Shared Library
          |
          v
Resolve pipeline entry point
          |
          v
Invoke BuildDeployPipeline()
          |
          v
Run build and deployment workflow</code></pre>

<p>The actual invocation syntax depends on whether the method is exposed through a class under <code>src/</code> or a global step under <code>vars/</code>.</p>

<h3>21.7 Save the Job</h3>

<ol>
  <li>Review the job configuration.</li>
  <li>Verify the Jenkinsfile source and path, if using SCM.</li>
  <li>Verify the Shared Library name and availability.</li>
  <li>Check the webhook trigger configuration, if required.</li>
  <li>Click <strong>Save</strong>.</li>
</ol>

<h3>21.8 Run the First Build Manually</h3>

<p>Before relying on GitHub webhooks, it is useful to test the pipeline manually.</p>

<ol>
  <li>Open the newly created Jenkins job.</li>
  <li>Click <strong>Build Now</strong> or <strong>Build with Parameters</strong>, depending on the job configuration.</li>
  <li>Open the build from the Build History section.</li>
  <li>Click <strong>Console Output</strong>.</li>
  <li>Observe the execution logs.</li>
</ol>

<p>The console output helps identify errors in Shared Library loading, Git checkout, Docker commands, credentials, or deployment.</p>

<h3>21.9 Understanding Build History</h3>

<p>Jenkins maintains a history of previous builds.</p>

<p>Each build receives a unique build number within that job.</p>

<p>For example:</p>

<pre><code>Build #1
Build #2
Build #3
Build #4</code></pre>

<p>In this project, the build number is used when tagging Docker images. This helps distinguish images created by different Jenkins executions.</p>

<p><strong>Key takeaway:</strong> The Jenkins job is the execution entry point. It loads the Shared Library, invokes the reusable pipeline, and provides the interface to monitor the CI/CD process.</p>


<h2>22. Understanding the Shared Library Pipeline Code</h2>

<p>The Jenkins Shared Library contains the main CI/CD implementation for this project.</p>

<p>The reusable method supplied for this project is:</p>

<pre><code>BuildDeployPipeline()</code></pre>

<p>This method coordinates the complete application lifecycle, from preparing the Jenkins workspace to building Docker images, pushing them to Docker Hub, deploying containers, performing health checks, and sending email notifications.</p>

<h3>22.1 Understanding the Overall Pipeline Structure</h3>

<p>Before studying individual stages, understand the overall structure of the method.</p>

<p>The pipeline follows this general execution flow:</p>

<pre><code>BuildDeployPipeline()
        |
        v
Initialize Pipeline
        |
        v
Set Timeout and Timestamps
        |
        v
Allocate Jenkins Node
        |
        v
Prepare Build Information
        |
        v
Clean Workspace
        |
        v
Check Docker Availability
        |
        v
Checkout Application Code
        |
        v
Collect Git and Build Information
        |
        v
Build Docker Images
        |
        v
Verify Docker Images
        |
        v
Tag Docker Images
        |
        v
Authenticate with Docker Hub
        |
        v
Push Docker Images
        |
        v
Deploy with Docker Compose
        |
        v
Perform Health Checks
        |
        v
Archive Container Logs
        |
        v
Clean Unused Docker Images
        |
        v
Send Email Notification</code></pre>

<h3>22.2 Understanding the Pipeline Method</h3>

<p>The pipeline method is defined using Groovy syntax.</p>

<p>The method name is:</p>

<pre><code>BuildDeployPipeline()</code></pre>

<p><strong>What is a method?</strong></p>

<p>A method is a reusable block of code that performs a particular task.</p>

<p>Instead of writing the same sequence of commands repeatedly, we place the logic inside a method and invoke it whenever required.</p>

<p>In this project, the method groups all the CI/CD operations into one reusable implementation.</p>

<h3>22.3 Understanding the timeout() Step</h3>

<p>The pipeline starts with a timeout configuration:</p>

<pre><code>timeout(time: 20, unit: 'MINUTES')</code></pre>

<p><strong>What does timeout mean?</strong></p>

<p>A timeout defines the maximum time allowed for a particular block of pipeline execution.</p>

<p>Here:</p>

<ul>
  <li><code>time: 20</code> defines the duration.</li>
  <li><code>unit: 'MINUTES'</code> specifies that the duration is measured in minutes.</li>
</ul>

<p>Therefore, the enclosed pipeline execution is allowed a maximum of 20 minutes.</p>

<p>If the timeout is exceeded, Jenkins aborts the timed-out execution and reports a timeout failure.</p>

<p><strong>Why is it useful?</strong></p>

<ul>
  <li>Prevents builds from running indefinitely.</li>
  <li>Helps identify commands that are stuck.</li>
  <li>Prevents unnecessary consumption of Jenkins resources.</li>
  <li>Provides a predictable maximum execution time.</li>
</ul>

<p><strong>Example:</strong> If a Docker image push gets stuck because of a network issue, the timeout helps prevent the pipeline from waiting forever.</p>

<h3>22.4 Understanding timestamps()</h3>

<p>The pipeline also uses:</p>

<pre><code>timestamps</code></pre>

<p>This enables timestamps in Jenkins console logs.</p>

<p>Without timestamps, console output might look like:</p>

<pre><code>Checking Docker...
Building frontend...
Building backend...</code></pre>

<p>With timestamps enabled, the output includes the time associated with each log line:</p>

<pre><code>18:20:01 Checking Docker...
18:20:04 Building frontend...
18:21:15 Building backend...</code></pre>

<p>The actual timestamp format depends on the Jenkins configuration.</p>

<p><strong>Why are timestamps useful?</strong></p>

<ul>
  <li>Help identify how long each operation takes.</li>
  <li>Make troubleshooting easier.</li>
  <li>Help correlate Jenkins logs with Docker and application logs.</li>
  <li>Make it easier to identify when a pipeline became stuck.</li>
</ul>

<h3>22.5 Understanding node()</h3>

<p>The pipeline uses:</p>

<pre><code>node {
    // Pipeline execution
}</code></pre>

<p>The <code>node</code> step allocates a Jenkins executor on an available agent and provides a workspace for the enclosed steps.</p>

<p>In a simple local Jenkins setup, the controller may also execute builds if it is configured to allow that.</p>

<p>In this project, the commands execute on the machine where the selected Jenkins executor runs.</p>

<p>This is important because that machine must have access to:</p>

<ul>
  <li>The Docker CLI.</li>
  <li>The Docker daemon.</li>
  <li>The Git executable.</li>
  <li>The required application files.</li>
  <li>The network connections needed for GitHub and Docker Hub.</li>
</ul>

<p><strong>Important:</strong> A Jenkins agent is the machine or execution environment where pipeline steps run. It is not the same thing as a Docker container, although an agent can itself run inside a container.</p>

<h3>22.6 Understanding the Build Variables</h3>

<p>Inside the pipeline, the following variables are defined:</p>

<pre><code>def frontendImage = 'profile-frontend'
def backendImage = 'profile-backend'
def dockerHubUsername = 'vislavathpavani5123'</code></pre>

<p>These variables store values that are reused throughout the pipeline.</p>

<table>
  <thead>
    <tr>
      <th>Variable</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>frontendImage</code></td>
      <td>Stores the local frontend Docker image name.</td>
    </tr>
    <tr>
      <td><code>backendImage</code></td>
      <td>Stores the local backend Docker image name.</td>
    </tr>
    <tr>
      <td><code>dockerHubUsername</code></td>
      <td>Stores the Docker Hub namespace used when tagging images for publishing.</td>
    </tr>
  </tbody>
</table>

<p>For example, instead of repeatedly writing <code>profile-frontend</code>, the pipeline can refer to <code>frontendImage</code>.</p>

<p>This makes the code easier to maintain.</p>

<h3>22.7 Understanding Application URLs</h3>

<p>The pipeline defines the frontend and backend endpoints:</p>

<pre><code>def frontendUrl = 'http://localhost:3000'
def backendUrl = 'http://localhost:8000/profile'</code></pre>

<p>These URLs are used later during the health-check stage.</p>

<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>http://localhost:3000</code></td>
      <td>Checks whether the frontend responds.</td>
    </tr>
    <tr>
      <td><code>http://localhost:8000/profile</code></td>
      <td>Checks whether the backend profile endpoint responds.</td>
    </tr>
  </tbody>
</table>

<p>Here, <code>localhost</code> refers to the machine running the pipeline commands.</p>

<p>These endpoints work only when the application containers expose the corresponding ports to that machine.</p>

<h3>22.8 Understanding Container Names</h3>

<p>The pipeline also defines the frontend and backend container names:</p>

<pre><code>def frontendContainer =
    'profileapp_dev_feature_builddeploy-profile-frontend-1'

def backendContainer =
    'profileapp_dev_feature_builddeploy-profile-backend-1'</code></pre>

<p>These names are used later when collecting container logs.</p>

<p>For example, the pipeline can use:</p>

<pre><code>docker logs &lt;container-name&gt;</code></pre>

<p>Docker identifies the container using its name and returns its logs.</p>

<p><strong>Important:</strong> These container names depend on the Docker Compose project and service naming configuration. If the project name or service names change, the hardcoded names may no longer match the actual containers.</p>

<h3>22.9 Understanding currentBuild.displayName</h3>

<p>The pipeline assigns a display name to the current Jenkins build:</p>

<pre><code>currentBuild.displayName = "Build-${BUILD_NUMBER}"</code></pre>

<p><strong>What is currentBuild?</strong></p>

<p><code>currentBuild</code> is a Jenkins pipeline object that provides information about the currently executing build.</p>

<p><strong>What is displayName?</strong></p>

<p>It is a customizable name displayed for the build in Jenkins.</p>

<p>For example, if the build number is 5, the display name becomes:</p>

<pre><code>Build-5</code></pre>

<p>The actual Jenkins build number remains unchanged. This only changes how the build is displayed.</p>

<h3>22.10 Understanding deleteDir()</h3>

<p>Before checking out the application source code, the pipeline executes:</p>

<pre><code>deleteDir()</code></pre>

<p>This step deletes the contents of the current Jenkins workspace.</p>

<p><strong>Why do we need it?</strong></p>

<p>Suppose Build 1 checked out the application and generated temporary files. Build 2 may reuse the same workspace.</p>

<p>Without cleaning, files left behind by the earlier build could affect the next execution.</p>

<p>By using <code>deleteDir()</code>, the pipeline starts with a clean workspace before checking out the application again.</p>

<p><strong>Remember:</strong> <code>deleteDir()</code> cleans the Jenkins workspace. It does not remove Docker containers, images, or volumes.</p>

<h3>22.11 Understanding the Docker Availability Check</h3>

<p>Before building application images, the pipeline verifies Docker availability using:</p>

<pre><code>docker info</code></pre>

<p>This command communicates with the Docker daemon and displays information about the Docker environment.</p>

<p>If Docker is running and accessible, Jenkins receives Docker information.</p>

<p>If Docker is not accessible, the command returns a non-zero exit status and the pipeline command fails.</p>

<p><strong>Why is this check important?</strong></p>

<ul>
  <li>Confirms that the Docker CLI can communicate with the Docker daemon.</li>
  <li>Identifies Docker connectivity problems before the image-building stages.</li>
  <li>Helps distinguish Docker environment issues from application build issues.</li>
</ul>

<h3>22.12 Execution Flow Covered So Far</h3>

<pre><code>BuildDeployPipeline()
        |
        v
Set 20-Minute Timeout
        |
        v
Enable Timestamps
        |
        v
Allocate Jenkins Node
        |
        v
Define Image Names
        |
        v
Define Docker Hub Username
        |
        v
Define Application URLs
        |
        v
Define Container Names
        |
        v
Set Build Display Name
        |
        v
Clean Jenkins Workspace
        |
        v
Check Docker Availability
        |
        v
Continue to Git Checkout</code></pre>

<p><strong>Key takeaway:</strong> These initial operations prepare the Jenkins execution environment before any application code is downloaded or Docker image is built.</p>


<h2>23. Git Checkout and Build Information</h2>

<p>After preparing the Jenkins workspace and checking Docker availability, the next operation is retrieving the application source code from GitHub.</p>

<p>The pipeline uses Git to download the Profile_App repository into the Jenkins workspace.</p>

<h3>23.1 Understanding Git Checkout</h3>

<p>Git checkout is the operation used by Jenkins to retrieve the required source code from a Git repository.</p>

<p>In this project, Jenkins retrieves the application from:</p>

<pre><code>https://github.com/12pavani/Profile_App.git</code></pre>

<p>The pipeline uses the Jenkins <code>checkout</code> step with Git SCM configuration.</p>

<h3>23.2 Understanding the Checkout Configuration</h3>

<p>The application checkout follows this structure:</p>

<pre><code>checkout([
    $class: 'GitSCM',
    branches: [[name: '*/main']],
    userRemoteConfigs: [[
        url: 'https://github.com/12pavani/Profile_App.git',
        credentialsId: 'github-cred'
    ]]
])</code></pre>

<p>Let's understand each component.</p>

<h4>checkout</h4>

<p><code>checkout</code> is a Jenkins Pipeline step used to retrieve source code from a configured source control repository.</p>

<h4>$class: 'GitSCM'</h4>

<p>This specifies that Jenkins should use its Git SCM implementation.</p>

<p>SCM stands for Source Code Management.</p>

<p>It identifies Git as the source control system used for this checkout.</p>

<h4>branches: [[name: '*/main']]</h4>

<p>This specifies the branch that Jenkins should check out.</p>

<p>Here, <code>main</code> is the branch name.</p>

<p>The <code>*</code> is used as a wildcard in the branch specification.</p>

<p>In simple terms, Jenkins is instructed to retrieve the main branch from the configured Git repository.</p>

<h4>userRemoteConfigs</h4>

<p>This section defines the remote Git repository from which Jenkins retrieves source code.</p>

<p>The repository URL is:</p>

<pre><code>https://github.com/12pavani/Profile_App.git</code></pre>

<h4>credentialsId</h4>

<p>This identifies the Jenkins credential used for repository authentication, when required.</p>

<pre><code>credentialsId: 'github-cred'</code></pre>

<p>Jenkins retrieves the credential stored under this ID instead of requiring the username and token to be written directly in the pipeline code.</p>

<h3>23.3 What Happens During Checkout?</h3>

<p>When Jenkins executes the checkout step, the following operations take place:</p>

<ol>
  <li>Jenkins identifies the configured Git repository URL.</li>
  <li>Jenkins establishes a connection with GitHub.</li>
  <li>Jenkins authenticates if the repository requires credentials.</li>
  <li>Jenkins retrieves the requested branch information.</li>
  <li>Git downloads the required source code into the Jenkins workspace.</li>
  <li>The application files become available for subsequent pipeline stages.</li>
</ol>

<p>After a successful checkout, the workspace contains the application repository structure.</p>

<pre><code>Jenkins Workspace/
|
+-- backend/
|
+-- frontend/
|
+-- docker-compose.yaml
|
+-- package.json
|
+-- package-lock.json</code></pre>

<p>The exact files depend on the current contents of the GitHub repository.</p>

<h3>23.4 Why Do We Need Git Checkout?</h3>

<p>Jenkins cannot build the application if it does not have access to the application's source code and Docker build context.</p>

<p>For example, the pipeline later executes:</p>

<pre><code>docker build -t profile-frontend:${BUILD_NUMBER} frontend</code></pre>

<p>For this command to work, the <code>frontend</code> directory must exist in the current working directory.</p>

<p>Similarly:</p>

<pre><code>docker build -t profile-backend:${BUILD_NUMBER} backend</code></pre>

<p>requires the <code>backend</code> directory to be available.</p>

<p>Git checkout makes these application files available inside the Jenkins workspace.</p>

<h3>23.5 Understanding Git Branch Information</h3>

<p>After checkout, the pipeline collects Git information using:</p>

<pre><code>git branch --show-current</code></pre>

<p>This command displays the name of the currently checked-out local branch.</p>

<p>For example:</p>

<pre><code>main</code></pre>

<p><strong>Why is it useful?</strong></p>

<ul>
  <li>Identifies which branch was checked out.</li>
  <li>Helps troubleshoot branch-related issues.</li>
  <li>Provides visibility into the source version used for a build.</li>
</ul>

<p><strong>Important:</strong> Depending on how Jenkins performs checkout, the workspace may be in a detached HEAD state. In that situation, <code>git branch --show-current</code> can return an empty value. The command alone is therefore not a reliable way to identify the checked-out commit's source branch in every Jenkins configuration.</p>

<h3>23.6 Understanding Git Commit Information</h3>

<p>The pipeline also executes:</p>

<pre><code>git rev-parse HEAD</code></pre>

<p>This command displays the full commit hash of the currently checked-out Git revision.</p>

<p>Example output:</p>

<pre><code>7d9a3b2c8f1e4a5d6c7b8a9f0e1d2c3b4a5f6e7d</code></pre>

<p>The displayed value is an illustrative example, not the actual commit hash of your repository.</p>

<p><strong>What is a commit hash?</strong></p>

<p>Whenever a developer commits changes to Git, Git creates a unique identifier for that commit.</p>

<p>This identifier is called a commit hash.</p>

<p>It helps identify the exact source code version used during a Jenkins build.</p>

<p><strong>Why is this important?</strong></p>

<ul>
  <li>Helps trace which code version was deployed.</li>
  <li>Supports debugging and troubleshooting.</li>
  <li>Helps compare changes between builds.</li>
  <li>Provides traceability for release management.</li>
</ul>

<h3>23.7 Understanding Git Commit History</h3>

<p>The pipeline executes:</p>

<pre><code>git log</code></pre>

<p>This command displays the commit history of the checked-out repository.</p>

<p>It typically includes:</p>

<ul>
  <li>Commit hash.</li>
  <li>Author information.</li>
  <li>Commit date.</li>
  <li>Commit message.</li>
</ul>

<p>Example:</p>

<pre><code>commit 7d9a3b2...
Author: Developer
Date:   Mon Nov 2 10:30:00 2026

    Update profile application</code></pre>

<p>This example illustrates the format of Git output.</p>

<p><strong>Why do we need git log?</strong></p>

<p>It provides information about the recent source code changes and helps identify which commits are associated with the current application version.</p>

<h3>23.8 Understanding Jenkins Build Information</h3>

<p>In addition to Git details, the pipeline collects Jenkins build information.</p>

<p>The following environment variables are used:</p>

<pre><code>JOB_NAME
BUILD_NUMBER
BUILD_URL
NODE_NAME
WORKSPACE</code></pre>

<p>These variables are automatically provided by Jenkins.</p>

<table>
  <thead>
    <tr>
      <th>Variable</th>
      <th>Meaning</th>
      <th>Example</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>JOB_NAME</code></td>
      <td>Name of the Jenkins job.</td>
      <td>Profile-App-CI-CD</td>
    </tr>
    <tr>
      <td><code>BUILD_NUMBER</code></td>
      <td>Unique build number within the job.</td>
      <td>5</td>
    </tr>
    <tr>
      <td><code>BUILD_URL</code></td>
      <td>URL of the current Jenkins build.</td>
      <td>http://localhost:8080/job/Profile-App-CI-CD/5/</td>
    </tr>
    <tr>
      <td><code>NODE_NAME</code></td>
      <td>Name of the Jenkins node executing the build.</td>
      <td>Built-in node</td>
    </tr>
    <tr>
      <td><code>WORKSPACE</code></td>
      <td>Absolute path to the current job workspace.</td>
      <td>/path/to/jenkins/workspace/Profile-App-CI-CD</td>
    </tr>
  </tbody>
</table>

<p>The example values above are illustrative. Your actual values depend on your Jenkins configuration.</p>

<h3>23.9 Understanding Each Jenkins Variable</h3>

<h4>JOB_NAME</h4>

<p>Identifies the Jenkins job currently executing.</p>

<p>This is useful when the same Shared Library is reused by multiple jobs.</p>

<h4>BUILD_NUMBER</h4>

<p>Identifies the current build execution.</p>

<p>For example, if Jenkins executes the job for the fifth time, the build number is:</p>

<pre><code>5</code></pre>

<p>This value is especially important in this project because it is used as the Docker image tag.</p>

<h4>BUILD_URL</h4>

<p>Provides the URL of the current Jenkins build.</p>

<p>It allows users to open the build directly from the Jenkins dashboard.</p>

<h4>NODE_NAME</h4>

<p>Identifies the Jenkins node on which the pipeline is running.</p>

<p>For example, a pipeline might run on the built-in Jenkins node or on a separately configured agent.</p>

<h4>WORKSPACE</h4>

<p>Identifies the directory where the pipeline is executing its file-related operations.</p>

<p>This helps troubleshoot problems involving missing Dockerfiles, incorrect paths, or missing application directories.</p>

<h3>23.10 Why Collect Git and Build Information?</h3>

<p>Collecting this information improves build traceability.</p>

<p>Suppose an application deployment has an issue. The team may need to identify:</p>

<ul>
  <li>Which Jenkins job executed the deployment?</li>
  <li>Which build number was responsible?</li>
  <li>Which Git commit was checked out?</li>
  <li>Which node executed the pipeline?</li>
  <li>Where was the application source code checked out?</li>
</ul>

<p>These details make troubleshooting and debugging easier.</p>

<h3>23.11 Complete Execution Flow</h3>

<pre><code>Prepare Jenkins Environment
           |
           v
Clean Workspace
           |
           v
Check Docker Availability
           |
           v
Checkout Profile_App
           |
           v
Retrieve Git Branch
           |
           v
Retrieve Commit Hash
           |
           v
Display Git History
           |
           v
Collect Jenkins Metadata
           |
           v
Proceed to Docker Image Build</code></pre>

<p><strong>Key takeaway:</strong> Git checkout provides the application source code required for the build, while Git and Jenkins metadata help identify exactly which code and build execution are being processed.</p>


<h2>24. Docker Image Building, Verification and Tagging</h2>

<p>After Jenkins successfully checks out the application source code from GitHub, the next step is to build Docker images for the frontend and backend applications.</p>

<p>In this project, the frontend and backend are built as two separate Docker images.</p>

<h3>24.1 What Is a Docker Image?</h3>

<p>A Docker image is a packaged version of an application that contains everything required to run it.</p>

<p>It can include:</p>

<ul>
  <li>Application source code.</li>
  <li>Runtime environment.</li>
  <li>Required dependencies and libraries.</li>
  <li>Configuration files.</li>
  <li>Startup instructions.</li>
</ul>

<p>Think of a Docker image as a ready-to-run package of your application.</p>

<p>Instead of installing dependencies and configuring the application manually on every machine, Docker allows you to package the application into an image and run it as a container.</p>

<h3>24.2 Why Are We Building Two Separate Images?</h3>

<p>Your Profile App contains two major components:</p>

<table>
  <thead>
    <tr>
      <th>Component</th>
      <th>Responsibility</th>
      <th>Port</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Frontend</td>
      <td>User interface that users interact with.</td>
      <td>3000</td>
    </tr>
    <tr>
      <td>Backend</td>
      <td>Handles application logic and profile API requests.</td>
      <td>8000</td>
    </tr>
  </tbody>
</table>

<p>Both components have different source code, dependencies, and runtime requirements.</p>

<p>Therefore, we package them independently.</p>

<p><strong>Frontend image:</strong> Contains the frontend application and its required dependencies.</p>

<p><strong>Backend image:</strong> Contains the backend application and its required dependencies.</p>

<p>This separation makes it easier to build, deploy, update, and troubleshoot each component independently.</p>

<h3>24.3 Understanding the Docker Build Command</h3>

<p>The pipeline executes the following command to build the frontend image:</p>

<pre><code>docker build -t profile-frontend:${BUILD_NUMBER} frontend</code></pre>

<p>Let's understand each component.</p>

<h4>docker build</h4>

<p>This tells Docker to build an image using the instructions provided in a Dockerfile.</p>

<h4>-t</h4>

<p>The <code>-t</code> option means tag.</p>

<p>It assigns a name and tag to the newly created image.</p>

<h4>profile-frontend</h4>

<p>This is the name of the Docker image.</p>

<p>It identifies the frontend application image.</p>

<h4>${BUILD_NUMBER}</h4>

<p>This is a Jenkins environment variable containing the current build number.</p>

<p>For example, if the current Jenkins build number is 5, the command becomes:</p>

<pre><code>docker build -t profile-frontend:5 frontend</code></pre>

<p>The image is assigned the tag <code>5</code>.</p>

<h4>frontend</h4>

<p>This is the Docker build context directory.</p>

<p>The build context tells Docker which directory contains the files needed to build the image.</p>

<p>Docker uses the Dockerfile and other required files from this directory.</p>

<h3>24.4 What Happens Internally During Docker Build?</h3>

<p>When Jenkins executes the Docker build command, Docker performs several operations:</p>

<ol>
  <li>Reads the Dockerfile from the specified build context.</li>
  <li>Processes the instructions in the Dockerfile.</li>
  <li>Downloads the required base image if it is not available locally.</li>
  <li>Installs application dependencies as instructed by the Dockerfile.</li>
  <li>Copies application files into the image.</li>
  <li>Creates the image layers.</li>
  <li>Produces the final Docker image with the requested name and tag.</li>
</ol>

<p>For example, a Dockerfile might contain instructions such as:</p>

<pre><code>FROM node:20
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]</code></pre>

<p>This is an illustrative example of a Node.js frontend Dockerfile. Your actual Dockerfile instructions determine how your application is packaged.</p>

<h3>24.5 Building the Backend Image</h3>

<p>After building the frontend image, Jenkins builds the backend image.</p>

<pre><code>docker build -t profile-backend:${BUILD_NUMBER} backend</code></pre>

<p>For build number 5, this becomes:</p>

<pre><code>docker build -t profile-backend:5 backend</code></pre>

<p>The build process is similar to the frontend build, but Docker uses the backend directory as its build context.</p>

<p>This ensures that backend dependencies and application files are packaged separately from the frontend.</p>

<h3>24.6 Why Use BUILD_NUMBER as the Image Tag?</h3>

<p>Docker image tags help identify different versions of an application.</p>

<p>Instead of repeatedly overwriting an image with the same tag, this pipeline uses the Jenkins build number as the image version.</p>

<p>For example:</p>

<table>
  <thead>
    <tr>
      <th>Jenkins Build</th>
      <th>Frontend Image</th>
      <th>Backend Image</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Build 1</td>
      <td>profile-frontend:1</td>
      <td>profile-backend:1</td>
    </tr>
    <tr>
      <td>Build 2</td>
      <td>profile-frontend:2</td>
      <td>profile-backend:2</td>
    </tr>
    <tr>
      <td>Build 3</td>
      <td>profile-frontend:3</td>
      <td>profile-backend:3</td>
    </tr>
  </tbody>
</table>

<p><strong>Benefits:</strong></p>

<ul>
  <li>Each build gets its own image tag.</li>
  <li>Previous versions can be identified.</li>
  <li>It becomes easier to trace deployments to Jenkins builds.</li>
  <li>Frontend and backend versions can be matched to the same build number.</li>
</ul>

<p>However, keeping a unique tag does not automatically guarantee that the image remains available forever. Image retention and cleanup policies also matter.</p>

<h3>24.7 Verifying the Docker Images</h3>

<p>After building both images, the pipeline verifies that the images exist locally.</p>

<p>The verification command is:</p>

<pre><code>docker images | grep "profile"</code></pre>

<p>Let's break it down.</p>

<h4>docker images</h4>

<p>Displays the Docker images available on the local Docker host.</p>

<p>It typically displays:</p>

<ul>
  <li>Repository name.</li>
  <li>Image tag.</li>
  <li>Image ID.</li>
  <li>Creation time.</li>
  <li>Image size.</li>
</ul>

<h4>| (Pipe Operator)</h4>

<p>The pipe operator passes the output of one command as input to another command.</p>

<p>Here, the output of <code>docker images</code> is passed to <code>grep</code>.</p>

<h4>grep "profile"</h4>

<p><code>grep</code> searches the supplied output for lines containing the word <code>profile</code>.</p>

<p>This helps filter the output and display the Profile App images.</p>

<p>Example output:</p>

<pre><code>REPOSITORY          TAG    IMAGE ID       CREATED         SIZE
profile-frontend    5      abc123456789   2 minutes ago   250MB
profile-backend     5      def987654321   1 minute ago    180MB</code></pre>

<p>The image IDs and sizes above are illustrative.</p>

<p><strong>Important:</strong> This command filters the image listing. It is useful for visibility, but it is not a strict assertion that both images exist. A more robust pipeline could explicitly check for each expected image and fail if either is missing.</p>

<h3>24.8 Why Do We Need Image Verification?</h3>

<p>Imagine the frontend image builds successfully but the backend image build fails.</p>

<p>If the pipeline continues without checking the results, later stages may fail when trying to tag or push the missing backend image.</p>

<p>Verification helps identify image-related problems before proceeding to the publishing stage.</p>

<p>Common Docker build failures include:</p>

<ul>
  <li>Dockerfile missing from the build context.</li>
  <li>Incorrect directory path.</li>
  <li>Dependency installation failure.</li>
  <li>Network issues while downloading base images.</li>
  <li>Insufficient disk space.</li>
  <li>Invalid Dockerfile instructions.</li>
</ul>

<h3>24.9 Tagging Images for Docker Hub</h3>

<p>After building and verifying the local images, the pipeline assigns Docker Hub repository names to them.</p>

<p>The frontend tagging command is:</p>

<pre><code>docker tag profile-frontend:${BUILD_NUMBER} vislavathpavani5123/profile-frontend:${BUILD_NUMBER}</code></pre>

<p>The backend tagging command is:</p>

<pre><code>docker tag profile-backend:${BUILD_NUMBER} vislavathpavani5123/profile-backend:${BUILD_NUMBER}</code></pre>

<p>Let's understand the syntax.</p>

<pre><code>docker tag SOURCE_IMAGE TARGET_IMAGE</code></pre>

<ul>
  <li><code>docker tag</code>: Assigns another name or tag to an existing local image.</li>
  <li><code>SOURCE_IMAGE</code>: The image that was built locally.</li>
  <li><code>TARGET_IMAGE</code>: The repository name and tag that will be used for publishing.</li>
</ul>

<p>In this project, the Docker Hub username is:</p>

<pre><code>vislavathpavani5123</code></pre>

<p>The resulting image names for build number 5 are:</p>

<pre><code>vislavathpavani5123/profile-frontend:5

vislavathpavani5123/profile-backend:5</code></pre>

<h3>24.10 Why Is Docker Tag Required?</h3>

<p>The local image initially has a name such as:</p>

<pre><code>profile-frontend:5</code></pre>

<p>But Docker Hub requires the image to be associated with the appropriate Docker Hub namespace and repository.</p>

<p>Therefore, we tag it as:</p>

<pre><code>vislavathpavani5123/profile-frontend:5</code></pre>

<p>This does not rebuild or duplicate the image. Docker creates another reference to the same local image.</p>

<p>The image can now be pushed to the corresponding Docker Hub repository.</p>

<h3>24.11 Understanding the Complete Image Lifecycle</h3>

<pre><code>GitHub Repository
       |
       v
Jenkins Git Checkout
       |
       v
Application Source Code
       |
       +----------------------+
       |                      |
       v                      v
Frontend Directory      Backend Directory
       |                      |
       v                      v
Docker Build             Docker Build
       |                      |
       v                      v
profile-frontend:5       profile-backend:5
       |                      |
       +----------+-----------+
                  |
                  v
          Verify Docker Images
                  |
                  v
          Tag Images for Docker Hub
                  |
                  v
  vislavathpavani5123/profile-frontend:5
  vislavathpavani5123/profile-backend:5
                  |
                  v
          Ready for Docker Hub Push</code></pre>

<h3>24.12 Common Errors and Troubleshooting</h3>

<table>
  <thead>
    <tr>
      <th>Error</th>
      <th>Possible reason</th>
      <th>What to check</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Dockerfile not found</td>
      <td>Incorrect build context or missing Dockerfile.</td>
      <td>Check the frontend or backend directory.</td>
    </tr>
    <tr>
      <td>Cannot connect to Docker daemon</td>
      <td>Docker Desktop is not running or Jenkins cannot access Docker.</td>
      <td>Run <code>docker info</code>.</td>
    </tr>
    <tr>
      <td>npm install failed</td>
      <td>Dependency or network issue.</td>
      <td>Check package files and build logs.</td>
    </tr>
    <tr>
      <td>No space left on device</td>
      <td>Insufficient disk space.</td>
      <td>Check Docker disk usage.</td>
    </tr>
    <tr>
      <td>Image not found</td>
      <td>Image build failed or an incorrect tag was used.</td>
      <td>Run <code>docker images</code>.</td>
    </tr>
  </tbody>
</table>

<p>Useful troubleshooting commands:</p>

<pre><code># Check Docker availability
docker info

# List local Docker images
docker images

# List images related to Profile App
docker images | grep profile

# Check Docker disk usage
docker system df

# Inspect a specific image
docker image inspect profile-frontend:${BUILD_NUMBER}</code></pre>

<h3>24.13 Interview Questions</h3>

<p><strong>Q1. What is the difference between a Docker image and a Docker container?</strong></p>

<p>An image is the packaged application template. A container is a running or created instance of that image.</p>

<p><strong>Q2. Why are frontend and backend built as separate Docker images?</strong></p>

<p>They have different responsibilities, dependencies, and runtime requirements. Separate images allow independent builds and deployments.</p>

<p><strong>Q3. What does the -t option do in docker build?</strong></p>

<p>It assigns a repository name and tag to the image being built.</p>

<p><strong>Q4. Does docker tag create a new Docker image?</strong></p>

<p>No. It creates another reference to an existing local image. It does not rebuild the image.</p>

<p><strong>Q5. Why use Jenkins BUILD_NUMBER as the Docker image tag?</strong></p>

<p>It provides a build-specific version identifier, making it easier to trace an image back to its Jenkins build.</p>

<p><strong>Q6. What is a Docker build context?</strong></p>

<p>It is the set of files made available to Docker during an image build. In this project, <code>frontend</code> and <code>backend</code> are separate build contexts.</p>

<p><strong>Q7. Does docker images | grep profile prove that both images exist?</strong></p>

<p>Not necessarily. It displays matching image entries, but a robust pipeline should explicitly verify that both expected image names and tags exist.</p>

<p><strong>Key takeaway:</strong> Jenkins builds two independent Docker images, verifies their presence, and tags them with the Docker Hub namespace and Jenkins build number. These tagged images are now ready to be uploaded to Docker Hub.</p>
