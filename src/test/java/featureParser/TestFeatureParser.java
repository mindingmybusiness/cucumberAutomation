package featureParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import gherkin.formatter.JSONFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;

public class TestFeatureParser {
	private static final Type FEATURE_LIST_TYPE = new TypeToken<List<Feature>>() {
	}.getType();

	public List<Feature> mainForParser(String filepath) throws Exception {
		// Step One: Parse feature into JSON using Gherkin
		String featureText = FixJava.readReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"));
		StringBuilder json = new StringBuilder();
		JSONFormatter formatter = new JSONFormatter(json);
		Parser parser = new Parser(formatter);
		parser.parse(featureText, filepath, 0);
		formatter.done();
		formatter.close();

		// Step Two: Convert the Features to plain old java object, and return an array
		// of features
		return new Gson().fromJson(json.toString(), FEATURE_LIST_TYPE);
	}

}
