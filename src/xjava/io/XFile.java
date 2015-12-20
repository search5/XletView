/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package xjava.io;

import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import net.beiker.xletview.util.Debug;

/**
 * Represents a file or directory in the filesystem.
 * @author Martin Sveden 
 */
public class XFile extends java.io.File{
	/*
	 * The reason why this class is not called File is because there are some classes that
	 * uses both java.io.File and this one and if both were called File it might create 
	 * unnecessary confusion. 
	 */
	
	
	private static final String NULL_STRING = null;
	
	private String name;
	
    private String path;
    
    private String absolutePath;
    
    private String parentPath;
        
    // constructors -->
    
    /**
     * Overrides constructor in java.io.File
     */
    public XFile(java.io.File parent, String child){
    	super(parent, child);
    	init(parent, child);    	
    }

    /**
     * Overrides constructor in java.io.File
     */
    public XFile(String parent, String child){
    	super("", "");
    	init(parent, child);
    }
    
    /**
     * Overrides constructor in java.io.File
     */
    public XFile(String path){        
        super(path);
        init(NULL_STRING, path);
    }
    
    /**
     * Overrides constructor in java.io.File
     */
    public XFile(URI uri){
    	super("");
    	// TODO fix
    }
    
    // constructors end //
    
    private void init(java.io.File parent, String path){
    	if(parent != null){
    		init(parent.getPath(), path);
    	}
    	else{
    		init(NULL_STRING, path);
    	}
    }
    
    private void init(String parent, String path){
    	Debug.write(this,"parent=" + parent + " path=" + path);
    	if(parent != null){
    		this.path = FileSystem.fixPath(parent + separatorChar + path);    		
    	}
    	else{
    		this.path = FileSystem.fixPath(path);	
    	}
    }
 
    // java.io.File stuff -->
    
    /**
     * The path is the same path that was used creating the file but
     * with the slashes fixed in a platform dependent way.
     */
    public String getPath(){    	    	
    	return this.path;
    }
     
    public String getAbsolutePath(){
//    	logger.debug("getAbsolutePath");
        return FileSystem.resolveAbsolutePath(getParent(), getName()); 
    }
    
    public boolean exists() {    	   	
    	return FileSystem.exists(this);
    	
    }

    /**
     * Returns the name of the file or directory denoted by this abstract
     * pathname.  This is just the last name in the pathname's name
     * sequence.  If the pathname's name sequence is empty, then the empty
     * string is returned.
     *
     * @return  The name of the file or directory denoted by this abstract
     *          pathname, or the empty string if this pathname's name sequence
     *          is empty
     */
    public String getName() {
    	int index = this.path.lastIndexOf(FileSystem.separatorChar);
    	if (index < 0) return this.path;
    	return this.path.substring(index + 1);
    }

	public String getParent() {
		String result = null;
		Debug.write(this,"getParent(), start");		

		int index = this.path.lastIndexOf(FileSystem.separatorChar);
		Debug.write(this,"index=" + index + ", path=" + this.path + " separatorChar = " + FileSystem.separatorChar);
		if (index > 1) {
			result = this.path.substring(0, index);
		}

//		if(parentPath == null){
//			
//			result = FileSystem.resolveParent(path);
//
//		}
//		else{
//			
//			result = parentPath;
//			
//		}
		Debug.write(this,"getParent(), result=" + result);		
		return result;
	}

	public java.io.File getParentFile() {		
		return null;
	}

	public boolean isAbsolute() {
		Debug.write(this,"isAbsolute");
		return false;
	}

	public java.io.File getAbsoluteFile() {		
		return new XFile(getAbsolutePath());
	}

	public String getCanonicalPath() throws IOException {
		return FileSystem.getCanonicalPath(this);
	}

	public java.io.File getCanonicalFile() throws IOException {
		return FileSystem.getCanonicalFile(this);
	}

	public URL toURL() throws MalformedURLException {
		return FileSystem.toURL(this);
	}

	public URI toURI() {
		return FileSystem.toURI(this);
	}

	public boolean canRead() {
		return FileSystem.canRead(this);
	}

	public boolean canWrite() {
		return FileSystem.canWrite(this);
	}

	public boolean isDirectory() {
		return FileSystem.isDirectory(this);
	}

	public boolean isFile() {
		return FileSystem.isFile(this);
	}

	public boolean isHidden() {	
		return FileSystem.isHidden(this);
	}

	public long lastModified() {
		return FileSystem.lastModified(this);
	}

	public long length() {
		return FileSystem.getLength(this);
	}

	public boolean createNewFile() throws IOException {
		Debug.write(this,"createNewFile");
		return false;
	}

	public boolean delete() {
		Debug.write(this,"delete");
		return false;
	}

	public void deleteOnExit() {
		Debug.write(this,"deleteOnExit");
	}

	public String[] list() {
		return FileSystem.list(this);
	}

	public String[] list(FilenameFilter filter) {
		Debug.write(this,"list");
		return null;
	}

	public java.io.File[] listFiles() {
		Debug.write(this,"listFiles");
		return null;
	}

	public java.io.File[] listFiles(FilenameFilter filter) {
		Debug.write(this,"listFiles");
		return null;
	}

	public java.io.File[] listFiles(FileFilter filter) {
		Debug.write(this,"listFiles");
		return null;
	}

	public boolean mkdir() {
		Debug.write(this,"mkdir");
		return false;
	}

	public boolean mkdirs() {
		Debug.write(this,"mkdirs");
		return false;
	}

	public boolean renameTo(java.io.File dest) {
		Debug.write(this,"renameTo");
		return false;
	}

	public boolean setLastModified(long time) {
		Debug.write(this,"setLastModified");
		return false;
	}

	public boolean setReadOnly() {
		Debug.write(this,"setReadOnly");
		return false;
	}

	public static java.io.File[] listRoots() {		
		return FileSystem.listRoots();
	}

	public static java.io.File createTempFile(String prefix, String suffix, java.io.File directory) throws IOException {
		Debug.info("createTempFile");
		return null;
	}

	public static java.io.File createTempFile(String prefix, String suffix) throws IOException {
		Debug.info("createTempFile");
		return null;
	}


	public boolean equals(Object obj) {
		Debug.write(this,"equals");
		return false;
	}

	public int hashCode() {
		Debug.write(this,"hashCode");
		return 0;
	}

	public String toString() {
		Debug.write(this,"toString");
		return null;
	}
    


}
