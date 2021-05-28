
def call(String projectPath) {
	DOCKER_REGISTRY_URL = "http://nexus.localdev.net:5000"
	DOCKER_REGISTRY_CREDENTIAL = "nexus"

	projectName = getProjectName()
	registryPath = "${DOCKER_REGISTRY_URL}/${projectPath}/${projectName}"

	versionNumber = "${env.BUILD_NUMBER.toInteger()}"
	versionTag = "${env.BRANCH_NAME}-${versionNumber}"

	echo "Deploying image to registry: ${registryPath}:${versionTag}"

	docker.withRegistry(registryPath, "${DOCKER_REGISTRY_CREDENTIAL}") {
		customImage = docker.build("${projectPath}/${projectName}:${versionTag}")
		customImage.push()
		customImage.push('latest')
	}
}
