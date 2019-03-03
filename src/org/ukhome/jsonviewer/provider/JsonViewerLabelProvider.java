package org.ukhome.jsonviewer.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.ukhome.jsonviewer.model.Json;
import org.ukhome.jsonviewer.util.StringUtils;

public class JsonViewerLabelProvider extends LabelProvider {

    @Override
    public Image getImage(Object element) {
        return null;
    }

    @Override
    public String getText(Object element) {

        String element2Str = null == element ? "" : element.toString();
        String elementName = ((Json) element).getName();
        String text = StringUtils.isEmpty(elementName) ? "" : StringUtils.isJson(element2Str) ? elementName + " : " : elementName;

        char prefixChar = element2Str.trim().equals("") ? '\0' : element2Str.trim().charAt(0);
        switch (prefixChar) {
            case '{':
                text += "{Object}";
                break;
            case '[':
                text += "[Array]";
                break;
            case '\0':
                text = null;
                break;
            default:
                break;
        }

        return text;
    }

}
