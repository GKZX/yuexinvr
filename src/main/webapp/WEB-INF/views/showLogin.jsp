<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userInfo</title>
</head>
<script src="js/aliyun-sdk.min.js"></script>
<script src="js/vod-sdk-upload.js"></script>
<script type="text/javascript">
userData = '';
document.getElementById("files")
        .addEventListener('change', function (event) {
          for(var i=0; i<event.target.files.length; i++) {
            uploader.addFile(event.target.files[i],
            'http://oss-cn-hangzhou.aliyuncs.com',
            'OSSBucketName', //按实际bucket名称填写
            event.target.files[i].name, userData);
          }
        });
        
var uploader = new VODUpload({
  // 文件上传失败
  'onUploadFailed': function (fileName, code, message) {
      console.log("onUploadFailed: " + fileName + code + "," + message);
  },
  // 文件上传完成
  'onUploadSucceed': function (fileName) {
      console.log("onUploadSucceed: " + fileName);
  },
  // 文件上传进度
  'onUploadProgress': function (fileName, totalSize, uploadedSize) {
      console.log("file:" + fileName + ", " + totalSize, uploadedSize,
      "percent:", Math.ceil(uploadedSize * 100 / totalSize));
  }
});

uploader.init(accessKeyId, accessKeySecret);
</script>
<body>

     姓名： ${userInfo.uname}
<form action="">
  <input type="file" name="file" id="files" multiple/>
</form>
</body>
</html>