package xyz.themanusia.gopresence.viewstudent;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vw_student")
public class VwStudent {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "nama")
    private String nama;

    @Column(name = "gambar")
    private String gambar;

    @Column(name = "status")
    private boolean status;

    @Column(name = "hadir")
    private int hadir;

    @Column(name = "alpa")
    private int alpa;

    @Column(name = "izin")
    private int izin;
}
