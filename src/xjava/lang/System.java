/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/
package xjava.lang;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Replaces java.lang.System for the Xlet
 * @author Martin Sveden 
 */
public class System {

    /** Don't let anyone instantiate this class */
    private System() {
    }
    
    /*
     * Has to be public like in java.lang.System
     */
    public static Properties props;

    /**
     */
    public static InputStream in = null;

    /**
     */
    public static PrintStream out = null;

    /**
     */
    public static PrintStream err = null;

    static{
        initSystem();
    }

    /**
     * Initialize the system class. 
     */
    private static void initSystem() {
        props = new Properties();
        initProperties(props);
        setIn(java.lang.System.in);
        
        
        /*try {
            OutputRedirector or = new OutputRedirector(new OutputServer(9999, System.out));
            PrintStream ps = new PrintStream(or);
            //setOut(ps);
            //setErr(ps);
        }
        catch (IOException e) {
        
            e.printStackTrace();
        }*/
        
        setOut(java.lang.System.out);
        setErr(java.lang.System.err);

        /* 
         Uncomment to sent the Xlet's output to a file
        try {
            File logFile = new File("application_out.txt");
            logFile.createNewFile();
            Log log = new Log(new File("application_out.txt"));
            PrintStream out = new PrintStream(log);
            setOut(out);
            setErr(out);
        }
        catch (IOException e) {
          setOut(java.lang.System.out);
          setErr(java.lang.System.err);
            e.printStackTrace();
        }
        */



    }

    /**
     * Reassigns the "standard" input stream.
     */
    public static void setIn(InputStream in) {
        System.in = in;
    }

    /**
     * Reassigns the "standard" output stream.
     */
    public static void setOut(PrintStream out) {
        System.out = out;
    }

    /**
     * Reassigns the "standard" error output stream.
     */
    public static void setErr(PrintStream err) {
        System.err = err;
    }

    /**
     * Sets the System security.
     */
    public static void setSecurityManager(final SecurityManager s) {
        // fix
    }

    /**
     * Gets the system security interface.
     */
    public static SecurityManager getSecurityManager() {
        // fix
        return null;
    }

    /**
     * Returns the current time in milliseconds. 
     */
    public static long currentTimeMillis() {
        return java.lang.System.currentTimeMillis();
    }

