
def call(String releaseName, String chart, String namespace) {
	HELM_REPO_URL = "http://nexus:8081"
	HELM_REPO_CREDENTIAL = "nexus"
	HELM_CMD = "/usr/local/bin/helm"
	projectName = getProjectName()

	withCredentials([usernamePassword(credentialsId: HELM_REPO_CREDENTIAL, usernameVariable: 'USER', passwordVariable: 'PASS')]) {
		sh """	printf 'Deploying %s to namespace %s' ${projectName} ${namespace}

	${HELM_CMD} repo add nexus ${HELM_REPO_URL}/repository/helm
	${HELM_CMD} repo update
	${HELM_CMD} upgrade --install ${releaseName} nexus/${chart} --namespace ${namespace} --debug --username ${USER} --password ${PASS}
	"""
	}
}
