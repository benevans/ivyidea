package org.clarent.ivyidea.resolve;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import org.apache.ivy.util.MessageLogger;
import org.clarent.ivyidea.ivy.IvyManager;

import java.util.List;

/**
 * @author Guy Mahieu
 */
public class Resolver {

    private List<ResolvedDependency> dependencies;
    private IvyManager ivyManager;

    public Resolver(IvyManager ivyManager) {
        this.ivyManager = ivyManager;
    }

    public List<ResolvedDependency> resolve(final Module module) {
        return resolve(module, null);
    }

    public List<ResolvedDependency> resolve(final Module module, final MessageLogger messageLogger) {
        ApplicationManager.getApplication().runReadAction(new Runnable() {
            public void run() {
                dependencies = new DependencyResolver().resolve(module, ivyManager, messageLogger);
            }
        });
        return dependencies;
    }

}
