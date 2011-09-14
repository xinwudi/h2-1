package org.h2.store.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.h2.util.IOUtils;

/**
 * This utility class contains utility functions that use the file system abstraction.
 */
public class FileUtils {

    /**
     * Checks if a file exists.
     * This method is similar to Java 7 <code>java.nio.file.Path.exists</code>.
     *
     * @param fileName the file name
     * @return true if it exists
     */
    public static boolean exists(String fileName) {
        return getFileSystem(fileName).exists(fileName);
    }

    /**
     * Create a directory (all required parent directories must already exist).
     * This method is similar to Java 7 <code>java.nio.file.Path.createDirectory</code>.
     *
     * @param directoryName the directory name
     */
    public static void createDirectory(String directoryName) {
        getFileSystem(directoryName).createDirectory(directoryName);
    }

    /**
     * Create a new file.
     * This method is similar to Java 7 <code>java.nio.file.Path.createFile</code>, but returns
     * false instead of throwing a exception if the file already existed.
     *
     * @param fileName the file name
     * @return true if creating was successful
     */
    public static boolean createFile(String fileName) {
        return getFileSystem(fileName).createFile(fileName);
    }

    /**
     * Delete a file or directory if it exists.
     * Directories may only be deleted if they are empty.
     * This method is similar to Java 7 <code>java.nio.file.Path.deleteIfExists</code>.
     *
     * @param path the file or directory name
     */
    public static void delete(String path) {
        getFileSystem(path).delete(path);
    }

    /**
     * Normalize a file name.
     * This method is similar to Java 7 <code>java.nio.file.Path.toRealPath</code>.
     *
     * @param fileName the file name
     * @return the normalized file name
     */
    public static String getCanonicalPath(String fileName) {
        return getFileSystem(fileName).getCanonicalPath(fileName);
    }

    /**
     * Get the parent directory of a file or directory.
     * This method is similar to Java 7 <code>java.nio.file.Path.getParent</code>.
     *
     * @param fileName the file or directory name
     * @return the parent directory name
     */
    public static String getParent(String fileName) {
        return getFileSystem(fileName).getParent(fileName);
    }

    /**
     * Check if the file name includes a path.
     * This method is similar to Java 7 <code>java.nio.file.Path.isAbsolute</code>.
     *
     * @param fileName the file name
     * @return if the file name is absolute
     */
    public static boolean isAbsolute(String fileName) {
        return getFileSystem(fileName).isAbsolute(fileName);
    }

    /**
     * Rename a file if this is allowed.
     * This method is similar to Java 7 <code>java.nio.file.Path.moveTo</code>.
     *
     * @param oldName the old fully qualified file name
     * @param newName the new fully qualified file name
     */
    public static void moveTo(String oldName, String newName) {
        getFileSystem(oldName).moveTo(oldName, newName);
    }

    /**
     * Get the file or directory name (the last element of the path).
     * This method is similar to Java 7 <code>java.nio.file.Path.getName</code>.
     *
     * @param path the directory and file name
     * @return just the file name
     */
    public static String getName(String path) {
        return getFileSystem(path).getName(path);
    }

    /**
     * List the files in the given directory.
     * This method is similar to Java 7 <code>java.nio.file.Path.newDirectoryStream</code>.
     *
     * @param path the directory
     * @return the list of fully qualified file names
     */
    public static String[] listFiles(String path) {
        return getFileSystem(path).listFiles(path);
    }

    /**
     * Get the last modified date of a file.
     * This method is similar to Java 7
     * <code>java.nio.file.attribute.Attributes.readBasicFileAttributes(file).lastModified().toMillis()</code>
     *
     * @param fileName the file name
     * @return the last modified date
     */
    public static long lastModified(String fileName) {
        return getFileSystem(fileName).lastModified(fileName);
    }

    /**
     * Get the size of a file in bytes
     * This method is similar to Java 7
     * <code>java.nio.file.attribute.Attributes.readBasicFileAttributes(file).size()</code>
     *
     * @param fileName the file name
     * @return the size in bytes
     */
    public static long size(String fileName) {
        return getFileSystem(fileName).size(fileName);
    }

    /**
     * Check if it is a file or a directory.
     * <code>java.nio.file.attribute.Attributes.readBasicFileAttributes(file).isDirectory()</code>
     *
     * @param fileName the file or directory name
     * @return true if it is a directory
     */
    public static boolean isDirectory(String fileName) {
        return getFileSystem(fileName).isDirectory(fileName);
    }