    /** 
     * Copies an array
     */
    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        java.lang.System.arraycopy(src, srcPos, dest, destPos, length);
    }

    /**
     * 
     */
    public static int identityHashCode(Object x) {
        return java.lang.System.identityHashCode(x);
    }

    /**
     * System properties. The following properties are guaranteed to be defined:
     * <dl>
     * <dt>java.version     <dd>Java version number
     * <dt>java.vendor      <dd>Java vendor specific string
     * <dt>java.vendor.url  <dd>Java vendor URL
     * <dt>java.home        <dd>Java installation directory
     * <dt>java.class.version   <dd>Java class version number
     * <dt>java.class.path  <dd>Java classpath
     * <dt>os.name      <dd>Operating System Name
     * <dt>os.arch      <dd>Operating System Architecture
     * <dt>os.version       <dd>Operating System Version
     * <dt>file.separator   <dd>File separator ("/" on Unix)
     * <dt>path.separator   <dd>Path separator (":" on Unix)
     * <dt>line.separator   <dd>Line separator ("\n" on Unix)
     * <dt>user.name        <dd>User account name
     * <dt>user.home        <dd>User home directory
     * <dt>user.dir     <dd>User's current working directory
     * </dl>
     */


    private static Properties initProperties(Properties props) {
        Properties real = java.lang.System.getProperties();
        props.setProperty("java.version", real.getProperty("java.version"));
        props.setProperty("java.vendor", real.getProperty("java.vendor"));
        
        /*
         * this one is not guaranteed to exist but I put it
         * here because there was applications at a broadcaster that use
         * it.
         */  
        props.setProperty("java.vm.vendor", real.getProperty("java.vm.vendor"));
        
        props.setProperty("java.vendor.url", real.getProperty("java.vendor.url"));
        props.setProperty("java.home", real.getProperty("java.home"));
        props.setProperty("java.class.version", real.getProperty("java.class.version"));
        props.setProperty("java.class.path", real.getProperty("java.class.path"));
        props.setProperty("os.name", real.getProperty("os.name"));
        props.setProperty("os.arch", real.getProperty("os.arch"));
        props.setProperty("os.version", real.getProperty("os.version"));
        props.setProperty("file.separator", real.getProperty("file.separator"));
        props.setProperty("path.separator", real.getProperty("path.separator"));
        props.setProperty("line.separator", real.getProperty("line.separator"));
        props.setProperty("user.name", real.getProperty("user.name"));
        props.setProperty("user.home", real.getProperty("user.home"));
        props.setProperty("user.dir", real.getProperty("user.dir"));
        //props.list(java.lang.System.out);    
        return props;
    }

    /**
     * Determines the current system properties. 
     * <p>
     * First, if there is a security manager, its 
     * <code>checkPropertiesAccess</code> method is called with no 
     * arguments. This may result in a security exception. 
     * <p>
     * The current set of system properties for use by the 
     * {@link #getProperty(String)} method is returned as a 
     * <code>Properties</code> object. If there is no current set of 
     * system properties, a set of system properties is first created and 
     * initialized. This set of system properties always includes values 
     * for the following keys: 
     * <table>
     * <tr><th>Key</th>
     *     <th>Description of Associated Value</th></tr>
     * <tr><td><code>java.version</code></td>
     *     <td>Java Runtime Environment version</td></tr>
     * <tr><td><code>java.vendor</code></td>
     *     <td>Java Runtime Environment vendor</td></tr
     * <tr><td><code>java.vendor.url</code></td>
     *     <td>Java vendor URL</td></tr>
     * <tr><td><code>java.home</code></td>
     *     <td>Java installation directory</td></tr>
     * <tr><td><code>java.vm.specification.version</code></td>
     *     <td>Java Virtual Machine specification version</td></tr>
     * <tr><td><code>java.vm.specification.vendor</code></td>
     *     <td>Java Virtual Machine specification vendor</td></tr>
     * <tr><td><code>java.vm.specification.name</code></td>
     *     <td>Java Virtual Machine specification name</td></tr>
     * <tr><td><code>java.vm.version</code></td>
     *     <td>Java Virtual Machine implementation version</td></tr>
     * <tr><td><code>java.vm.vendor</code></td>
     *     <td>Java Virtual Machine implementation vendor</td></tr>
     * <tr><td><code>java.vm.name</code></td>
     *     <td>Java Virtual Machine implementation name</td></tr>
     * <tr><td><code>java.specification.version</code></td>
     *     <td>Java Runtime Environment specification  version</td></tr>
     * <tr><td><code>java.specification.vendor</code></td>
     *     <td>Java Runtime Environment specification  vendor</td></tr>
     * <tr><td><code>java.specification.name</code></td>
     *     <td>Java Runtime Environment specification  name</td></tr>
     * <tr><td><code>java.class.version</code></td>
     *     <td>Java class format version number</td></tr>
     * <tr><td><code>java.class.path</code></td>
     *     <td>Java class path</td></tr>
     * <tr><td><code>java.library.path</code></td>
     *     <td>List of paths to search when loading libraries</td></tr>
     * <tr><td><code>java.io.tmpdir</code></td>
     *     <td>Default temp file path</td></tr>
     * <tr><td><code>java.compiler</code></td>
     *     <td>Name of JIT compiler to use</td></tr>
     * <tr><td><code>java.ext.dirs</code></td>
     *     <td>Path of extension directory or directories</td></tr>
     * <tr><td><code>os.name</code></td>
     *     <td>Operating system name</td></tr>
     * <tr><td><code>os.arch</code></td>
     *     <td>Operating system architecture</td></tr>
     * <tr><td><code>os.version</code></td>
     *     <td>Operating system version</td></tr>
     * <tr><td><code>file.separator</code></td>
     *     <td>File separator ("/" on UNIX)</td></tr>
     * <tr><td><code>path.separator</code></td>
     *     <td>Path separator (":" on UNIX)</td></tr>
     * <tr><td><code>line.separator</code></td>
     *     <td>Line separator ("\n" on UNIX)</td></tr>
     * <tr><td><code>user.name</code></td>
     *     <td>User's account name</td></tr>
     * <tr><td><code>user.home</code></td>
     *     <td>User's home directory</td></tr>
     * <tr><td><code>user.dir</code></td>
     *     <td>User's current working directory</td></tr>
     * </table>
     * <p>
     * Multiple paths in a system property value are separated by the path
     * separator character of the platform.
     * <p>
     * Note that even if the security manager does not permit the 
     * <code>getProperties</code> operation, it may choose to permit the 
     * {@link #getProperty(String)} operation.
     *
     * @return     the system properties
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkPropertiesAccess</code> method doesn't allow access 
     *              to the system properties.
     * @see        #setProperties
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertiesAccess()
     * @see        java.util.Properties
     */
    public static Properties getProperties() {
        return props;
    }

    /**
     * Sets the system properties to the <code>Properties</code> 
     * argument. 
     * <p>
     * First, if there is a security manager, its 
     * <code>checkPropertiesAccess</code> method is called with no 
     * arguments. This may result in a security exception. 
     * <p>
     * The argument becomes the current set of system properties for use 
     * by the {@link #getProperty(String)} method. If the argument is 
     * <code>null</code>, then the current set of system properties is 
     * forgotten. 
     *
     * @param      props   the new system properties.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkPropertiesAccess</code> method doesn't allow access 
     *              to the system properties.
     * @see        #getProperties
     * @see        java.util.Properties
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertiesAccess()
     */
    public static void setProperties(Properties props) {
        if (props == null) {
            props = new Properties();
            initProperties(props);
        }
        System.props = props;
    }

    /**
     * Gets the system property indicated by the specified key. 
     * <p>
     * First, if there is a security manager, its 
     * <code>checkPropertyAccess</code> method is called with the key as 
     * its argument. This may result in a SecurityException. 
     * <p>
     * If there is no current set of system properties, a set of system 
     * properties is first created and initialized in the same manner as 
     * for the <code>getProperties</code> method. 
     *
     * @param      key   the name of the system property.
     * @return     the string value of the system property,
     *             or <code>null</code> if there is no property with that key.
     *
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkPropertyAccess</code> method doesn't allow
     *              access to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        java.lang.SecurityException
     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)
     * @see        java.lang.System#getProperties()
     */
    public static String getProperty(String key) {
        if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key can't be empty");
        }
        return props.getProperty(key);
    }

    /**
     * Gets the system property indicated by the specified key. 
     * <p>
     * First, if there is a security manager, its 
     * <code>checkPropertyAccess</code> method is called with the 
     * <code>key</code> as its argument. 
     * <p>
     * If there is no current set of system properties, a set of system 
     * properties is first created and initialized in the same manner as 
     * for the <code>getProperties</code> method. 
     *
     * @param      key   the name of the system property.
     * @param      def   a default value.
     * @return     the string value of the system property,
     *             or the default value if there is no property with that key.
     *
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkPropertyAccess</code> method doesn't allow
     *             access to the specified system property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #setProperty
     * @see        java.lang.SecurityManager#checkPropertyAccess(java.lang.String)
     * @see        java.lang.System#getProperties()
     */
    public static String getProperty(String key, String def) {
        if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key can't be empty");
        }
        return props.getProperty(key, def);
    }

    /**
     * Sets the system property indicated by the specified key. 
     * <p>
     * First, if a security manager exists, its 
     * <code>SecurityManager.checkPermission</code> method
     * is called with a <code>PropertyPermission(key, "write")</code>
     * permission. This may result in a SecurityException being thrown.
     * If no exception is thrown, the specified property is set to the given
     * value.
     * <p>
     *
     * @param      key   the name of the system property.
     * @param      value the value of the system property.
     * @return     the previous value of the system property,
     *             or <code>null</code> if it did not have one.
     *
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkPermission</code> method doesn't allow 
     *             setting of the specified property.
     * @exception  NullPointerException if <code>key</code> is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     * @see        #getProperty
     * @see        java.lang.System#getProperty(java.lang.String)
     * @see        java.lang.System#getProperty(java.lang.String, java.lang.String)
     * @see        java.util.PropertyPermission
     * @see        SecurityManager#checkPermission
     * @since      1.2
     */
    public static String setProperty(String key, String value) {
        if (key == null) {
            throw new NullPointerException("key can't be null");
        }
        if (key.equals("")) {
            throw new IllegalArgumentException("key can't be empty");
        }
        return (String) props.setProperty(key, value);
    }

    /**
     * Gets an environment variable. An environment variable is a
     * system-dependent external variable that has a string value.
     *
     * @deprecated The preferred way to extract system-dependent information
     *             is the system properties of the
     *             <code>java.lang.System.getProperty</code> methods and the
     *             corresponding <code>get</code><em>TypeName</em> methods of
     *             the <code>Boolean</code>, <code>Integer</code>, and
     *             <code>Long</code> primitive types.  For example:
     * <blockquote><pre>
     *     String classPath = System.getProperty("java.class.path",".");
     * <br>
     *     if (Boolean.getBoolean("myapp.exper.mode"))
     *         enableExpertCommands();
     * </pre></blockquote>
     * 
     * @param  name of the environment variable
     * @return the value of the variable, or <code>null</code> if the variable
     *           is not defined.
     * @see    java.lang.Boolean#getBoolean(java.lang.String)
     * @see    java.lang.Integer#getInteger(java.lang.String)
     * @see    java.lang.Integer#getInteger(java.lang.String, int)
     * @see    java.lang.Integer#getInteger(java.lang.String, java.lang.Integer)
     * @see    java.lang.Long#getLong(java.lang.String)
     * @see    java.lang.Long#getLong(java.lang.String, long)
     * @see    java.lang.Long#getLong(java.lang.String, java.lang.Long)
     * @see    java.lang.System#getProperties()
     * @see    java.lang.System#getProperty(java.lang.String)
     * @see    java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static String getenv(String name) {
        throw new Error("getenv no longer supported, use properties and -D instead: " + name);
    }

    /**
     * Terminates the currently running Java Virtual Machine. The 
     * argument serves as a status code; by convention, a nonzero status 
     * code indicates abnormal termination. 
     * <p>
     * This method calls the <code>exit</code> method in class 
     * <code>Runtime</code>. This method never returns normally. 
     * <p>
     * The call <code>System.exit(n)</code> is effectively equivalent to 
     * the call:
     * <blockquote><pre>
     * Runtime.getRuntime().exit(n)
     * </pre></blockquote>
     *
     * @param      status   exit status.
     * @throws  SecurityException
     *        if a security manager exists and its <code>checkExit</code> 
     *        method doesn't allow exit with the specified status.
     * @see        java.lang.Runtime#exit(int)
     */
    public static void exit(int status) {
        // Don't !!
        //Runtime.getRuntime().exit(status);
    }

    /**
     * Runs the garbage collector.
     * <p>
     * Calling the <code>gc</code> method suggests that the Java Virtual 
     * Machine expend effort toward recycling unused objects in order to 
     * make the memory they currently occupy available for quick reuse. 
     * When control returns from the method call, the Java Virtual 
     * Machine has made a best effort to reclaim space from all discarded 
     * objects.
     * <p>
     * The call <code>System.gc()</code> is effectively equivalent to the 
     * call:
     * <blockquote><pre>
     * Runtime.getRuntime().gc()
     * </pre></blockquote>
     *
     * @see     java.lang.Runtime#gc()
     */
    public static void gc() {
        // check the MHP spec
        Runtime.getRuntime().gc();
    }

    /**
     * Runs the finalization methods of any objects pending finalization.
     * <p>
     * Calling this method suggests that the Java Virtual Machine expend 
     * effort toward running the <code>finalize</code> methods of objects 
     * that have been found to be discarded but whose <code>finalize</code> 
     * methods have not yet been run. When control returns from the 
     * method call, the Java Virtual Machine has made a best effort to 
     * complete all outstanding finalizations. 
     * <p>
     * The call <code>System.runFinalization()</code> is effectively 
     * equivalent to the call:
     * <blockquote><pre>
     * Runtime.getRuntime().runFinalization()
     * </pre></blockquote>
     *
     * @see     java.lang.Runtime#runFinalization()
     */
    public static void runFinalization() {
        // check the MHP spec
        //Runtime.getRuntime().runFinalization();
    }

    /**
     * Enable or disable finalization on exit; doing so specifies that the
     * finalizers of all objects that have finalizers that have not yet been
     * automatically invoked are to be run before the Java runtime exits.
     * By default, finalization on exit is disabled.
     * 
     * <p>If there is a security manager, 
     * its <code>checkExit</code> method is first called
     * with 0 as its argument to ensure the exit is allowed. 
     * This could result in a SecurityException.
     *
     * @deprecated  This method is inherently unsafe.  It may result in
     *      finalizers being called on live objects while other threads are
     *      concurrently manipulating those objects, resulting in erratic
     *      behavior or deadlock.
     * @param value indicating enabling or disabling of finalization
     * @throws  SecurityException
     *        if a security manager exists and its <code>checkExit</code> 
     *        method doesn't allow the exit.
     *
     * @see     java.lang.Runtime#exit(int)
     * @see     java.lang.Runtime#gc()
     * @see     java.lang.SecurityManager#checkExit(int)
     * @since   JDK1.1
     */
    public static void runFinalizersOnExit(boolean value) {
        //Runtime.getRuntime().runFinalizersOnExit(value);
    }

    /**
     * Loads a code file with the specified filename from the local file 
     * system as a dynamic library. The filename 
     * argument must be a complete path name. 
     * <p>
     * The call <code>System.load(name)</code> is effectively equivalent 
     * to the call:
     * <blockquote><pre>
     * Runtime.getRuntime().load(name)
     * </pre></blockquote>
     *
     * @param      filename   the file to load.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkLink</code> method doesn't allow 
     *             loading of the specified dynamic library
     * @exception  UnsatisfiedLinkError  if the file does not exist.
     * @see        java.lang.Runtime#load(java.lang.String)
     * @see        java.lang.SecurityManager#checkLink(java.lang.String)
     */
    public static void load(String filename) {
        //Runtime.getRuntime().load0(getCallerClass(), filename);
    }

    /**
     * Loads the system library specified by the <code>libname</code> 
     * argument. The manner in which a library name is mapped to the 
     * actual system library is system dependent.
     * <p>
     * The call <code>System.loadLibrary(name)</code> is effectively 
     * equivalent to the call
     * <blockquote><pre>
     * Runtime.getRuntime().loadLibrary(name)
     * </pre></blockquote>
     *
     * @param      libname   the name of the library.
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkLink</code> method doesn't allow 
     *             loading of the specified dynamic library
     * @exception  UnsatisfiedLinkError  if the library does not exist.
     * @see        java.lang.Runtime#loadLibrary(java.lang.String)
     * @see        java.lang.SecurityManager#checkLink(java.lang.String)
     */
    public static void loadLibrary(String libname) {
        //Runtime.getRuntime().loadLibrary0(getCallerClass(), libname);
    }

    /**
     * Maps a library name into a platform-specific string representing
     * a native library.
     *
     * @param      libname the name of the library.
     * @return     a platform-dependent native library name.
     * @see        java.lang.System#loadLibrary(java.lang.String)
     * @see        java.lang.ClassLoader#findLibrary(java.lang.String)
     * @since      1.2
     */
    public static String mapLibraryName(String libname) {
        // check MHP spec
        return java.lang.System.mapLibraryName(libname);
    }

}
