package v2.org.analysis.apihandle.generation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import v2.org.analysis.apihandle.generation.stub.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TypeMap {
	private static TypeMap instance = null;

	public static synchronized TypeMap getInstance() {
		if (instance == null) {
			instance = new TypeMap();
		}

		return instance;
	}

	private HashMap<String, Type> typeMap = new HashMap<>();

	public TypeMap() {
		String path = "/" + TypeMap.class.getPackage().getName().replace(".", "/") + "/TypeMap.json";
		InputStream stream = TypeMap.class.getResourceAsStream(path);

		Gson gson = new Gson();
		List<Type> typeList = gson.fromJson(new InputStreamReader(stream), (new TypeToken<List<Type>>(){}).getType());
		for (Type type : typeList) {
			typeMap.put(type.from, type);
		}
	}
	
	public Type getTypeMapping(String oldType) {
		Type type = this.typeMap.get(oldType);
		return type;
	}
}
