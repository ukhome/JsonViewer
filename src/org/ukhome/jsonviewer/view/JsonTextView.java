package org.ukhome.jsonviewer.view;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.ukhome.jsonviewer.util.JsonFormat;

public class JsonTextView extends ViewPart {

    public static final String ID = "org.ukhome.jsonviewer.view.JsonTextView";

    private Text messageText;

    @Override
    public void createPartControl(Composite parent) {
        Font boldFont = JFaceResources.getFontRegistry().getBold(JFaceResources.DEFAULT_FONT);

        Composite curtain = createCurtain(parent);
        Composite banner = createBanner(curtain);
        createSubject(banner, boldFont);
        createSourceRow(banner, boldFont);
        createDateRow(banner, boldFont);
        
        messageText = new Text(curtain, SWT.MULTI | SWT.WRAP);
        messageText.setLayoutData(new GridData(GridData.FILL_BOTH));
        messageText.setText(JsonFormat.formatJson(JsonFormat.JSON4));
    }

    @Override
    public void setFocus() {
        messageText.setFocus();
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
     * 
     * @param parent
     * @param boldFont
     */
    private void createSubject(Composite parent, Font boldFont) {
        Label subjectLabel = new Label(parent, SWT.NONE);
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
        Label sourceLabel = new Label(parent, SWT.NONE);
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
        Label dateLabel = new Label(parent, SWT.NONE);
        dateLabel.setText("Date:");
        dateLabel.setFont(boldFont);

        dateLabel = new Label(parent, SWT.WRAP);
        dateLabel.setText("2019-01-30 22:50:43");
    }

}
