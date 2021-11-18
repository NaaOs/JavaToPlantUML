package codetoplantuml;

import java.util.ArrayList;
import java.util.Map;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class MethodVisitor extends VoidVisitorAdapter<String>{

	public static ArrayList<Map<String, ArrayList<String>>> methodList = new ArrayList<Map<String, ArrayList<String>>>();

	// クラスやインターフェースなどを取得する
	@Override
	public void visit(MethodDeclaration  n, String arg) {

		// アクセス修飾子
		ArrayList<String> accessTemp = new ArrayList<String>();
		// アクセス修飾子以外
		ArrayList<String> OtherTemp = new ArrayList<String>();
		// 戻り値
		ArrayList<String> returnTemp = new ArrayList<String>();
		// メソッド名
		ArrayList<String> methodNameTemp = new ArrayList<String>();
		// 引数
		ArrayList<String> argumentTemp = new ArrayList<String>();

		System.out.println("-----method-----");
		System.out.println("methodSentence > " + n.getDeclarationAsString());

		//アクセス修飾子の取得
		System.out.println("methodAccess > " + n.getAccessSpecifier());
		accessTemp.add(n.getAccessSpecifier().asString());

		// アクセス修飾子以外の取得
		// 1から始めるのは0番目はアクセス修飾子であるため
		for(int i = 1; i < n.getModifiers().size(); i++) {
			System.out.println("methodwithoutAccess > " + n.getModifiers().get(i).toString());
			OtherTemp.add(n.getModifiers().get(i).toString());
		}

		// 返り値の型取得
		System.out.println("methodType > " + n.getTypeAsString());
		returnTemp.add(n.getTypeAsString());

		// メソッド名の取得
		System.out.println("methodName > " + n.getNameAsString());
		methodNameTemp.add(n.getNameAsString());

		// 引数の取得
		for(int i = 0; i < n.getParameters().size(); i++) {
			System.out.println("Args > " + n.getParameter(i));
			argumentTemp.add(n.getParameter(i).toString());
		}
		//System.out.println("arg > " + arg);



		MethodDefinition definition = new MethodDefinition(accessTemp, OtherTemp, returnTemp, methodNameTemp, argumentTemp);


		methodList.add(definition.getMap());

		super.visit(n, arg);
	}

}
