package net.beiker.xletview.classloader;

import javassist.*;

/**
 *
 * @author Martin Sveden
 */
public class XletCodeConverter extends CodeConverter{

	public XletCodeConverter(){
		try{
			ClassPool tempPool = ClassPool.getDefault();
			CtClass font = tempPool.get("java.awt.Font");                
			CtClass singleton = tempPool.get("xjava.awt.Font");		
			replaceNew(font, singleton, "create");
		}
		catch(NotFoundException e){
			e.printStackTrace();
		}
		
	}

}
