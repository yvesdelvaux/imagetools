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
package fr.yvesdelvaux.imagetools.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * File tools to manage I/O : get files in the directory and write all images.
 * 
 * @author Yves DELVAUX
 *
 */
public class FileTools {

	/**
	 * Logger {@link Logger}
	 */
	private static final Logger log = LogManager.getLogger(FileTools.class);

	/**
	 * Read directory witch contains images
	 * 
	 * @param directoryPath
	 *            {@link java.lang.String}
	 * @return {@link java.util.List} {@link java.io.File}
	 */
	public List<File> readDirectory(String directoryPath) {

		File repertoire = new File(directoryPath);

		// check if repertory is a directory
		if (!repertoire.isDirectory()) {
			log.error("The input path is a directory, not a file !");
			log.error("path : " + repertoire.getAbsolutePath());
			return null; // TODO: Do an exception here
		}

		// initialize result
		List<File> result = new ArrayList<>();

		// Get all files
		File[] tabFile = repertoire.listFiles();

		// Add all file without directories
		for (File file : tabFile) {
			if (!file.isDirectory())
				result.add(file);
		}

		return result;
	}

	/**
	 * Write image in the output directory.
	 * 
	 * @param outputImagePath
	 *            {@link java.lang.String}
	 * @param outputImage
	 *            {@link java.awt.image.BufferedImage}
	 */
	public void writeImage(String outputImagePath, BufferedImage outputImage) {
		// extracts extension of output file
		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);
		// writes to output file
		try {
			// Create Output File
			File outputFile = new File(outputImagePath);
			// If directory output doesn't exist, we create it.
			if (!outputFile.getParentFile().exists()) {
				createDirectory(outputFile.getParentFile());
			}
			// Writing images
			ImageIO.write(outputImage, formatName, outputFile);
		} catch (IOException e) {
			log.error("Image writing error", e);
		}
	}

	/**
	 * Create directories if they are not exist
	 * 
	 * @param directory
	 *            {@link java.io.File}
	 */
	public void createDirectory(File directory) {
		directory.mkdirs();
	}

	/**
	 * Read Image from a file
	 * @param file {@link File}
	 * @return {@link BufferedImage}
	 */
	public BufferedImage readImage(File file) {
		try {
			return ImageIO.read(file);
		} catch (IOException ex) {
			log.error("Error reading image", ex);
		}
		return null;
	}

}
