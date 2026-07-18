import com.pavani.pipeline.profileAppBuildDeployPipeline

def call() {

    echo "******** Starting Profile App Pipeline ********"
    echo "Job Name : ${env.JOB_NAME}"

    if (env.JOB_NAME.contains("DEV_Feature_BuildDeploy")) {

        echo "DEV Feature Build Deploy Pipeline"

        def BuildDeployPipelineObj = new profileAppBuildDeployPipeline()
        BuildDeployPipelineObj.BuildDeployPipeline()

    } else {

        error "No matching pipeline found for Job: ${env.JOB_NAME}"

    }
}