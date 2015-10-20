/**
 * Project: BE-PUMv2
 * Package name: v2.org.analysis.apihandle.generation
 * File name: GenTest.java
 * Created date: Oct 19, 2015
 * Description:
 */
package v2.org.analysis.apihandle.generation;

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
import v2.org.analysis.apihandle.generation.stub.Variable;

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
				"int",
				"nBufferLength",
				"The length of the buffer for the current directory string, in TCHARs. The buffer length must include room for a terminating null character.");
		paramContext.put("v", v1);
		Velocity.mergeTemplate("initialize/CastValue.vm", "ASCII", paramContext, w);
		v1.initStr = w.toString();
		paramsList.add(v1);
		w.getBuffer().setLength(0);

		paramsList
				.add(new Variable(
						"char[]",
						"lpBuffer",
						"A pointer to the buffer that receives the current directory string. This null-terminated string specifies the absolute path to the current directory. To determine the required buffer size, set this parameter to NULL and the nBufferLength parameter to 0."));
		func.paramsList = paramsList;

		func.ret = new Variable(
				"DWORD",
				null,
				"If the function succeeds, the return value specifies the number of characters that are written to the buffer, not including the terminating null character.");

		context.put("func", func);

		/* lets render a template */

		template = TemplateUtil.getTemplate("NewAPIDeclare.vm");
		template.merge(context, w);

		System.out.println(w.toString());
	}

}
