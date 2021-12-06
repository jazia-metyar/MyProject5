pipeline {
    agent any
     tools {
        maven 'MAVEN_HOME'
      }

    stages {
     stage ('Initialize') {
                      steps {
                          bat '''
                              echo "PATH = ${PATH}"
                          '''
                      }
                  }

        stage('Install stage') {
            steps {
              bat 'mvn clean install'
        }
    }
    }
    post {
    always{
    cleanWs()
    }
    }


    }