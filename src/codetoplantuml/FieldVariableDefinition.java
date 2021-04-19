package codetoplantuml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FieldVariableDefinition {

	// フィールド変数の情報を格納する
	private Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	// コンストラクタ
	public FieldVariableDefinition(ArrayList<String> accessModifier,
							ArrayList<String> otherModifierList,
							ArrayList<String> className,
							ArrayList<String> fieldVariableName
							) {

		// 引数をMapに格納
		map.put("accessModifier", accessModifier);
		map.put("otherModifierList", otherModifierList);
		map.put("className", className);
		map.put("fieldVariableName", fieldVariableName);
	}

	public Map<String, ArrayList<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, ArrayList<String>> map) {
		this.map = map;
	}

}
