/*

 This file is part of XleTView 
 Copyright (C) 2003 Martin Sved�n
 
 This is free software, and you are 
 welcome to redistribute it under 
 certain conditions;

 See LICENSE document for details.

*/


package org.davic.mpeg.sections;

import org.davic.mpeg.sections.SectionFilterEvent;

/**
 * 
 * 
 * @author Martin Sveden
 * @statuscode 4
 */
public class EndOfFilteringEvent extends SectionFilterEvent{
 
	public EndOfFilteringEvent(SectionFilter sectionFilter, Object appData){
		super(sectionFilter, appData);
	}
	
	public Object getSource()	{
		return super.getSource();
	}
}
