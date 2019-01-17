package js.user.service;

import org.springframework.stereotype.Service;

import js.user.model.FileVo;

@Service
public class FileUploadService {

	public boolean saveFile(String path, FileVo file) {
		boolean flag = file.saveFile(path);
		return flag;
	}
	
}
