package org.ukhome.jsonviewer.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ControlFactory {
    public static Font controlFont;
    public static Color controlForeColor;
    public static Color textBGColor = new Color(Display.getDefault(), 203, 233, 207);
    public static Color controlBGColor = new Color(Display.getDefault(), 236, 236, 236);

    private ControlFactory() {
    }

    private static class ControlFactorySingle{
        private static ControlFactory instance = new ControlFactory();
    }

    public static ControlFactory getDefault() {
        return ControlFactorySingle.instance;
    }

    /**
     * 创建 label 控件
     */
    public Label createLabel(Composite parent, int style) {
        Label label = new Label(parent, style);
        if (controlFont != null) {
            label.setFont(controlFont);
        }
        if (controlForeColor != null) {
            label.setForeground(controlForeColor);
        }
        if (controlBGColor != null) {
            label.setBackground(controlBGColor);
        }
        return label;
    }

    /**
     * 创建 text 控件
     */
    public Text createText(Composite parent, int style) {
        Text text = new Text(parent, style);
        if (controlFont != null) {
            text.setFont(controlFont);
        }
        if (controlForeColor != null) {
            text.setForeground(controlForeColor);
        }
        if (controlBGColor != null) {
            text.setBackground(controlBGColor);
        }
        return text;
    }

    /**
     * 创建 combo 控件
     */
    public Combo createCombo(Composite parent, int style) {
        Combo combo = new Combo(parent, style);
        if (controlFont != null) {
            combo.setFont(controlFont);
        }
        if (controlForeColor != null) {
            combo.setForeground(controlForeColor);
        }
        if (controlBGColor != null) {
            combo.setBackground(controlBGColor);
        }
        return combo;
    }
}
