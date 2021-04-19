package codetoplantuml;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ExtendedVisitor extends VoidVisitorAdapter<String>{

	public static ArrayList<String> extendedName = new ArrayList<String>();

	// クラスやインターフェースなどを取得する
	@Override
	public void visit(ClassOrInterfaceDeclaration  n, String arg) {

		try {
			System.out.println("Extends > " + n.getExtendedTypes().get(0).getName().toString());

			extendedName.add(n.getExtendedTypes().get(0).getName().toString());
			super.visit(n, arg);
		} catch (IndexOutOfBoundsException e) {
			// 継承クラスがないときにここを通る
		}

	}


}
