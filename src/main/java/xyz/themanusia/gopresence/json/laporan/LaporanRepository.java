package xyz.themanusia.gopresence.json.laporan;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LaporanRepository extends CrudRepository<Laporan, Long> {

    List<Laporan> getLaporansByUser_UsernameOrderByDateDesc(String username);
}
