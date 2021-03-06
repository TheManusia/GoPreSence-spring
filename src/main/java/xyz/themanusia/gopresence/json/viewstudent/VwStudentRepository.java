package xyz.themanusia.gopresence.json.viewstudent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VwStudentRepository extends CrudRepository<VwStudent, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM themanusia_absensi.vw_student WHERE username = ?1")
    VwStudent getViewStudentById(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM themanusia_absensi.vw_student")
    List<VwStudent> getViewStudent();
}
