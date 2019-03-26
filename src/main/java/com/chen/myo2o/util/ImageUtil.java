package com.chen.myo2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ImageUtil {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName, String targetAddr) {
		String realFileName =getRandomFileName();
		String extension = getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200).outputQuality(0.25f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	public static void main(String[] args) throws IOException {
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		Thumbnails.of(new File("E:\\image\\916492631372800465.jpg")).size(1280,960)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.png")),0.8f).outputQuality(0.8f)
				.toFile("F:\\IdeaProjects\\myo2o\\target\\test-classes\\upload\\item\\shop\\9\\916492631372800466.jpg");

	}

	public static String getRandomFileName() {
		//获取随机五位数
		int rannum = r.nextInt(89999)+10000;
		String nowTimeStr = sdf.format(new Date());
		return nowTimeStr + rannum;
	}
}
