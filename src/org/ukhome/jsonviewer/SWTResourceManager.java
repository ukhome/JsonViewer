package org.ukhome.jsonviewer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class SWTResourceManager {

    private static Map<RGB, Color> res_colorMap = new HashMap<RGB, Color>();

    private static Map<String, Font> res_fontMap = new HashMap<String, Font>();

    private static Map<Font, Font> res_fontToBoldFontMap = new HashMap<Font, Font>();

    public static Color getColor(int r, int g, int b) {
        return getColor(new RGB(r, g, b));
    }

    public static Color getColor(RGB rgb) {
        Color color = res_colorMap.get(rgb);
        if (color == null) {
            Display display = Display.getDefault();
            color = new Color(display, rgb);
            res_colorMap.put(rgb, color);
        }
        return color;
    }

    public static void disposeColors() {
        for (Color color : res_colorMap.values()) {
            color.dispose();
        }
        res_colorMap.clear();
    }

    public static Font getFont(String name, int height, int style) {
        return getFont(name, height, style, false, false);
    }

    public static Font getFont(String name, int size, int style, boolean strikeout, boolean underline) {
        String fontName = name + '|' + size + '|' + style + '|' + strikeout + '|' + underline;
        Font font = res_fontMap.get(fontName);
        if (font == null) {
            FontData fontData = new FontData(name, size, style);
            if (strikeout || underline) {
                try {
                    Class<?> logFontClass = Class.forName("org.eclipse.swt.internal.win32.LOGFONT"); //$NON-NLS-1$
                    Object logFont = FontData.class.getField("data").get(fontData); //$NON-NLS-1$
                    if (logFont != null && logFontClass != null) {
                        if (strikeout) {
                            logFontClass.getField("lfStrikeOut").set(logFont, Byte.valueOf((byte) 1)); //$NON-NLS-1$
                        }
                        if (underline) {
                            logFontClass.getField("lfUnderline").set(logFont, Byte.valueOf((byte) 1)); //$NON-NLS-1$
                        }
                    }
                } catch (Throwable e) {
                    System.err.println("Unable to set underline or strikeout" + " (probably on a non-Windows platform). " + e); //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
            font = new Font(Display.getDefault(), fontData);
            res_fontMap.put(fontName, font);
        }
        return font;
    }

    public static Font getBoldFont(Font baseFont) {
        Font font = res_fontToBoldFontMap.get(baseFont);
        if (font == null) {
            FontData fontDatas[] = baseFont.getFontData();
            FontData data = fontDatas[0];
            font = new Font(Display.getDefault(), data.getName(), data.getHeight(), SWT.BOLD);
            res_fontToBoldFontMap.put(baseFont, font);
        }
        return font;
    }

    public static void disposeFonts() {
        for (Font font : res_fontMap.values()) {
            font.dispose();
        }
        res_fontMap.clear();
        
        for (Font font : res_fontToBoldFontMap.values()) {
            font.dispose();
        }
        res_fontToBoldFontMap.clear();
    }

    public static void dispose() {
        disposeColors();
        disposeFonts();
    }
}
