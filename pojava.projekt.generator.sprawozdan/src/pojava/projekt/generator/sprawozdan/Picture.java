package pojava.projekt.generator.sprawozdan;

//Ada Szymorek
public class Picture extends TexElement {

	/*TO DO: 
	 * -communication between Picture, parent class (TexElement) and GraphicsDialog
	 * - reading Picture file (FIleReader) */
	public Picture() {
		// TODO Auto-generated constructor stub
	}

	@Override
	String makeElementCode() {
		String pictureCode = "\n\\begin{figure}[H]\n" +
				"\\includegraphics[scale=1]{picture.png}\n" +
				"\\caption{" +
				title +
				"}" +
				"\n\\end{figure}\n";
		return pictureCode;
	}

}
