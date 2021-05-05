package pojava.projekt.generator.sprawozdan;

import java.io.File;

//Ada Szymorek
public class Graph extends CsvElement {

	/*TO DO: 
	 * -Communication between Graph, parent classes (TexElement, CsvElement) and GraphDialog. 
	 * -Chart library handling, probably JFreeChart 
	 * -Fix pasteToTextFrame problem (when printing the chart code, the table code is also printed, because of common parent for both)*/
	public Graph(File file, String t) {
		super(file);
		title = t;
	}

	@Override
	String makeElementCode() {
		String graphCode = "\n\\begin{figure}[H]\n" +
						"\\includegraphics[scale=1]{picture.png}\n" +
						"\\caption{" +
						title +
						"}" +
						"\n\\end{figure}\n";
		return graphCode;
	}
}
