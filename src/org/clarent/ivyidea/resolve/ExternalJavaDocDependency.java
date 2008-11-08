package org.clarent.ivyidea.resolve;

import com.intellij.openapi.roots.OrderRootType;
import org.clarent.ivyidea.intellij.compatibility.IntellijCompatibilityHelper;

import java.io.File;

/**
 * @author Guy Mahieu
 */

public class ExternalJavaDocDependency extends ExternalDependency {

    public ExternalJavaDocDependency(File externalArtifact) {
        super(externalArtifact);
    }

    protected String getTypeName() {
        return "javadoc";
    }

    protected OrderRootType getType() {
        return IntellijCompatibilityHelper.getInstance().getJavadocOrderRootType();
    }

}