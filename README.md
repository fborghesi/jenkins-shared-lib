# My Jenkins library

This is my personal Jenkins shared library providing commodity functions for my projects.


## Jenkins set up

Setting up this shared library on Jenkins is very easy, you just need to follow the instructions below.

### Github credentials
First of all you'll need to configure your Github account for Jenkins to be able to download the shared library from github on every build.

* Open Jenkins in your browser and log in.
* Click on the **Manage Jenkins** menu option.
* Click on **Manage Credentials**.
* In the **Stores scoped to Jenkins** select **Jenkins**, then click on **Global credentials (unrestricted)**
* Now click on **Add Credentials**
* In the **Kind** field select the option *Username with password*
* Fill in your github username and password.
* Enter a meaningfull Id for these credentials, say "my_github_credentials"
* Click **Ok** to save your changes.

### Shared library set up
* In the Jenkins' Dashboard, click on **Manage Jenkins**
* Click on **Configure Jenkins**
* Find the **Global Pipeline Libraries** section.
* For the Library's **Name** field, enter *jenkins-shared-lib*.
* In the **Retrieval Method** section, make sure *Modern SCM* is selected.
* Fill in the **Project Repository** with https://github.com/fborghesi/jenkins-shared-lib.git.
* Select *my_github_credentials* in the **Credentials* drop down (or the Id you entered in the section above).



## Shared library functions

The following utility functions have been defined

### dockerBuildPush()
Builds a Docker Image and pushes it to the registry twice, once tagged as <branch_name>-<build_number> and another as 'latest'.
Params:
 * Projet Path: Registry path where to place the image. That is http://nexus.localdev.net:5000/<project_path>/<project_name>

### getProjectName()
Gets the actual project name. Specially useful when projects are organized into folders.

### helmDeploy()
Deploys a Helm chart from the local nexus repo.
Params:
 * releaseName: The release name, something meaninfull to the piece of software being released.
 * chart: The chart name to download from the registry.
 * namespace: The kubernetes namespace where to deploy the chart.


### helmPush()
Creates a Helm package with the project descriptos and pushes it to the Helm registry.
Params: 
 * chartDir: Directory where the chart is.

### writeVersionFile()
Writes a file named VERSION in the project's build dir that can be included into the image at build time. The file contains image information that can be usefull at runtime.