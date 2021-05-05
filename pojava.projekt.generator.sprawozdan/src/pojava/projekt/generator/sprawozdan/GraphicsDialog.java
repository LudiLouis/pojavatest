package pojava.projekt.generator.sprawozdan;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

//Ada Szymorek and Wiktor Ludwiniak
public class GraphicsDialog extends CustomDialog {
	
	protected Path picPath;
	protected static int val = 0; 
	
	public GraphicsDialog() {
			super();
			setBounds(100, 100, 393, 157);
			
			//--------cancel Button (from CustomDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.WEST, cancelButton, -81, SpringLayout.EAST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, cancelButton, 0, SpringLayout.EAST, contentPane);
			contentPane.add(cancelButton);
			
			//--------ok Button (from CustomDialog)--------------------------------
			sl_contentPane.putConstraint(SpringLayout.SOUTH, okButton, 0, SpringLayout.SOUTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, okButton, -87, SpringLayout.EAST, contentPane);
			okButton.addActionListener(new OkListener());
			contentPane.add(okButton);
			
			//--------title Label (from CustomDialog) --------------------------------
			titleLabel.setText("Podaj tytu\u0142 obrazka:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, titleLabel, 13, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, titleLabel, -50, SpringLayout.WEST, titleTextField);
			contentPane.add(titleLabel);
			
			//--------title Text Field (from CustomDialog) --------------------------------
			sl_contentPane.putConstraint(SpringLayout.NORTH, titleTextField, 10, SpringLayout.NORTH, contentPane);
			sl_contentPane.putConstraint(SpringLayout.WEST, titleTextField, 173, SpringLayout.WEST, contentPane);
			sl_contentPane.putConstraint(SpringLayout.EAST, titleTextField, 0, SpringLayout.EAST, contentPane);
			
			contentPane.add(titleTextField);
			titleTextField.setColumns(21);
			
			//--------file chooser Label --------------------------------
			JLabel fileChooserLabel = new JLabel("Wybierz plik z obrazkiem:");
			sl_contentPane.putConstraint(SpringLayout.NORTH, fileChooserLabel, 20, SpringLayout.SOUTH, titleLabel);
			sl_contentPane.putConstraint(SpringLayout.WEST, fileChooserLabel, 8, SpringLayout.WEST, contentPane);
			contentPane.add(fileChooserLabel);
			
			//--------file chooser button --------------------------------
			JButton searchButton = new JButton("Przegl\u0105daj...");
			searchButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileCh = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					val = fileCh.showSaveDialog(null);
					
					
					fileCh.setDialogTitle("Wybierz plik.");
					fileCh.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					
					if (val==JFileChooser.APPROVE_OPTION)
		            {
						File picFile = new File ( fileCh.getSelectedFile().getAbsolutePath());
		                
						picPath = FileSystems.getDefault().getPath(fileCh.getSelectedFile().getAbsolutePath());
						
						
		            }
					
				}
			});
			
			sl_contentPane.putConstraint(SpringLayout.NORTH, searchButton, 13, SpringLayout.SOUTH, titleTextField);
			sl_contentPane.putConstraint(SpringLayout.EAST, fileChooserLabel, -115, SpringLayout.WEST, searchButton);
			sl_contentPane.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, contentPane);
			contentPane.add(searchButton);
			//-----------------------------------------------------------------------------------------------------------------
		}
		public class OkListener implements ActionListener{
		//Creates a Picture's instance and calls methods necessary for creating TeX code (only pasteToTextField, temporarily)
			@Override
			public void actionPerformed(ActionEvent e) {
				title = titleTextField.getText();
				Picture pic = new Picture();
				pic.pasteToTextFrame(MainInterface.codeField, pic.makeElementCode());	
		
		}
	}
}

