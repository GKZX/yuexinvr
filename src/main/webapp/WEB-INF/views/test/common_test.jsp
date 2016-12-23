<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>user tests</title>
</head>
<body>
  <div id="mocha"><p><a href=".">Index</a></p></div>
  <div id="userManager"></div>
  <div id="messages"></div>
  <div id="fixtures"></div>
  <script src="../js/test/mocha.js"></script>
  <script src="../js/test/chai.js"></script>
  <script src="../js/vue.js"></script>
  <script src="../js/jquery-3.1.1.min.js"></script>
  <script src="../js/common.js"></script>
  <script src="../js/user.js"></script>
  <script>mocha.setup('bdd')</script>
  <script src="../js/test/common_test.js"></script>
  <script>mocha.run();</script>
</body>
</html>