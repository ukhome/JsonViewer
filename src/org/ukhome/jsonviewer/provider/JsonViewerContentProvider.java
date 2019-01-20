package org.ukhome.jsonviewer.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class JsonViewerContentProvider implements ITreeContentProvider {
	
	private List<Object> contents = new ArrayList<Object>();

	@Override
	public Object[] getElements(Object inputElement) {
		getAllElements(inputElement);
		return (Object[]) contents.toArray();
	}


	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return null != contents && !contents.isEmpty() ? true : false;
	}
	
	private void getAllElements(Object inputElement) {
		contents.add("111");
		contents.add("222");

	}

}
