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
import org.ukhome.jsonviewer.util.StringUtils;

public class JsonTreeView extends ViewPart {

    public static final String ID = "org.ukhome.jsonviewer.view.JsonView";

    private static TreeViewer treeViewer;

    public JsonTreeView() {

    }
    
    public static void setTreeInput(String input){
        treeViewer.setInput(makeRoot(input));
    }
    

    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FillLayout(SWT.HORIZONTAL));
        treeViewer = new TreeViewer(container, SWT.BORDER);
        
        /*
         * ITreeContentProvider                     
         * 1. Object[] getElements                          入参：下面第5步的input.child，    出参：object[i]节点内容
         * 2. hasChildren                                   入参：下面第5步的input.child，    出参：是否有子节点 
         * 3. Object[] getChildren，第2步返回true时调用，      入参：第1步出参object[i]，        出参：子节点内容
         */
        treeViewer.setContentProvider(new JsonViewerContentProvider());
        /*
         * ILabelProvider                           
         * 4. getImage、getText 入参：下面第5步的input.child
         */
        treeViewer.setLabelProvider(new JsonViewerLabelProvider());
        /*
         * 5. 入参同时也是上面的getElements、getImage、getText入参的parent
         */
        treeViewer.setInput(null);
//        treeViewer.setInput(makeRoot(JsonFormat.JSON4 + ""));
        // getSite().setSelectionProvider(treeViewer);
        
        addTreeListener();
    }

    @Override
    public void setFocus() {
        //treeViewer.getControl().setFocus();
    }

    private static Json makeRoot(String jsonStr) {
        if (!StringUtils.isJson(jsonStr)) return null;
        char rootTag = jsonStr.charAt(0);
        String root = rootTag == '[' ? '[' + jsonStr + ']' : "{\"root\":" + jsonStr + '}';
        try {
            return Json.getInstance(root);
        } catch (Exception e) {
            e.printStackTrace();
            return Json.getInstance("不是有效的JSON", "ERROR");
        }
    }

    private void addTreeListener() {
        final Tree tree = treeViewer.getTree();
        tree.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {// 单击
                super.widgetSelected(e);
                Object element = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();

                // 刷新
//              treeViewer.expandToLevel(2);    // 展开到二级菜单
//              treeViewer.refresh();
                System.err.println(element);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {// 双击
                Object element = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
                System.out.println(element);
            }
        });        
    }



}
