package com.pavani.pipeline

def BuildDeployPipeline() {
    node {
        stage('check') {
            echo "Hi This is OK"
        }
    }
}