pipeline {
    agent any
    options {
        timestamps()
    }
    stages {
        stage("SCM"){
            steps {
                cleanWs()
                script {
                    dir('src'){
                        sh 'git clone https://github.com/diplo-devops-usach-2021/TrabajoFinalM4SWD.git/ .'
                    }
                }
            }
        }
        stage("Compile"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean compile -e'
                    }
                }
            }
        }
        stage("Test"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean test -e'
                    }
                }
            }
        }
        stage("Jar"){
            steps {
                script {
                    dir('src'){
                        sh './mvnw clean package -e'
                    }
                }
            }
        }
        stage("Run"){
            steps {
                script {
                    dir('src'){
                        sh 'nohup bash mvnw spring-boot:run &'
                        sleep(30)
                    }
                }
            }
        }
        stage("Postman Test"){
            steps {
                script {
                    dir('src'){
                        nodejs('NodeJS') {
                            sh "newman run src/test/resources/postman/postman.json"
                        }
                    }
                }
            }
        }
        stage("Selenium Test"){
            steps {
                script {
                    sh "echo selenium"
                }
            }
        }
        stage("JMeter Test"){
            steps {
                script {
                    sh "echo jmeter"
                }
            }
        }
    }
}