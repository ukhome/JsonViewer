package org.ukhome.jsonviewer.view;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;
import org.ukhome.jsonviewer.model.Json;
import org.ukhome.jsonviewer.provider.JsonViewerContentProvider;
import org.ukhome.jsonviewer.provider.JsonViewerLabelProvider;

public class JsonViewer extends ViewPart {
	
	public static final String ID = "org.ukhome.jsonviewer.view.JsonViewer";
	public static final String JSON = "[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":1}}]";
	public static final String JSON1 = "{\"11\":[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":1}}]}";
	public static final String JSON2 = "[[{\"code\":10000,\"msg\":null,\"data\":{\"time\":\"2017-10-15 18:09:56\",\"userId\":\"61028f94-de92-4c65-aad3-2fc8614e1d34\",\"userName\":\"devautotest\",\"status\":0}},{\"code\":10003,\"msg\":null,\"data\":{\"time\":\"2018-11-15 18:09:56\",\"userId\":\"31028f94-de92-4c25-aad3-2fc8614e1d34\",\"userName\":\"master\",\"status\":1}}]]";

	private TreeViewer treeViewer;

	public JsonViewer() {
		
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		treeViewer = new TreeViewer(container, SWT.BORDER);
		final Tree tree = treeViewer.getTree();
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {//单击
				super.widgetSelected(e);
				Object element = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();

				// 刷新
				treeViewer.expandToLevel(2);	// 展开到二级菜单
				treeViewer.refresh();			// 回调provider
				System.err.println(element);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {//双击
				Object element = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
				System.err.println(element);
			}
		});
		/* ITreeContentProvider
		 * 1. Object[] getElements	 					入参：下面第5步的input.child，	出参：object[i]节点内容
		 * 2. hasChildren 								入参：下面第5步的input.child，	出参：是否有子节点
		 * 3. Object[] getChildren，第2步返回true时调用，	入参：第1步出参object[i]，	出参：子节点内容
		 */
		treeViewer.setContentProvider(new JsonViewerContentProvider());
		/* ILabelProvider
		 * 4. getImage、getText 							入参：下面第5步的input.child
		 */
		treeViewer.setLabelProvider(new JsonViewerLabelProvider());
		/*
		 * 5. 入参同时也是上面的getElements、getImage、getText入参的parent
		 */
		treeViewer.setInput(Json.getInstance(makemmmmRoot(JSON1)));
		//getSite().setSelectionProvider(treeViewer);
	}

	private String makemmmmRoot(String jsonStr) {
		if (null == jsonStr || jsonStr.trim().equals("")) return null;
		if(jsonStr.trim().charAt(0) != '[' && jsonStr.trim().charAt(0) != '{') return null;
		char rootTag = jsonStr.charAt(0);
		String root = rootTag == '[' ? '[' + jsonStr + ']' : "{\"root\":" + jsonStr + '}';
		return root;
	}

	@Override
	public void setFocus() {

	}

}
