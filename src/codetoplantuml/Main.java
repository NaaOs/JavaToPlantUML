package codetoplantuml;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;

public class Main extends ExtendsClass implements InterFaceClass {
//public class Main extends ExtendsClass {
//public class Main {
	// クラスを定義したリスト
	private static ArrayList<ClassDefinition> classList = new ArrayList<ClassDefinition>();
	// ソースファイルの指定（仮）
	private static Path source = Paths.get("src\\codetoplantuml\\Main.java");

	public static void main(String[] args) {

		// コード解析クラスのインスタンス化
		CodeAnalysis codeAnalysis = new CodeAnalysis();
		// 解析した結果をノードで受け取る(仮)
		List<Node> sourceAst = codeAnalysis.Analysis(source);

		if(sourceAst == null) {
			System.out.println("ソースが解析されませんでした。");
			System.exit(0);
		}

		// 抽象構文木を渡し、クラスのリストに格納
		// -1をしているのはクラス部分を指定しているため
		AstModeling astModeling = new AstModeling(sourceAst.get(sourceAst.size() - 1));
		classList.addAll(astModeling.getClassesList());

		// package名を取得
		String packageName = sourceAst.get(0).toString().replace("package ", "").replace(";", "");

		// PlantUMLに出力
		OutPlantUML outPlantUML = new OutPlantUML();
		outPlantUML.outPlantUML(packageName, classList);

	}

	// ただのテストクラス
	// 特に処理は書かない
	// 作成していく時の確認用
	private static String test(String test, int testtest) {
		return "SSSS";

	}

}


