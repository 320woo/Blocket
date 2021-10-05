package com.b101.recruit.reponse;

import com.b101.recruit.domain.dto.GalleryDto;
import com.b101.recruit.domain.entity.Activity;
import com.b101.recruit.domain.entity.Certificate;
import com.b101.recruit.domain.entity.FinalEducation;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("FileResponse")
public class FileRes {

	private Long id;
	private Long pid;

//	private Certificate certificate;
//	private Activity activity;
//	private FinalEducation finalEducation;

	private String filePath;
	private String imgFullPath;
	private String title;
	private String sortation; // 구분
	private Long sid;
	private Object data;

	public static FileRes of(GalleryDto gallery,Object object) {
		FileRes res = new FileRes();
		res.setId(gallery.getId());
		res.setPid(gallery.getPid());
		res.setSid(gallery.getSid());
		res.setTitle(gallery.getTitle());
//		res.setImgFullPath(gallery.getImgFullPath());
		res.setFilePath(gallery.getFilePath());
		res.setSortation(gallery.getSortation());
		res.setData(object);
		return res;
	}

}
