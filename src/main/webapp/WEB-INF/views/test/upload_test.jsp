<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>upload tests</title>
  <link href="../css/mocha.css" rel="stylesheet" />
</head>
<body>
  <div id="mocha"><p><a href="../upload">Upload</a></p></div>
  <div id="userManager"></div>
  <div id="messages"></div>
  <div id="fixtures"></div>
  <div id="uploadManage"></div>
  <script src="../js/test/mocha.js"></script>
  <script src="../js/test/chai.js"></script>
  <script src="../js/vue.js"></script>
  <script src="../js/jquery-3.1.1.min.js"></script>
  <script src="../js/aliyun-sdk.min.js"></script>
  <script src="../js/vod-sdk-upload.min.js"></script>
  <script src="../js/common.js"></script>
  <script src="../js/upload.js"></script>
  <script>mocha.setup('bdd')</script>
  <script src="../js/test/upload_test.js"></script>
  <script>mocha.run();</script>
</body>
</html>