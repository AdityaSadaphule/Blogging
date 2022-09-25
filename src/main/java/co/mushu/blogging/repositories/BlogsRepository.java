package co.mushu.blogging.repositories;

import co.mushu.blogging.models.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogsRepository extends JpaRepository<Blogs,String> {
}
