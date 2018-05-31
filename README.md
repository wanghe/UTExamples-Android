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


## 单元测试

#### UT 写法
* 直接验证
    * 有返回值的接口
    * 无关联方的接口

* 间接验证
    * 没有返回值的接口
    * 有关联方的接口

* 异步验证
    * 需要异步执行的接口


#### UT 最佳实践
* 每一个 Case 保证只有一个被测代码实例.
相关方都是理想实现。所谓理想实现，是被mock的相关接口，只为了被测代码而做的实现.

* 每一个 Case 只测一个接口