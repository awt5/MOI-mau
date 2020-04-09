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
                sh 'ehco "Running the tests"'
                sh 'java -version"'
            }
        }
        stage('Publish to Artifactory'){
            steps{
                sh 'exit -1'
            }
        }
        stage('Deploy'){
            parallel{
                stage('DeployToDevEnv'){
                    steps{
                        sh 'ehco "Deployong to dev enviorment"'
                    }
                }
                stage('DeployToQaEnv'){
                    steps{
                        sh 'ehco "Deployong to QA enviorment"'
                    }
                }
            }
            
        }
    }
}