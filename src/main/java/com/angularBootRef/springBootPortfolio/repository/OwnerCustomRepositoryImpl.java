package com.angularBootRef.springBootPortfolio.repository;

import com.angularBootRef.springBootPortfolio.domain.Owner;
import com.angularBootRef.springBootPortfolio.domain.OwnerCarInfo;
import com.angularBootRef.springBootPortfolio.domain.OwnerCarInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

@Slf4j
@Component
public class OwnerCustomRepositoryImpl implements OwnerCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    //simple select all with criteria Builder
    public List<Owner> findAllOwnersCriteria() {

        log.info("findAllOwners ByCarIdCriteria " );
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);
        criteriaQuery.select(ownerRoot);

        List<Owner> owners = entityManager.createQuery(criteriaQuery).getResultList();
//
//        owners.stream().forEach (item -> {
//                        log.info("each item in list .. " + item.getOwnerId() + item.getFirstName());
//                    });
        //if i enable this line, it will fetch the cars and reviews from the db as they are set by lazy in entity
//        owners.stream().forEach (item -> {
//            log.info("each item in list .. " + item.getCar().toString());
//        });

        return owners;
    }

    //select with parameter with criteria Builder
    public Owner findOwnerByOwnerId(Long ownerId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);

        //verbose mapping
        criteriaQuery.select(ownerRoot);
        ParameterExpression<Long> ownerIdParam = criteriaBuilder.parameter(Long.class, "ownerId");
        criteriaQuery.where(criteriaBuilder.equal(ownerRoot.get("ownerId"), ownerIdParam));

        TypedQuery<Owner> q = entityManager.createQuery(criteriaQuery);
        q.setParameter(ownerIdParam, ownerId);
        Owner owner = q.getSingleResult();
        log.info("owner is " + owner.toString());

        //more concise
        Owner ownerConcise = entityManager.createQuery(criteriaQuery.select(ownerRoot)
                                        .where(criteriaBuilder.equal(ownerRoot.get("ownerId"), ownerId)))
                                        .getSingleResult();

        return owner;
    }

    //join and where select with criteriaBuilder
    public List<Owner> findAllOwnersByCarIdCriteria(Long carId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Owner> criteriaQuery = criteriaBuilder.createQuery(Owner.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);

        Join<Object, Object> car = ownerRoot.join("car");
        criteriaQuery.select(ownerRoot);
        criteriaQuery.where(criteriaBuilder.equal(car.get("id"), carId));

        TypedQuery<Owner> q = entityManager.createQuery(criteriaQuery);
        List<Owner> ownerList = q.getResultList();

        ownerList.stream().forEach (item -> { log.info("each item in list .. " + item); });

        return ownerList;
    }

    //select one attribute with criteria builder
    public String findFirstNameByOwnerId(Long ownerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);

        String firstName = entityManager.createQuery(criteriaQuery.select(ownerRoot.get("firstName"))
        .where(criteriaBuilder.equal(ownerRoot.get("ownerId"), ownerId)))
        .getSingleResult();
        log.info("firstName is: " + firstName );

        return firstName;
    }

    //map pojo with criteria builder
    public OwnerCarInfo findOwnerCarInfoByOwnerIdCriteria(Long ownerId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OwnerCarInfo> criteriaQuery = criteriaBuilder.createQuery(OwnerCarInfo.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);
        Join<Object, Object> car = ownerRoot.join("car");

        criteriaQuery.select(criteriaBuilder.construct(OwnerCarInfo.class, ownerRoot.get("firstName"), ownerRoot.get("lastName"), car.get("make")));

        criteriaQuery.where(criteriaBuilder.equal(ownerRoot.get("ownerId"), ownerId));
        TypedQuery<OwnerCarInfo> q = entityManager.createQuery(criteriaQuery);

        OwnerCarInfo singleResult = q.getSingleResult();
        log.info("singleResult.  logger info for argument " + singleResult.toString() );

        return singleResult;
    }

    //map pojo using sqlresultmapping and mappingName instead of criteriaBuilder
    public OwnerCarInfoDto findOwnerCarInfoByOwnerId(String carMake) {

        log.info("findOwnerCarInfoByOwnerId ...: " + carMake);

        String sqlQuery = "select o.owner_id, o.first_name, o.last_name, c.make from owner o " +
        "INNER JOIN  car c ON c.id = o.car_id " +
        "where c.make = ?1";

        Query query = entityManager.createNativeQuery(sqlQuery, "ownerCarInfoQueryMapping");
        query.setParameter(1, carMake);

        OwnerCarInfoDto ownerCarInfoDto = (OwnerCarInfoDto) query.getSingleResult();

        return ownerCarInfoDto;
    }

    //select multi attributes without pojo using criteria builder
    public void findMultiSelectByOwnerId(Long ownerId) {
        //select multiple variables from one table
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Owner> ownerRoot = criteriaQuery.from(Owner.class);
        Join<Object, Object> car = ownerRoot.join("car");

        Selection<Object> firstName = ownerRoot.get("firstName").alias("firstName");
        Selection<Object> lastName = ownerRoot.get("lastName").alias("lastName");
        Selection<Object> make = car.get("make").alias("make");
        criteriaQuery.multiselect(firstName, lastName, make);

        criteriaQuery.where(criteriaBuilder.equal(ownerRoot.get("ownerId"), ownerId));
        TypedQuery<Tuple> q = entityManager.createQuery(criteriaQuery);
        Tuple tuple = q.getSingleResult();
        log.info("logger info for firstName " + tuple.get("firstName") );
        log.info("logger info for lastName " + tuple.get("lastName") );
        log.info("logger info for make " + tuple.get("make") );

    }

    public void updateOwnerUsername(String oldUsername, String newUsername) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaUpdate<Owner> update = criteriaBuilder.createCriteriaUpdate(Owner.class);
        Root ownerRoot = update.from(Owner.class);

        // set update and where clause
        update.set("username", newUsername);
        update.where(criteriaBuilder.equal(ownerRoot.get("username"), oldUsername));

        entityManager.createQuery(update).executeUpdate();
    }

    public void deleteOwnerUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaDelete<Owner> delete = criteriaBuilder.createCriteriaDelete(Owner.class);
        Root ownerRoot = delete.from(Owner.class);

        // set update and where clause
        delete.where(criteriaBuilder.equal(ownerRoot.get("username"), username));

        entityManager.createQuery(delete).executeUpdate();
    }

    //update owner username to 'tester' where the carId = ?
    //Note with JPA2.2 it is necessary to update via createNativeQuery as critieraUpdate restricts it
    public void updateOwnerUsernameByCarId(Long carId) {
        log.info("updateOwnerUsernameByCarId " + carId );

        // REF https://stackoverflow.com/questions/22070249/how-to-delete-delete-entities-from-a-joined-table-with-jpa-2-1-criteriadelete/34593119
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Owner> update = cb.createCriteriaUpdate(Owner.class);
        Root<Owner> ownerRoot = update.from(Owner.class);

        Subquery<Owner> subquery = update.subquery(Owner.class);
        Root<Owner> ownerRoot1 = subquery.from(Owner.class);
        subquery.select(ownerRoot1);

        Join<Object, Object> carJoin = ownerRoot1.join("car");
        subquery.where(cb.equal(carJoin.get("id"), carId));

        update.where(ownerRoot.in(subquery));
        entityManager.createQuery(update).executeUpdate();

    }

    //createNativeQuery update with join
    public void updateOwnerUsernameByCarMake(String carMake, String userUpdateName) {
        log.info("updateOwnerUsernameByCarMake ... " + userUpdateName);

        String sqlQuery =
                "update owner o INNER JOIN  car c ON c.id = o.car_id set o.first_name = ?1 where c.make = ?2";

        entityManager.createNativeQuery(sqlQuery).setParameter(1,userUpdateName).setParameter(2,carMake).executeUpdate();
        log.info("just after the entityManager query has been run");
    }

}
