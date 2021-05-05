package pojava.projekt.generator.sprawozdan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.filechooser.FileSystemView;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;


// Used FlatLaf LaF - https://www.formdev.com/flatlaf/
// Wiktor Ludwiniak - MainInterface
public class MainInterface extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int returnValue = 0;
	private JPanel  panelCode, panelLeft;
	private JMenuItem save, saveAs, open;
	private JMenuBar menuBar; 
	private JMenu file;
	private JButton bGraph, bTable, bSection, bJPG;
	public static JTextArea codeField; //codeField is necessary in TexElement's pasteToTextFrame method, it should be public static
	public File cf;
	
	public MainInterface() throws HeadlessException {
		
		//--------MEGAFRAME parameters -----------------------------
		
		this.setSize(800,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		
		//----------------Menu---------------------------------------
		menuBar = new JMenuBar();
		file = new JMenu ("Plik");
		
		JFileChooser fileCh = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		fileCh.setDialogTitle("Wybierz plik.");
		fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		save = new JMenuItem("Zapisz");
		save.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
	  	              File f = cf;
	  	              FileWriter out = new FileWriter(f);
	  	              out.write(codeField.getText());
	  	              out.close();
	  	          } catch (FileNotFoundException except) {
	  	              Component f = null;
	  	              JOptionPane.showMessageDialog(f,"Nieznaleziono pliku.");
	  	          } catch (IOException except) {
	  	              Component f = null;
	  	              JOptionPane.showMessageDialog(f,"B³¹d.");
	  	          }
			}
		}
	);
		saveAs = new JMenuItem("Zapisz jako");
		saveAs.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				returnValue = fileCh.showSaveDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
  	          try {
  	              cf = new File ( fileCh.getSelectedFile().getAbsolutePath()+".tex");
  	              FileWriter out = new FileWriter(cf);
  	              out.write(codeField.getText());
  	              out.close();
  	          } catch (FileNotFoundException except) {
  	              Component f = null;
  	              JOptionPane.showMessageDialog(f,"Nieznaleziono pliku.");
  	          } catch (IOException except) {
  	              Component f = null;
  	              JOptionPane.showMessageDialog(f,"B³¹d.");
  	          }
				
				}
			}
		}
	);
		open= new JMenuItem("Wczytaj");
		open.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String in = "";
				returnValue = fileCh.showOpenDialog(null);
    	          if (returnValue == JFileChooser.APPROVE_OPTION) 
    	          {
    	        	  
    	          
    	          
    	          try{
    	        	  File f = new File(fileCh.getSelectedFile().getAbsolutePath());
    	              FileReader read = new FileReader(f);
    	              Scanner scan = new Scanner(read);
    	              while(scan.hasNextLine()){
    	                  String line = scan.nextLine() + "\n";
    	                  in = in + line;
    	          }
    	              scan.close();
    	              codeField.setText(in);
    	          }
    	          catch (FileNotFoundException except) {
      	              Component f = null;
      	              JOptionPane.showMessageDialog(f,"Nieznaleziono pliku.");
      	          } catch (IOException except) {
      	              Component f = null;
      	              JOptionPane.showMessageDialog(f,"B³¹d.");
      	          }
    	  }
			
			}
		}
	);
		
		file.add(save);
		file.add(saveAs);
		file.add(open);
		menuBar.add(file);
		this.setJMenuBar(menuBar);
		
		//--------left panel buttons --------------------------------
		
		panelLeft = new JPanel();
		panelLeft.setLayout(new GridLayout(5,1));
		
		bTable  = new JButton("Tabela");
		bTable.addActionListener(new ActionListener()
		{
			// Open Table Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
				/*every GUI's element must be located in EventDispatchThread. In actionPerformed, EventDispatchThread
				 *   is setted by default, isn't necessary to place code in EventQueue try-catch block*/
							TableDialog frame = new TableDialog();
							frame.setVisible(true);
			}
		}
	);
		bJPG = new JButton("JPG/PNG");
		bJPG.addActionListener(new ActionListener()
		{
			// Open Graphics Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							GraphicsDialog frame = new GraphicsDialog();
							frame.setVisible(true);
			}
		}
	);
		bSection = new JButton("Sekcja");
		bSection.addActionListener(new ActionListener()
		{
			// Open Section Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							SectionDialog frame = new SectionDialog();
							frame.setVisible(true);
			}
		}
	);
		bGraph = new JButton("Wykres");
		bGraph.addActionListener(new ActionListener()
		{
			// Open Plot Dialog
			@Override
			public void actionPerformed(ActionEvent e)
			{
							PlotDialog frame = new PlotDialog();
							frame.setVisible(true);
			}
		}
	);
		panelLeft.add(bTable);
		panelLeft.add(bJPG);
		panelLeft.add(bSection);
		panelLeft.add(bGraph);
		
		this.add(panelLeft, BorderLayout.LINE_START);
		
		//---------Text panel Center---------------------------------------
			/* JTextArea is located in JScrollPane (setViewportView method provides this), so scrolls show automatically */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(680, 535));
		codeField = new JTextArea("\\documentclass[12pt, a4paper]{article}\n"
									+ "\\usepackage{graphicx}"
									+ "\\usepackage{float}\r\n"
									+ "\\usepackage{polski}\n"
									+ "\\begin{document}\n"
									+ "\\end{document}\n"
									);
		codeField.setFont(new Font("Consolas", Font.PLAIN, 14));
		codeField.setCaretPosition(codeField.getDocument().getLength() - 16 ); // Setting default caret position between beginning 
		//and ending of the document
		panelCode = new JPanel();
		panelCode.add(scrollPane);
		scrollPane.setViewportView(codeField);
		
		this.add(panelCode, BorderLayout.CENTER);
		
		//-----------------------------------------------------------
	}

	public MainInterface(GraphicsConfiguration gc) {
		super(gc);
	}

	public MainInterface(String title) throws HeadlessException {
		super(title);
	}

	public MainInterface(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	public static void main(String[] args) {
		
		//FlatLightLaf.install();
		FlatDarkLaf.install();
		
		/*every GUI's element must be located in EventDispatchThread.*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface mi = new MainInterface();
					mi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
