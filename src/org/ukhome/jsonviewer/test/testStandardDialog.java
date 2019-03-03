package org.ukhome.jsonviewer.test;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class testStandardDialog implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {

		// File standard dialog
		FileDialog fileDialog = new FileDialog(window.getShell());
		// Set the text
		fileDialog.setText("Select File");

		// Set filter on .txt files
		fileDialog.setFilterExtensions(new String[] { "*.txt","*.pdf" });

		// Put in a readable name for the filter
		fileDialog.setFilterNames(new String[] { "Textfiles(*.txt)" });

		// Open Dialog and save result of selection
		String selected = fileDialog.open();
		System.out.println("ѡ����ļ��ǣ�"+selected);

		// Directly standard selection
		DirectoryDialog dirDialog = new DirectoryDialog(window.getShell());
		dirDialog.setText("Select your home directory");
		String selectedDir = dirDialog.open();
		System.out.println("ѡ���·���ǣ�"+selectedDir);

		// Select Font
		FontDialog fontDialog = new FontDialog(window.getShell());
		fontDialog.setText("Select your favorite font");
		FontData selectedFond = fontDialog.open();
		System.out.println("ѡ�������壺"+selectedFond);

		// Select Color
		ColorDialog colorDialog = new ColorDialog(window.getShell());
		fontDialog.setText("Select your favorite color");
		RGB selectedColor = colorDialog.open();
		System.out.println("ѡ����"+selectedColor+"��ɫ");

		// Now a few messages
		String selected1=MessageDialog.openConfirm(window.getShell(), "Confirm",
				"Please confirm")+"";
		System.out.println("Confirm�����"+selected1);
		
		MessageDialog.openError(window.getShell(), "Error", "Error occured");
		
		MessageDialog
				.openInformation(window.getShell(), "Info", "Info for you");
		System.out.println("openInformation");
		
		String selected2=(MessageDialog.openQuestion(window.getShell(), "Question",
				"Really, really?"))+"";
		System.out.println("Question�����"+selected2);
		
		MessageDialog.openWarning(window.getShell(), "Warning", "I warn you");
		System.out.println("openWarning");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

}
