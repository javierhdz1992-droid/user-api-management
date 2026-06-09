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
        choice(
                name: 'ENV',
                choices: ['DEV', 'PROD'],
                description: 'Execution Environment'
        )

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

        stage('Clean') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                mvn test ^
                -Denv=%ENV% ^
                -Dheadless=%HEADLESS%
                """
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