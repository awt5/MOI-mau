pipeline {
    agent any
    environment {
            EMAIL_TEAM = 'geralt702@gmail.com, mauricio.oroza@fundacion-jala.org'
            EMAIL_ADMIN = 'mauricio.oroza@fundacion-jala.org'
            EMAIL_ME = 'mau.oroza1@gmail.com'
            //for dockerhub:
            PROJECT_NAME = 'moi-project'
            PROJECT_VER = '1.0'
            DOCKER_CR = 'docker-credentials'
            USER_DOCKER_HUB = 'snip77'
    }

    stages{
        stage('Build MOI'){
            steps{
                sh 'echo "Start building app for moi-mau"'
                sh 'echo "Giving gradle permissions..."'
                sh 'chmod +x gradlew'
                script {
                    if (env.BRANCH_NAME == 'master') {
                        sh './gradlew -Pmoi_version=${PROJECT_VER} clean build'
                    } else {
                        sh './gradlew clean build'
                    }
                }
            }
            post {
                success{
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
            }
            steps{
                sh 'echo "Deploying to DEV environment"'
                sh 'docker-compose down -v'
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
                        branch 'develop' //
                    }
                    steps{
                        sh 'echo "Publishing to local..."'
                        sh 'echo "Running test"'
                        sh './gradlew -Partifactory_repokey=libs-snapshot-local artifactoryPublish'
                    }
                }
                stage('Publishing to release'){
                    when {
                        branch 'master'
                    }
                    steps{
                        sh 'echo "publishing to release"'
                        sh './gradlew -Partifactory_repokey=libs-release-local -Pmoi_version=${PROJECT_VER} artifactoryPublish'
                    }
                }
            }
        }

        stage('Publish To Docker Hub'){
            parallel{
                stage('Publish Develop'){ 
                    when {
                        branch 'develop' 
                    }
                    steps{
                        withDockerRegistry([ credentialsId: "${DOCKER_CR}", url: "https://index.docker.io/v1/" ]) {
                            sh 'docker tag ${PROJECT_NAME}:latest ${USER_DOCKER_HUB}/${PROJECT_NAME}:$BUILD_NUMBER'
                            sh 'docker push ${USER_DOCKER_HUB}/${PROJECT_NAME}'
                        }
                    }
                }

                stage('Publish Release'){ 
                    when {
                        branch 'master'
                    }
                    steps{
                        withDockerRegistry([ credentialsId: "${DOCKER_CR}", url: "https://index.docker.io/v1/" ]) {
                            sh 'docker tag ${PROJECT_NAME}:latest ${DOCKER_USER}/${PROJECT_NAME}:1.0'
                            sh 'docker push ${DOCKER_USER}/${PROJECT_NAME}'
                        }
                    }
                }
            }
        }

        stage('Promote To QA'){
            environment {
                APP_PORT=9097
                QA_HOME='/deployments/qa'
            }
            when {
                anyOf{
                    branch 'develop'
                    branch 'master'
                }
            }
            steps{
                sh 'docker-compose down -v'
                sh 'cp docker-compose.yml $QA_HOME'
                sh 'echo "Deploying to QA environment"'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml down -v'
                sh 'docker-compose -f $QA_HOME/docker-compose.yml up -d'
            }
        }

        stage('Run Automation Tests'){
            when {
                anyOf{
                    branch 'develop'
                    branch 'master'
                }
            }
            steps{
                echo 'Running automation tests'
            }
        }

        stage('Clean'){
            environment {
                APP_PORT=9096

            }
            steps{
                sh 'echo "Cleaning..."'
                sh 'docker-compose down -v'
                sh 'docker rmi $(docker images -aq -f dangling=true)'
                // deleteDir()
                // dir("${workspace}@tmp") {
                //     deleteDir()
                // }
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
