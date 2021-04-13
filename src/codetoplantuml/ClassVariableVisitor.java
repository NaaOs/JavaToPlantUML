package codetoplantuml;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVariableVisitor extends VoidVisitorAdapter<String> {

	public static ArrayList<String> classVariableList = new ArrayList<String>();

	@Override
	public void visit(ClassOrInterfaceDeclaration n, String arg) {

		// アクセス修飾子
		ArrayList<String> accessTemp = new ArrayList<String>();
		// アクセス修飾子以外
		ArrayList<String> OtherTemp = new ArrayList<String>();
		// クラス変数名
		ArrayList<String> classVariable = new ArrayList<String>();

		try {
			// クラス変数のアクセス修飾子
			accessTemp.add(n.getAccessSpecifier().asString());

			// アクセス修飾子以外の修飾子
			for (int i = 0; i < n.getModifiers().size(); i++) {
				OtherTemp.add(n.getModifiers().get(i).toString());
			}

			// クラス変数名
			classVariable.add(n.getNameAsString());

			// TODO これらの情報をどう渡すか悩み中


			super.visit(n, arg);

		} catch (IndexOutOfBoundsException e) {
			// クラス変数が実装されていないときここを通る

		}

	}

}
