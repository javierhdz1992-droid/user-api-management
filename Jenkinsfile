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
                description: 'Select execution environment'
        )

        booleanParam(
                name: 'HEADLESS',
                defaultValue: false
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                // Ejecutar la fase 'verify' para que el goal 'allure:report' ligado a verify se ejecute
                bat """
                mvn clean verify -Denv=${params.ENV} -Dheadless=${params.HEADLESS} -Dallure.results.directory=target/allure-results
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