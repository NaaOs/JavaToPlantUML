package codetoplantuml;

import java.util.ArrayList;
import java.util.Map;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class FieldVariableVisitor extends VoidVisitorAdapter<String> {

	public static ArrayList<Map<String, ArrayList<String>>> fieldVariableList = new ArrayList<Map<String, ArrayList<String>>>();

	@Override
	public void visit(FieldDeclaration n, String arg) {

		// アクセス修飾子
		ArrayList<String> accessTemp = new ArrayList<String>();
		// アクセス修飾子以外
		ArrayList<String> OtherTemp = new ArrayList<String>();
		// フィールド変数クラス名
		ArrayList<String> fieldVariableClassName = new ArrayList<String>();
		// フィールド変数名
		ArrayList<String> fieldVariableName = new ArrayList<String>();

		System.out.println("-----FieldVariable-----");

		try {
			// フィールド変数のアクセス修飾子
			System.out.println("Access : " + n.getAccessSpecifier().asString());
			accessTemp.add(n.getAccessSpecifier().asString());

			// アクセス修飾子以外の修飾子
			for (int i = 1; i < n.getModifiers().size(); i++) {
				System.out.println("WithoutAccess : " + n.getModifiers().get(i).toString());
				OtherTemp.add(n.getModifiers().get(i).toString());
			}

			// フィールド変数クラス名
			System.out.println("FieldVariableClass : " + n.getVariable(0).getTypeAsString());
			fieldVariableClassName.add(n.getVariable(0).getTypeAsString());

			// フィールド変数名
			System.out.println("FieldVariable : " + n.getVariable(0).getNameAsString());
			fieldVariableName.add(n.getVariable(0).getNameAsString());

			FieldVariableDefinition fieldVariableDefinition = new FieldVariableDefinition(accessTemp, OtherTemp, fieldVariableClassName, fieldVariableName);

			fieldVariableList.add(fieldVariableDefinition.getMap());

			super.visit(n, arg);

		} catch (IndexOutOfBoundsException e) {
			// フィールド変数が実装されていないときここを通る

		}

	}

}
