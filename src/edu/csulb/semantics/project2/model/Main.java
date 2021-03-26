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
		System.out.println("\n---Building Ontology---");
		System.out.println("\n\n--Model creation in progress...");
		Model model = new Model();
		model.addClasses();
		model.addDataAndObjectProperties();
		model.addInstances();
		try {
			System.out.println("\n--Writing OWL file in progress...");
			model.writeModelToFile();
			System.out.println("\n\n---Crashes.owl file created!---");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
