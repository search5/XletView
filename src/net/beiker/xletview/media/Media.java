/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sveden
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/

package net.beiker.xletview.media;

/**
 * Media that can be "played" as video, like a video file or an image
 * @author Martin Sveden
 */
public class Media {

    public static final int TYPE_VIDEO = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_INVALID = 2;
        
    private String path;
    private int type;
    
    private String[] validImageTypes = {".jpg"};
    private String[] validVideoTypes = {".avi", ".mov"};
    
    public Media(String path){
        this.path = path;        
        this.type = resolveType(path);
    }
    
    /**
     * Resolves if the media type is video or image
     */
    private int resolveType(String path){
        String s = path.toLowerCase();
//        if (s.indexOf(filters[i]) == s.length() - filters[i].length()) {
        for(int i = 0; i < validImageTypes.length; i++){
            if (s.indexOf(validImageTypes[i]) == s.length() - validImageTypes[i].length()) {
                return TYPE_IMAGE;
            }
        }
        for(int i = 0; i < validVideoTypes.length; i++){
            if (s.indexOf(validVideoTypes[i]) == s.length() - validVideoTypes[i].length()) {
                return TYPE_VIDEO;
            }
        }
        return TYPE_INVALID;
    }
    
    public String getPath(){
        return path;
    }
    
    public int getType(){
        return type;        
    }
    
    public String toString(){
        return "[Media] type=" + type + ", path=" + path;
    }
}
