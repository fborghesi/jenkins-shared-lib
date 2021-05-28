
def call(String chartDir) {
	HELM_REPO_URL = "http://nexus:8081"
	HELM_REPO_CREDENTIAL = "nexus"
	HELM_CMD = "/usr/local/bin/helm"
	projectName = getProjectName()

	withCredentials([usernamePassword(credentialsId: HELM_REPO_CREDENTIAL, usernameVariable: 'USER', passwordVariable: 'PASS')]) {
		sh """	printf 'Packaging %s in dir %s' ${projectName} ${chartDir}

	# create package
	PKG_TGZ=`${HELM_CMD} package ${chartDir} | awk '{print \$NF}'`
	printf 'Generated package file %s' \$PKG_TGZ

	# chart upload
	printf 'Deploying chart %s to helm repo: %s' \$PKG_TGZ ${HELM_REPO_URL}
	curl -v --upload-file `basename \$PKG_TGZ` -u $USER:$PASS ${HELM_REPO_URL}/repository/helm/
	"""
  }
}
