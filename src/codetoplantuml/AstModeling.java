package codetoplantuml;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;

public class AstModeling {

	// クラスの情報が入ったリスト
	private ArrayList<ClassDefinition> classesList = new ArrayList<ClassDefinition>();

	private Node node;

	public AstModeling(Node node) {
		this.node = node;
	}

	private void nodeAnalysis() {

		// 一応空にしておく
		classesList.clear();

		// クラス名の取得
		String className = node.getChildNodes().get(1).toString();

		// スーパークラス名の取得
		node.accept(new ExtendedVisitor(), null);
		// インターフェースクラス名の取得
		node.accept(new InterfaceVisitor(), null);
		// メソッド名の取得
		node.accept(new MethodVisitor(), null);
		// クラス変数の取得
		node.accept(new ClassVariableVisitor(), null);

		// 各要素をクラスリストに格納
		ClassDefinition classDefinition = new ClassDefinition(className);

		// スーパークラスのリストがからでなければクラス定義に挿入
		if (!ExtendedVisitor.extendedName.isEmpty()) {
			classDefinition.setExtendsName(ExtendedVisitor.extendedName.get(0).toString());
		}

		// インターフェースのリストがからでなければクラス定義に挿入
		if (!InterfaceVisitor.interfaceList.isEmpty()) {
			ArrayList<String> tempList = new ArrayList<String>();
			tempList.addAll(InterfaceVisitor.interfaceList);
			classDefinition.setInterfaceNameList(tempList);
		}

		// メソッドのリストが空でなければクラス定義の挿入
		if (!MethodVisitor.methodList.isEmpty()) {
			classDefinition.setMethodsList(MethodVisitor.methodList);
		}

		// クラス変数のリストが空でなければクラス定義の挿入
		if (!ClassVariableVisitor.classVariableList.isEmpty()) {
			classDefinition.setClassVariableList(ClassVariableVisitor.classVariableList);
		}



		classesList.add(classDefinition);

	}

	// getter, setter
	public ArrayList<ClassDefinition> getClassesList() {
		// クラスのリスト型に成形した後に返す
		nodeAnalysis();
		return classesList;
	}

	public void setClasses(ArrayList<ClassDefinition> classesList) {
		this.classesList = classesList;
	}

}
