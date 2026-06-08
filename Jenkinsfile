pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/javierhdz1992-droid/user-api-management.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            allure([
                includeProperties: false,
                results: [[path: 'target/allure-results']]
            ])
        }
    }
}