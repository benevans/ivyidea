package org.clarent.ivyidea.intellij.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import org.clarent.ivyidea.intellij.facet.ui.IvyFacetEditorTab;
import org.jdom.Element;

import java.util.logging.Logger;

/**
 * @author Guy Mahieu
 */

public class IvyFacetConfiguration implements FacetConfiguration {

    private static final Logger LOGGER = Logger.getLogger(IvyFacetConfiguration.class.getName());

    private String ivyFile;
    private boolean useProjectSettings;
    private String ivySettingsFile;

    public static IvyFacetConfiguration getInstance(Module module) {
        final IvyFacet ivyFacet = IvyFacet.getInstance(module);
        if (ivyFacet != null) {
            return ivyFacet.getConfiguration();
        } else {
            LOGGER.info("Module " + module.getName() + " does not have the IvyIDEA facet configured; ignoring.");
            return null;
        }
    }

    public String getIvyFile() {
        return ivyFile;
    }

    public void setIvyFile(String ivyFile) {
        this.ivyFile = ivyFile;
    }

    public boolean isUseProjectSettings() {
        return useProjectSettings;
    }

    public void setUseProjectSettings(boolean useProjectSettings) {
        this.useProjectSettings = useProjectSettings;
    }

    public String getIvySettingsFile() {
        return ivySettingsFile;
    }

    public void setIvySettingsFile(String ivySettingsFile) {
        this.ivySettingsFile = ivySettingsFile;
    }

    public FacetEditorTab[] createEditorTabs(FacetEditorContext editorContext, FacetValidatorsManager validatorsManager) {
        return new FacetEditorTab[] { new IvyFacetEditorTab(editorContext) };
    }

    public void readExternal(Element element) throws InvalidDataException {
        setIvyFile(element.getAttributeValue("ivyFile", ""));
        setUseProjectSettings(Boolean.valueOf(element.getAttributeValue("useProjectSettings", Boolean.FALSE.toString())));
        setIvySettingsFile(element.getAttributeValue("ivySettingsFile", ""));
   }

    public void writeExternal(Element element) throws WriteExternalException {
        element.setAttribute("ivyFile", ivyFile == null ? "" : ivyFile);
        element.setAttribute("useProjectSettings", Boolean.toString(useProjectSettings));
        element.setAttribute("ivySettingsFile", ivySettingsFile == null ? "" : ivySettingsFile);
    }
}
