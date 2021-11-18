package codetoplantuml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OutPlantUML {

	public void outPlantUML(String packageName, ArrayList<ClassDefinition> classes) {

		try(FileWriter fw = new FileWriter("output.pu")) {

			for(ClassDefinition cd : classes) {

				if (cd.getFieldVariableList().isEmpty()) {
					continue;
				}

				fw.write("@startuml " + packageName.replace("\r\n\r", ""));

				fw.write("package " + packageName.replace("\r\n\r\n", " {\n"));
				// クラスの出力
				fw.write("class ");
				fw.write(cd.getClassName());
				fw.write(" {\n");


				// フィールド変数の出力
				for(Map<String, ArrayList<String>> field : cd.getFieldVariableList()) {

					if (field.get("otherModifierList").toString().contains("static")) {
						fw.write("{static}");
					}

					// アクセス修飾子の分岐
					switch(field.get("accessModifier").toString()) {
					case "[public]":
						fw.write("+ ");
						break;
					case "[private]":
						fw.write("- ");
						break;
					case "[protected]":
						fw.write("# ");
						break;
					case "[package_private]":
						fw.write("~ ");
						break;

					default:
						// アクセス修飾子がないとき
						System.out.println("アクセス修飾子 ： " + field.get("accessModifier").toString());
						fw.write("~ ");
					}

					// フィールド変数クラス名
					fw.write(field.get("className").toString().replace("[", "").replace("]", " "));

					// フィールド変数名
					fw.write(field.get("fieldVariableName").toString().replace("[", "").replace("]", "") + "\n");

				}

				// メソッドの出力
				for(Map<String, ArrayList<String>> method : cd.getMethodsList()) {

					if (method.toString().contains("static")) {
						fw.write("{static} ");
					}

					// アクセス修飾子の分岐
					switch(method.get("accessModifier").toString()) {
					case "[public]":
						fw.write("+ ");
						break;
					case "[private]":
						fw.write("- ");
						break;
					case "[protected]":
						fw.write("# ");
						break;
					case "[package_private]":
						fw.write("~ ");
						break;

					default:
						// アクセス修飾子がないとき
						System.out.println("アクセス修飾子 ： " + method.get("accessModifier").toString());
						fw.write("~ ");
					}

					// メソッド名
					fw.write(method.get("methodName").toString().replace("[", "").replace("]", "") + " ");

					// 引数の出力
					String argTemp = method.get("argumentList").toString();
					fw.write("( " + argTemp.replace("[", "").replace("]", "") + " ) ");
					fw.write(": ");
					// 戻り値の型
					fw.write(method.get("returnStyle").toString().replace("[", "").replace("]", "") + "\n");

				}

				// クラス出力の終わり
				fw.write("}\n");

				// 継承クラス（あれば）
				if(cd.getExtendsName() != null) {
					fw.write("class ");
					fw.write(cd.getExtendsName());
					fw.write(" {\n");
					fw.write("}\n");

					// 継承の関係を図示
					fw.write(cd.getClassName() + " <|-- " + cd.getExtendsName() + "\n");
				}


				// インターフェースクラス（あれば）
				if(cd.getInterfaceNameList() != null) {
					for(String interfaceName : cd.getInterfaceNameList()) {
						fw.write("interface " + interfaceName);
						fw.write(" {\n");
						fw.write("}\n");

						// インターフェースの関係を図示
						fw.write(cd.getClassName() + " <|.. " + interfaceName + "\n");
					}
				}

			}

			// パッケージ出力の終わり
			fw.write("}\n");


			fw.write("@enduml\n");
		} catch (IOException e) {
			System.out.println("ファイルの書き込みができませんでした");
		}
	}

}
