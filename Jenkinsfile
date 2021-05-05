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
                echo 'TODO: Unit Test'
//                 sh 'mvn -Dtest=RetailCustomerApplicationTest test -f cmp-mock-customer-es/pom.xml'
            }
            post {
                always {
                    echo 'TODO: post unit test'
//                     junit 'cmp-mock-customer-es/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build and Push') {
            steps {
                sh 'docker build -t gcr.io/burner-panagnih/mock-es:v1 cmp-mock-customer-es/'
                sh 'gcloud auth configure-docker'
                sh 'docker push gcr.io/burner-panagnih/mock-es:v1'
            }
//             post {
//                 always {
//                     sh 'gcloud auth configure-docker'
//                     sh 'docker push gcr.io/burner-panagnih/mock-es:v1'
//                 }
//             }
        }
        stage('Deploy') {
            steps {
                script{
                   try{
                       sh 'gcloud container clusters get-credentials cluster-1'
                       sh 'kubectl create deployment es-deployment --image=gcr.io/burner-panagnih/mock-es:v1'
                       sh 'kubectl expose deployment es-deployment --type LoadBalancer --port 8070'
//                        sh 'kubectl apply -f .'
                   }catch(error){
//                         sh 'kubectl create deployment es-deployment --image=gcr.io/burner-panagnih/mock-es:v1'
//                        sh 'kubectl create -f .'
                   }
                }
            }
        }
        stage('Integration Test') {
            steps {
                echo 'TODO: Integration Test'
//                 sh 'mvn -Dtest=KarateTest -DfailIfNoTests=false test -f cmp-mock-customer-es/pom.xml'
            }
        }
    }
}