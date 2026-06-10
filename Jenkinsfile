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
                defaultValue: false,
                description: 'No run tests in headless mode'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('DEV Tests') {
            steps {
                bat """
                mvn clean test -Denv=DEV -Dheadless=${params.HEADLESS}
                """
            }
        }

        stage('PROD Tests') {
            steps {
                bat """
                mvn clean test -Denv=PROD -Dheadless=${params.HEADLESS}
                """
            }
        }
    }

    post {

        always {

            junit allowEmptyResults: true,
                    testResults: 'target/surefire-reports/*.xml'

            allure([
                    results: [[path: 'target/allure-results']]
            ])

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