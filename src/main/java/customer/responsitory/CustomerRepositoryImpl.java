package customer.responsitory;

import customer.models.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CustomerRepositoryImpl implements ICustomerRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(sql , Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT c from Customer c where c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Customer customer) {
            entityManager.merge(customer);
    }

    @Override
    public void delete(int id) {
        Customer customer = findById(id);
        if (customer != null) {
            entityManager.remove(customer);
        }
    }
}
