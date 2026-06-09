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

        stage('Matrix Test') {
            parallel {
                stage('DEV') {
                    steps {
                        bat """
                mvn clean test -Denv=DEV -Dheadless=${params.HEADLESS} ^
                -Dallure.results.directory=target/allure-results-dev
                """
                    }
                }

                stage('PROD') {
                    steps {
                        bat """
                mvn clean test -Denv=PROD -Dheadless=${params.HEADLESS} ^
                -Dallure.results.directory=target/allure-results-prod
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

            allure([
                    results: [
                            [path: 'target/allure-results-dev'],
                            [path: 'target/allure-results-prod']
                    ]
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