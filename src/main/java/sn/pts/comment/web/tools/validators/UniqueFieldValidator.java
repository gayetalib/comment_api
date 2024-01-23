package sn.pts.comment.web.tools.validators;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import sn.pts.comment.web.tools.constraints.UniqueValue;


public class UniqueFieldValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private String fieldName;
    private Class<?> entityClass;
    private boolean ignoreCase;
    private Long idValue;

    @Override
    public void initialize(UniqueValue annotation) {
        fieldName = annotation.fieldName();
        entityClass = annotation.entityClass();
        ignoreCase = annotation.ignoreCase();
        idValue = annotation.idValue();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        EntityManager entityManager = EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerFactory);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<?> root = query.from(entityClass);

        Predicate predicate = criteriaBuilder.equal(ignoreCase ? criteriaBuilder.upper(root.get(fieldName)) : root.get(fieldName), ignoreCase ? value.toString().toUpperCase() : value);
        if (idValue != -1L) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.notEqual(root.get("id"), idValue));
        }
        query.select(criteriaBuilder.count(root)).where(predicate);
        Long count = entityManager.createQuery(query).getSingleResult();

        return count == 0;

        /*query.select(criteriaBuilder.count(root)).where(criteriaBuilder.equal(ignoreCase ? criteriaBuilder.upper(root.get(fieldName)) : root.get(fieldName), ignoreCase ? value.toString().toUpperCase() : value));
        Long count = entityManager.createQuery(query).getSingleResult();
        return count == 0;*/
    }
}