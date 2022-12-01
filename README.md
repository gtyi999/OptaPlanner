

#####  OptaPlanner 中文版

https://www.optaplanner.org

##### 编译说明

```
. maven 版本必须要 3.6.3++
. jdk 8 环境下编译通过
. 原版有三个错误代码提示，已经进行修正<IllegalStateException>
 <IllegalStateException> orElseThrow(() -> new IllegalStateException("Impossible state: Rule for constraint (" +onstraint + ") not found."))));
```



##### 启动运行
mvn clean install -DskipTests=true

cd optaplanner-examples

mvn exec:java

##### 功能截图

https://images.gitee.com/uploads/images/2020/1118/105544_cd1f1bae_5596112.png

https://images.gitee.com/uploads/images/2020/1118/120805_dc7aef6c_5596112.png

https://images.gitee.com/uploads/images/2020/1118/120825_b3ed6fbc_5596112.png

https://images.gitee.com/uploads/images/2020/1118/120841_42b75933_5596112.png

