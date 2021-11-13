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
                    git credentialsId: '7ac5e4f0-3d0d-488f-9d6b-e712995aaf53', url: 'https://github.com/learningisart/lia-project-java.git'
                }
            }
        }
        stage("Maven build") {
            steps {
                script {
                    // Let's clone the source
                    echo 'Maven build'
                    sh 'mvn -version'
                    sh 'mvn clean package'
                }
            }
        }
        stage("copy war") {
            steps {
                script {
                    // Let's clone the source
                    echo 'copy war'
                    sh "ls -l ${WORKSPACE}/target/java-web-app-1.0.war"
                    sh "cp ${WORKSPACE}/target/java-web-app-1.0.war /home/ubuntu/apache-tomcat-9.0.54/webapps"
                    sh "ls -l /home/ubuntu/apache-tomcat-9.0.54/webapps/java-web-app-1.0.war"
                }
            }
        }
        stage("test") {
            steps {
                script {
                    // Let's clone the source
                    parallel action1: {
                        // do something
                         echo 'action1'
                         sh "sleep 60"
                    }, action2: {
                        // do something else
                         echo 'action2'
                         sh "sleep 60"
                    },
                    failFast: true|false
                }
            }
        }
        stage("deploy") {
            when {
                    anyOf {
                        environment name: 'Phase', value: 'Deploy'
                    }
                }
            steps {
                script {
                    // Let's clone the source
                    echo 'tomcat restart'
                    sh "/home/ubuntu/apache-tomcat-9.0.54/bin/shutdown.sh"
                    sh "sleep 5"
                    sh "/home/ubuntu/apache-tomcat-9.0.54/bin/startup.sh"
                }
            }
        }


    }
    
}


