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
}