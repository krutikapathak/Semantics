/**
 * 
 */
package edu.csulb.semantics.project2.model;

import java.io.IOException;

/**
 * @author krutikapathak
 *
 */
public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		model.addClasses();
		model.addDataAndObjectProperties();
		model.addInstances();
		try {
			model.writeModelToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
