def call(String project, String hubUser) {
    sh "docker image build -t ${hubUser}/${project}:beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER} ."
    withCredentials([usernamePassword(
            credentialsId: 'dockerhub_id',
            usernameVariable: 'USERNAME',
            passwordVariable: 'PASSWORD'
    )]) {
        
        sh "docker login -u '$USER' -p '$PASS'" 
        
    }
    sh "docker image push ${hubUser}/${project}:beta-${env.BRANCH_NAME}-${env.BUILD_NUMBER}"
}