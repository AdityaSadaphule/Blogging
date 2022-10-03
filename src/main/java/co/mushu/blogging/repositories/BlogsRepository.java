package co.mushu.blogging.repositories;

import co.mushu.blogging.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository extends JpaRepository<Blog,String> {
}
