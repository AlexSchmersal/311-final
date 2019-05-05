import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.jupiter.api.Test;

class Tester {
	
	String testSource1 = "ExampleClass1.txt";
	String testSource2 = testSource1;
	
	public CompilationUnit parse(String str) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		return cu;
	}
	
	public String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
		
		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		
		reader.close();
		
		return fileData.toString();
	}

	@Test
	/**
	 * Exact Matching
	 */
	void testExact(){
		try {
			Strategies strategy = new Strategies(parse(readFileToString(testSource1)),parse(readFileToString(testSource2)));
			assertTrue(strategy.exactMatch());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * Simplified Anchored Class Fingerprinting
	 */
	void testACF() {
		try {
			Strategies strategy = new Strategies(parse(readFileToString(testSource1)),parse(readFileToString(testSource2)));
			assertTrue(strategy.simplifiedACF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Simplified Class Similarity
	 */
	void testSCS() {
		try {
			Strategies strategy = new Strategies(parse(readFileToString(testSource1)),parse(readFileToString(testSource2)));
			assertTrue(strategy.simplifiedSimilarity());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
