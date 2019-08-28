package sbtmkdirs

import scala.io.StdIn.readLine
import java.io.File
import FileUtils.SLASH
import Utils._

object Sbtmkdirs extends App {

    var projectName = ""
    var bCreateGitignoreFile = false
    var bCreateReadmeFile = false
    var bCreateResourcesDir = false
    var bCreateJavaDir = false
    var bCreateScalaTestFile = false
    var bGoAhead = false

    if (args.length == 1) {
        projectName = args(0)
    }

    //print("This script creates an SBT project directory beneath the current directory.")

    while (!bGoAhead) {

        println("")
        projectName = getProjectName(projectName)

        val sCreateGitignoreFile = readLine("Create .gitignore File? (Y/n): ")
        bCreateGitignoreFile = isYes(sCreateGitignoreFile, true)

        val sCreateReadmeFile = readLine("Create README.md File? (Y/n): ")
        bCreateReadmeFile = isYes(sCreateReadmeFile, true)

        val sCreateResourcesDir = readLine("Create ‘resources’ subdirs? (y/N): ")
        bCreateResourcesDir = isYes(sCreateResourcesDir, false)

        val sCreateJavaDir = readLine("Create ‘java’ subdirs? (y/N): ")
        bCreateJavaDir = isYes(sCreateJavaDir, false)

        val summary = s"""
        |-----------------------------------------------
        |Directory/Project name:   ${projectName}
        |Create .gitignore file?:  ${booleanAsYOrN(bCreateGitignoreFile)}
        |Create README.md file?:   ${booleanAsYOrN(bCreateReadmeFile)}
        |Create ‘resources’ dirs?: ${booleanAsYOrN(bCreateResourcesDir)}
        |Create ‘java’ dirs?:      ${booleanAsYOrN(bCreateJavaDir)}
        |-----------------------------------------------
        |Create Project? (Y/n): """.stripMargin
        val sProceed = readLine(summary)

        bGoAhead = isYes(sProceed, true)
    }

    /*
     * Left the loop, time to create everything.
     * -----------------------------------------
     */

    // create the required dirs and build.sbt
    createDirs(projectName + SLASH + "src" + SLASH + "main" + SLASH + "scala")
    createDirs(projectName + SLASH + "src" + SLASH + "test" + SLASH + "scala")
    createDir(projectName + SLASH + "project")
    FileUtils.writeFile(
        projectName + SLASH + "build.sbt",
        BuildSbtData.buildDotSbtData(projectName)
    )

    // create other dirs as requested
    if (bCreateJavaDir) {
        createDirs(projectName + SLASH + "src" + SLASH + "main" + SLASH + "java")
        createDirs(projectName + SLASH + "src" + SLASH + "test" + SLASH + "java")
    }

    if (bCreateResourcesDir) {
        createDirs(projectName + SLASH + "src" + SLASH + "main" + SLASH + "resources")
        createDirs(projectName + SLASH + "src" + SLASH + "test" + SLASH + "resources")
    }

    // create .gitignore as requested
    if (bCreateGitignoreFile) {
        FileUtils.writeFile(
            projectName + SLASH + ".gitignore",
            GitignoreData.gitignoreString
        )
    }

    println("Project created.")


}








