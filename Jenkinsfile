pipeline {
    agent any
    options {
        timestamps()
    }
    stages {        
        stage("Compile"){
            steps {
                script {
                    sh './mvnw clean compile -e'
                }
            }
        }
        stage("Test"){
            steps {
                script {
                    sh './mvnw clean test -e'
                }
            }
        }
        stage("Jar"){
            steps {
                script {            
                    sh './mvnw clean package -e'
                }
            }
        }
        stage("Run"){
            steps {
                script {
                    sh 'nohup bash mvnw spring-boot:run -Dserver.port=9090 &'
                    sleep(15)
                }
            }
        }
        stage("Postman Test"){
            steps {
                script {
                    nodejs('NodeJS') {
                        sh "newman run src/test/resources/postman/postman.json"
                    }
                }
            }
        }
    }
}