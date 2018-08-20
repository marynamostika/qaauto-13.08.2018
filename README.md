                                       Environment setup
1. In Google find "Intellij Idea". In the first result found, chouse "Intellij Idea Community", download and instal it.
2. In Google find "jdk". 
   Open the first result found > accept license agreement > choose the version depending on your operating system > download and instal.
3. Download ChromeDriver, choose the version depending on your operating system, download and instal to c/windows/system32.
4. Open IntellijIdea > File > New Project. 
   On the left side choose "Maven". 
   On the right side in the field "Project SDK" specify the path where the downloaded jdk.
   GroupId- is a name of the organization.
   ArtifatId - is a name of the project.
   In Google find "selenium maven" > choose "Selenium Java" > choose the latest version > copy the text.
   In a  pom file past the text and add <dependencies></dependensies>.