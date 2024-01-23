package sn.pts.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.pts.comment.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
