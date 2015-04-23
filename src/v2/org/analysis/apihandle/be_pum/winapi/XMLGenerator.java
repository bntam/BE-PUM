/**
 * Project: BE_PUM_V1
 * Package name: v2.org.analysis.apihandle.be_pum.winapi
 * File name: XMLGenerator.java
 * Created date: Mar 16, 2015
 * Description:
 */
package v2.org.analysis.apihandle.be_pum.winapi;

import java.io.File;

/**
 * @author Yen Nguyen
 *
 */
public class XMLGenerator {

	/**
	 * 
	 */
	public XMLGenerator() {
		// TODO Auto-generated constructor stub
	}

	private static void println(String packageName, String funcName, String fileName) {
		System.out.println("\t\t<API funcName=\"" + funcName.toLowerCase() + "\" className=\""
				+ packageName + fileName + "\" />");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		String workingDir = System.getProperty("user.dir");
		String path = workingDir + "\\src\\" + XMLGenerator.class.getPackage().getName().replace(".", "\\");
		// System.out.println("Current working directory : " + path);
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<APIMap>");
		File directory = new File(path);
		for (File file : directory.listFiles()) {
			if (file.isDirectory() && !file.getName().equals("structures")) {
				System.out.println("\t<DLL name=\"" + file.getName() + "\">");

				File subDir = new File(file.getAbsolutePath() + "\\functions");
				for (File api : subDir.listFiles()) {
					String packageName = XMLGenerator.class.getPackage().getName() + "." + file.getName()
							+ ".functions.";
					String funcName = api.getName().replace(".java", "");
					
					//System.out.println("\t\t" + funcName);
					
					println(packageName, funcName, funcName);
					println(packageName, funcName + "A", funcName);
					println(packageName, funcName + "W", funcName);
					count++;
				}

				System.out.println("\t</DLL>");
			}
		}

		System.out.println("</APIMap>");
		System.out.println("Total: " + count);
	}

}
