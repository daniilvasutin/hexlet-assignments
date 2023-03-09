package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{

    public SingleTag(String tagName, Map<String, String> tagInside) {
        super(tagName, tagInside);
    }

    @Override
    public String toString() {
        return "<" + getTagName() + getTagInside() + ">";
    }
}
// END
