package xyz.themanusia.gopresence.json.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getUserByUsername(String username);
}
