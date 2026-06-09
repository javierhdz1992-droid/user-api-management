pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK25'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(
                numToKeepStr: '20',
                artifactNumToKeepStr: '10'
        ))
    }

    parameters {
        booleanParam(
                name: 'HEADLESS',
                defaultValue: true,
                description: 'Run tests in headless mode'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Matrix Test (DEV + PROD)') {
            steps {
                script {
                    ['DEV', 'PROD'].each { environment ->

                        echo "Running tests for ENV = ${environment}"

                        bat """
                        mvn clean test ^
                        -Denv=${environment} ^
                        -Dheadless=${params.HEADLESS}
                        """
                    }
                }
            }
        }
    }

    post {

        always {

            junit allowEmptyResults: true,
                    testResults: 'target/surefire-reports/*.xml'

            allure(
                    commandline: 'Allure',
                    results: [[path: 'target/allure-results']]
            )

            archiveArtifacts(
                    artifacts: 'target/**/*',
                    fingerprint: true
            )
        }

        success {
            echo 'SUCCESS: All tests executed successfully.'
        }

        unstable {
            echo 'UNSTABLE: Some tests failed.'
        }

        failure {
            echo 'FAILURE: Build execution failed.'
        }

        aborted {
            echo 'ABORTED: Build was cancelled.'
        }
    }
}