package codetoplantuml;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

public class CodeAnalysis {

	public List<Node> Analysis(Path source) {

		List<Node> nodes = null;

		try {
			// 構文解析
			CompilationUnit unit = StaticJavaParser.parse(source);

			nodes = unit.getChildNodes();

		} catch (IOException e) {
			System.out.println("指定されたファイルが存在しません");
		}
		return nodes;
	}
}
