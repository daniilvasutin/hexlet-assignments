package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String tagName;
    protected Map<String, String>  tagInside;

    public Tag(String tagName, Map<String, String> tagInside) {
        this.tagName = tagName;
        this.tagInside = tagInside;
    }

    public String getTagName() {
        return tagName;
    }

    public String getTagInside() {
        String res = "";
        if (tagInside.size() == 0) return res;

        for (var item : tagInside.entrySet()){
            res += item.getKey() + "=" + "\"" + item.getValue() + "\"" + " ";
        }
        return " " + res.trim();
    }

    public abstract String toString();
}
// END
