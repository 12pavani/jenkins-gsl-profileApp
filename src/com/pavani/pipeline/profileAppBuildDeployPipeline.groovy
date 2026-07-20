package com.pavani.pipeline

def BuildDeployPipeline() {
    node {
        stage('Cleanup') {
            echo "******** Entering cleanup stage ********"
            deleteDir()
            echo "******** Exiting cleanup stage ********"
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
            sh 'docker images'
            echo "******** Exiting Verify images stage ********"
        }

        stage('Check Docker') {
            sh '''
                docker info >/dev/null 2>&1 || {
                    echo "Docker is NOT running. Please start Docker Desktop."
                    exit 1
                } 
            '''
        }
    }
}