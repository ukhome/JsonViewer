package org.ukhome.jsonviewer.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.ukhome.jsonviewer.model.IBase;
import org.ukhome.jsonviewer.model.Json;

public class JsonViewerContentProvider implements ITreeContentProvider {

    /**
     * 构造根节点
     */
    @Override
    public Object[] getElements(Object inputElement) {
        return ((Json) inputElement).getChildren();
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return Json.getInstance(parentElement.toString()).getChildren();
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public boolean hasChildren(Object element) {
        return Json.getInstance(element.toString()).hasChildern();
    }

    protected Object[] getAllElements(Object inputElement) {
        return inputElement instanceof IBase ? ((IBase<?>) inputElement).getChildren() : new String[] { inputElement.toString() };
    }

}
