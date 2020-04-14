pipeline {
    agent any
    stages{
        stage('build'){
            steps{
                sh 'echo "Start building app for moi-mau"'
            }
        }
        stage('Unit test'){
            steps{
                sh 'echo "Running the tests"'
                sh 'java -version'
            }
        }
        stage('Publish to Artifactory'){
            steps{
                sh 'echo "Running the tests"'
                //sh 'exit -1'
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
        EMAIL_ME = 'mau.oroza1@gmail.comn'
    }
    post {
        always {
            mail to: "${EMAIL_ADMIN}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Pipeline ${env.BUILD_URL} executed."
                 sh 'echo "Sending mail always"'
        }
        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${env.JOB_NAME}${env.BUILD_NUMBER}",
                 body: "Pipeline ${env.BUILD_URL} succeded"
                 sh 'echo "Sending mail succsess"'
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Failed ${env.BUILD_URL}"
                 sh 'echo "Sending mail failure"'
        }
    }
        
}