1. 需要启动mqfiltersrv, 或按github上文档说法, 启动broker时可在配置文件中带filterServerNums=1参数

2. 注意, 默认给的filter实现类有错(重载接口方法错误了)

3. 注意配置CLASSPATH	环境变量, 否则动态生成类时会报`ToolProvider.getSystemJavaCompiler() Return NULL!`异常

4. 为什么每次都需要上传filter源码?