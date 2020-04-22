pipeline {
    agent any
    environment {
            EMAIL_TEAM = 'geralt702@gmail.com, mauricio.oroza@fundacion-jala.org'
            EMAIL_ADMIN = 'mauricio.oroza@fundacion-jala.org'
            EMAIL_ME = 'mau.oroza1@gmail.com'
            //for dockerhub:
            PROJECT_NAME = 'moi-project'
            DOCKER_CR = 'docker-credentials'
            USER_DOCKER_HUB = 'snip77'
    }
    stages{
        stage('Build MOI'){
            steps{
                sh 'echo "Start building app for moi-mau"'
                sh 'echo "Giving gradle permissions..."'
                sh 'chmod +x gradlew'
                //agregar caso en que falle sh 'exit -1'
                sh './gradlew clean build'
            }
            post {
                always{
                    junit 'build/test-results/**/*.xml'
                    publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/jacoco/test/html', reportFiles: 'index.html', reportName: "Test Coverage"])
                    publishHTML (target: [allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: "Test Report"])
                }
            }
        }
        stage('Sonarqube-Code Quality'){
            steps{
                sh 'echo "Running SONAR SCAN"'
                //sh 'exit -1'
                sh './gradlew sonarqube'
            }
        }
        stage('DeployToDevEnv'){
            environment {
                APP_PORT=9096
                APP_PORT=3036
            }
            steps{
                sh 'echo "Deploying to DEV environment"'
                sh 'docker-compose config'
                sh 'docker-compose build'
                sh 'docker-compose up -d'
            }
        }
        stage('Run Acceptance Tests'){
            steps{
                echo 'Running acceptance testing'
            }
            post {
                success {
                    sh 'echo "Saving jar..."'
                    archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
                }
            }
        }
        stage('Publishing to artifactory'){
            parallel{
                stage('Publishing to local'){
                    when {
                        branch 'jenkins-c'
                    }
                    steps{
                        sh 'echo "Publishing to local..."'
                        sh 'echo "Running test"'
                        sh './gradlew artifactoryPublish'
                    }
                }
                stage('Publishing to release'){
                    when {
                        branch 'master'
                    }
                    steps{
                        sh 'echo"publishing to release"'
                        sh './gradlew artifactoryPublish'
                    }
                }
            }
        }
        stage('Publish To Docker Hub'){ 
            when {
                branch 'jenkins-c' //develop
            }
            steps{
                withDockerRegistry([ credentialsId: "${DOCKER_CR}", url: "https://index.docker.io/v1/" ]) {
                    sh 'docker tag ${PROJECT_NAME}:latest ${USER_DOCKER_HUB}/${PROJECT_NAME}:v1.0-$BUILD_NUMBER'
                    sh 'docker push ${USER_DOCKER_HUB}/${PROJECT_NAME}'
                }
            }
        }
        stage('DeployToQAEnv'){
            steps{
                sh 'echo "Deployong to QA enviorment"'
            }
        }
        stage('RunAutomationTests'){
            steps{
                sh 'echo "RunningAutoTests"'
            }
        }
        stage('Automation Testing'){
            when {
                branch 'develop'
            }
            steps{
                echo 'Running automation test'
            }
        }
        stage('Clean'){
            steps{
                sh 'echo "publishing to release"'

                //sh 'sudo docker rmi $(sudo docker images -aq -f 'dangling=true')'
            }
        }
    }
    post {
        always {
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
