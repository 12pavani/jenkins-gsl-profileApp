package com.pavani.pipeline

def BuildDeployPipeline() {
    // node {
    //     stage('check') {
    //         echo "Hi This is OK"
    //     }
    // }

    pipeline {
        agent any 

        stages {
            stage("check") {
                steps {
                    echo "Hello, its declarative pipeline"
                }
            }
        }
    }
}