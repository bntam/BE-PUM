/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.generation
 * File name: GenTest.java
 * Created date: Oct 19, 2015
 * Description:
 */
package v2.org.analysis.apihandle.generation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import v2.org.analysis.apihandle.generation.stub.Function;
import v2.org.analysis.apihandle.generation.stub.ParamsList;
import v2.org.analysis.apihandle.generation.stub.Type;
import v2.org.analysis.apihandle.generation.stub.Variable;
import v2.org.analysis.apihandle.winapi.structures.GeneratedStruct;
import v2.org.analysis.util.SystemUtil;

/**
 * @author Yen Nguyen
 *
 */
public class GenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/* first, we init the runtime engine. */
		Properties p = new Properties();
		p.setProperty("file.resource.loader.path", "data/templates/");
		Velocity.init(p);

//		System.out.println(GenStruct());
		
		System.out.println(TypeMap.getInstance().getTypeMapping("WORD"));
	}

	static String GenAPI() {
		StringWriter w = new StringWriter();
		Template template = null;

		/* lets make a Context and put data into it */

		VelocityContext context = new VelocityContext();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		Date now = new Date();
		context.put("date", dateFormat.format(now));

		Function func = new Function();
		func.funcName = "IsValidAcl";
		func.libName = "kernel32";
		func.description = "The IsValidAcl function validates an access control list (ACL).";

		Vector<String> importList = new Vector<String>();
		importList.add("com.sun.jna.WString");
		importList.add("com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference");
		func.importList = importList;

		VelocityContext paramContext = new VelocityContext();
		ParamsList paramsList = new ParamsList();

		Variable v1 = new Variable(
				new Type("int"),
				"nBufferLength",
				"The length of the buffer for the current directory string, in TCHARs. The buffer length must include room for a terminating null character.");
		paramContext.put("v", v1);
		Velocity.mergeTemplate("initialize/CastValue.vm", "ASCII", paramContext, w);
		v1.initStr = w.toString();
		paramsList.add(v1);
		w.getBuffer().setLength(0);

		paramsList
				.add(new Variable(
						new Type("char[]"),
						"lpBuffer",
						"A pointer to the buffer that receives the current directory string. This null-terminated string specifies the absolute path to the current directory. To determine the required buffer size, set this parameter to NULL and the nBufferLength parameter to 0."));
		func.paramsList = paramsList;

		func.ret = new Variable(
				new Type("DWORD"),
				null,
				"If the function succeeds, the return value specifies the number of characters that are written to the buffer, not including the terminating null character.");

		context.put("func", func);

		/* lets render a template */

		// template = TemplateUtil.getTemplate("NewAPIDeclare.vm");
		template = TemplateUtil.getTemplate("StructDeclaration.vm");
		template.merge(context, w);

		return w.toString();
	}

	static String GenStruct() {
		String newStruct = null;
		StringWriter w = new StringWriter();
		Template template = null;

		/* lets make a Context and put data into it */

		VelocityContext context = new VelocityContext();

		Function func = new Function();
		func.funcName = "IsValidAcl";

		ParamsList paramsList = new ParamsList();
		paramsList.add(new Variable(new Type("int"), "nBufferLength"));
		paramsList.add(new Variable(new Type("char[]"), "lpBuffer"));
		func.paramsList = paramsList;

		context.put("func", func);

		/* lets render a template */
		template = TemplateUtil.getTemplate("StructDeclaration.vm");
		template.merge(context, w);

		// Get the declaration string for current structure
		newStruct = w.toString();

		// Reset buffer
		w.getBuffer().setLength(0);

		context.put("newStruct", newStruct);

		String path = "src/" + GeneratedStruct.class.getPackage().getName().replace(".", "/")
				+ "/GeneratedStruct.java";
		File source = new File(path);

		try {
			SystemUtil.copyFileUsingStream(source, 
					new File(Velocity.getProperty("file.resource.loader.path")
					.toString() + "GeneratedStruct.vm"));

			Velocity.mergeTemplate("GeneratedStruct.vm", "UTF-8", context, w);
			
			FileWriter fileWriter = new FileWriter(source.getAbsoluteFile());
			@SuppressWarnings("resource")
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(w.toString());
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return w.toString();
	}
}
