package codetoplantuml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OutPlantUML {

	@SuppressWarnings("unchecked")
	public void outPlantUML(String packageName, ArrayList<ClassDefinition> classes) {

		try(FileWriter fw = new FileWriter("output.pu")) {
			fw.write("@startuml " + packageName + "\n");


			fw.write("package " + packageName.replace("\r\n\r\n", "{\n"));

			for(ClassDefinition cd : classes) {

				// クラスの出力
				fw.write("class ");
				fw.write(cd.getClassName());
				fw.write(" {\n");

				// メソッドの出力
				for(Map<String, ArrayList<ClassDefinition>> method : cd.getMethodsList()) {
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
