## 网站预览图
![关于](https://github.com/18113996630/major_show/tree/master/src/main/resources/web_pictures/about.png)
![网站首页](https://github.com/18113996630/major_show/tree/master/src/main/resources/web_pictures/index.png)
![专业大类列表](https://github.com/18113996630/major_show/tree/master/src/main/resources/web_pictures/major.png)
![专业详情](https://github.com/18113996630/major_show/tree/master/src/main/resources/web_pictures/detail.png)

## 数据库

- 专业类别表
- 专业表
- 专业详情表
- 视频表
- 评论表
- 评论点赞表
- 视频点赞表
- 视频需求表
- 日志表
- 配置表
- 用户表
- 网站留言表
- 专业问题表
- 问题回答表
- 黑名单表

## 前端

thymeleaf+bootstrap+jquery+layer

## 后台

springboot+mybatis-plus



## 项目进度

#### 20190812

1. **创建数据库表**
2. **创建项目，引入相关依赖**
3. **使用mybatis-plus generator自动创建代码**

------

#### 20190813

1. **自定义错误页面**
2. **爬取相关网站数据，使用python进行数据处理**
3. **数据入库，并修改数据库原始数据**

------

#### 20190814

1. **编写html页面，使用bootstrap模板进行页面框架搭建**

   1. **提取公共head、公共foot、公共网页导航，方便其他页面进行复用**

      ```html
      <!DOCTYPE html>
      <html lang="en" xmlns:th="http://www.thymeleaf.org">
      <head th:fragment="common-header(title, link)">
          <meta charset="utf-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1">
      
          <meta name="description" content="">
          <meta name="author" content="">
          <link rel="icon" href="/img/favicon.ico">
      
          <title th:replace="${title}">demo-project</title>
          <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
          <link th:href="@{/css/ie10-viewport-bug-workaround.css}" rel="stylesheet">
          <link rel="stylesheet" th:href="@{/css/header.css}">
          <th:block th:replace="${link}"/>
          <link rel="stylesheet" th:href="@{/css/common.css}">
      </head>
      <body>
      <!-- 类似指定id为header，复用方法：<div th:replace="commons/header :: header"></div>-->
      <nav class="navbar navbar-fixed-top navbar-inverse nav_color" th:fragment="header">
          <div class="container content_container">
              <div class="navbar-header">
                  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                          aria-expanded="false" aria-controls="navbar">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                  </button>
                  <a class="navbar-brand" th:href="@{/}" th:text="${conf.webName}">Subject Show</a>
              </div>
              <div id="navbar" class="collapse navbar-collapse">
                  <ul class="nav navbar-nav">
                      <li><a th:href="@{/}" th:text="${conf.headSubject}">专业类别</a></li>
                      <li><a th:href="@{'/majors/1'(page=1, size=8)}" th:text="${conf.headMajor}">专业</a></li>
                      <li><a th:href="@{/top}" th:text="${conf.headTop}">视频贡献排行榜</a></li>
                      <li><a th:href="@{/about}" th:text="${conf.headAbout}">关于我</a></li>
                      <li><a th:href="@{/contact}" th:text="${conf.headContact}">联系我</a></li>
                      <li><a th:href="@{/contribute}" th:text="${conf.headContribute}">我要投稿</a></li>
                  </ul>
              </div>
          </div>
      </nav>
      </body>
      </html>
      ```

      ```html
      <!DOCTYPE html>
      <html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
      <head th:replace="commons/header :: common-header(~{}, ~{::link})">
          <link rel="stylesheet" th:href="@{/css/footer.css}">
      </head>
      <div class="foot_div" th:fragment="footer(script)">
          <script th:src="@{/js/jquery.min.js}"></script>
          <script th:src="@{/js/bootstrap.min.js}"></script>
      <!--    <script th:src="@{/js/common.js}"></script>-->
          <script th:src="@{/layer/layer.js}"></script>
          <th:block th:replace="${script}"/>
          <hr>
          <p th:text="${conf.footer}">&copy; 2016 Company, Inc.</p>
      </div>
      </html>
      ```

      

2. **专业类别页面数据回显及排版**

------

#### 20190815

1. **切面获取资源请求，异步保存到日志表，同时提供并发请求量监控**

#### 20190816

1. **新增视图**

   **vw_video_up**(展示所有video的up主)

   **vw_authed_up**(展示所有已授权的up主)

2. **页面自适应支持(使得在手机上也能正常显示)**
3. **添加专业页面、提供专业搜索功能**

#### 20190817

1. **添加专业详情页面**
   1. 页面顶部提供**当前专业种类下该专业附近的几个专业**
   2. **页面排版**
2. **在index页面添加搜索功能**
3. **添加专业详情表**
4. **修改video表结构(将封面和up头像本地化)**
   1. 增加**封面名**字段和**头像名**字段,直接从**static/img目录**下获取图片信息

#### 20190818

1. **进行WebMvcConfigurer配置，增加静态资源目录(磁盘中指定的目录)**

   ```java
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       log.info("添加静态封面资源路径:{}", coverPath);
       log.info("添加静态头像资源路径:{}", facePath);
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
       //访问路径：http://localhost:8081/cover/1c8e2b974c543582117948b670375434d8001abd.jpg
       //addResourceHandler填:/cover/**，addResourceLocations填本地路径：file:E:/workspace/java/cover/
       registry.addResourceHandler("/cover/**").addResourceLocations(coverPath);
       registry.addResourceHandler("/face/**").addResourceLocations(facePath);
   }
   ```

   

2. **修改详情页布局**
   1. 将视频时长放至封面右下角
   2. 增加详情翻页
   3. 增加多余段落进行折叠功能

#### 20190819

1. **爬取专业详情数据(文字)并分析入库**
2. **增加后台管理页面**
   1. **编写登录界面**
   2. **编写拦截器，进行身份验证**

#### 20190820

1. **调整专业详情页面布局**
2. **增加视频点赞、踩、点击量统计功能**

#### 20190821

1. **调整专业页面布局**

2. **增加视频需求提交功能**(想看某个专业的视频，但是没有，可以提交需求)

#### 20190825

1. **增加评论功能**
   1. **添加评论表**
   2. **增加评论点赞表**
2. **增加视频投稿功能**

#### 20190826

1. **修改关于、联系我、视频贡献排行榜页面并完善其功能**
2. **添加后台管理相关功能并完善**
3. **增加网站的可配置性**

### 20190901

1. **增加联系界面的留言功能**
    1. **增加message表**(留言表)，**修改user表结构**
    2. **根据ip获取所在城市方法设置超时时间，避免因超时导致长时间阻塞**
    3. **添加后台查看留言面板**
2. **增加专业介绍、课程、就业方向可编辑功能**
    1. 增加**configuration**表字段
    
### 20190902
1. **专业介绍、课程、就业方向可编辑功能实现**
   
### 20190903

1. **后台代码bug修复**
    1. **select回显**
    
        将下拉框的值设置为要选中的值即可：
                
        `$("#subjectId").val(data.subjectId)`
2. **专业详情界面的编辑功能**
    1. **增加详情修改表：detail_update**
    2. **等待网站备案后再完成剩余功能**


### 20190910
 
1. **网站备案成功，配置https**

### 20190912
1. **测试修改专业描述、课程、就业信息**
    1. **修改user表，增加ip字段**
2. **增加xss防御**

### 20190914
1. **增加异常访问检测机制（未完成）**
    1. **使用flink流处理进行检测:读取nginx-log进行处理**
    - 自定义source
    - 数据去重

### 20190916
1. **完成异常ip检测功能**
    1. **增加ip黑名单表**

### 20191001
1. **增加知乎相关提问模块**
    1. **增加major_question、question_answer表**
    
### 20191015
1. **增加知乎评论**
    1. 增加**major_question**与**question_answer**表
    
### 20191016
1. 修改页面css样式
2. 修改footer
