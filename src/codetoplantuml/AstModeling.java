package codetoplantuml;

import java.util.ArrayList;
import java.util.Map;

import com.github.javaparser.ast.Node;

public class AstModeling {

	// クラスの情報が入ったリスト
	private ArrayList<ClassDefinition> classesList = new ArrayList<ClassDefinition>();

	// スーパークラス名のリスト
	private ArrayList<String> superClassesList;

	// インターフェースクラス名のリスト
	private ArrayList<String> interfaceClassesList;

	// インターフェースクラス名のリスト
	private ArrayList<Map<String, ArrayList<String>>> fieldVariablesList;

	// メソッド名のリスト
	private ArrayList<Map<String, ArrayList<String>>> methodList;

	private Node node;

	public AstModeling(Node node) {
		this.node = node;
	}

	public void nodeAnalysis() {

		// 一応空にしておく
		classesList.clear();

		// クラス名の取得
		String className = node.getChildNodes().get(1).toString();

		// スーパークラス名の取得
		node.accept(new ExtendedVisitor(), null);
		// インターフェースクラス名の取得
		node.accept(new InterfaceVisitor(), null);
		// フィールド変数の取得
		node.accept(new FieldVariableVisitor(), null);
		// メソッド名の取得
		node.accept(new MethodVisitor(), null);

		// 各要素をクラスリストに格納
		ClassDefinition classDefinition = new ClassDefinition(className);

		// スーパークラスのコピー
		superClassesList = new ArrayList<String>((ArrayList<String>) ExtendedVisitor.extendedName);

		// インターフェースクラスのコピー
		interfaceClassesList = new ArrayList<String>((ArrayList<String>) InterfaceVisitor.interfaceList);

		// フィールド変数名のコピー
		fieldVariablesList = new ArrayList<Map<String, ArrayList<String>>>((ArrayList<Map<String, ArrayList<String>>>) FieldVariableVisitor.fieldVariableList);

		// スーパークラスのコピー
		methodList = new ArrayList<Map<String, ArrayList<String>>>((ArrayList<Map<String, ArrayList<String>>>) MethodVisitor.methodList);


		// スーパークラスのリストがからでなければクラス定義に挿入
		if (!superClassesList.isEmpty()) {
			classDefinition.setExtendsName(superClassesList.get(0).toString());
		}

		// インターフェースのリストがからでなければクラス定義に挿入
		if (!interfaceClassesList.isEmpty()) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.addAll(interfaceClassesList);
			classDefinition.setInterfaceNameList(tempList);
		}

		// フィールド変数のリストが空でなければクラス定義の挿入
		if (!fieldVariablesList.isEmpty()) {
			classDefinition.setFieldVariableList(fieldVariablesList);
		}

		// メソッドのリストが空でなければクラス定義の挿入
		if (!methodList.isEmpty()) {
			classDefinition.setMethodsList(methodList);
		}

		// 各リストをクリア
		ExtendedVisitor.extendedName.clear();
		InterfaceVisitor.interfaceList.clear();
		FieldVariableVisitor.fieldVariableList.clear();
		MethodVisitor.methodList.clear();

		classesList.add(classDefinition);


	}

	// getter, setter
	public ArrayList<ClassDefinition> getClassesList() {
		return classesList;
	}

	public void setClasses(ArrayList<ClassDefinition> classesList) {
		this.classesList = classesList;
	}

}
