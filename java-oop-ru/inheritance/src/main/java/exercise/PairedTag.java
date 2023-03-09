package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String tagText;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> tagInside, String tagText, List<Tag> children) {
        super(tagName, tagInside);
        this.tagText = tagText;
        this.children = children;
    }

    @Override
    public String toString() {

        String chilTag = "";

        for (var item : children) {
            chilTag += item.toString();
        }

        return "<" + getTagName() + getTagInside() + ">" + chilTag + tagText + "</" + getTagName() + ">";
    }
}
// END
