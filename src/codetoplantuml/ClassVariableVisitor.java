package codetoplantuml;

import java.util.ArrayList;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassVariableVisitor extends VoidVisitorAdapter<String>{

	public static ArrayList<String> classVariableList = new ArrayList<String>();

	// クラスやインターフェースなどを取得する
	@Override
	public void visit(VariableDeclarator  n, String arg) {

		try {
			System.out.println("Variable > " + n.getNameAsString());
			//System.out.println("arg > " + arg);

			//interfaceList.add();


			super.visit(n, arg);

		} catch (IndexOutOfBoundsException e) {
			// クラス変数が実装されていないときここを通る

		}

	}


}
