import java.time.*

def call() {
	now = LocalDateTime.now()
	fileName = "${WORKSPACE}/files/VERSION"
    fileContents = "Date: ${now}\nGit Project: ${env.GIT_PROJECT}\nGit Branch: ${env.BRANCH_NAME}\nJenkins build #: ${env.VERSION_NUMBER}"

	writeFile(file: fileName, text: fileContents)
}