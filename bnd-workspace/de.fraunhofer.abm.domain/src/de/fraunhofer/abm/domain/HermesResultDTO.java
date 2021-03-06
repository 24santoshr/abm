package de.fraunhofer.abm.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class HermesResultDTO {
	public String id = UUID.randomUUID().toString();
    public Date date;
    public String dir;
    public String status;
    public String error;
    public String stackTrace;
    public List<HermesBuildDTO> hermesBuilds = new ArrayList<>();
    public String versionId;

}
