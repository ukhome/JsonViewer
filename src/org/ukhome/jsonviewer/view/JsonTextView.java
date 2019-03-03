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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.ukhome.jsonviewer.Activator;
import org.ukhome.jsonviewer.SWTResourceManager;
import org.ukhome.jsonviewer.model.Json;
import org.ukhome.jsonviewer.util.ControlFactory;
import org.ukhome.jsonviewer.util.JsonFormat;

public class JsonTextView extends ViewPart {

    public static final String ID = "org.ukhome.jsonviewer.view.JsonTextView";

    /**收缩按钮*/
    private Action shrinkAction;
    /**展开按钮*/
    private Action expansionAction;
    /**转译按钮*/
    private Action translationAction;
    /**切换收缩按钮*/
    private Action toggleFormatAction;
    /**文本域*/
    private StyledText messageText;
    /**收缩状态*/
    //private boolean shrinkState;

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
        messageText.setText(JsonFormat.formatJson(Json.getInstance(JsonFormat.JSON4).toString()));

        makeActions();
        hookContextMenu(); // 配合弹出式菜单一起使用
        contributeToActionBars();
    }

    @Override
    public void setFocus() {
        messageText.setFocus();
    }
    
    public void update(RGB rgb) {
        messageText.setBackground(SWTResourceManager.getColor(rgb));
    }

    /**
     * @param isInitJsonTree 是否重新加载json树
     */
    private void expansionJsonText(boolean isInitJsonTree) {
        String text = JsonFormat.removeTranslation(messageText.getText());
        text = JsonFormat.formatJson(Json.getInstance(text).toString());
        messageText.setText(text);
        if(isInitJsonTree) JsonTreeView.setTreeInput(text);
    }

    /**
     * @param isRemoveJsonTree 是否清除json树
     */
    private void shrinkJsonText(boolean isRemoveJsonTree) {
        String text = JsonFormat.removeAll(messageText.getText());
        messageText.setText(text);
        if(isRemoveJsonTree) JsonTreeView.setTreeInput(null);
    }

    /**
     * 幕布
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
        makeToggleAction("toggle expansion state");
        makeShrinkAction("shrink json");
        makeExpansionAction("expansion json");
        makeTranslationAction("remove translation");
    }

    private void makeToggleAction(String name) {
        toggleFormatAction = makeAction(0, name);
    }

    private void makeExpansionAction(String name) {
        expansionAction = makeAction(1, name);
    }

    private void makeShrinkAction(String name) {
        shrinkAction = makeAction(2, name);
    }

    private void makeTranslationAction(String name) {
        translationAction = makeAction(3, name);
    }

    /**
     * 
     * @param type 1-展开 2-收缩 3-去除转译 other-切换收缩(暂时没实现)
     * @param name the text and toolTip of action
     * @return
     */
    private Action makeAction(int type, final String name) {
        Action action;
        switch (type) {
            case 1:
                action = new Action() {
                    public void run() {
                        expansionJsonText(false);
                    }
                };
                action.setImageDescriptor(Activator.getImageDescriptor("icons/expansion.gif"));
                break;
            case 2:
                action = new Action() {
                    public void run() {
                        shrinkJsonText(false);
                    }
                };
                action.setImageDescriptor(Activator.getImageDescriptor("icons/shrink.gif"));
                break;
            case 3:
                action = new Action() {
                    public void run() {
                        messageText.setText(JsonFormat.removeTranslation(messageText.getText()));
                    }
                };
                action.setImageDescriptor(Activator.getImageDescriptor("icons/translation.gif"));
                break;
            default:
                action = new Action() {
                    public void run() {
                        showMessage(name);
                    }
                };
                action.setImageDescriptor(Activator.getImageDescriptor("icons/toggle.gif"));
                break;
        }
        action.setText(name);
        action.setToolTipText(name);
        return action;
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
                expansionJsonText(true);
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
        manager.add(toggleFormatAction);
        manager.add(translationAction);
        manager.add(new Separator());
    }

    private void fillLocalToolBar(IToolBarManager manager) {
        manager.add(shrinkAction);
        manager.add(expansionAction);
        manager.add(new Separator());
        manager.add(translationAction);
    }

    private void showMessage(String message) {
        MessageDialog.openInformation(messageText.getShell(), "Sample View", message);
    }

}
