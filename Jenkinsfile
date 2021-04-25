pipeline {
    agent any
    tools {
        maven 'maven_3_8_1'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package -f ./cmp-mock-customer-es/pom.xml'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn -Dtest=RetailCustomerApplicationTest test -f ./cmp-mock-customer-es/pom.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Test') {
            steps {
                sh 'mvn -Dtest=KarateTest test -f ./cmp-mock-customer-es/pom.xml'
            }
        }
    }
}