package org.ukhome.jsonviewer.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.ukhome.jsonviewer.Activator;
import org.ukhome.jsonviewer.view.JsonTextView;

public class JsonPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    ColorFieldEditor colorEditor;

    public JsonPreferencePage() {
        super(GRID);
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription("A demonstration of a preference page implementation");
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common GUI
     * blocks needed to manipulate various types of preferences. Each field editor
     * knows how to save and restore itself.
     */
    public void createFieldEditors() {
        colorEditor = new ColorFieldEditor(PreferenceConstants.BACKGROUND_COLOR, "Background Color", getFieldEditorParent());
        colorEditor.getColorSelector().setColorValue(new RGB(0, 207, 220));
        addField(colorEditor);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        
    }

    public void useExample() {
        addField(new DirectoryFieldEditor(PreferenceConstants.P_PATH, "&Directory preference:", getFieldEditorParent()));
        addField(new BooleanFieldEditor(PreferenceConstants.P_BOOLEAN, "&An example of a boolean preference", getFieldEditorParent()));

        addField(new RadioGroupFieldEditor(PreferenceConstants.P_CHOICE, "An example of a multiple-choice preference",
                1, new String[][] { { "&Choice 1", "choice1" }, { "C&hoice 2", "choice2" } }, getFieldEditorParent()));
        addField(new StringFieldEditor(PreferenceConstants.P_STRING, "A &text preference:", getFieldEditorParent()));
    }

    /**
     * 单击“恢复默认值”按钮时调用该方法
     */
    protected void performDefaults() {
//        IPreferenceStore preferenceStore = getPreferenceStore();
    }

    /**
     * 覆盖父类中的方法，但单击“应用”按钮时调用该方法
     */
    public boolean performOk() {
//        IPreferenceStore preferenceStore = getPreferenceStore();
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        IViewPart vp = activePage.findView(JsonTextView.ID);
        ((JsonTextView)vp).update(colorEditor.getColorSelector().getColorValue());
        return true;
    }

    @Override // 用于扩展自己的按钮
    protected void contributeButtons(Composite parent) {
    }

}
