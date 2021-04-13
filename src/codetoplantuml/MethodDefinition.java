package codetoplantuml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// メソッドの定義ファイル
public class MethodDefinition {

	// メソッドの情報を格納する
	private Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	// コンストラクタ
	public MethodDefinition(ArrayList<String> accessModifier,
							ArrayList<String> otherModifierList,
							ArrayList<String> returnStyle,
							ArrayList<String> methodNameList,
							ArrayList<String> argumentList) {

		// 引数をMapに格納
		map.put("accessModifier", accessModifier);
		map.put("otherModifierList", otherModifierList);
		map.put("returnStyle", returnStyle);
		map.put("methodName", methodNameList);
		map.put("argumentList", argumentList);
	}

	public Map<String, ArrayList<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, ArrayList<String>> map) {
		this.map = map;
	}

}
