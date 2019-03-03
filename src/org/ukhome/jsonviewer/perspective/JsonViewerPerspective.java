package org.ukhome.jsonviewer.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;
import org.ukhome.jsonviewer.view.DateUtilView;
import org.ukhome.jsonviewer.view.JsonTextView;
import org.ukhome.jsonviewer.view.JsonTreeView;

public class JsonViewerPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(false);// 不显示编辑区，所以下面的0.5f没用了，实际上是全占了

        layout.addStandaloneView(JsonTreeView.ID, true, IPageLayout.LEFT, 0.25f, editorArea);
        IViewLayout jsonTreeLayout = layout.getViewLayout(JsonTreeView.ID);
        jsonTreeLayout.setCloseable(false);
        jsonTreeLayout.setMoveable(false);

        IFolderLayout folder = layout.createFolder("messages", IPageLayout.TOP, 1f, editorArea);
        folder.addPlaceholder(JsonTextView.ID + ":*");// :*表示允许多个
        folder.addView(JsonTextView.ID);

        IViewLayout jsonTextLayout = layout.getViewLayout(JsonTextView.ID);
        jsonTextLayout.setCloseable(false);

        folder.addPlaceholder(DateUtilView.ID + ":*");
        folder.addView(DateUtilView.ID);

    }
}
