/**
 * Copyright 2017 - Yves DELVAUX
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language governing permissions 
 * and limitations under the License.
 */
package fr.yvesdelvaux.imagetools.launcher;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.yvesdelvaux.imagetools.tools.FileTools;
import fr.yvesdelvaux.imagetools.tools.ImageTools;

/**
 * Main class
 * Launcher of the little program.
 * This program resize images from a scaled height.
 * @author Yves DELVAUX
 *
 */
public class ImageToolsLauncher {
	
	/**
	 * Logger {@link Logger}
	 */
	private static final Logger log = LogManager.getLogger(ImageToolsLauncher.class);
	
	/**
	 * File tools to manage I/O {@link File}
	 */
	private static final FileTools fileTools = new FileTools();
	
	/**
	 * Image tools to resize images {@link ImageTools}
	 */
	private static final ImageTools imgTools = new ImageTools();

	/**
	 * The main operation
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Get input data from the console
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Repertory path with images to resize : ");
		String repositoryPath = sc.nextLine();
		
		System.out.println("Pattern file name : ");
		String patternFile = sc.nextLine();
		
		System.out.println("Size height image (px) : ");
		String sizeHeight = sc.nextLine();
		
		sc.close();
		
		// Get all files in the repository
		List<File> files = fileTools.readDirectory(repositoryPath);
		
		// Resize program
		log.debug("Resize in process...");
		int i = 1;
		String fileName;
		for(File file : files){
			if(i >= 10)
				fileName = patternFile + i;
			else
				fileName = patternFile + "0" + i;
			
			// Call the method
			imgTools.resize(file,fileName, Integer.valueOf(sizeHeight));
			i++;
		}
		
		log.debug("Done !");
		log.debug("Your images are in the folder \"resized\" in the image repository.");

	}

}
