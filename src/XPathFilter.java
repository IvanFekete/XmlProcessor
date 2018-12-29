import javax.swing.*;

public class XPathFilter {
    String tag, param;
    JCheckBox enabled;

    public XPathFilter(JCheckBox enabled, String tag, String param) {
        this.enabled = enabled;
        this.tag = tag;
        this.param = param;
    }

    public String getTag() {
        return tag;
    }

    public boolean isEnabled() {
        return enabled.isSelected();
    }

    public String getParam() {
        return param;
    }
}
