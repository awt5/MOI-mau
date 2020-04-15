pipeline {
    agent any
    stages{
        stage('Build MOI'){
            steps{
                sh 'echo "Start building app for moi-mau"'
                sh 'chmod +x gradlew'
                sh './gradlew clean build'
            }
        }
        stage('Sonarqube'){
            steps{
                sh 'echo "Running SONAR SCAN"'
                //sh 'exit -1'
                sh './gradlew sonarqube'
            }
        }
        stage('Deploying'){
            parallel{
                stage('DeployToDevEnv'){
                    steps{
                        sh 'echo "Deployong to dev enviorment"'
                    }
                }
                stage('DeployToQaEnv'){
                    steps{
                        sh 'echo "Deployong to QA enviorment"'
                    }
                }
            }
            
        }
    }
    environment {
        EMAIL_TEAM = 'geralt702@gmail.com, mauricio.oroza@fundacion-jala.org'
        EMAIL_ADMIN = 'mauricio.oroza@fundacion-jala.org'
        EMAIL_ME = 'mau.oroza1@gmail.com'
    }
    post {
        always {
            junit 'build/test-results/**/*.xml'
            //unit 'build/test-results/**/*.xml'
            mail to: "${EMAIL_ADMIN}", 
                 subject: "Pipeline-> ${currentBuild.fullDisplayName} executed, status: ${currentBuild.currentResult}",
                 body: "The pipeline ${currentBuild.fullDisplayName} has been executed, refer to ${env.BUILD_URL} for more info."
                 sh 'echo "Sending mail always"'
        }

        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} on this pipeline-> ${env.JOB_NAME}${env.BUILD_NUMBER}",
                 body: "The pipeline ${currentBuild.fullDisplayName} has succeeded, more info at ${env.BUILD_URL}"
                 sh 'echo "Sending mail succsess"'
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} on this pipeline-> ${currentBuild.fullDisplayName}",
                 body: "Something has go wrong in the pipeline ${currentBuild.fullDisplayName}, please se the logs at ${env.BUILD_URL}"
                 sh 'echo "Sending mail failure"'
        }
    }
        
}