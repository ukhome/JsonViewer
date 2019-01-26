package org.ukhome.jsonviewer.model;

public interface IBase<T> {
	/**
	 * 获取名称
	 */
	public String getName();
	
	/**
	 * 获取的父节点
	 * @return
	 */
	public T getParent();
	
	/**
	 * 设置父节点
	 * @param object
	 */
	public void setParent(T object);
	
	/**
	 * 设置子节点
	 * @param children
	 */
	public void setChildren(T[] children);
	
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
