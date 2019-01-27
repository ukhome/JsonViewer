package org.ukhome.jsonviewer.model;

public interface IBase<T> {
    /**
     * 获取名称
     */
    public String getName();

    /**
     * 获取子节点
     * @return
     */
    public T[] getChildren();

    /**
     * 是否有子节点
     * @return
     */
    public boolean hasChildern();

    /**
     * 当前树节点的等级
     * @return
     */
    public int currentLevel();
}
