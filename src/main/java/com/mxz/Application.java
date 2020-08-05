package com.mxz;

/**
 * 运行原理
 *
 * jar
 * 下有spring.factories 和MANIFEST.MF
 *  从run方法进入
 *  创建SpringApplication 对象   执行该对象的run方法
 *  new SpringApplication(primarySources).run(args);
 * 
 * 创建 SpringApplication对象
 *    //调用构造方法
    public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
            //默认resourceLoader 为null
            this.resourceLoader = resourceLoader;
            //校验primarySources不为空
            Assert.notNull(primarySources, "PrimarySources must not be null");
            //赋值primarySources集合对象  说明此处是可以传递多个.class对象 当我们的启动类不在同一级包中
           // com.ricky/ricky02  我们的启动类在ricky中，默认是只扫描ricky包中的类，如果想扫描ricky02中 可以将需要扫描的类放到此处。
            this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
            //加载webApplicationType 对象 推断是web环境 还是非web环境
            this.webApplicationType = deduceWebApplicationType();
           //设置实例为ApplicationContextInitializer的Spring工厂实例
           //可以通过spring.factories文件注入 继承自ApplicationContextInitializer的类
           //也可以调用addInitializer方法手动注入
            setInitializers((Collection) getSpringFactoriesInstances(
                    ApplicationContextInitializer.class));
            //注入ApplicationListener实例的监听  实现方式类似上边  通过文件或者add方法都可以
            setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
            // 通过堆栈里获取的方式，判断main函数，找到原始启动的main函数
            this.mainApplicationClass = deduceMainApplicationClass();
        }
        
        
  //执行run方法 返回ConfigurableApplicationContext 对象
public ConfigurableApplicationContext run(String... args) {
          //开启执行时间记录器
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
          //定义context
        ConfigurableApplicationContext context = null;
          //定义exceptionReporters
        Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
          //设置java.awt.headless模式 主要是在缺少显示设备和键盘鼠标等下的支持  设置系统变量为true
        configureHeadlessProperty();
          //根据传递的参数 加载spirng.factories中的SpringApplicationRunListener实例监听对象
        SpringApplicationRunListeners listeners = getRunListeners(args);
          //启动监听
        listeners.starting();
        try {
              // 将传入的参数转换为ApplicationArguments格式
            ApplicationArguments applicationArguments = new DefaultApplicationArguments(
                    args);
              // 根据applicationArguments准备基础环境变量 监听listeners变量 绑定到Spring中
              //如果webApplicationType是0则做下转换处理  最后 attach做些检查判断处理
            ConfigurableEnvironment environment = prepareEnvironment(listeners,
                    applicationArguments);
             // 从环境变量中检查和设置spring.beaninfo.ignore的状态 true或false
            configureIgnoreBeanInfo(environment);
             // 从环境变量中检查和设置banner.mode的模式  OFF时不打印
              // 这部分是sb项目启动时显示几行springboot字符串头像
              //自定义配置可参阅 SpringApplicationBannerPrinter
            Banner printedBanner = printBanner(environment);
             //创建应用上下文环境 检查是web环境还是默认环境等生成相对应环境
            context = createApplicationContext();
             // 从spring.factories中获取SpringBootExceptionReporter类型的实例 
            exceptionReporters = getSpringFactoriesInstances(
                    SpringBootExceptionReporter.class,
                    new Class[] { ConfigurableApplicationContext.class }, context);
             //准备context 
              // 参阅第五步
            prepareContext(context, environment, listeners, applicationArguments,
                    printedBanner);
             //刷新上下文context  注意根据ServletWeb和ReactiveWeb以及默认的applicationContext的不同来进行具体刷新
            refreshContext(context);
             // 刷新后的操作  现在是保留方法
            afterRefresh(context, applicationArguments);
              //结束执行时间记录器
            stopWatch.stop();
             // 是否开启启动日志
            if (this.logStartupInfo) {
                new StartupInfoLogger(this.mainApplicationClass)
                        .logStarted(getApplicationLog(), stopWatch);
            }
             // 发布事件  context.publishEvent(ApplicationStartedEvent)
            listeners.started(context);
             // 将ApplicationRunner和CommandLineRunner类型的回调处理
            callRunners(context, applicationArguments);
        }
        catch (Throwable ex) {
            handleRunFailure(context, ex, exceptionReporters, listeners);
            throw new IllegalStateException(ex);
        }

        try {
             // context.publishEvent(ApplicationReadyEvent) 发布监听
            listeners.running(context);
        }
        catch (Throwable ex) {
            handleRunFailure(context, ex, exceptionReporters, null);
            throw new IllegalStateException(ex);
        }
        return context;
    }
    
    private void prepareContext(ConfigurableApplicationContext context,
            ConfigurableEnvironment environment, SpringApplicationRunListeners listeners,
            ApplicationArguments applicationArguments, Banner printedBanner) {
       // 设置context环境变量
        context.setEnvironment(environment);
       // 注册internalConfigurationBeanNameGenerator实例  setResourceLoader 或者setClassLoader
        postProcessApplicationContext(context);
        // 执行ApplicationContextInitializer.initialize方法
        applyInitializers(context);
        // 设置监听上下文
        listeners.contextPrepared(context);
        // 如果启动日志开的话 则启动日志实例
        if (this.logStartupInfo) {
            logStartupInfo(context.getParent() == null);
            logStartupProfileInfo(context);
        }
        // Add boot specific singleton beans
        // 通过bean工厂注册springApplicationArguments的单例对象
        context.getBeanFactory().registerSingleton("springApplicationArguments",
                applicationArguments);
        // 如果 printedBanner 不为空则通过bean工厂注册springBootBanner的单例对象
        if (printedBanner != null) {
            context.getBeanFactory().registerSingleton("springBootBanner", printedBanner);
        }

        // Load the sources  加载sources
        Set<Object> sources = getAllSources();
        Assert.notEmpty(sources, "Sources must not be empty");          
        //通过 createBeanDefinitionLoader方法  获取lBeanDefinitionLoader 并设置beanNameGenerator、environment、resourceLoader
       // 最后根据类型进行装载registerbean 常见的类型有xml ,annotation,package,resouces等等 最后返回bean数量
        load(context, sources.toArray(new Object[0]));          
        // 通过SimpleApplicationEventMulticaster的Executor 去invoke是ApplicationListener类型的listener
          // 循环执行listener.onApplicationEvent(event);
        listeners.contextLoaded(context);
    }
 * 
 **/

