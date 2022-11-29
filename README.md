:projectKey: org.optaplanner:optaplanner
:sonarBadge: image:https://sonarcloud.io/api/project_badges/measure?project={projectKey}
:sonarLink: link="https://sonarcloud.io/dashboard?id={projectKey}"

= OptaPlanner 中文版

https://www.optaplanner.org/[www.optaplanner.org]

== 编译说明
. maven 版本必须要 3.6.3++
. jdk 8 环境下编译通过
. 原版有三个错误代码提示，已经进行修正<IllegalStateException>
   .<IllegalStateException> orElseThrow(
() -> new IllegalStateException("Impossible state: Rule for constraint (" +
                                        constraint + ") not found."))));

== 启动运行
----
mvn clean install -DskipTests=true
cd optaplanner-examples
mvn exec:java
----

== 功能截图
image::https://images.gitee.com/uploads/images/2020/1118/105544_cd1f1bae_5596112.png["IDEA", link="#"]

image::https://images.gitee.com/uploads/images/2020/1118/120805_dc7aef6c_5596112.png["IDEA", link="#"]

image::https://images.gitee.com/uploads/images/2020/1118/120825_b3ed6fbc_5596112.png["IDEA", link="#"]

image::https://images.gitee.com/uploads/images/2020/1118/120841_42b75933_5596112.png["IDEA", link="#"]


== Quick development start

To build and run from source:

----
$ mvn clean install -DskipTests
$ cd optaplanner-examples
$ mvn exec:java
----

To develop with IntelliJ IDEA, Eclipse or NetBeans, open the root `pom.xml` as a new project
and configure a _Run/Debug configuration_ like this:

* Type: Application
* Main class: `org.optaplanner.examples.app.OptaPlannerExamplesApp`
* VM options: `-Xmx2G -server` (memory only needed when using the big datasets in the examples)
* Program arguments: (none)
* Working directory: `$MODULE_DIR$` (must resolve to optaplanner-examples directory)
* Use classpath of module: `optaplanner-examples`

=== Starter issues

If you're just starting out with OptaPlanner and want to contribute,
take a look at our https://issues.redhat.com/issues/?jql=project%20%3D%20PLANNER%20AND%20status%20in%20(Open%2C%20Reopened)%20AND%20labels%20%3D%20starter%20ORDER%20BY%20priority%20DESC[starter issues].
They're specifically chosen to be easier for first time contributors.

== Developing Drools, OptaPlanner and jBPM

*If you want to build or contribute to a kiegroup project, https://github.com/kiegroup/droolsjbpm-build-bootstrap/blob/master/README.md[read this document].*

*It will save you and us a lot of time by setting up your development environment correctly.*
It solves all known pitfalls that can disrupt your development.
It also describes all guidelines, tips and tricks.
If you want your pull requests (or patches) to be merged into master, please respect those guidelines.

=== Code style

OptaPlanner has adopted the https://github.com/quarkusio/quarkus[Quarkus] code style, enforces it, and automatically formats code during the build.
To setup your IDE, please see the
<<ide-configuration/ide-configuration.adoc#, IDE Setup Instructions>>.

=== Definition of Done

To consider any individual ticket "Done", following requirements must be satisfied:

  . Every change must go through PR; source code of both the feature/bugfix and its tests have been reviewed.
  . Documentation (if applicable) exists and has been reviewed.
  . There is test coverage proving the feature works and tests are passing.

In order to avoid introducing unstable features, the PR will be merged only after these points have been fulfilled. For PRs contributed by community the core team will assist with making the functionality meet these conditions.
