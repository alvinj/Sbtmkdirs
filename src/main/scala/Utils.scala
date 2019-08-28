package sbtmkdirs

import java.io.File
import scala.io.StdIn.readLine

object Utils {

    /**
     * stay in a loop until you have a valid project name.
     * `defaultProjectName` may well be blank.
     */
    def getProjectName(defaultProjectName: String): String = {

        var tmpProjectName = defaultProjectName
        var count = 1
        val invalidProjectNameMsg = "Project Name can’t contain blanks."
        val projectNameCantBeBlankMsg = "Project Name can’t be blank."

        while (true) {

            promptForProjectName(tmpProjectName)
            tmpProjectName = readLine()

            // TODO this is some spaghetti code
            // only check this the first time through the loop
            if (count == 1 && defaultProjectName.trim != "") {
                // already had a project name
                if (isBlank(tmpProjectName)) {
                    // didn’t supply a project name, they want to accept the default
                    return defaultProjectName
                } else {
                    // already had a default project name but they gave you a new one
                    if (isValidProjectName(tmpProjectName)) {
                        // return the new name
                        return tmpProjectName
                    } else {
                        // they gave you a new name, but it’s not valid
                        System.err.println(invalidProjectNameMsg)
                        tmpProjectName = ""
                    }
                }
            } else {
                // did not already have a project name
                if (isBlank(tmpProjectName)) {
                    // this is bad, the default name and new name are both blank
                    System.err.println(projectNameCantBeBlankMsg)
                    tmpProjectName = ""
                } else {
                    if (isValidProjectName(tmpProjectName)) {
                        return tmpProjectName
                    } else {
                        // they gave you a new name, but it’s not valid
                        System.err.println(invalidProjectNameMsg)
                        tmpProjectName = ""
                    }
                }
            }

            count += 1

        }

        ""  //to make the compiler happy

    }

    def isValidProjectName(name: String) = {
        if (containsBlankSpaces(name)) false else true
    }

    def containsBlankSpaces(s: String): Boolean = s.contains(" ")

    def booleanAsYOrN(b: Boolean) = if (b) "y" else "n"

    def createDir(canonDirName: String): Boolean = {
        (new File(canonDirName)).mkdir
    }

    def createDirs(canonDirName: String): Boolean = {
        (new File(canonDirName)).mkdirs
    }

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