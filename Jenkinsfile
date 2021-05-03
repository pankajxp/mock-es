pipeline {
    agent any
    tools {
        maven 'maven_3_8_1'
    }
    environment {
        JSON_KEY_FILE = '/Users/panagnih/Documents/Sapient/GCP/burner-panagnih-service-account-key.json'
        PATH = '/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:/Users/panagnih/tools/google-cloud-sdk/bin'
    }
    stages {
        stage('Build and Package') {
            steps {
                sh 'mvn -B -DskipTests clean package -f cmp-mock-customer-es/pom.xml'
            }
        }
        stage('Unit Test') {
            steps {
                sh 'mvn -Dtest=RetailCustomerApplicationTest test -f cmp-mock-customer-es/pom.xml'
            }
            post {
                always {
                    junit 'cmp-mock-customer-es/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'docker build --label poc-mock-es  -t gcr.io/burner-panagnih/mock-es:v1 cmp-mock-customer-es/'
            }
            post {
                always {
                    sh 'gcloud auth configure-docker'
                    sh 'docker push gcr.io/burner-panagnih/mock-es:v1'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
            }
        }
        stage('Integration Test') {
            steps {
                echo 'todo'
//                 sh 'mvn -Dtest=KarateTest -DfailIfNoTests=false test -f cmp-mock-customer-es/pom.xml'
            }
        }
    }
}