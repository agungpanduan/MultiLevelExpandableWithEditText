package com.example.MultiLevelExpandableListView.Model;

public class Level {
    private String Parent;
    private String Name;
    private String Value;
    private String Level;
    private String ChildLevel;
    private String isCheckedLevel1 = "NO";

    public Level() {

    }

    public Level(String Parent, String Name, String Value, String Level, String ChildLevel) {
        this.Parent = Parent;
        this.Name = Name;
        this.Value = Value;
        this.Level = Level;
        this.ChildLevel = ChildLevel;
    }

    public String getParent() {
        return Parent;
    }

    public void setParent(String Parent) {
        this.Parent = Parent;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getChildLevel() {
        return ChildLevel;
    }

    public void setChildLevel(String ChildLevel) {
        this.ChildLevel = ChildLevel;
    }

}
