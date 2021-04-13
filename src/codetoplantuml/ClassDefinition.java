package codetoplantuml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassDefinition {
	// クラスの定義ファイル

	/**
	 * クラス名
	 */
	private String className;
	/**
	 * インターフェースリスト
	 */
	private ArrayList<String> interfaceNameList;
	/**
	 * 継承クラス名
	 */
	private String extendsName;
	/**
	 * クラス変数リスト
	 */
	private ArrayList<String> classVariableList;
	/**
	 * メソッドリスト
	 */
	private ArrayList<Map> methodsList;

	/**
	 * コンストラクタ
	 * @param className
	 * @param interfaceNameList
	 * @param extendsName
	 * @param classVariableList
	 * @param methodsList
	 */
	public ClassDefinition(String className) {
		this.className = className;
		this.interfaceNameList = null;
		this.extendsName = null;
		this.classVariableList = null;
		this.methodsList = null;
	}

	// 以下getter, setter
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getInterfaceNameList() {
		return interfaceNameList;
	}

	public void setInterfaceNameList(ArrayList<String> interfaceNameList) {
		this.interfaceNameList = interfaceNameList;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

	public ArrayList<String> getClassVariableList() {
		return classVariableList;
	}

	public void setClassVariableList(ArrayList<String> classVariableList) {
		this.classVariableList = classVariableList;
	}

	public ArrayList<Map> getMethodsList() {
		return methodsList;
	}

	public void setMethodsList(ArrayList<Map> methodsList) {
		this.methodsList = methodsList;
	}
}
