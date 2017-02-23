package net.anumbrella.menusummary.bean;

/**
 * author：anumbrella
 * Date:17/2/23 下午2:31
 */

public class Type {

    private String typeName;

    private int id;

    private int icon;

    public Type(int id, int icon, String typeName) {
        this.typeName = typeName;
        this.id = id;
        this.icon = icon;
    }


    public String getTypeName() {

        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