/**
 * 简略总结就是
 * 初始化 找到SpringApplicatiRunListener 启动监听   配置环境  listener 环境准备  
 *  ApplicationCont创建完成之后  加载所有ApplicationContextIntializer 处理context  listener 再操作一遍   
 *  将EnableAuto加载到IOc容器的bean 加载到context中  refresh  执行CommandLineRunner  finished
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @SpringBootApplication 注释
 *  @EnableAutoConfiguration  借助 @Import 收集注册特定bean 自动 去spring.factories下寻找
 *  @EnableAspectJAutoProxy  proxyTargetClass 默认代理模式使用cglib还是
 *  @EnableScheduling 是将spring调度相关的bean定义到IOc容器中 
 *  loadSpringFactories  下加载  META-INF/spring.factories
 *  可配置  监听器  或者 setListener
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter( type = FilterType.REGEX, pattern = "com.mxz.service.plan.*")
})
//@MapperScan("com.mxz.service.*.mapper")
public class Application {

	public static void main(String[] args) {
	    SpringApplication application = new SpringApplication(Application.class);
	    application.run(args);
	}

//    @Bean
//    InitializingBean saveData(LocationRepository repo){
//        return ()->{
//            repo.save(new Location((long) 1,"1",38.998064, 117.317267));
//            repo.save(new Location((long)2,"2",38.997793, 117.317069));
//            repo.save(new Location((long)3,"3",38.998006, 117.317101));
//            repo.save(new Location((long)4,"4",38.997814, 117.317332));
//        };
//    }
	
}

