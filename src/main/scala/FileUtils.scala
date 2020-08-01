package sbtmkdirs

import java.io._

final object FileUtils {

    val SLASH = System.getProperty("file.separator")

    /**
     * write a `Seq[String]` to the `filename`.
     */
    def writeFile(filename: String, lines: Seq[String]): Unit = {
        val file = new File(filename)
        val bw = new BufferedWriter(new FileWriter(file))
        for (line <- lines) {
            bw.write(line)
        }
        bw.close()
    }
    
    /**
     * write a `String` to the `filename`.
     */
    def writeFile(filename: String, s: String): Unit = {
        val file = new File(filename)
        val bw = new BufferedWriter(new FileWriter(file))
        bw.write(s)
        bw.close()
    }

}