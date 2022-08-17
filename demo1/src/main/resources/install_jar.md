## 安装 本地Jar 包命立

mvn install:install-file -Dfile=jar包的位置 -DgroupId=上面的groupId -DartifactId=上面的artifactId -Dversion=上面的version -Dpackaging=jar

mvn install:install-file -Dfile=INetSDK.jar -DgroupId=com.dahua.netsdk -DartifactId=dahua-netsdk-jni -Dversion=1.0.0 -Dpackaging=jar
