package js.user.model;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public class FileVo {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;		
	}
	
	public boolean saveFile(String path) {
		boolean flag = true;
		
		File dir = new File(path);
		
		if( !dir.exists() )
			dir.mkdirs();
		
		File f = new File(dir, file.getOriginalFilename());
		
		System.out.println(file.getOriginalFilename());
		
		try {
			file.transferTo(f);
		} catch (Exception e) {	
			flag = false;
			e.printStackTrace();
		}
		
		return flag;
	}
}








