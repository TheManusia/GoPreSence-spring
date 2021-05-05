package xyz.themanusia.gopresence.json.absen;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AbsenRepository extends CrudRepository<Absen, Long> {

    Absen findTopByOrderByIdDesc();

    @Query(nativeQuery = true, value = "SELECT IF((SELECT COUNT(*)" +
            " FROM themanusia_absensi.data_absensi WHERE tanggal = CURDATE() AND username = ?1) > 0, true, false)")
    int isUserExist(String username);
}
