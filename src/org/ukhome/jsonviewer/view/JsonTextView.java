package org.ukhome.jsonviewer.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.ukhome.jsonviewer.Activator;
import org.ukhome.jsonviewer.util.ControlFactory;
import org.ukhome.jsonviewer.util.JsonFormat;

public class JsonTextView extends ViewPart {

    public static final String ID = "org.ukhome.jsonviewer.view.JsonTextView";

    private Action action1;
    private StyledText messageText;

    @Override
    public void createPartControl(Composite parent) {
        Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);

        Composite curtain = createCurtain(parent);
        Composite banner = createBanner(curtain);
        createSubject(banner, boldFont);
        createSourceRow(banner, boldFont);
        createDateRow(banner, boldFont);

        messageText = new StyledText(curtain, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
        messageText.setLayoutData(new GridData(GridData.FILL_BOTH));
        messageText.setBackground(ControlFactory.textBGColor);
        messageText.setFont(ControlFactory.getDefaultFont());
        messageText.setText(JsonFormat.formatJson(JsonFormat.JSON4));
        
        makeActions();
        hookContextMenu(); // 配合弹出式菜单一起使用
        contributeToActionBars();
    }

    @Override
    public void setFocus() {
        messageText.setFocus();
    }

    /**
     * 幕布
     * 
     * @param parent
     * @return
     */
    private Composite createCurtain(Composite parent) {
        Composite curtain = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        curtain.setLayout(layout);
        curtain.setBackground(ControlFactory.controlBGColor);
        return curtain;
    }

    /**
     * 横幅
     * 
     * @param parent
     * @return
     */
    private Composite createBanner(Composite parent) {
        Composite banner = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout = new GridLayout();
        layout.marginHeight = 5;
        layout.marginWidth = 10;
        layout.numColumns = 2;
        banner.setLayout(layout);
        banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false));
        return banner;
    }

    /**
     * 主题
     * 
     * @param parent
     * @param boldFont
     */
    private void createSubject(Composite parent, Font boldFont) {
        Label subjectLabel = ControlFactory.getDefault().createLabel(parent, SWT.NONE);
        subjectLabel.setText("Subject:");
        subjectLabel.setFont(boldFont);
        subjectLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));

        subjectLabel = new Label(parent, SWT.WRAP);
        subjectLabel.setText("This is a message about the cool Eclipse RCP!");
    }

    /**
     * 源码
     * 
     * @param parent
     * @param boldFont
     */
    private void createSourceRow(Composite parent, Font boldFont) {
        Label sourceLabel = ControlFactory.getDefault().createLabel(parent, SWT.NONE);
        sourceLabel.setText("Source:");
        sourceLabel.setFont(boldFont);

        final Link link = new Link(parent, SWT.NONE);
        link.setText("<a>https://github.com/ukhome/JsonViewer</a>");
    }

    /**
     * 日期
     * 
     * @param parent
     * @param boldFont
     */
    private void createDateRow(Composite parent, Font boldFont) {
        Label dateLabel = ControlFactory.getDefault().createLabel(parent, SWT.NONE);
        dateLabel.setText("Date:");
        dateLabel.setFont(boldFont);

        dateLabel = new Label(parent, SWT.WRAP);
        dateLabel.setText("2019-01-30 22:50:43");
    }

    private void makeActions() {
        action1 = new Action() {
            public void run() {
                showMessage("Action 1 executed");
            }
        };
        action1.setText("expend");
        action1.setToolTipText("expend json");
        action1.setImageDescriptor(Activator.getImageDescriptor("icons/expend.gif"));
    }

    /**
     * 右击
     */
    private void hookContextMenu() {
        MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true); // 监听
        menuMgr.addMenuListener(new IMenuListener() {

            @Override
            public void menuAboutToShow(IMenuManager manager) {
                String text = messageText.getText();
                text = JsonFormat.formatJson(text);
                messageText.setText(text);
                JsonTreeView.setTreeInput(text);
                // JsonTreeView.setTreeInput(messageText.getSelectionText());
            }

        });
        Menu m = menuMgr.createContextMenu(messageText);
        messageText.setMenu(m);
    }

    private void contributeToActionBars() {
        IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(IMenuManager manager) {
        manager.add(action1);
        manager.add(new Separator());
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        manager.add(action1);
    }

    private void showMessage(String message) {
        MessageDialog.openInformation(messageText.getShell(), "Sample View", message);
    }

}
