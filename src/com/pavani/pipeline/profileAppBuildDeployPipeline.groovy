package com.pavani.pipeline

def BuildDeployPipeline() {
    node {
        stage('Cleanup') {
            echo "******** Entering the cleanup stage ********"
            deleteDir()
            echo "******** Existing the cleanup stage ********"
        }

        stage('Checkout') {
            echo "******** Entering the Checkout stage ********"
            checkout([
                $class: 'GitSCM',
                branches: [[name: '*/main']],
                userRemoteConfigs: [[
                    url: 'https://github.com/12pavani/Profile_App.git',
                    credentialsId: 'github-cred'   
                ]]
            ])
            echo "******** Existing the Checkout stage ********"
        }
    }
}