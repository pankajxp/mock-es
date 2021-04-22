pipeline {
    tools {
        maven '3.6.3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Run') {
            steps {
                sh 'mvn spring-boot:run'
            }
        }
    }
}