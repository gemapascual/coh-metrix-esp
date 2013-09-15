package pucp.edu.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import pucp.edu.cohmetrixesp.metrics.DescriptiveAnalyzer;
import pucp.edu.cohmetrixesp.structs.CohStats;
import pucp.edu.cohmetrixesp.structs.CohText;
import pucp.edu.cohmetrixesp.utils.IFreelingAnalyzer;
import pucp.edu.cohmetrixesp.utils.ImplFreelingAnalyzer;
import pucp.edu.cohmetrixesp.utils.ParagraphSplitter;

public class DescriptiveAnalyzerTest {
	DescriptiveAnalyzer desc = DescriptiveAnalyzer.getInstance();
	IFreelingAnalyzer freeling = ImplFreelingAnalyzer.getInstance();
	String small, medium;
	Properties prop;
	ParagraphSplitter p_splitter;
	CohText ctSmall, ctMedium;
	@Before
	public void load_test_files() {
		prop = new Properties();
		try {
			small = new String(Files.readAllBytes(Paths
					.get("./testfiles/smallFile.txt")));
			medium = new String(Files.readAllBytes(Paths
					.get("./testfiles/mediumFile.txt")));
			prop.load(new FileInputStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ctSmall = new CohText(small);
		ctMedium = new CohText(medium);
		ctSmall.analyze(freeling);
		ctMedium.analyze(freeling);
		// System.out.println(prop.getProperty("tok_file"));
		// System.out.println(desc.number_of_words(small));
	}

	@Test
	public void numberOfParagraphsTest() {
		CohText ctSmall = new CohText(small);
		CohText ctMedium = new CohText(medium);
		ctSmall.analyze(freeling);
		ctMedium.analyze(freeling);
		assertEquals("numero de parrafo distintos", 2,
				desc.numberOfParagraphs(ctSmall));
		assertEquals("numero de parrafo distintos", 6,
				desc.numberOfParagraphs(ctMedium));
	}
	
	@Test
	public void numberOfSentencesTest() {
		assertEquals("numero de oraciones distintos", 16,
				desc.numberOfSentences(ctMedium));
		assertEquals("numero de oraciones distintos", 3,
				desc.numberOfSentences(ctSmall));
	}
	public void testStatistics(CohStats returned , CohStats expected) {
		assertNotNull(returned);
		System.out.println(returned);
	}
	@Test
	public void lenghtParagraphTest(){
		testStatistics(desc.lengthOfParagraphs(ctMedium), new CohStats(1.0, 2.9));
		testStatistics(desc.lengthOfParagraphs(ctSmall), new CohStats(1.0, 2.9));
	}
	@Test
	public void numberOfWordsInSentencesTest(){
		testStatistics(desc.numberOfWordsInSentences(ctMedium), new CohStats(1.0, 2.9));
		testStatistics(desc.numberOfWordsInSentences(ctSmall), new CohStats(1.0, 2.9));
	}	@Test
	public void numberOfLettersInWordsTest(){
		testStatistics(desc.numberOfLettersInWords(ctMedium), new CohStats(1.0, 2.9));
		testStatistics(desc.numberOfLettersInWords(ctSmall), new CohStats(1.0, 2.9));
	}	@Test
	public void numberOfSyllablesInWordsTest(){
		testStatistics(desc.numberOfSyllablesInWords(ctMedium), new CohStats(1.0, 2.9));
		testStatistics(desc.numberOfSyllablesInWords(ctSmall), new CohStats(1.0, 2.9));
	}
}
