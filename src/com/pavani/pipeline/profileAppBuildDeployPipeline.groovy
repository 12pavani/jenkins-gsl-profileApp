package com.pavani.pipeline

def BuildDeployPipeline() {
    node {
        stage('Cleanup') {
            echo "******** Entering the cleanup stage ********"
            deleteDir()
            echo "******** Existing the cleanup stage ********"
        }
    }
}