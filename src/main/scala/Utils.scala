package sbtmkdirs

import java.io.File
import scala.io.StdIn.readLine

object Utils {

    /**
     * stay in a loop until you have a valid project name.
     * `defaultProjectName` may well be blank.
     */
    def getProjectName(defaultProjectName: String): String = {

        var lastProjectName = defaultProjectName
        var newProjectName = ""
        val invalidProjectNameMsg = "Invalid project name (can’t contain blank spaces).\n"
        val projectNameCantBeBlankMsg = "Project Name can’t be blank.\n"

        while (true) {

            promptForProjectName(lastProjectName)
            newProjectName = readLine()

            // two situations: gave us a blank name, or some text
            if (isBlank(newProjectName)) {
                if (isValidProjectName(lastProjectName)) {
                    // didn’t supply a new project name, they want to accept the default
                    return lastProjectName
                } else {
                    // newProjectName is blank and lastProjectName is invalid
                    System.err.println(projectNameCantBeBlankMsg)
                    lastProjectName = ""
                }
            } else {
                // gave us a new newProjectName
                if (isValidProjectName(newProjectName)) {
                    return newProjectName  //name is valid, return it
                } else {
                    // they gave you a new name, but it’s not valid
                    System.err.println(invalidProjectNameMsg)
                    lastProjectName = ""
                }
            }

        }

        ""  //to make the compiler happy

    }

    def isValidProjectName(name: String): Boolean = {
        if (name.trim == "") {
            false
        }
        else if (containsWhitespace(name)) false else true
    }

    def containsWhitespace(s: String): Boolean = s.matches(".*\\s.*")

    def booleanAsYOrN(b: Boolean) = if (b) "y" else "n"

    def createDir(canonDirName: String): Boolean = (new File(canonDirName)).mkdir

    def createDirs(canonDirName: String): Boolean = (new File(canonDirName)).mkdirs

    def isYes(userInput: String, default: Boolean): Boolean =
        if (userInput.trim.equalsIgnoreCase("Y") || userInput.trim.equalsIgnoreCase("yes")) {
            true
        }
        else if (userInput.trim.equalsIgnoreCase("N") || userInput.trim.equalsIgnoreCase("no")) {
            false
        }
        else if (isBlank(userInput)) {
            default
        } else {
            // note that the user can type in "foo" or anything else
            default
        }

    def isBlank(s: String): Boolean = s.trim == ""

    def promptForProjectName(currentProjectName: String): Unit = {
        if (currentProjectName == "") {
            print(s"Directory/Project Name: ")
        } else {
            print(s"Directory/Project Name ($currentProjectName): ")
        }
    }

}