package org.ukhome.jsonviewer.test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
public class TestText{
    public static void main(String[] args){
        //文本框（可以输入文字的文本框控件）
        Display display=new Display();
        Shell shell=new Shell(display);
        shell.setText("i am a shell");
        shell.setSize(400,400);
        shell.setLayout(new FillLayout(SWT.VERTICAL));//必须要有
        //shell.setLayout(new FillLayout(SWT.HORIZONTAL));//必须要有
 
        //(1)新建一个文本框,要想监听文本框是否被选中，需要将文本框的属性设置为final
        final Text content=new Text(shell,SWT.WRAP|SWT.V_SCROLL);
        content.setSize(400,100);
        
        //全选按钮
        Button selectAll=new Button(shell,SWT.PUSH);
        selectAll.setText("全选");
        selectAll.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                content.selectAll();
            }
        });
        
        //取消选择按钮
        Button cancel=new Button(shell,SWT.PUSH);
        cancel.setText("取消选择");
        cancel.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                if(content.getSelectionCount()>0){
                    //有选中的内容
                    content.clearSelection();
                }
            }
        });
        
        //复制按钮
        Button copy=new Button(shell,SWT.PUSH);
        copy.setText("复制选中");
        copy.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                if(content.getSelectionCount()>0){
                    //如果有选中的文本，则复制到剪贴板
                    content.copy();
                }
            }
        });
        
        //粘贴按钮
        Button paste=new Button(shell,SWT.PUSH);
        paste.setText("粘贴");
        paste.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                content.paste();
            }
        });
        
        //删除选中的文字
        Button delete=new Button(shell,SWT.PUSH);
        delete.setText("删除选中的文字(剪贴)");
        delete.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                content.cut();
            }
        });
        
        //清空文本框的内容
        Button clear=new Button(shell,SWT.PUSH);
        clear.setText("清空文本框");
        clear.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event){
                content.setText("");
            }
        });
        
        //打开窗口，进行窗口的显示
        shell.open();
        while(!shell.isDisposed()){
            //当窗口没有被释放的时候
            if(!display.readAndDispatch()){
                display.sleep();
            }       
        }
        display.dispose();
    }
}

