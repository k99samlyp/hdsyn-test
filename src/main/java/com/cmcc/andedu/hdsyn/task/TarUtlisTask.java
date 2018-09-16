package com.cmcc.andedu.hdsyn.task;

import java.io.File;

import com.cmcc.andedu.hdsyn.service.EduSynHdDbService;
import com.cmcc.andedu.hdsyn.utils.FtpUtils;

public class TarUtlisTask {
	
	FtpUtils ftpUtils;
	
	EduSynHdDbService eduSynHdDbService;
	
	public int tarUtlis(String pathname, String filename, String localpath) {
		
		ftpUtils.downloadFile(pathname, filename, localpath);
		
		File file = new File(localpath.trim());
		int tarRead = eduSynHdDbService.solveingDb(file);
		
		return tarRead;
		
	}

	public FtpUtils getFtpUtils() {
		return ftpUtils;
	}

	public void setFtpUtils(FtpUtils ftpUtils) {
		this.ftpUtils = ftpUtils;
	}

	public EduSynHdDbService getEduSynHdDbService() {
		return eduSynHdDbService;
	}

	public void setEduSynHdDbService(EduSynHdDbService eduSynHdDbService) {
		this.eduSynHdDbService = eduSynHdDbService;
	}

}