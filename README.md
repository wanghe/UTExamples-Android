# UTExamples - Android


* `mobile/` 例子工程根目录
* `mobile/src/main/` 例子工程代码目录
* `mobile/src/test/` 例子工程单元测试目录



## 如何review

1. `./gradlew clean build` 构建项目
2. 在虚拟机或者设备上运行该项目构建成生的 apk
3. 查看 `src/main/com.chehejia.example.utexamples.mvp.Contract` 接口和其说明
4. 查看 `com.chehejia.example.utexamples.mvp` 包下的接口实现
5. 查看 `MainActivity` 的实现
6. 查看 `src/test/com.chehejia.example.utexamples` 包下的单元测试用例.
7. 查看 `src/test/com.chehejia.example.utexamples.mockito` 包下的应用了 `mockito` 后的单元测试用例.