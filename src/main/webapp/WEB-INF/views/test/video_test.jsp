<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>video tests</title>
  <link href="../css/mocha.css" rel="stylesheet" />
</head>
<body>
  <div id="mocha"><p><a href="../video">Video</a></p></div>
  <div id="userManager"></div>
  <div id="messages"></div>
  <div id="fixtures"></div>
  <div id="videoManager"></div>
  <div class="M-box2" style="display:none"></div>
  <script src="../js/test/mocha.js"></script>
  <script src="../js/test/chai.js"></script>
  <script src="../js/vue.js"></script>
  <script src="../js/jquery-3.1.1.min.js"></script>
  <script src="../js/jquery.pagination.min.js"></script>
  <script src="../js/common.js"></script>
  <script src="../js/video.js"></script>
  <script>mocha.setup('bdd')</script>
  <script src="../js/test/video_test.js"></script>
  <script>mocha.run();</script>
</body>
</html>