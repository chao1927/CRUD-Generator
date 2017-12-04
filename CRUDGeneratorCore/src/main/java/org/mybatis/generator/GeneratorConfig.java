package org.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class GeneratorConfig {

	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		File configFile = new File(GeneratorConfig.class.getResource("/generatorConfig.xml").toURI());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, null, warnings);
		myBatisGenerator.generate(null);
		System.out.println(warnings);
	}
}
