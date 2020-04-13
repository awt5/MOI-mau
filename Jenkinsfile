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
                //sh 'java -version"'
            }
        }
        stage('Publish to Artifactory'){
            steps{
                sh 'echo "Running the tests"'
                //sh 'exit -1'
            }
        }
        stage('Deploy'){
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
                 body: "The pipeline ${env.BUILD_URL} has been executed."
                 sh 'echo "Sending mail always"'
        }
        success {
            mail to: "${EMAIL_ME}", 
                 subject: "${currentBuild.currentResult} Pipeline: ${env.JOB_NAME}${env.BUILD_NUMBER}",
                 body: "The pipeline ${env.BUILD_URL} has been well executed"
        }
        failure {
            mail to: "${EMAIL_TEAM}",
                 subject: "${currentBuild.currentResult} Pipeline: ${currentBuild.fullDisplayName}",
                 body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
        
}