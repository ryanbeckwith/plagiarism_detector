/**
 * Print out all the files in a directory given as command-line argument.
 *
 * Author: Nicholas Zufelt
 * Date: 4/23/17
 * Course: AP Computer Science A
 */

import java.io.File;

public class ReadFolder {
    public static void main(String[] args) {
        /* Note: This is just sitting in `main` right now, so you'll
         * need to change it!  This is just example code.
         */
        File dir = new File(args[0]);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("Something's wrong with the directory reading.");
        }
    }
}
