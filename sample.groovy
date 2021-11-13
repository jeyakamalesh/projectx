pipeline {
    
    agent {
        //http://ec2-18-212-168-4.compute-1.amazonaws.com:8080/jenkins/computer/(built-in)/
        //label "Built-In"
        label "SL1"
    }

    parameters {
        choice(choices: ['Build', 'Deploy'], description: 'Select what do you want ?', name: 'Phase')
    }

    stages {
        stage("Git Code Checkout") {
            steps {
                script {
                    // Let's clone the source
                    echo 'Git clone'
                }
            }
        }
        stage("Maven build") {
            steps {
                script {
                    // Let's clone the source
                    echo 'Maven build'
                }
            }
        }
        stage("copy war") {
            steps {
                script {
                    // Let's clone the source
                    echo 'copy war'
                }
            }
        }
        stage("Move war") {
            steps {
                script {
                    // Let's clone the source
                    echo 'move war to webapps'
                }
            }
        }
        stage("tomcat restart") {
            steps {
                script {
                    // Let's clone the source
                    echo 'tomcat restart'
                }
            }
        }


    }
    
}


