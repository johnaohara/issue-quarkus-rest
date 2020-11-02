# Inject EntitymanagerFactory

To reproduce; 

1. Build quarkus/quarkus-rest branch 

2. Build common proj 

```shell script
$ ./mvnw clean install
```

```shell script
$ cd quarkus-rest
$ mvn clean compile quarkus:dev
```

Exception;

```commandline
2020-11-02 13:11:24,613 ERROR [io.qua.run.boo.StartupActionImpl] (Quarkus Main Thread) Error running Quarkus: java.lang.reflect.InvocationTargetException
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at io.quarkus.runner.bootstrap.StartupActionImpl$3.run(StartupActionImpl.java:134)
        at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.lang.ExceptionInInitializerError
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
        at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
        at java.base/java.lang.Class.newInstance(Class.java:584)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:61)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:38)
        at io.quarkus.runtime.Quarkus.run(Quarkus.java:104)
        at io.quarkus.runner.GeneratedMain.main(GeneratedMain.zig:29)
        ... 6 more
Caused by: java.lang.RuntimeException: Failed to start quarkus
        at io.quarkus.runner.ApplicationImpl.<clinit>(ApplicationImpl.zig:282)
        ... 15 more
Caused by: java.lang.RuntimeException: Error injecting org.acme.repository.WorldRepository org.acme.ExampleResource.worldRepository
        at org.acme.ExampleResource_Bean.create(ExampleResource_Bean.zig:171)
        at org.acme.ExampleResource_Bean.create(ExampleResource_Bean.zig:194)
        at io.quarkus.arc.impl.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:96)
        at io.quarkus.arc.impl.AbstractSharedContext.access$000(AbstractSharedContext.java:14)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:29)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:26)
        at io.quarkus.arc.impl.LazyValue.get(LazyValue.java:26)
        at io.quarkus.arc.impl.ComputingCache.computeIfAbsent(ComputingCache.java:69)
        at io.quarkus.arc.impl.AbstractSharedContext.get(AbstractSharedContext.java:26)
        at org.acme.ExampleResource_Bean.get(ExampleResource_Bean.zig:226)
        at org.acme.ExampleResource_Bean.get(ExampleResource_Bean.zig:242)
        at io.quarkus.arc.impl.ArcContainerImpl.beanInstanceHandle(ArcContainerImpl.java:418)
        at io.quarkus.arc.impl.ArcContainerImpl.beanInstanceHandle(ArcContainerImpl.java:431)
        at io.quarkus.arc.impl.ArcContainerImpl$1.get(ArcContainerImpl.java:254)
        at io.quarkus.arc.impl.ArcContainerImpl$1.get(ArcContainerImpl.java:251)
        at io.quarkus.arc.runtime.BeanContainerImpl$1.create(BeanContainerImpl.java:35)
        at io.quarkus.rest.common.runtime.core.ArcBeanFactory.createInstance(ArcBeanFactory.java:24)
        at io.quarkus.rest.server.runtime.handlers.InstanceHandler.<init>(InstanceHandler.java:15)
        at io.quarkus.rest.server.runtime.QuarkusRestRecorder.buildResourceMethod(QuarkusRestRecorder.java:646)
        at io.quarkus.rest.server.runtime.QuarkusRestRecorder.handler(QuarkusRestRecorder.java:312)
        at io.quarkus.deployment.steps.QuarkusRestProcessor$setupEndpoints-482351183.deploy_0(QuarkusRestProcessor$setupEndpoints-482351183.zig:3339)
        at io.quarkus.deployment.steps.QuarkusRestProcessor$setupEndpoints-482351183.deploy(QuarkusRestProcessor$setupEndpoints-482351183.zig:40)
        at io.quarkus.runner.ApplicationImpl.<clinit>(ApplicationImpl.zig:262)
        ... 15 more
Caused by: java.lang.RuntimeException: Error injecting javax.persistence.EntityManagerFactory org.acme.repository.WorldRepositoryEntityManagerFactory.entityManagerFactory
        at org.acme.repository.WorldRepositoryEntityManagerFactory_Bean.create(WorldRepositoryEntityManagerFactory_Bean.zig:178)
        at org.acme.repository.WorldRepositoryEntityManagerFactory_Bean.create(WorldRepositoryEntityManagerFactory_Bean.zig:201)
        at io.quarkus.arc.impl.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:96)
        at io.quarkus.arc.impl.AbstractSharedContext.access$000(AbstractSharedContext.java:14)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:29)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:26)
        at io.quarkus.arc.impl.LazyValue.get(LazyValue.java:26)
        at io.quarkus.arc.impl.ComputingCache.computeIfAbsent(ComputingCache.java:69)
        at io.quarkus.arc.impl.AbstractSharedContext.get(AbstractSharedContext.java:26)
        at org.acme.repository.WorldRepositoryEntityManagerFactory_Bean.get(WorldRepositoryEntityManagerFactory_Bean.zig:233)
        at org.acme.repository.WorldRepositoryEntityManagerFactory_Bean.get(WorldRepositoryEntityManagerFactory_Bean.zig:249)
        at org.acme.ExampleResource_Bean.create(ExampleResource_Bean.zig:154)
        ... 37 more
Caused by: java.lang.IllegalStateException: Persistence providers are not available during the static init phase.
        at io.quarkus.hibernate.orm.runtime.StaticInitHibernatePersistenceProviderResolver.getPersistenceProviders(StaticInitHibernatePersistenceProviderResolver.java:15)
        at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:77)
        at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
        at io.quarkus.hibernate.orm.runtime.JPAConfig$LazyPersistenceUnit.get(JPAConfig.java:117)
        at io.quarkus.hibernate.orm.runtime.JPAConfig.getEntityManagerFactory(JPAConfig.java:60)
        at io.quarkus.hibernate.orm.runtime.HibernateOrmRecorder$4.get(HibernateOrmRecorder.java:95)
        at io.quarkus.hibernate.orm.runtime.HibernateOrmRecorder$4.get(HibernateOrmRecorder.java:90)
        at javax.persistence.EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.create(EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.zig:122)
        at javax.persistence.EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.create(EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.zig:138)
        at io.quarkus.arc.impl.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:96)
        at io.quarkus.arc.impl.AbstractSharedContext.access$000(AbstractSharedContext.java:14)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:29)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:26)
        at io.quarkus.arc.impl.LazyValue.get(LazyValue.java:26)
        at io.quarkus.arc.impl.ComputingCache.computeIfAbsent(ComputingCache.java:69)
        at io.quarkus.arc.impl.AbstractSharedContext.get(AbstractSharedContext.java:26)
        at javax.persistence.EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.get(EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.zig:170)
        at javax.persistence.EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.get(EntityManagerFactory_5765c6617fc468eada6bd349796f5641bd0143b6_Synthetic_Bean.zig:186)
        at org.acme.repository.WorldRepositoryEntityManagerFactory_Bean.create(WorldRepositoryEntityManagerFactory_Bean.zig:161)
        ... 48 more
```