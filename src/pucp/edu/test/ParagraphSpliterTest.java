package pucp.edu.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.upc.freeling.Paragraph;
import edu.upc.freeling.Util;
import pucp.edu.cohmetrixesp.metrics.CohParagraph;
import pucp.edu.cohmetrixesp.metrics.ParagraphSplitter;

public class ParagraphSpliterTest {
	
	String small, medium;
	Properties prop;
	ParagraphSplitter p_splitter; 

	@Before
	public void load_test_files(){
		System.loadLibrary("freeling_javaAPI");
		Util.initLocale("default");
		prop = new Properties();
		try {
			small = new String(Files.readAllBytes(Paths.get("./testfiles/smallFile.txt")));
			medium = new String(Files.readAllBytes(Paths.get("./testfiles/mediumFile.txt")));
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println(prop.getProperty("tok_file"));
		//System.out.println(desc.number_of_words(small));
	}

	@Test
	public void paragraphs_splitting() {
		p_splitter = new ParagraphSplitter();
		for (CohParagraph s: p_splitter.split(medium))
			System.out.println(s.getText() + "/n");
		assertEquals("error - numero distinto de parrafos", 2, p_splitter.split(small).size());
		assertEquals("error - numero distinto de parrafos", 6, p_splitter.split(medium).size());
	}
	
}