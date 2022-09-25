package co.mushu.blogging.repositories;


import co.mushu.blogging.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,String> {

}
