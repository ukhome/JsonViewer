package org.ukhome.jsonviewer.model;

import org.ukhome.jsonviewer.util.StringUtils;

public class JsonItem extends Json {

    private String key;
    private String item;

    protected JsonItem(String key, String item) {
        super(key);
        this.key = key;
        this.item = item;
    }

    @Override
    public String toString() {
        return this.key + "=" + this.item;
    }

    @Override
    public String getName() {
        return toString();
    }

    @Override
    public Json[] getChildren() {
        return new Json[] { Json.getInstance(item, key) };
    }

    @Override
    public boolean hasChildern() {
        return StringUtils.isJson(this.item);
    }

}
