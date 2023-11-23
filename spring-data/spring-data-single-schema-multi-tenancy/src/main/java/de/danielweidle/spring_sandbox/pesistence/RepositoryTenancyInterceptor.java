package de.danielweidle.spring_sandbox.pesistence;

import de.danielweidle.spring_sandbox.TenantContext;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Aspect
@Component
@RequiredArgsConstructor
class RepositoryTenancyInterceptor {

    private final EntityManager entityManager;

    // TODO:
    //  - Investigate if this should be done when em session is opened?
    //  - Either the repositories itself could be an option as well.
    //@Pointcut("this(org.springframework.data.jpa.repository.JpaRepository)")
    //@Pointcut("this(de.danielweidle.spring_sandbox.pesistence.ProductionOrders)")
    //@Pointcut("execution (* org.hibernate.internal.SessionFactoryImpl.SessionBuilderImpl.openSession(..))")
    @Pointcut("this(de.danielweidle.spring_sandbox.pesistence.ProductionOrdersPersistenceAdapter)")
    public void jpaRepositoryInterceptor() {
    }

    @Around("jpaRepositoryInterceptor()")
    //@AfterReturning(pointcut = "openSession()", returning = "session")
    public Object intercept(ProceedingJoinPoint joinPoint) throws Throwable {
        String tenant = TenantContext.getTenant();
        Session session = entityManager.unwrap(Session.class);
        Filter tenantFilter = session.enableFilter("tenantFilter");
        tenantFilter.setParameter("productionLine", tenant);
        return joinPoint.proceed();
    }


}
