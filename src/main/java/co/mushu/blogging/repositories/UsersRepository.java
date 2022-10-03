package co.mushu.blogging.repositories;


import co.mushu.blogging.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends CrudRepository<Users,String> {

}
