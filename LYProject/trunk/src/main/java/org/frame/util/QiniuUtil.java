package org.frame.util;

import org.frame.response.JsonRestResponse;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.frame.response.RestUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class QiniuUtil {
	public static final String ACCESS_KEY = PropertiesUtil.get("QN_ACCESS_KEY"); // 这两个登录七牛
	// 账号里面可以找到
	public static final String SECRET_KEY = PropertiesUtil.get("QN_SECRET_KEY");
	// 要上传的空间
	public static final String bucketname = PropertiesUtil.get("QN_bucketname"); //
	// 密钥配置
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	// 创建上传对象
	UploadManager uploadManager = new UploadManager();

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public JsonRestResponse upload(MultipartFile file) throws IOException {
		try {
			String key = UUID.randomUUID().toString();
			// 获取文件名称
			String fileName = file.getOriginalFilename();
			// 文件名称后的类型
			String extensionName = fileName
					.substring(fileName.lastIndexOf("."));
			key += extensionName;
			// 调用put方法上传
			Response res = uploadManager
					.put(file.getBytes(), key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
			return RestUtil.createResponse(key);
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.bodyString());
			return RestUtil.createResponseErro(r.bodyString());
		}
	}
	
	public String download(String url){
		return auth.privateDownloadUrl(url);
	}

}
