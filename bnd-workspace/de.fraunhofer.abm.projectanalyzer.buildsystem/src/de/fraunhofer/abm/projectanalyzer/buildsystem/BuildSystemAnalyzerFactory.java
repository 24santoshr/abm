package de.fraunhofer.abm.projectanalyzer.buildsystem;

import org.osgi.service.component.annotations.Component;

import de.fraunhofer.abm.projectanalyzer.api.ProjectAnalyzer;
import de.fraunhofer.abm.projectanalyzer.api.ProjectAnalyzerFactory;

@Component
public class BuildSystemAnalyzerFactory implements ProjectAnalyzerFactory {

    @Override
    public ProjectAnalyzer createNewAnalyzer() {
        return new BuildSystemAnalyzer();
    }
}
