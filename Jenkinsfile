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
                        dir('dev') {
                            bat """
                    mvn clean test -Denv=DEV -Dheadless=${params.HEADLESS} ^
                    -Dallure.results.directory=target/allure-results
                    """
                        }
                    }
                }

                stage('PROD') {
                    steps {
                        dir('prod') {
                            bat """
                    mvn clean test -Denv=PROD -Dheadless=${params.HEADLESS} ^
                    -Dallure.results.directory=target/allure-results
                    """
                        }
                    }
                }
            }
        }
    }

    post {

        always {

            junit allowEmptyResults: true,
                    testResults: 'dev/target/surefire-reports/*.xml, prod/target/surefire-reports/*.xml'

            allure([
                    results: [
                            [path: 'dev/target/allure-results'],
                            [path: 'prod/target/allure-results']
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