package xyz.themanusia.gopresence.json.absen;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.themanusia.gopresence.json.user.User;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "data_absensi")
public class Absen {

    @Id
    @Column(name = "id_data")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "username")
    @ManyToOne
    private User user;

    @Column(name = "tanggal")
    private Date date;

    @Column(name = "waktu")
    private Time time;
}
