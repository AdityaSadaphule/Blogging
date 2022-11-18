package co.mushu.blogging.repositories;


import co.mushu.blogging.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends CrudRepository<Users,String> {

}
