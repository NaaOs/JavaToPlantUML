package codetoplantuml;

import java.util.ArrayList;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class InterfaceVisitor extends VoidVisitorAdapter<String>{

	public static ArrayList<String> interfaceList = new ArrayList<String>();

	// クラスやインターフェースなどを取得する
	@Override
	public void visit(ClassOrInterfaceDeclaration  n, String arg) {

		try {
			System.out.println("Interface > " + n.getImplementedTypes().get(0).toString());
			//System.out.println("arg > " + arg);

			interfaceList.add(n.getImplementedTypes().get(0).toString());


			super.visit(n, arg);

		} catch (IndexOutOfBoundsException e) {
			// インターフェースが実装されていないときここを通る

		}

	}


}
