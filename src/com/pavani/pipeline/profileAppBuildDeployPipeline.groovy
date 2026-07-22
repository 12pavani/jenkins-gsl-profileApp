package com.pavani.pipeline

def BuildDeployPipeline() {
    node {
        stage('Cleanup') {

            echo "******** Entering cleanup stage ********"

            deleteDir()

            echo "******** Exiting cleanup stage ********"
        }

        stage('Check Docker') {

            echo "******** Entering Check Docker stage ********"

            sh '''
                docker info >/dev/null 2>&1 || {
                    echo "Docker is NOT running. Please start Docker Desktop."
                    exit 1
                } 
            '''

            echo "******** Exiting Check Docker stage ********"
        }

        stage('Checkout') {

            echo "******** Entering Checkout stage ********"

            checkout([
                $class: 'GitSCM',
                branches: [[name: '*/main']],
                userRemoteConfigs: [[
                    url: 'https://github.com/12pavani/Profile_App.git',
                    credentialsId: 'github-cred'   
                ]]
            ])

            echo "******** Exiting Checkout stage ********"
        }

        stage('Build Frontend') {

            echo "******** Entering Build Frontend stage ********"

            sh ' docker build -t profile-frontend:${BUILD_NUMBER} frontend '

            echo "******** Exiting Build Frontend stage ********"
        }

        stage('Build Backend') {

            echo "******** Entering Build Backend stage ********"

            sh ' docker build -t profile-backend:${BUILD_NUMBER} backend '

            echo "******** Exiting Build Backend stage ********"
        }

        stage('Verify images') {

            echo "******** Entering Verify images stage ********"

            sh 'docker images | grep "profile"'

            echo "******** Exiting Verify images stage ********"
        }

        stage('Docker Compose') {

            echo "******** Entering Docker Compose stage ********"

            sh '''
                export BUILD_NUMBER=${BUILD_NUMBER}
                docker compose -f docker-compose.yaml up -d 
            '''

            echo "******** Exiting Docker Compose stage ********"
        }

        stage('Health Check') {

            echo "******** Entering Health Check stage ********"

            sh ''' 
                # list running containers 
                docker ps
                #frontend url
                # -f is fail silently 
                curl -f http://localhost:3000 
                #backend url
                curl -f http://localhost:8000/profile
            '''

            echo "******** Exiting Health Check stage ********"
        }

        stage('Email Notification') {

            echo "******** Entering Email Notification stage ********"

            emailext(
                subject: "Profile App Build - ${currentBuild.currentResult}",
                body: """
                    Job : ${env.JOB_NAME}
                    Build : ${env.BUILD_NUMBER}

                    Status : ${currentBuild.currentResult}

                    URL : ${env.BUILD_URL}
                """,
                to: 'vislavathpavani5123@gmail.com'
            )

            echo "******** Exiting Email Notification stage ********"
        }
    }
}