    /**
     * Open a random access file object.
     * This method is similar to Java 7 <code>java.nio.channels.FileChannel.open</code>.
     *
     * @param fileName the file name
     * @param mode the access mode. Supported are r, rw, rws, rwd
     * @return the file object
     */
    public static FileObject openFileObject(String fileName, String mode) throws IOException {
        return getFileSystem(fileName).openFileObject(fileName, mode);
    }

    /**
     * Create an input stream to read from the file.
     * This method is similar to Java 7 <code>java.nio.file.Path.newInputStream</code>.
     *
     * @param fileName the file name
     * @return the input stream
     */
    public static InputStream newInputStream(String fileName) throws IOException {
        return getFileSystem(fileName).newInputStream(fileName);
    }

    /**
     * Create an output stream to write into the file.
     * This method is similar to Java 7 <code>java.nio.file.Path.newOutputStream</code>.
     *
     * @param fileName the file name
     * @param append if true, the file will grow, if false, the file will be
     *            truncated first
     * @return the output stream
     */
    public static OutputStream newOutputStream(String fileName, boolean append) {
        return getFileSystem(fileName).newOutputStream(fileName, append);
    }

    // special methods =======================================

    /**
     * Check if the file is writable.
     *
     * @param fileName the file name
     * @return if the file is writable
     */
    public static boolean canWrite(String fileName) {
        return getFileSystem(fileName).canWrite(fileName);
    }

    /**
     * Check if a file is read-only.
     * This method is similar to Java 7
     * <code>java.nio.file.Path.checkAccess(AccessMode.WRITE)</code>
     *
     * @param fileName the file name
     * @return if it is read only
     */
    public static boolean isReadOnly(String fileName) {
        return getFileSystem(fileName).isReadOnly(fileName);
    }

    /**
     * Disable the ability to write.
     *
     * @param fileName the file name
     * @return true if the call was successful
     */
    public static boolean setReadOnly(String fileName) {
        return getFileSystem(fileName).setReadOnly(fileName);
    }

    /**
     * Get the unwrapped file name (without wrapper prefixes if wrapping /
     * delegating file systems are used).
     *
     * @param fileName the file name
     * @return the unwrapped
     */
    public static String unwrap(String fileName) {
        return getFileSystem(fileName).unwrap(fileName);
    }

    /**
     * Check if a file starts with a given prefix.
     *
     * @param fileName the complete file name
     * @param prefix the prefix
     * @return true if it starts with the prefix
     */
    public static boolean fileStartsWith(String fileName, String prefix) {
        return getFileSystem(fileName).fileStartsWith(fileName, prefix);
    }

    /**
     * Create a new temporary file.
     *
     * @param prefix the prefix of the file name (including directory name if
     *            required)
     * @param suffix the suffix
     * @param deleteOnExit if the file should be deleted when the virtual
     *            machine exists
     * @param inTempDir if the file should be stored in the temporary directory
     * @return the name of the created file
     */
    public static String createTempFile(String prefix, String suffix, boolean deleteOnExit, boolean inTempDir)
            throws IOException {
        return getFileSystem(prefix).createTempFile(prefix, suffix, deleteOnExit, inTempDir);
    }

    // utility methods =======================================

    /**
     * Delete a directory or file and all subdirectories and files.
     *
     * @param path the path
     * @param tryOnly whether errors should  be ignored
     */
    public static void deleteRecursive(String path, boolean tryOnly) {
        if (exists(path)) {
            if (FileUtils.isDirectory(path)) {
                for (String s : FileUtils.listFiles(path)) {
                    deleteRecursive(s, tryOnly);
                }
            }
            if (tryOnly) {
                FileUtils.tryDelete(path);
            } else {
                delete(path);
            }
        }
    }

    /**
     * Create the directory and all required parent directories.
     *
     * @param dir the directory name
     */
    public static void createDirectories(String dir) {
        if (dir != null && !FileUtils.exists(dir)) {
            String parent = FileUtils.getParent(dir);
            createDirectories(parent);
            createDirectory(dir);
        }
    }

    /**
     * Copy a file from one directory to another, or to another file.
     *
     * @param original the original file name
     * @param copy the file name of the copy
     */
    public static void copy(String original, String copy) throws IOException {
        InputStream in = FileUtils.newInputStream(original);
        OutputStream out = FileUtils.newOutputStream(copy, false);
        IOUtils.copyAndClose(in, out);
    }

    private static FileSystem getFileSystem(String fileName) {
        return FileSystem.getInstance(fileName);
    }

    /**
     * Try to delete a file (ignore errors).
     *
     * @param fileName the file name
     * @return true if it worked
     */
    public static boolean tryDelete(String fileName) {
        try {
            getFileSystem(fileName).delete(fileName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}