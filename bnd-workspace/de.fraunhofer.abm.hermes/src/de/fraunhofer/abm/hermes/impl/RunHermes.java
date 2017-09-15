package de.fraunhofer.abm.hermes.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import de.fraunhofer.abm.builder.api.BuildUtils;
import de.fraunhofer.abm.builder.docker.base.AbstractHermesStep;
import de.fraunhofer.abm.domain.HermesStepDTO;

public class RunHermes extends AbstractHermesStep<String> {

	

	private static final transient Logger logger = LoggerFactory.getLogger(RunHermes.class);
	private String containerName;
	private String repoDir;
	private String csvName;
	BufferedReader r,e;
	String line;
	public RunHermes(String repoDir/*, ExecutorService executor*/) {
		super(repoDir/*, executor*/);
		this.repoDir = repoDir;
		this.name = "Run Hermes application through sbt";
		
	}
	
	@Override
	public String execute() throws InterruptedException
	{ setStatus(STATUS.IN_PROGRESS);
	    logger.info("Running Hermes Application");
		  csvName =  UUID.randomUUID().toString();
	        try {
	        	
	        	
	        	Process runSbt = Runtime.getRuntime().exec("docker exec "+containerName+" sbt \"project OPAL-DeveloperTools\" "+ " \"runMain org.opalj.hermes.HermesCLI src/main/resources/hermes.json -csv " +csvName+".csv"+"\"");
	           /* output = result.stdout;
	            errorOutput = result.stderr;
	            setStatus(result.exitValue == 0 ? STATUS.SUCCESS : STATUS.FAILED);
	            System.out.println("Hermes process status"+ getStatus());*/
	            r = new BufferedReader(new InputStreamReader(runSbt.getInputStream()));
				  e = new BufferedReader(new InputStreamReader(runSbt.getErrorStream()));
				  while (true) {
				         line = r.readLine();
				         if (line == null) { break; }
				         System.out.println(line);
				     }
					 
				     while (true) {
				         line = e.readLine();
				         if (line == null) { break; }
				         System.out.println(line);
				     }
	            
	        } catch (Throwable t) {
	            logger.error("Couldn't run Hermes Docker: " + containerName, t);
	            errorOutput = BuildUtils.createErrorString("Couldn't run Hermes docker container:" + containerName, t);
	            setThrowable(t);
	        }

	        return csvName;
	}
	
	public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

	
	
	
	@Override
	public HermesStepDTO toDTO(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
